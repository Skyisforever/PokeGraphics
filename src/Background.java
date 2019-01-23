import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Background {
	BufferedImage image = null;
	Room room = null;

	public Background(Room room) {
		this.room = room;
		define();
	}

	public void define() {
		try {
			image = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void draw(Graphics g) {
		// g.drawImage(image, 0, 0, null);
		if (room.player.currentpokemon.effect.darkenScreen || room.opponent.currentpokemon.effect.darkenScreen) {
			g.setColor(new Color(0, 0, 0, 128));
			g.fillRect(0, 0, 800, 400);
		}

	}
}
