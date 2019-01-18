import java.awt.Graphics;
import java.util.ArrayList;

public class Room {
	public static Player player=new Player("player", null);
	public static Player opponent = new Player("opponent", null);
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
		player.pokemons=new ArrayList<Pokemon>();
		player.GeneratePokemon(player.pokemons);
		player.currentpokemon=player.pokemons.get(0);
		opponent.pokemons=new ArrayList<Pokemon>();
		opponent.GeneratePokemon(opponent.pokemons);
		opponent.currentpokemon=opponent.pokemons.get(0);
		 ArrayList<Item> items=new ArrayList<Item>();
		items.add(new Item("cureall", 1));
		items.add(new Item("healingpotion", 1));
		player.items=items;
	//	player.draw(g);
	//	opponent.draw(g);
		bg = new Background();
		p = new Enemy();
		pt = new Platform();
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
