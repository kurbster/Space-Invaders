package Handlers;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//Keeping track of the user key input
public class KeyInput extends KeyAdapter {

	private static final boolean[] keys = new boolean[256];
	private static final boolean[] lastKeys = new boolean[256];
	
	public void keyPressed(KeyEvent e){
		keys[e.getKeyCode()] = true;
		
	}
	
	public void keyReleased(KeyEvent e){
		keys[e.getKeyCode()] = false;
		
	}
	
	public static void update(){
		for(int i = 0; i < 256; i++){
			lastKeys[i] = keys[i];
		}
	}
	
	public static boolean isKeyDown(int key){
		return keys[key];
	}
	
	public static boolean wasKeyPressed(int key){
		return isKeyDown(key) && !lastKeys[key];
	}
	
	public static boolean wasKeyReleased(int key){
		return !isKeyDown(key) && lastKeys[key];
	}
}
