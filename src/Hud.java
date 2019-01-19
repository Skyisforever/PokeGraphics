import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Hud {

	public static String text = "";
	public static Typewriter tw = new Typewriter(50);

	public static String mode = "";

	public static boolean clicked = false;
	private static float mouseX, mouseY;

	public Hud() {
		Thread tTypeWriter = new Thread(tw);
		tTypeWriter.start();
		define();
	}

	public void define() {
		// tw.set("This is a sample piece of text you piece of heigui da bian");
		// tw.next();
	}

	public void setMode(String mode) {
		Hud.mode = mode;
		switch (mode) {
		case "choices":
			tw.set("ATTACK         BAG\n\nPOKEMON        RUN");
			tw.next();
			break;
		case "run":
			tw.set("You have nowhere to run!          Return");
			tw.next();
			break;
		case "attack":
			tw.set(Room.player.currentpokemon.skills.get(0).name + "         "
					+ Room.player.currentpokemon.skills.get(1).name + "\n\n"
					+ Room.player.currentpokemon.skills.get(2).name + "        "
					+ Room.player.currentpokemon.skills.get(3).name + "           return");
			tw.next();
			break;
		case "bag":
			tw.set(Room.player.items.get(0).name + "         " + Room.player.items.get(1).name + "         return");
			tw.next();
			break;
		case "pokemon":
			tw.set(Room.player.pokemons.get(0).name + "          " + Room.player.pokemons.get(1).name + "          "
					+ Room.player.pokemons.get(2).name + "       return");
			tw.next();
			break;
		}
	}

	public static String getMode() {
		return Hud.mode;
	}

	public void physics() {
		if (clicked) {
			switch (mode) {
			case "choices":
				choices();
				break;
			case "run":
				run();
				break;
			case "attack":
				attack();
				break;
			case "pokemon":
				pokemon();
				break;
			case "bag":
				bag();
				break;

			}
			clicked = false;
		}
	}

	public void choices() {
		if ((mouseX >= 33 && mouseX <= 119) && (mouseY >= 428 && mouseY <= 455)) {

			setMode("attack");

		} else if ((mouseX >= 203 && mouseX <= 267) && (mouseY >= 427 && mouseY <= 453)) {

			setMode("bag");

		} else if ((mouseX >= 34 && mouseX <= 131) && (mouseY >= 482 && mouseY <= 511)) {

			setMode("pokemon");

		} else if ((mouseX >= 208 && mouseX <= 268) && (mouseY >= 484 && mouseY <= 508)) {
			setMode("run");

		}
	}

	public void attack() {
		if ((mouseX >= 79 && mouseX <= 145) && (mouseY >= 449 && mouseY <= 459)) {
			Room.player.currentpokemon.currentattack = Room.player.currentpokemon.skills.get(0);

		} else if ((mouseX >= 220 && mouseX <= 361) && (mouseY >= 427 && mouseY <= 455)) {
			Room.player.currentpokemon.currentattack = Room.player.currentpokemon.skills.get(1);

		} else if ((mouseX >= 34 && mouseX <= 131) && (mouseY >= 482 && mouseY <= 511)) {
			Room.player.currentpokemon.currentattack = Room.player.currentpokemon.skills.get(2);

		} else if ((mouseX >= 208 && mouseX <= 268) && (mouseY >= 484 && mouseY <= 508)) {
			Room.player.currentpokemon.currentattack = Room.player.currentpokemon.skills.get(3);

		} else if ((mouseX >= 422 && mouseX <= 525) && (mouseY >= 483 && mouseY <= 512)) {
			setMode("choices");

		}

	}

	public void bag() {
		if ((mouseX >= 33 && mouseX <= 119) && (mouseY >= 428 && mouseY <= 455)) {
			if (Room.player.items.get(0).stock != 0) {
				cureall(Room.player.currentpokemon);
				Room.player.items.get(0).stock = 0;

			} else {
				tw.set("You are out of this item");
				tw.next();
			}
		} else if ((mouseX >= 225 && mouseX <= 399) && (mouseY >= 426 && mouseY <= 454)) {
			if (Room.player.items.get(1).stock != 0) {
				maxheal(Room.player.currentpokemon);
				Room.player.items.get(1).stock = 0;

			} else {
				tw.set("You are out of this item");
				tw.next();
			}
		} else if ((mouseX >= 473 && mouseX <= 579) && (mouseY >= 424 && mouseY <= 455)) {
			setMode("choices");
		}

	}

	public void pokemon() {
		if ((mouseX >= 33 && mouseX <= 119) && (mouseY >= 428 && mouseY <= 455)) {
			// player.currentpokemon.currentattack=player.currentpokemon.attacks.get(1);
		} else if ((mouseX >= 37 && mouseX <= 184) && (mouseY >= 429 && mouseY <= 453)) {

		} else if ((mouseX >= 267 && mouseX <= 388) && (mouseY >= 427 && mouseY <= 457)) {

		} else if ((mouseX >= 466 && mouseX <= 488) && (mouseY >= 428 && mouseY <= 454)) {

		} else if ((mouseX >= 634 && mouseX <= 741) && (mouseY >= 426 && mouseY <= 464)) {
			setMode("choices");
		}

	}

	public void run() {
		if ((mouseX >= 433 && mouseX <= 532) && (mouseY >= 428 && mouseY <= 460)) {
			setMode("choices");
		}

	}

	public static void setCoord(float x, float y) {
		mouseX = x;
		mouseY = y;
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

	public static void Modifycurrenthealth(Pokemon x, Pokemon y, Attack attack) {
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
		if (attack.name.equals("WakeupSlap")) {
			if (isasleep(y)) {
				finaldamage = finaldamage * 2;
				slowprint(x.name + " uses " + attack.name + "! ");
				slowestprint("It deals " + finaldamage + " damage");
				slowprint(y.name + " suffered twice the damage and has woken up.");
				wakeup(y);
				y.currenthealth = y.currenthealth - finaldamage;
				return;
			}
		}
		slowprint(x.name + " uses " + attack.name + "! ");
		if (applyconfusion(x)) {
			Random h = new Random();
			int b = h.nextInt(100);
			if (b < 50) {
				x.currenthealth = x.currenthealth - finaldamage;
				slowprint(x.name + " is confused and attacked himself for " + finaldamage + " damage!");
				return;
			}
		}
		slowestprint("It deals " + finaldamage + " damage");
		if (TrueEffectiveness > 1) {
			slowestprint("It's Super Effective!");
		} else if (TrueEffectiveness == 0.5) {
			slowestprint("It's Not Very Effective...");
		} else if (TrueEffectiveness == 0) {
			slowestprint("It's Completely Ineffective!");
		}
		if (attack.status.name.equals("poison")) {
			Random rand = new Random();
			int num = rand.nextInt(100);
			if (num < 45) {
				y.statuses.add(new Status("poison", 99));
				slowestprint(y.name + " has been poisoned!");
			}
		} else if (attack.status.name.equals("sleep")) {
			Random rand = new Random();
			int num = rand.nextInt(100);
			if (num < 75) {
				y.statuses.add(new Status("sleep", 2));
				slowestprint(y.name + " has fallen asleep!");
			} else {
				slowestprint(y.name + " successfully resisted the urge to fall asleep");
			}
		} else if (attack.status.name.equals("paralysis")) {
			Random rand = new Random();
			int num = rand.nextInt(100);
			if (num < 50) {
				y.statuses.add(new Status("paralysis", 2));
				slowestprint(y.name + " has been paralyzed!");
			}
		} else if (attack.status.name.equals("freeze")) {
			Random rand = new Random();
			int num = rand.nextInt(100);
			if (num < 30) {
				y.statuses.add(new Status("freeze", 1));
				slowestprint(y.name + " has been frozen!");
			}
		} else if (attack.status.name.equals("confuse")) {
			Random rand = new Random();
			int num = rand.nextInt(100);
			if (num < 75) {
				y.statuses.add(new Status("confuse", 5));
				slowestprint(y.name + " has been confused!");
			}
		}
		y.currenthealth = y.currenthealth - finaldamage;
		if (attack.name.equals("LeechSeed")) {
			slowprint("Bulbasaur steals " + Math.round(finaldamage * .4) + " Health");
			x.currenthealth = x.currenthealth + Math.round(finaldamage * .4);
		}
	}

	public static void Battle(Player user, Player opponent) {
		if (user.currentpokemon.currentattack == null) {
			Modifycurrenthealth(opponent.currentpokemon, user.currentpokemon, opponent.currentpokemon.currentattack);
			if (user.currentpokemon.health <= 0) {
				userswap(user);
			}
			return;
		}
		if (applyparalysis(user.currentpokemon)) {
			user.currentpokemon.speed = user.currentpokemon.speed / 2;
		}
		if (applyparalysis(opponent.currentpokemon)) {
			opponent.currentpokemon.speed = opponent.currentpokemon.speed / 2;
		}
		if (user.currentpokemon.speed >= opponent.currentpokemon.speed) {
			fastprint(user.currentpokemon.name + " will attack first");
			Modifycurrenthealth(user.currentpokemon, opponent.currentpokemon, user.currentpokemon.currentattack);
			if (opponent.currentpokemon.currenthealth <= 0) {
				slowprint(opponent.currentpokemon.name + " has fainted!");
				opponentswap(opponent);
				return;
			}
			Modifycurrenthealth(opponent.currentpokemon, user.currentpokemon, opponent.currentpokemon.currentattack);
			if (user.currentpokemon.currenthealth <= 0) {
				slowprint(user.currentpokemon.name + " has fainted!");
				userswap(user);
			}

		} else if (user.currentpokemon.speed < opponent.currentpokemon.speed) {
			slowprint(opponent.currentpokemon.name + " will attack first");
			Modifycurrenthealth(opponent.currentpokemon, user.currentpokemon, opponent.currentpokemon.currentattack);
			if (user.currentpokemon.currenthealth <= 0) {
				slowprint(user.currentpokemon.name + " has fainted!");
				userswap(user);
				return;
			}
			Modifycurrenthealth(user.currentpokemon, opponent.currentpokemon, user.currentpokemon.currentattack);
			if (opponent.currentpokemon.currenthealth <= 0) {
				slowprint(opponent.currentpokemon.name + " has fainted!");
				opponentswap(opponent);
			}
		}
		if (user.currentpokemon.health <= 0) {
			userswap(user);
		}
		user.currentpokemon.currentattack = null;
	}

	public static void userswap(Player user) {
		int a = 0;
		tw.set("Your remaining pokemon are:");
		for (int i = 0; i < 3; i++) {
			if (user.pokemons.get(i).currenthealth > 0) {
				slowprint("Type " + i + " for " + user.pokemons.get(i).name);
			} else {
				a++;
			}
			if (a == 3) {
				gameoveruser(user);
			}
		}
		boolean faultyinput = true;
		int y = -1;
		do {
			if (Character.isDigit(x.charAt(0))) {
				y = Integer.parseInt(x);
				if (y <= user.pokemons.size() && y >= 0) {
					faultyinput = false;
				}
			} else {
				faultyinput = true;
				System.out.println("faulty input, please re-enter");
				x = scan.nextLine().toLowerCase();
			}
		} while (faultyinput);
		if (user.currentpokemon.equals(user.pokemons.get(y))) {
			slowprint(user.currentpokemon + " is already summoned!");
			return;
		}
		user.currentpokemon = user.pokemons.get(y);
		slowestprint(user.currentpokemon.name + "! I choose you!");

	}

	public static void applypoison(Pokemon one, Pokemon two) {
		if (one.statuses != null) {
			for (int i = 0; i < one.statuses.size(); i++) {
				if (one.statuses.get(i).name.equals("poison")) {

					one.currenthealth = one.currenthealth * 0.8;
				}
			}
		}
		if (two.statuses != null) {
			for (int i = 0; i < two.statuses.size(); i++) {
				if (two.statuses.get(i).name.equals("poison")) {

					two.currenthealth = two.currenthealth * 0.8;
				}
			}
		}
	}

	static boolean applyparalysis(Pokemon x) {
		if (x.statuses == null) {
			return false;
		}
		for (int i = 0; i < x.statuses.size(); i++) {
			if (x.statuses.get(i).name.equals("paralyze")) {
				x.statuses.get(i).duration--;
				if (x.statuses.get(i).duration == 0) {
					x.statuses.remove(i);
				}

				return true;
			}
		}
		return false;
	}

	static boolean applysleep(Pokemon x) {
		if (x.statuses == null) {
			return false;
		}
		for (int i = 0; i < x.statuses.size(); i++) {
			if (x.statuses.get(i).name.equals("sleep")) {
				x.statuses.get(i).duration--;
				if (x.statuses.get(i).duration == 0) {
					x.statuses.remove(i);
				}

				return true;
			}
		}
		return false;
	}

	void cureall(Pokemon x) {
		x.statuses.clear();
		tw.set(x.name + " has been cured of all ailments");
		tw.next();

	}

	static void maxheal(Pokemon x) {
		x.currenthealth = x.health;
		tw.set(x.name + " has fully recovered his health");
		tw.next();
	}

	static boolean applyconfusion(Pokemon x) {
		if (x.statuses == null) {
			return false;
		}
		for (int i = 0; i < x.statuses.size(); i++) {
			if (x.statuses.get(i).name.equals("confuse")) {
				x.statuses.get(i).duration--;
				if (x.statuses.get(i).duration == 0) {
					x.statuses.remove(i);
				}

				return true;
			}
		}
		return false;
	}

	static void wakeup(Pokemon x) {
		for (int i = 0; i < x.statuses.size(); i++) {
			if (x.statuses.get(i).name.equals("sleep")) {
				x.statuses.remove(i);
				return;
			}
		}
	}

	static boolean applyfreeze(Pokemon x) {
		if (x.statuses == null) {
			return false;
		}
		for (int i = 0; i < x.statuses.size(); i++) {
			if (x.statuses.get(i).name.equals("freeze")) {
				x.statuses.get(i).duration--;
				if (x.statuses.get(i).duration == 0) {
					x.statuses.remove(i);
				}

				return true;
			}
		}
		return false;
	}

	static boolean isasleep(Pokemon x) {
		for (int i = 0; i < x.statuses.size(); i++) {
			if (x.statuses.get(i).name.equals("sleep")) {

				return true;
			}
		}
		return false;
	}

	public void draw(Graphics g) {
		g.setColor(Color.ORANGE);
		g.fillRect(20, 400, Frame.size.width - 55, 150);
		g.setColor(Color.WHITE);
		g.fillRect(30, 410, Frame.size.width - 75, 130);

		g.setFont(new Font("Courier", Font.BOLD, 20));
		g.setColor(Color.BLACK);
		drawString(g, text, 40, 450);
	}

	void drawString(Graphics g, String text, int x, int y) {
		int i = 0;
		for (String line : text.split("\n")) {
			g.drawString(line, x, y += (i++ == 0) ? 0 : g.getFontMetrics().getHeight());
		}
	}

}
