import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Pokemon {
	BufferedImage pokemonimage = null;
	AffineTransformOp op;
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

	private float x, y;
	private boolean opponent = false;
	private String animationType;
	public Effect effect = null;
	private AnimationLoader al;

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
		if (player.name.equals("opponent")) {
			x = 450;
			y = 35;
			opponent = true;
		} else {
			x = 130;
			y = 245;
		}
		animationType = "";
		effect = new Effect(this, al);
		if (name.equals("Pikachu")) {
			System.out.println(currenthealth);
			try {
				pokemonimage = ImageIO.read(getClass().getResource("pika.png"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (name.equals("Bulbasaur")) {
			try {
				pokemonimage = ImageIO.read(getClass().getResource("bulbasaur.png"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (name.equals("Jigglypuff")) {
			try {
				pokemonimage = ImageIO.read(getClass().getResource("jigglypuff1.png"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (name.equals("Greninja")) {
			try {
				pokemonimage = ImageIO.read(getClass().getResource("greninja2.png"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (name.equals("Psyduck")) {
			try {
				pokemonimage = ImageIO.read(getClass().getResource("psyduck.png"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void attack() {
		animationType = "attack";
	}

	public void faint() {
		animationType = "faint";
	}

	public boolean animationPlaying() {
		return animationType.equals("");
	}

	int frame_counter = 0;
	
	public void cleanUp() {
		animationType = "";
		frame_counter = 0;
	}

	public void physics() {
		int[] deltas = {0, 0};
		if (!animationType.equals("")) {
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
					if (frame_counter >= 30) {
						cleanUp();
						break;
					} else {
						// we don't use currentattack.name
						// because many attacks use this
						deltas = al.nextFrame("QuickAttack", frame_counter);
					}
					break;
				case "Icebeam":
				case "Nightslash":
				case "Confusion":
				case "Leechseed":
				case "Thunder":
					effect.set(currentattack.name);
					break;	
				default:
					cleanUp();
				} // end switch(currentattack.name)
				break;

			case "faint":
				// faint animation here
			}

			frame_counter++;

			if (opponent) {
				deltas[0] *= -1;
				deltas[1] *= -1;
			}

			x += deltas[0];
			y += deltas[1];
		}

	}

	public void effectDone() {
		animationType = "";
	}

	public void draw(Graphics g) {
		// Graphics2D g2d = (Graphics2D) g;
		// g2d.drawImage(op.filter(pokemonimage, null), 400, 600, null);
		g.drawImage(pokemonimage, (int) x, (int) y, null);

	}

}
