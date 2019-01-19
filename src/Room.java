import java.awt.Graphics;
import java.util.ArrayList;

public class Room {

	public Player player;
	public Player opponent;

	private Background bg;
	private Enemy enemy;
	private Platform pt;
	private Hud hud;

	public Game game;
	private Thread tGame;

	public Room() {
		define();
	}

	public void physics() {
		hud.physics();
	}

	private void define() {

		player = initPlayer("player");
		opponent = initPlayer("opponent");

		addItem(player, new Item("cureall", 1));
		addItem(player, new Item("healingpotion", 1));

		bg = new Background();
		enemy = new Enemy();
		pt = new Platform();
		hud = new Hud(this);
		
		Screen.ms.setHud(hud);

		game = new Game(player, opponent, hud);
		tGame = new Thread(game);
		tGame.start();
	}

	private Player initPlayer(String name) {
		ArrayList<Item> items = new ArrayList<Item>();
		Player player = new Player(name, items);
		player.pokemons = new ArrayList<Pokemon>();
		player.GeneratePokemon(player.pokemons);
		player.currentpokemon = player.pokemons.get(0);
		return player;
	}

	private void addItem(Player player, Item item) {
		player.items.add(item);
	}

	public void draw(Graphics g) {
		bg.draw(g);
		pt.draw(g);
		enemy.draw(g);
		hud.draw(g);
	}
}
