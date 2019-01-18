import java.awt.Graphics;

public class Room {
	

	private Background bg;
	private Enemy p;
	private Platform pt;
	private Hud hud;
	
	public Room() {
		define();
	}
	
	void physics() {
		hud.physics();
	}

	
	public void define() {
		bg = new Background();
		p = new Enemy();
		pt=new Platform();
		hud = new Hud();
		hud.setMode("choices");
	}

	public void draw(Graphics g) {
		bg.draw(g);
		pt.draw(g);
		p.draw(g);
		hud.draw(g);
	}
}
