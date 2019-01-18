import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

public class Hud {

	public static String text = "";
	public static Typewriter tw = new Typewriter(50);

	public static String mode = "";

	public static boolean clicked = false;
	private static float mouseX, mouseY;

	public Hud() {
		Thread tTypeWriter = new Thread(tw);
		tTypeWriter.start();
		define();
	}

	public void define() {
		// tw.set("This is a sample piece of text you piece of heigui da bian");
		// tw.next();
	}

	public void setMode(String mode) {
		Hud.mode = mode;
		switch (mode) {
		case "choices":
			tw.set("ATTACK         BAG\n\nPOKEMON        RUN");
			tw.next();
			break;
		case "run":
			tw.set("You have nowhere to run!          Return");
			tw.next();
			break;
		}
	}

	public static String getMode() {
		return Hud.mode;
	}

	public void physics() {
		if (clicked) {
			switch (mode) {
			case "choices":
				choices();
				break;
			case "run":
				run();
				break;
			}
			clicked = false;
		}
	}

	public void choices() {
		if ((mouseX >= 33 && mouseX <= 119) && (mouseY >= 428 && mouseY <= 455)) {
			System.out.println("attack");
			setMode("attack");

		} else if ((mouseX >= 203 && mouseX <= 267) && (mouseY >= 427 && mouseY <= 453)) {
			System.out.println("bag");
			setMode("bag");

		} else if ((mouseX >= 34 && mouseX <= 131) && (mouseY >= 482 && mouseY <= 511)) {
			System.out.println("pokemon");
			setMode("pokemon");

		} else if ((mouseX >= 208 && mouseX <= 268) && (mouseY >= 484 && mouseY <= 508)) {
			setMode("run");

		}
	}

	public void attack() {
		if ((mouseX >= 33 && mouseX <= 119) && (mouseY >= 428 && mouseY <= 455)) {
			// player.currentpokemon.currentattack=player.currentpokemon.attacks.get(1);
		} else if ((mouseX >= 203 && mouseX <= 267) && (mouseY >= 427 && mouseY <= 453)) {

		} else if ((mouseX >= 34 && mouseX <= 131) && (mouseY >= 482 && mouseY <= 511)) {

		} else if ((mouseX >= 208 && mouseX <= 268) && (mouseY >= 484 && mouseY <= 508)) {

		}

	}

	public void bag() {
		if ((mouseX >= 33 && mouseX <= 119) && (mouseY >= 428 && mouseY <= 455)) {

		} else if ((mouseX >= 203 && mouseX <= 267) && (mouseY >= 427 && mouseY <= 453)) {

		} else if ((mouseX >= 34 && mouseX <= 131) && (mouseY >= 482 && mouseY <= 511)) {

		} else if ((mouseX >= 208 && mouseX <= 268) && (mouseY >= 484 && mouseY <= 508)) {

		}

	}

	public void pokemon() {
		if ((mouseX >= 33 && mouseX <= 119) && (mouseY >= 428 && mouseY <= 455)) {
			// player.currentpokemon.currentattack=player.currentpokemon.attacks.get(1);
		} else if ((mouseX >= 203 && mouseX <= 267) && (mouseY >= 427 && mouseY <= 453)) {

		} else if ((mouseX >= 34 && mouseX <= 131) && (mouseY >= 482 && mouseY <= 511)) {

		} else if ((mouseX >= 208 && mouseX <= 268) && (mouseY >= 484 && mouseY <= 508)) {

		}

	}

	public void run() {
		if ((mouseX >= 437 && mouseX <= 532) && (mouseY >= 429 && mouseY <= 456)) {
			setMode("choices");
		}

	}

	public static void setCoord(float x, float y) {
		mouseX = x;
		mouseY = y;
	}

	public void draw(Graphics g) {
		g.setColor(Color.ORANGE);
		g.fillRect(20, 400, Frame.size.width - 55, 150);
		g.setColor(Color.WHITE);
		g.fillRect(30, 410, Frame.size.width - 75, 130);

		g.setFont(new Font("Courier", Font.BOLD, 20));
		g.setColor(Color.BLACK);
		drawString(g, text, 40, 450);
	}

	void drawString(Graphics g, String text, int x, int y) {
		int i = 0;
		for (String line : text.split("\n")) {
			g.drawString(line, x, y += (i++ == 0) ? 0 : g.getFontMetrics().getHeight());
		}
	}

}
