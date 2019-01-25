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
		case "Leechseed":
			loadImage("leechseed.png");
			if (frame_counter == 0) {
				if (Game.opponentsattack)
					setPos(450, 35);
				else
					setPos(140, 200);
			}
			deltas = al.nextFrame(name, frame_counter, this);
			if (Game.opponentsattack) {
				deltas[0] *= -.7;
				deltas[1] *= -2;
				deltas[2] *= -1;
			}
			break;
			
		}

		if (!animate) {
			a.cleanUp();
			return;
		}

		frame_counter++;

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
