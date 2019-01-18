import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;

public class Frame extends JFrame {
	public static final String title = "Pokemon Arena";
	public static final Dimension size = new Dimension(800, 600);

	public Frame() {
		setTitle(title);
		setSize(size);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(1, 1, 0, 0));
		Screen screen = new Screen();
		add(screen);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Frame();
	}
}
