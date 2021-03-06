import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Enemy {

	int x = 450;
	int y = 45;
	float angle = 0;

	BufferedImage image = null;
	AffineTransform tx;
	AffineTransformOp op;

	public Enemy() {
		define();
	}

	public void define() {
		try {
			image = ImageIO.read(getClass().getResource("pika.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		double rotationRequired = Math.toRadians(angle);
		double locationX = image.getWidth() / 2;
		double locationY = image.getHeight() / 2;
		tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
		op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
	}

	public void move() {
		this.x += 1;
		this.angle++;
		double rotationRequired = Math.toRadians(angle);
		double locationX = image.getWidth() / 2;
		double locationY = image.getHeight() / 2;
		tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
		op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
	}

	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(op.filter(image, null), x, y, null);
	}
}
