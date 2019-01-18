import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Platform {
	BufferedImage img = null;

	public Platform() {
		define();
	}

	public void define() {
		try {
			img = ImageIO.read(new File("platform.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics g) {
		g.drawImage(img,  425, 150, null);
	}
}
