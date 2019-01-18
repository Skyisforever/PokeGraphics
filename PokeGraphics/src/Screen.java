import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Screen extends JPanel implements Runnable {
	Thread thread = new Thread(this);
	Room room = null;
	MouseHandle ms = new MouseHandle();

	boolean defined = false;

	public Screen() {
		thread.start();
	}

	public void define() {
		room = new Room();
		addMouseListener(ms);
		defined = true;
	}

	public void paintComponent(Graphics g) {
		if (!defined)
			return;
		g.setColor(new Color(245,245,245));
		g.fillRect(0,  0,  800,  600);
		
		room.draw(g);
	}

	public void run() {
		define();
		while (true) {
			repaint();
			room.physics();
			try {
				Thread.sleep(10);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
}
