import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Effect {
	private BufferedImage img = null;
	private float x, y;
	private boolean drawEffect = false;
	private Pokemon p;
	
	public boolean darkenScreen = false;
	
	public Effect(Pokemon p) {
		this.p = p;
	}
	
	private int frame_counter = 0;
	public void set(String name) {
		int dx = 0;
		int dy = 0;
		drawEffect = true;
		switch (name) {
		case "Thunder":
			darkenScreen = true;
			try {
				img = ImageIO.read(new File("res/lightning2.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (frame_counter >= 60) {
				frame_counter = 0;
				drawEffect = false;
				darkenScreen = false;
				p.effectDone();
				return;
			}
			if (frame_counter == 0) {
				x = 475;
				y = -100;
				dx = 0;
				dy = 0;
			} else if (frame_counter >= 30 && frame_counter < 60) {
				dx = 0;
				dy = -1;
			} else if (frame_counter < 30 || frame_counter >= 60) {
				dx = 0;
				dy = 1;
			}
			break;
		}
		
		frame_counter++;
		
		// need to change this, possibly just mirror
		// or done by-attack-name basis
		/*
		if (opponent) {
			dx *= -1;
			dy *= -1;
		}
		*/

		x += dx;
		y += dy;
		
	}
	
	public void draw(Graphics g) {
		if (drawEffect)
			g.drawImage(img, (int) x, (int) y, null);
	}
}
