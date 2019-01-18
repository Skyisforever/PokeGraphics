import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.print.DocFlavor.URL;

public class Player {
String name;
ArrayList<Pokemon> pokemons;
Pokemon currentpokemon;
Item currentitem;
ArrayList<Item> items;
public Player(String name, ArrayList<Item> items) throws FileNotFoundException {
	this.name=name;
	this.items=items;
}
public void GeneratePokemon(ArrayList<Pokemon> input) throws FileNotFoundException {
ArrayList<Pokemon> listofpokemon=new ArrayList<Pokemon>();
java.net.URL url = getClass().getResource("/PokemonList.txt");
BufferedReader in = null;
try {
	in = new BufferedReader(
	        new InputStreamReader(
	        url.openStream()));
} catch (IOException e) {
	e.printStackTrace();
}

String cur_line;
try {
while ((cur_line = in.readLine()) != null) {
	String[] x=cur_line.split(",");
	ArrayList<String> types=new ArrayList<String>();
	ArrayList<Attack> skills=new ArrayList<Attack>();
	int i=1;
	while (Character.isLetter(x[i].charAt(0))) {
		types.add(x[i]);
		i++;
	}
	double health= Double.parseDouble(x[i]);
	double attack=Double.parseDouble(x[i+1]);
	double defense=Double.parseDouble(x[i+2]);
	double speed=Double.parseDouble(x[i+3]);
	i=i+4;
	for (int z=0; z<4; z++) {
		skills.add(new Attack(Double.parseDouble(x[i+3]), x[i],x[i+1],new Status(x[i+2], 0)));
		i=i+4;
	}
	Pokemon y=new Pokemon(x[0],types, health, attack, defense ,speed, skills, new ArrayList<Status>());
	listofpokemon.add(y);
}
}catch(Exception e) {
	
}
ArrayList<Integer> duplicate=new ArrayList<Integer>();
	for (int i=0; i<3; i++) {
		Random rand=new Random();
		int number=rand.nextInt(5);
		while (duplicate.contains(number)) {
			number=rand.nextInt(5);
		}
		input.add(listofpokemon.get(number));	
		duplicate.add(number);
		
	}
	
}
}