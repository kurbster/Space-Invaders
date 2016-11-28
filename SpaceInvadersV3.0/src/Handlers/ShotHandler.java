package Handlers;

import java.util.ArrayList;

import Entities.Alien;
import Entities.Shot;

public class ShotHandler {

	private static final ArrayList<Shot> shot = new ArrayList();
	
	public static void addShot(Shot s){
		shot.add(s);
	}
	
	public static void removeShot(Shot s){
		shot.remove(s);
	}
	
	public static void removeAll(){
		shot.clear();
	}
	
	public static ArrayList<Shot> getShots(){
		return shot;
	}
}
