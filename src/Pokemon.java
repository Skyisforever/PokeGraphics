import java.util.ArrayList;

public class Pokemon {
	String name;
	ArrayList<String> type;
	ArrayList<Attack> skills;
	double health;
	double attack;
	double defense;
	double speed;
	double currenthealth;
	ArrayList<Status> statuses;
	Attack currentattack;

	public Pokemon(String name, ArrayList<String> type, double health, double attack, double defense, double speed,
			ArrayList<Attack> skills, ArrayList<Status> statuses) {
		this.name = name;
		this.health = health;
		this.attack = attack;
		this.defense = defense;
		this.speed = speed;
		this.skills = skills;
		this.type = type;
		this.statuses = statuses;
		currenthealth = health;
	}
}
