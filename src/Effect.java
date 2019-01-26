import java.awt.Graphics;
import java.awt.Graphics2D;

public class Effect extends Animator {
	private Pokemon p;
	private boolean imgLoaded = false;
	private float dxs, dys, dts;

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
		dxs = dys = dts = 1;
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
				dxs = 1f;
				dys = 1;
				dts = 1;
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

			dxs = -.7f;
			dys = -2;
			dts = -1;

			deltas = al.nextFrame(name, frame_counter, this);
			break;
		case "Icebeam":
			loadImage("icebeam.png");
			if (frame_counter == 0) {
				if (Game.opponentsattack)
					setPos(450, 35);
				else
					setPos(140, 200);
			}

			dxs = -.7f;
			dys = -2;
			dts = -1;
			
			deltas = al.nextFrame(name, frame_counter, this);
			break;
		case "Lick":;
			if (frame_counter == 0) {
				if (Game.opponentsattack) {
					loadImage("backlick.png");
					setPos(130, 285);
				}
				else {
					loadImage("forwardlick.png");
					setPos(450, 70);
				}
			}
			dxs = 1f;
			dys = 1;
			dts = 1;
			deltas = al.nextFrame(name, frame_counter, this);
			break;
		case "Sleep":;
		if (frame_counter == 0) {
			loadImage("singing.png");
			if (Game.opponentsattack) {
				setPos(430, 23);
			}
			else {
				setPos(115, 220);
			}
		}
		dxs = 1f;
		dys = 1;
		dts = 1;
		deltas = al.nextFrame(name, frame_counter, this);
		break;
		default:
			a.cleanUp();
			return;
		case "Psyshock":
			
			if (frame_counter == 0) {
				if (Game.opponentsattack) {
					loadImage("psyshockbackward.png");
					setPos(450, 35);
				}
					
				else {
					loadImage("psyshockforward.png");
					setPos(140, 200);
				}
			}

			dxs = -.7f;
			dys = -1;
			dts = -1;
			
			deltas = al.nextFrame(name, frame_counter, this);
			break;
	case "Hydropump":
			
			if (frame_counter == 0) {
				if (Game.opponentsattack) {
					loadImage("hydropumpbackward.png");
					setPos(450, 35);
				}
					
				else {
					loadImage("hydropumpforward.png");
					setPos(140, 200);
				}
			}

			dxs = -.7f;
			dys = -1;
			dts = -1;
			
			deltas = al.nextFrame(name, frame_counter, this);
			break;

		}

		if (!animate) {
			a.cleanUp();
			return;
		}

		frame_counter++;

		if (Game.opponentsattack) {
			deltas[0] *= dxs;
			deltas[1] *= dys;
			deltas[2] *= dts;
		}

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
