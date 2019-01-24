import java.awt.Graphics;
import java.util.ArrayList;

public class Room {

	public Player player;
	public Player opponent;

	private Background bg;
	private Platform pt2;
	private Platform pt1;
	private Hud hud;
	private AnimationLoader al;

	public Game game;
	private Thread tGame;

	public Room() {
		define();
	}

	public void physics() {
		hud.physics();
		player.physics();
		opponent.physics();
	}

	private void define() {
		
		al = new AnimationLoader(getClass().getResource("AnimationList.txt"));

		player = initPlayer("player");
		opponent = initPlayer("opponent");

		addItem(player, new Item("cureall", 1));
		addItem(player, new Item("healingpotion", 1));

		bg = new Background(this);
		pt1 = new Platform(120,350);
		pt2=new Platform(425,140);
		hud = new Hud(this);
		
		Screen.ms.setHud(hud);

		game = new Game(player, opponent, hud);
		tGame = new Thread(game);
		tGame.start();
	}

	private Player initPlayer(String name) {
		ArrayList<Item> items = new ArrayList<Item>();
		Player player = new Player(name, items, al);
		player.pokemons = new ArrayList<Pokemon>();
		player.GeneratePokemon(player.pokemons);
		player.currentpokemon = player.pokemons.get(0);
		return player;
	}

	private void addItem(Player player, Item item) {
		player.items.add(item);
	}

	public void draw(Graphics g) {
		pt1.draw(g);
		pt2.draw(g);
	    player.draw(g);
	    opponent.draw(g);
		bg.draw(g);
		player.currentpokemon.effect.draw(g);		// might need to change this later
		opponent.currentpokemon.effect.draw(g);		// ^^
		//enemy.draw(g);
		hud.draw(g);
	}
}
