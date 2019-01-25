import java.awt.Color;
import java.awt.Font;
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
	private int baseX, baseY;

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
			baseX = 450;
			baseY = 35;
			opponent = true;
		} else {
			setPos(130, 245);
			baseX = 130;
			baseY = 245;
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
	
	public void hit() {
		animationType = "hit";
		animate = true;
	}

	@Override
	public void cleanUp() {
		animate = false;
		animationType = "";
		frame_counter = 0;
	}

	public void physics() {
		if (name.equals("Pikachu") && currenthealth<=health*.5 ) {
			setImage("pikachudistressed.png");
		}
		if (name.equals("Bulbasaur") && currenthealth<=health*.5 ) {
			setImage("bulbasaurdistressed.png");
		}
		if (name.equals("Jigglypuff") && currenthealth<=health*.5 ) {
			setImage("jigglypuffdistressed.png");
		}
		if (name.equals("Greninja") && currenthealth<=0 ) {
			setImage("greninjadistressed.png");
		}
		if (name.equals("Psyduck") && currenthealth<=health*.5 ) {
			setImage("psyduckdistressed.png");
		}
		float[] deltas = { 0.0f, 0.0f, 0.0f };
		if (animate) {
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
				case "Nightslash":
				case "Pound":
					deltas = al.nextFrame("QuickAttack", frame_counter, this);
					break;
				
				case "Icebeam":
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
				break;
			case "hit":
				deltas = al.nextFrame("hit", frame_counter, this);
				break;
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
		
		g.setColor(currenthealth<=health*.5?Color.green:Color.orange);
		g.fillRect(baseX-75,baseY+10,(int)(75 * currenthealth / health),10);
		g.setColor(Color.black);
		g.drawRect(baseX-75,baseY+10,75,10);
		g.setFont(new Font("Courier", Font.BOLD, 13));
		g.drawString(currenthealth + "/" + health, baseX-75, baseY+40);
	}

}
