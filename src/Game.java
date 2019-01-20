public class Game implements Runnable {

	// ALL FIELDS SHOULD BE PRIVATE
	private String state = "idle";

	// SEE CODE FOR USAGE
	private boolean go = false;

	// CLICK COORDINATES
	private int x, y;

	// REFERENCES TO APPROPRIATE OBJECTS
	private Player player, opponent;
	private Hud hud;

	public Game(Player player, Player opponent, Hud hud) {
		this.player = player;
		this.opponent = opponent;
		this.hud = hud;

		setState("choices");
	}

	// DO NOT MODIFY
	public void actionTaken(float x, float y) {
		this.x = (int) x;
		this.y = (int) y;
		go = true;
		System.out.println(state);
	}

	// DO NOT MODIFY EXCEPT FOR CHANGING hud.set() TEXT
	public void setState(String state) {
		this.state = state;

		switch (state) {
		case "choices":
			hud.set("ATTACK         BAG\n\nPOKEMON        RUN");
			break;
		case "run":
			hud.set("You have nowhere to run!          Return");
			break;
		case "attack":
			String attackText = "";
			for (Attack a : player.currentpokemon.skills) {
				attackText += String.format("%-15s", a.name);
			}
			attackText = attackText.substring(0, 30) + "\n\n" + attackText.substring(30) + "return";
			hud.set(attackText);
			break;
		case "bag":
			hud.set(player.items.get(0).name + "         " + player.items.get(1).name + "         return");
			break;
		case "pokemon":
			hud.set(player.pokemons.get(0).name + "          " + player.pokemons.get(1).name + "          "
					+ player.pokemons.get(2).name + "       return");
			break;
		}

		// after text is shown, go = false;
		go = false;
	}

	@Override
	public void run() {
		for (;;) {
			while (!go) {
				sleep(10);
			}

			switch (state) {
			case "choices":
				choices();
				break;
			case "run":
				runAway();
				break;
			case "attack":
				attack();
				break;
			case "bag":
				bag();
				break;
			default:
				// if click occurs not on text
				go = false;
				break;
			}

		}
	}

	public void choices() {
		if ((x >= 33 && x <= 119) && (y >= 428 && y <= 455)) {
			setState("attack");
		} else if ((x >= 203 && x <= 267) && (y >= 427 && y <= 453)) {
			setState("bag");
		} else if ((x >= 34 && x <= 131) && (y >= 482 && y <= 511)) {
			setState("pokemon");
		} else if ((x >= 208 && x <= 268) && (y >= 484 && y <= 508)) {
			setState("run");
		}
	}

	private void runAway() {
		if ((x >= 433 && x <= 532) && (y >= 428 && y <= 460)) {
			setState("choices");
		}
	}

	private void attack() {
		if ((x >= 422 && x <= 525) && (y >= 483 && y <= 512)) {
			setState("choices");
			return;
		}
		if ((x >= 79 && x <= 145) && (y >= 449 && y <= 459)) {
			player.currentpokemon.currentattack = player.currentpokemon.skills.get(0);

		} else if ((x >= 220 && x <= 361) && (y >= 427 && y <= 455)) {
			player.currentpokemon.currentattack = player.currentpokemon.skills.get(1);

		} else if ((x >= 34 && x <= 131) && (y >= 482 && y <= 511)) {
			player.currentpokemon.currentattack = player.currentpokemon.skills.get(2);

		} else if ((x >= 208 && x <= 268) && (y >= 484 && y <= 508)) {
			player.currentpokemon.currentattack = player.currentpokemon.skills.get(3);
		} else {
			return;
		}

		doAttack();
	}

	// METHODS RELATED TO ATTACK
	// PLACEHOLDER
	private void doAttack() {
		String pokemon = player.currentpokemon.name;
		String attack = player.currentpokemon.currentattack.name;
		hud.set(pokemon + " uses " + attack + "!");

		while (hud.isTyping()) {
			sleep(10);
		}

		sleep(3000);
		hud.set("It's super effective!");
		sleep(3000);
		setState("choices");

	}

	// METHODS RELATED TO BAG
	// PLACEHOLDER
	private void bag() {

	}

	private void sleep(int i) {
		try {
			Thread.sleep(i);
		} catch (Exception e) {
		}
	}

}
