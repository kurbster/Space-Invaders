package Handlers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//Keeping track of the user mouse input for the menu
public class MouseInput extends MouseAdapter{

	//Most mouses only have 6 buttons
	private static final boolean[] buttons = new boolean[6];
	private static final boolean[] lastButtons = new boolean[6];
	private static int x = -1, y = -1;
	private static int lastX = x, lastY = y;
	private static boolean moving = false;
	
	public void mousePressed(MouseEvent e){
		System.out.println("button: " + e.getButton());
		buttons[e.getButton()] = true;
	}
	
	public void mouseReleased(MouseEvent e){
		buttons[e.getButton()] = false;
	}
	
	public void mouseMoved(MouseEvent e){
		x = e.getX();
		y = e.getY();
		
		moving = true;
	}
	
	public static void update(){
		for(int i = 0; i < 6; i++)
			lastButtons[i] = buttons[i];
		
		if(lastX == x && lastY == y)
			moving = false;
		
		lastX = x;
		lastY = y;
	}
	
	public static boolean isDown(int button){
		return buttons[button];
	}
	
	public static boolean wasPressed(int button){
		return isDown(button) && !lastButtons[button];
	}
	
	public static boolean wasReleased(int button){
		return !isDown(button) && lastButtons[button];
	}
	
	public static int getX() {
		return x;
	}
	
	public static int getY() {
		return y;
	}

	public static boolean isMoving() {
		return moving;
	}
}
