import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Platform {
	BufferedImage img = null;
	private int x, y;
	public Platform(int x, int y) {
		this.x=x;
		this.y=y;
		define();
	}

	public void define() {
		try {
			img = ImageIO.read(getClass().getResource("platform.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void draw(Graphics g) {
		g.drawImage(img, x, y, null);
	}
}
