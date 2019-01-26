import java.util.Random;

public class Game implements Runnable {

	// ALL FIELDS SHOULD BE PRIVATE
	private String state = "idle";
	private boolean mustswap = false;
	// SEE CODE FOR USAGE
	private boolean go = false;
	public static boolean opponentsattack=false;;
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
		// System.out.println(state);
	}

	// DO NOT MODIFY EXCEPT FOR CHANGING hud.set() TEXT
	public void setState(String state) {
		this.state = state;

		switch (state) {
		case "choices":
			String choiceText = "";
			choiceText += String.format("%-15s", "ATTACK");
			choiceText += String.format("%-15s", "BAG");
			choiceText += String.format("%-15s", "POKEMON");
			choiceText += String.format("%-15s", "RUN");
			choiceText = choiceText.substring(0, 30) + "\n\n" + choiceText.substring(30);
			hud.set(choiceText);
			break;
		case "run":
			String runText = "";
			runText += String.format("%-35s", "You have nowhere to run!");
			runText += String.format("%-15s", "return");
			hud.set(runText);
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
			String bagText = "";
			bagText += String.format("%-11s", player.items.get(0).name);
			bagText += String.format("%-17s", player.items.get(1).name);
			bagText += String.format("%-15s", "return");
			hud.set(bagText);
			break;
		case "pokemon":
			String pokemonText = "";
			for (int i = 0; i < 3; i++) {
				pokemonText += String.format("%-15s", player.pokemons.get(i).name);
			}
			pokemonText = pokemonText.substring(0, 30) + "\n\n" + pokemonText.substring(30) + "return";
			hud.set(pokemonText);
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
			case "pokemon":
				pokemon();
				break;
			default:
				// if click occurs not on text
				go = false;
				break;
			}

		}
	}

	public void pokemon() {
		if (player.pokemons.get(0).currenthealth <= 0 && player.pokemons.get(1).currenthealth <= 0
				&& player.pokemons.get(2).currenthealth <= 0) {
			hud.set("All your pokemon have fainted! Better luck next time.");

			while (hud.isTyping()) {
				sleep(10);
			}
			sleep(20000);
			System.exit(0);
		}
		if ((x >= 207 && x <= 307) && (y >= 483 && y <= 508)) {
		    if (mustswap) {
		        go = false;
		    } else {
			setState("choices");
		    }
		    return;
		}
		if ((x >= 39 && x <= 164) && (y >= 431 && y <= 452)) {
			swapPokemon(0);

		} else if ((x >= 206 && x <= 326) && (y >= 431 && y <= 455)) {
			swapPokemon(1);

		} else if ((x >= 34 && x <= 158) && (y >= 487 && y <= 509)) {
			swapPokemon(2);
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

	private void swapPokemon(int x) {
		if (player.pokemons.get(x).currenthealth > 0) {
			hud.set(player.pokemons.get(x).name + ", I choose you!");
			mustswap = false;
			while (hud.isTyping()) {
				sleep(10);
			}
			sleep(3000);
			player.currentpokemon = player.pokemons.get(x);
			setState("choices");
		} else {
			hud.set("That pokemon is fainted!");
			while (hud.isTyping()) {
				sleep(10);
			}
			sleep(1000);
			setState("pokemon");
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
		Opponentinput();
		if ((x >= 33 && x <= 127) && (y >= 426 && y <= 456)) {
			player.currentpokemon.currentattack = player.currentpokemon.skills.get(0);
			Battle();

		} else if ((x >= 220 && x <= 361) && (y >= 427 && y <= 455)) {
			player.currentpokemon.currentattack = player.currentpokemon.skills.get(1);
			Battle();

		} else if ((x >= 34 && x <= 131) && (y >= 482 && y <= 511)) {
			player.currentpokemon.currentattack = player.currentpokemon.skills.get(2);
			Battle();

		} else if ((x >= 201 && x <= 331) && (y >= 480 && y <= 520)) {
			player.currentpokemon.currentattack = player.currentpokemon.skills.get(3);
			Battle();
		} else {
			return;
		}
	}

	private void Opponentinput() {
		Random rand = new Random();
		int number = rand.nextInt(4);
		opponent.currentpokemon.currentattack = opponent.currentpokemon.skills.get(1);
	}

	// METHODS RELATED TO ATTACK
	// PLACEHOLDER
	private void Battle() {
		String nextState="pokemon";
		if (player.currentpokemon.currentattack == null) {

			Modifycurrenthealth(opponent.currentpokemon, player.currentpokemon, opponent.currentpokemon.currentattack);

			if (player.currentpokemon.health <= 0) {
				mustswap = true;
				hud.set(player.currentpokemon.name + " has fainted!");
				while (hud.isTyping()) {
					sleep(10);
				}
				sleep(1000);
				setState("pokemon");
				return;
			}
			
		}
		if (applyparalysis(player.currentpokemon)) {
			player.currentpokemon.speed = player.currentpokemon.speed / 2;
		}
		if (applyparalysis(opponent.currentpokemon)) {
			opponent.currentpokemon.speed = opponent.currentpokemon.speed / 2;
		}
		if (player.currentpokemon.speed >= opponent.currentpokemon.speed) {
			Modifycurrenthealth(player.currentpokemon, opponent.currentpokemon, player.currentpokemon.currentattack);
			if (opponent.currentpokemon.currenthealth <= 0) {

				hud.set(opponent.currentpokemon.name + " has fainted!");
				while (hud.isTyping()) {
					sleep(10);
				}
				sleep(1000);
				// draw pokemon faint

				opponentswap(opponent);
				// draw new pokemon
				setState("choices");
				return;
				
			}
			opponentsattack=true;
			Modifycurrenthealth(opponent.currentpokemon, player.currentpokemon, opponent.currentpokemon.currentattack);
			opponentsattack=false;
			if (player.currentpokemon.currenthealth <= 0) {
				mustswap = true;
				hud.set(player.currentpokemon.name + " has fainted!");
				while (hud.isTyping()) {
					sleep(10);
				}
				sleep(1000);
				setState("pokemon");
				return;
				
			}

		} else if (player.currentpokemon.speed < opponent.currentpokemon.speed) {
			opponentsattack=true;
			Modifycurrenthealth(opponent.currentpokemon, player.currentpokemon, opponent.currentpokemon.currentattack);
			opponentsattack=false;
			if (player.currentpokemon.currenthealth <= 0) {
				mustswap = true;
				hud.set(player.currentpokemon.name + " has fainted!");
				while (hud.isTyping()) {
					sleep(10);
				}
				sleep(1000);
				setState("pokemon");
				return;
				
			}
			Modifycurrenthealth(player.currentpokemon, opponent.currentpokemon, player.currentpokemon.currentattack);
			if (opponent.currentpokemon.currenthealth <= 0) {
				hud.set(opponent.currentpokemon.name + " has fainted!");
				while (hud.isTyping()) {
					sleep(10);
				}
				sleep(1000);
				opponentswap(opponent);
				setState("choices");
				return;
				
			}
		}
		applypoison(player.currentpokemon,opponent.currentpokemon);
		if (player.currentpokemon.health <= 0) {
			mustswap = true;
			setState("pokemon");
			return;
			
		}
		//player.currentpokemon.currentattack = null;
		setState("choices");
	}

	public void Modifycurrenthealth(Pokemon x, Pokemon y, Attack attack) {
		 if (applysleep(x)) {
		 return;
		 }
		 if (applyfreeze(x)) {
		 return;
		 }
		double TrueEffectiveness = 1;
		for (int i = 0; i < y.type.size(); i++) {
			TrueEffectiveness *= Effectiveness(y.type.get(i), attack);
		}
		double finaldamage = Math.round((attack.basedamage * (x.attack / y.defense)) * TrueEffectiveness);
		if(attack.name.equals("WakeupSlap")) {
			if(isasleep(y)) {
			finaldamage=finaldamage*2;
			hud.set(x.name+" uses " +attack.name+"! ");
			while (hud.isTyping()) {
				sleep(10);
			}
			sleep(1000);
			hud.set(y.name+" suffered twice the damage and has woken up.");
			while (hud.isTyping()) {
				sleep(10);
			}
			sleep(1000);
			wakeup(y);
			y.currenthealth=y.currenthealth-finaldamage;
			if (y.currenthealth<=0) {
				y.currenthealth=0;
			}
			return;
			}
		}
		hud.set(x.name + " uses " + x.currentattack.name + "!");
		while (hud.isTyping()) {
			sleep(10);
		}
		
		/* GOING TO HAVE TO IMPLEMENT LOGIC TO MAKE SURE ANIMATION HAPPENS ONLY IF NOT
		 * CONFUSED, ETC.
		 */
		
        // ANIMATE!!
        x.attack();
        while(x.animationPlaying()) {
            sleep(10);
        }
        
        sleep(250);
        
        y.hit();
        while(y.animationPlaying()) {
        	sleep(10);
        }
        
        sleep(1000);
        
        // END ANIMATE!!
        for (int i = 0; y.currenthealth > 0 && i < finaldamage; ++i) {
        	y.currenthealth -= 1;
        	sleep(100);
        }
		
		if (attack.name.equals("Leechseed")) {
			double leechadd = Math.round(finaldamage * .4);
			System.out.println(leechadd);
			for (int i = 0; x.currenthealth < x.health && i < leechadd; ++i) {
	        	x.currenthealth += 1;
	        	sleep(100);
	        }
		}
		
		
		sleep(2000);
		if (applyconfusion(x)) {
			Random h=new Random();
			int b=h.nextInt(100);
			if (b<50) {
				x.currenthealth=x.currenthealth-finaldamage;
				hud.set(x.name+" is confused and attacked himself!");
				while (hud.isTyping()) {
					sleep(10);
				}
				sleep(1000);
				return;
			}
		}
		if (TrueEffectiveness > 1) {

			hud.set("It's Super Effective!!!");
			while (hud.isTyping()) {
				sleep(10);
			}

			sleep(1000);
		} else if (TrueEffectiveness == 0.5) {
			hud.set("It's Not Very Effective...");
			while (hud.isTyping()) {
				sleep(10);
			}

			sleep(1000);
		} else if (TrueEffectiveness == 0) {
			hud.set("It's Completely Ineffective!");
			while (hud.isTyping()) {
				sleep(10);
			}

			sleep(1000);
		}
		if (attack.status.name.equals("poison")) {
			Random rand=new Random();
			int num=rand.nextInt(100);
			if (num<45) {
				y.statuses.add(new Status("poison", 99));
				hud.set(y.name+" has been poisoned!");
				while (hud.isTyping()) {
					sleep(10);
				}
				sleep(1000);
			}
		}
		else if (attack.status.name.equals("sleep")) {
			Random rand=new Random();
			int num=rand.nextInt(100);
			if (num<75) {
				y.statuses.add(new Status("sleep", 2));
				hud.set(y.name+" has fallen asleep!");
				while (hud.isTyping()) {
					sleep(10);
				}

				sleep(1000);
			}
			else {
				hud.set(y.name+" successfully resisted the urge to fall asleep");
				while (hud.isTyping()) {
					sleep(10);
				}

				sleep(1000);
			}
		}
		else if (attack.status.name.equals("paralysis")) {
			Random rand=new Random();
			int num=rand.nextInt(100);
			if (num<50) {
				y.statuses.add(new Status("paralysis", 2));
				hud.set(y.name+" has been paralyzed");
				while (hud.isTyping()) {
					sleep(10);
				}
				sleep(1000);
			}
		}
		else if (attack.status.name.equals("freeze")) {
			Random rand=new Random();
			int num=rand.nextInt(100);
			if (num<30) {
				y.statuses.add(new Status("freeze", 1));
				hud.set(y.name+" has been frozen!");
				while (hud.isTyping()) {
					sleep(10);
				}

				sleep(1000);
			}
		}
		else if (attack.status.name.equals("confuse")) {
			Random rand=new Random();
			int num=rand.nextInt(100);
			if (num<75) {
				y.statuses.add(new Status("confuse", 5));
				hud.set(y.name+" has been confused!");
				while (hud.isTyping()) {
					sleep(10);
				}
				sleep(1000);
			}
		}

	}
//	private void doAttack() {
//		String pokemon = player.currentpokemon.name;
//		String attack = player.currentpokemon.currentattack.name;
//		hud.set(pokemon + " uses " + attack + "!");
//
//		while (hud.isTyping()) {
//			sleep(10);
//		}
//
////		sleep(3000);
//		hud.set("It's super effective!");
////		sleep(3000);
//		setState("choices");
//
//	}

	// METHODS RELATED TO BAG
	// PLACEHOLDER
	private void bag() {
		if ((x >= 362 && x <= 461) && (y >= 430 && y <= 458)) {
			setState("choices");
			return;
		}
		if ((x >= 37 && x <= 135) && (y >= 429 && y <= 456)) {
			 useitem("cureall");

		} else if ((x >= 166 && x <= 333) && (y >= 426 && y <= 454)) {
			useitem("healingpotion");

		}
	}

	public void opponentswap(Player opponent) {
		Pokemon current = opponent.currentpokemon;
		if (current.equals(opponent.pokemons.get(0))) {
			hud.set("The opponent summons " + opponent.pokemons.get(1).name + "!");
			while (hud.isTyping()) {
				sleep(10);
			}
			sleep(2000);
			opponent.currentpokemon = opponent.pokemons.get(1);
		} else if (current.equals(opponent.pokemons.get(1))) {
			hud.set("The opponent summons " + opponent.pokemons.get(2).name + "!");
			while (hud.isTyping()) {
				sleep(10);
			}
			sleep(2000);
			opponent.currentpokemon = opponent.pokemons.get(2);
		} else if (current.equals(opponent.pokemons.get(2))) {
			hud.set("Congratulations, you are the Pokemon Champion!");
			while (hud.isTyping()) {
				sleep(10);
			}
			sleep(20000);
			System.exit(0);
		}
	}

	private boolean applyparalysis(Pokemon x) {
		if (x.statuses == null) {
			return false;
		}
		for (int i = 0; i < x.statuses.size(); i++) {
			if (x.statuses.get(i).name.equals("paralyze")) {
				x.statuses.get(i).duration--;
				if (x.statuses.get(i).duration == 0) {
					x.statuses.remove(i);
				}
				hud.set(x.name + " is paralyzed and moving at half his normal speed.");
				while (hud.isTyping()) {
					sleep(10);
				}
				sleep(1000);
				return true;
			}
		}
		return false;
	}
	private boolean applyfreeze(Pokemon x) {
		if (x.statuses==null) {
			return false;
		}
		for (int i=0; i<x.statuses.size(); i++) {
			if (x.statuses.get(i).name.equals("freeze")) {
				x.statuses.get(i).duration--;
				if (x.statuses.get(i).duration==0) {
					x.statuses.remove(i);
				}
				hud.set(x.name+" is frozen and cannot move!");
				while (hud.isTyping()) {
					sleep(10);
				}
				sleep(1000);
				return true;
			}
		}
		return false;
	}
	private boolean applysleep(Pokemon x) {
		if (x.statuses==null) {
			return false;
		}
		for (int i=0; i<x.statuses.size(); i++) {
			if (x.statuses.get(i).name.equals("sleep")) {
				x.statuses.get(i).duration--;
				if (x.statuses.get(i).duration==0) {
					x.statuses.remove(i);
				}
				hud.set(x.name+" is sleeping and cannot perform any action.");
				while (hud.isTyping()) {
					sleep(10);
				}
				sleep(1000);
				return true;
			}
		}
		return false;
		}
	private void applypoison(Pokemon one, Pokemon two) {
		if(one.statuses!=null) {
		for (int i=0; i<one.statuses.size();i++) {
			if (one.statuses.get(i).name.equals("poison")) {
				hud.set(one.name+" is poisoned and takes some damage.");
				while (hud.isTyping()) {
					sleep(10);
				}
				sleep(2000);
				one.currenthealth=one.currenthealth*0.8;
			}
		}
		}
		if(two.statuses!=null) {
		for (int i=0; i<two.statuses.size();i++) {
			if (two.statuses.get(i).name.equals("poison")) {
				hud.set(two.name+" is poisoned and takes some damage.");
				while (hud.isTyping()) {
					sleep(10);
				}
				sleep(2000);
				two.currenthealth=two.currenthealth*0.8;
			}
		}
		}
	}
	private boolean applyconfusion(Pokemon x) {
		if (x.statuses==null) {
			return false;
		}
		for (int i=0; i<x.statuses.size(); i++) {
			if (x.statuses.get(i).name.equals("confuse")) {
				x.statuses.get(i).duration--;
				if (x.statuses.get(i).duration==0) {
					x.statuses.remove(i);
				}
				
				return true;
			}
		}
		return false;
	}
	static boolean isasleep(Pokemon x) {
		for (int i=0; i<x.statuses.size();i++) {
			if (x.statuses.get(i).name.equals("sleep")) {
				
				return true;
			}
		}
		return false;
	}
	public static double Effectiveness(String type, Attack attack) {
		if ((attack.type.equals("water") || attack.type.equals("grass")) && type.equals("water")) {
			return 2;
		} else if ((attack.type.equals("steel") || attack.type.equals("fire") || attack.type.equals("water")
				|| attack.type.equals("ice")) && type.equals("water")) {
			return .5;
		} else if (attack.type.equals("fighting") && type.equals("normal")) {
			return 2;
		} else if (attack.type.equals("ghost") && type.equals("normal")) {
			return 0;
		} else if (attack.type.equals("ground") && type.equals("electric")) {
			return 2;
		} else if ((attack.type.equals("flying") || attack.type.equals("steel") || attack.type.equals("electric"))
				&& type.equals("electric")) {
			return .5;
		} else if (attack.type.equals("psychic") && type.equals("dark")) {
			return 0;
		} else if ((attack.type.equals("fighting") || attack.type.equals("bug") || attack.type.equals("fairy"))
				&& type.equals("dark")) {
			return 2;
		} else if ((attack.type.equals("ghost") || attack.type.equals("dark")) && type.equals("dark")) {
			return .5;
		} else if ((attack.type.equals("ground") || attack.type.equals("water") || attack.type.equals("grass")
				|| attack.type.equals("electric")) && type.equals("grass")) {
			return .5;
		} else if ((attack.type.equals("flying") || attack.type.equals("poison") || attack.type.equals("bug")
				|| attack.type.equals("fire") || attack.type.equals("ice")) && type.equals("grass")) {
			return 2;
		} else if ((attack.type.equals("ground") || attack.type.equals("psychic")) && type.equals("poison")) {
			return 2;
		} else if ((attack.type.equals("fighting") || attack.type.equals("poison") || attack.type.equals("bug")
				|| attack.type.equals("grass") || attack.type.equals("fairy")) && type.equals("poison")) {
			return .5;
		} else if ((attack.type.equals("poison") || attack.type.equals("ghost")) && type.equals("fairy")) {
			return 2;
		} else if ((attack.type.equals("bug") || attack.type.equals("fighting") || attack.type.equals("dark"))
				&& type.equals("fairy")) {
			return .5;
		} else if (attack.type.equals("dragon") && type.equals("fairy")) {
			return 0;
		}
		return 1;
	}
	private void useitem(String item) {
		if (item.equals("healingpotion")) {
			if (player.items.get(1).stock==0) {
				hud.set("You are out of that item");
				while (hud.isTyping()) {
					sleep(10);
				}
				sleep(1000);
				setState("bag");
				return;
			}
			else {
				player.currentpokemon.currenthealth=player.currentpokemon.health;
				hud.set(player.currentpokemon.name+" has been healed to full health.");
				player.items.get(1).stock--;
				while (hud.isTyping()) {
					sleep(10);
				}
				sleep(1000);
				setState("choices");
				return;
			}
		}
		else if(item.equals("cureall")) {
			if (player.items.get(1).stock==0) {
				hud.set("You are out of that item");
				while (hud.isTyping()) {
					sleep(10);
				}
				sleep(1000);
				setState("bag");
				return;
			}
			else {
				player.currentpokemon.statuses.clear();
				hud.set(player.currentpokemon.name+" has been cured of all ailments.");
				player.items.get(1).stock--;
				while (hud.isTyping()) {
					sleep(10);
				}
				sleep(1000);
				setState("choices");
				return;
			}
		}
	}
	static void wakeup(Pokemon x) {
		for (int i=0; i<x.statuses.size();i++) {
			if (x.statuses.get(i).name.equals("sleep")) {
				x.statuses.remove(i);
				return;
			}
		}
	}
	private void sleep(int i) {
		try {
			Thread.sleep(i);
		} catch (Exception e) {
		}
	}

}