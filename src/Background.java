import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Background {
	BufferedImage image = null;

	public Background() {
		define();
	}

	public void define() {
		try {
			image = ImageIO.read(new File("background.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics g) {
		g.drawImage(image, 0, 0, null);
	}
}
