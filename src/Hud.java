import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

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


	public void physics() {
		if (clicked) {
			Screen.room.game.actionTaken(mouseX, mouseY);
			clicked = false;
		}
	}

	public static void setCoord(float x, float y) {
		mouseX = x;
		mouseY = y;
	}

	public void set(String text) {
		tw.set(text);
		tw.next();
	}
	
	public boolean isTyping() {
		return tw.isTyping();
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
