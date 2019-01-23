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
	String animationType;
	Player player;
	
	float x, y;
	boolean opponent = false;
	
	public Pokemon(String name, ArrayList<String> type, double health, double attack, double defense, double speed,
			ArrayList<Attack> skills, ArrayList<Status> statuses, Player player) {
		this.name = name;
		this.health = health;
		this.attack = attack;
		this.defense = defense;
		this.speed = speed;
		this.skills = skills;
		this.type = type;
		this.statuses = statuses;
		this.player = player;
		currenthealth = health;
		define();
	}
	public void define() {
	    if (player.name.equals("opponent")) {
	        x = 450;
	        y = 45;
	        opponent = true;
	    } else {
    	    x = 20;
    	    y = 300;
	    }
	    animationType = "";
	    name = "Pikachu";
	    //if (name.equals("Pikachu")) {
			try {
				pokemonimage = ImageIO.read(new File("pika.png"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		//}
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
	
	
	public void attack() {
	    switch(name) {
	    case "Pikachu":
	        animationType = "pika";
	        break;
	    default:
	        animationType = "pika";
	        break;
	    }
	}
	
	public boolean animationPlaying() {
	    return animationType.equals("");
	}
	
	
	int frame_counter = 0;
	int dx = 0;
	int dy = 0;
	public void physics() {
	    if (!animationType.equals("")) {
	        switch(animationType) {
	        case "pika":
	            if (frame_counter >= 30) {
	                animationType = "";
	                frame_counter = 0;
	                break;
	            } else if (frame_counter < 15) {
	                dx = 1;
	                dy = -1;
	            } else if (frame_counter >= 15){
	                dx = -1;
	                dy = 1;
	            }
	            frame_counter++;
	            break;
	        }
	        
	        if (opponent) {
	            dx *= -1;
	            dy *= -1;
	        }
	        
	        x += dx;
	        y += dy;
	    }
		
	}
	public void draw(Graphics g) {
		//Graphics2D g2d = (Graphics2D) g;
		//g2d.drawImage(op.filter(pokemonimage, null), 400, 600, null);
	    g.drawImage(pokemonimage, (int)x, (int)y, null);
	}
	
}
