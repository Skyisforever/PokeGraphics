import java.awt.Graphics;
import java.awt.Graphics2D;

public class Effect extends Animator {
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
			setImage(fileName);
			imgLoaded = true;
		}
	}

	@Override
	public void cleanUp() {
		frame_counter = 0;
		animate = false;
		darkenScreen = false;
		imgLoaded = false;
	}

	private int frame_counter = 0;

	public void set(String name, Animator a) {
		float[] deltas = { 0.0f, 0.0f, 0.0f };
		animate = true;

		switch (name) {
		case "Thunder":
			darkenScreen = true;
			loadImage("lightning2.png");
			if (frame_counter == 0) {
				if (Game.opponentsattack)
					setPos(155, 110);
				else
					setPos(475, -100);
			}
			deltas = al.nextFrame(name, frame_counter, this);
			break;
		case "Confusion":
			loadImage("confusion.png");
			if (frame_counter == 0) {
				if (Game.opponentsattack)
					setPos(155, 250);
				else
					setPos(460, 50);
			}
			deltas = al.nextFrame(name, frame_counter, this);
			break;
		}
		
			
		if (!animate) {
			a.cleanUp();
			return;
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

		doRotation();

	}

	public void draw(Graphics g) {
		if (animate) {
			Graphics2D g2d = (Graphics2D) g;
			g2d.drawImage(img, tx, null);
		}
	}
}
