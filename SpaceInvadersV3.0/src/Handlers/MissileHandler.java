package Handlers;

import java.util.ArrayList;

import Entities.Missile;

public class MissileHandler {

	private static final ArrayList<Missile> missiles = new ArrayList();
	
	public static void addMissile(Missile m){
		missiles.add(m);
	}
	
	public static void removeMissile(Missile m){
		missiles.remove(m);
	}
	
	public static void removeAll(){
		missiles.clear();
	}
	public static ArrayList<Missile> getMissiles(){
		return missiles;
	}
}
