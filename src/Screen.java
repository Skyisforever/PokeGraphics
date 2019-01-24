import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Screen extends JPanel implements Runnable, ActionListener {
	private Thread thread = new Thread(this);
	private Room room = null;
	public static MouseHandle ms = new MouseHandle();

	boolean defined = false;
	
	private Timer timer = new Timer(10, this);

	public Screen() {
		thread.start();
	}

	public void define() {
		room = new Room();
		addMouseListener(ms);
		timer.start();
		defined = true;
	}

	public void paintComponent(Graphics g) {
		if (!defined)
			return;
		g.setColor(new Color(245, 245, 245));
		g.fillRect(0, 0, 800, 600);

		room.draw(g);
	}

	public void run() {
		define();
		while (true) {
			room.physics();
			try {
				Thread.sleep(10);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
	
	public void actionPerformed(ActionEvent ev) {
		if (ev.getSource()==timer) {
			repaint();
		}
	}
}
