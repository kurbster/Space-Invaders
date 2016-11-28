package KirbyKuzniaSpaceInvaders;

//Author Kirby Kuznia
//For CSE 110 Honors Project
//Submitted on 11/27/16
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import Util.Constants;

public class SpaceInvaders {

	public static void main(String[] args){
		
		//Creating the window and starting the game
		final Game game = new Game();
		JFrame frame = new JFrame(Constants.TITLE);
		frame.add(game);
		frame.setSize(Constants.WIDTH, Constants.HEIGHT);
		frame.setResizable(false);
		frame.setFocusable(true);
		frame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.err.println("Exiting game");
				game.stop();
				
			}
		});
		frame.setLocationRelativeTo(null);
		frame.requestFocus();
		frame.setVisible(true);
		game.start();
	}
}
