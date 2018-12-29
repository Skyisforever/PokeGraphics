import java.awt.Graphics;

public class Room {
	

	private Background bg;
	private Player p;
	
	public Room() {
		define();
	}
	
	void physics() {
		//p.move();
	}

	
	public void define() {
		bg = new Background();
		p = new Player();
	}

	public void draw(Graphics g) {
		bg.draw(g);
		p.draw(g);
	}
}
