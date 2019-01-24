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
	private AnimationLoader al;
	
	private boolean imgLoaded = false;
	public boolean darkenScreen = false;
	
	public Effect(Pokemon p, AnimationLoader al) {
		this.p = p;
		this.al = al;
	}
	
	private void loadImage(String fileName) {
		if (!imgLoaded) {
    		try {
    			img = ImageIO.read(Effect.class.getResource(fileName));
    			imgLoaded = true;
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
		}
	}
	
	private void cleanUp() {
		frame_counter = 0;
		drawEffect = false;
		darkenScreen = false;
		imgLoaded = false;
		p.effectDone();
	}
	
	private int frame_counter = 0;
	public void set(String name) {
		int[] deltas = {0, 0};
		drawEffect = true;
		
		switch (name) {
		case "Thunder":
			darkenScreen = true;
			loadImage("lightning2.png");
			
			if (frame_counter >= 60) {
				cleanUp();
				return;
			}
			if (frame_counter == 0) {
				x = 475;
				y = -100;				
			} else {
				// in animation
				deltas = al.nextFrame(name, frame_counter);
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

		x += deltas[0];
		y += deltas[1];
		
	}
	
	public void draw(Graphics g) {
		if (drawEffect)
			g.drawImage(img, (int) x, (int) y, null);
	}
}
