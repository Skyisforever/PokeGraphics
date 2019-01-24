import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Effect {
	private BufferedImage img = null;
	private float x, y, angle;
	private boolean drawEffect = false;
	private Pokemon p;
	private AnimationLoader al;
	private AffineTransform tx;
	private AffineTransformOp op;

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
	
	public void doRotation(float dx, float dy) {
		double locationX = img.getWidth() / 2 + dx;
		double locationY = img.getHeight() / 2 + dy;
		tx = AffineTransform.getRotateInstance(angle, locationX, locationY);
		op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
	}

	private int frame_counter = 0;

	public void set(String name) {
		float[] deltas = { 0.0f, 0.0f, 0.0f };
		drawEffect = true;

		switch (name) {
		case "Thunder":
			darkenScreen = true;
			loadImage("lightning2.png");

			if (frame_counter >= 60) {
				cleanUp();
				return;
			}
			if (frame_counter == 0 && Game.opponentsattack) {
				x = 155;
				y = 110;
			}
			if (frame_counter == 0 && !Game.opponentsattack) {
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
		 * if (opponent) { dx *= -1; dy *= -1; }
		 */

		x += deltas[0];
		y += deltas[1];
		angle += deltas[2];

		doRotation(deltas[0], deltas[1]);

	}

	public void draw(Graphics g) {
		if (drawEffect)
			g.drawImage(img, (int) x, (int) y, null);
	}
}
