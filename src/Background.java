import java.awt.Color;
import java.awt.Graphics;

public class Background {
	Room room = null;

	public Background(Room room) {
		this.room = room;
		define();
	}

	public void define() {
		
	}

	public void draw(Graphics g) {
		if (room.player.currentpokemon.effect.darkenScreen || room.opponent.currentpokemon.effect.darkenScreen ||
				room.player.currentpokemon.darkenScreen || room.opponent.currentpokemon.darkenScreen) {
			g.setColor(new Color(0, 0, 0, 128));
			g.fillRect(0, 0, 800, 400);
		}

	}
}
