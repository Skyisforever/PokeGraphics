import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Pokemon {
	BufferedImage pokemonimage=null;
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
	public Pokemon(String name, ArrayList<String> type, double health, double attack, double defense, double speed,
			ArrayList<Attack> skills, ArrayList<Status> statuses) {
		this.name = name;
		this.health = health;
		this.attack = attack;
		this.defense = defense;
		this.speed = speed;
		this.skills = skills;
		this.type = type;
		this.statuses = statuses;
		currenthealth = health;
	}
	public void define() {
	 if (name.equals("pikachu")) {
			try {
				pokemonimage = ImageIO.read(new File("pika.png"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
//	 else if (name.equals("bulbasaur")) {
//			try {
//				pokemonimage=ImageIO.read(new File("bulbasaur.png"));
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	 else if (name.equals("jigglypuff")) {
//			try {
//				pokemonimage=ImageIO.read(new File("jigglypuff.png"));
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	 else if (name.equals("greninja")) {
//			try {
//				pokemonimage=ImageIO.read(new File("greninja.png"));
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	 else if (name.equals("psyduck")) {
//			try {
//				pokemonimage=ImageIO.read(new File("psyduck.png"));
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
	
	}
	public void physics() {
		
	}
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(op.filter(pokemonimage, null), 400, 600, null);
	}
	
}
