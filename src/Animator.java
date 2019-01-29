import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Animator {
	public BufferedImage img = null;
	public float angle;
	public float x;
	public float y;
	public int frame_counter;
	public AffineTransform tx;
	public AnimationLoader al;
	public boolean animate;
	public boolean darkenScreen = false;
	
	public Animator() {
		frame_counter = 0;
		x = 0;
		y = 0;
	}
	
	public void cleanUp() {
		frame_counter = 0;
		animate = false;
	}
	
	public void setImage(String name) {
		try {
			img = ImageIO.read(getClass().getResource(name));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean animationPlaying() {
		return animate;
	}
	
	public void setPos(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void doRotation() {
		tx = new AffineTransform();
		tx.translate(x+img.getWidth()/2, y+img.getHeight()/2);
		tx.rotate(angle * Math.PI/180.0);
		tx.translate(-img.getWidth()/2, -img.getHeight()/2);
	}
	
	
	
}
