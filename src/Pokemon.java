import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;


public class Pokemon extends Animator {
	String name;
	ArrayList<String> type;
	ArrayList<Attack> skills;
	double health;
	double attack;
	double defense;
	double speed;
	double currenthealth;
	ArrayList<Status> statuses;
	Attack currentattack;
	Player player;

	private boolean opponent = false;
	private String animationType;
	public Effect effect = null;

	public Pokemon(String name, ArrayList<String> type, double health, double attack, double defense, double speed,
			ArrayList<Attack> skills, ArrayList<Status> statuses, Player player, AnimationLoader al) {
		this.name = name;
		this.health = health;
		this.attack = attack;
		this.defense = defense;
		this.speed = speed;
		this.skills = skills;
		this.type = type;
		this.statuses = statuses;
		this.player = player;
		this.currenthealth = health;
		this.al = al;
		define();
	}

	public void define() {
		if (name.equals("Pikachu")) {
			//System.out.println(currenthealth);
			setImage("pika.png");
		} else if (name.equals("Bulbasaur")) {
			setImage("bulbasaur.png");
		} else if (name.equals("Jigglypuff")) {
			setImage("jigglypuff1.png");
		} else if (name.equals("Greninja")) {
			setImage("greninja2.png");
		} else if (name.equals("Psyduck")) {
			setImage("psyduck.png");
		}

		if (player.name.equals("opponent")) {
			setPos(450, 35);
			opponent = true;
		} else {
			setPos(130, 245);
		}
		angle = 0;
		animationType = "";
		effect = new Effect(this, al);

		doRotation();

	}

	public void attack() {
		animationType = "attack";
		animate = true;
	}

	public void faint() {
		animationType = "faint";
	}

	@Override
	public void cleanUp() {
		animate = false;
		animationType = "";
		frame_counter = 0;
	}

	public void physics() {
		float[] deltas = { 0.0f, 0.0f, 0.0f };
		if (animate) {
			animate = true;
			switch (animationType) {
			case "attack":
				switch (currentattack.name) {
				case "Scratch":
				case "GyroBall":
				case "BrickBreak":
				case "WakeupSlap":
				case "Tackle":
				case "QuickAttack":
				case "Nuzzle":
					deltas = al.nextFrame("QuickAttack", frame_counter, this);
					break;
				case "Icebeam":
				case "Nightslash":
				case "Confusion":
				case "Leechseed":
				case "Thunder":
					effect.set(currentattack.name, this);
					break;
				default:
					cleanUp();
				} // end switch(currentattack.name)
				break;

			case "faint":
				// faint animation here
			default:
				cleanUp();
			}

			if (!animate) {
				return;
			}
			
			frame_counter++;

			if (opponent) {
				deltas[0] *= -1;
				deltas[1] *= -1;
				deltas[2] *= -1;
			}
			
			x += deltas[0];
			y += deltas[1];
			angle += deltas[2];

			doRotation();

		}

	}

	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(img, tx, null);
	}

}
