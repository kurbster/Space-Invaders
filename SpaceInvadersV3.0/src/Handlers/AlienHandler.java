package Handlers;

import java.util.ArrayList;

import Entities.Alien;
import Entities.Missile;

//All of the object handlers are the same
//They keep track of all the entities in the game
public class AlienHandler {

	private static final ArrayList<Alien> aliens = new ArrayList();
	
	public static void addAlien(Alien a){
		aliens.add(a);
	}
	
	public static void removeAlien(Alien a){
		aliens.remove(a);
	}
	
	public static void removeAll(){
		aliens.clear();
	}
	
	public static ArrayList<Alien> getAliens(){
		return aliens;
	}
}
