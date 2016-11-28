package States;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import Entities.Alien;
import Handlers.KeyInput;
import Handlers.MouseInput;
import KirbyKuzniaSpaceInvaders.Game;
import Rendering.ui.Button;
import Util.Constants;
import Util.Fonts;
//When the game has already been played once we are in this state
//Same exact thing as the menu state but the title is Game Over
public class GameOverState implements State {

	//Array of button objects
	private Button[] options;
	private int currentSelection;
	
	@Override
	public void init(){
		System.out.println("game over state");
		//Initializing the button objects
		options = new Button[2];
		options[0] = new Button("Play", 200 + 0 * 80, new Font("Arial", Font.PLAIN, 32), 
				new Font("Arial", Font.BOLD, 48), Color.WHITE, Color.YELLOW);
		options[1] = new Button("Exit", 200 + 1 * 80, new Font("Arial", Font.PLAIN, 32), 
				new Font("Arial", Font.BOLD, 48), Color.WHITE, Color.YELLOW);
	}

	@Override
	public void enter() {}

	@Override
	public void tick(StateManager stateManager) {//This is so the user can use the keys to control the keyboard
		if(KeyInput.wasKeyPressed(KeyEvent.VK_UP) || KeyInput.wasKeyPressed(KeyEvent.VK_W)){
			currentSelection--;
			//If they go past the top go to the bottom
			if(currentSelection < 0)
				currentSelection = options.length - 1;
		}
		
		if(KeyInput.wasKeyPressed(KeyEvent.VK_DOWN) || KeyInput.wasKeyPressed(KeyEvent.VK_S)){
			currentSelection++;
			//If they go past the bottom go to the top
			if(currentSelection >= options.length)
				currentSelection = 0;
		}
		
		boolean clicked = false;
		for(int i = 0; i < options.length; i++){
			if(options[i].intersects(new Rectangle(MouseInput.getX(), MouseInput.getY(), 1, 1))){
				currentSelection = i;
				clicked = MouseInput.wasPressed(MouseEvent.BUTTON1);
			}
		}
		
		if(clicked || KeyInput.wasKeyPressed(KeyEvent.VK_ENTER))
			select(stateManager);}


	private void select(StateManager stateManager){
		switch(currentSelection){
		case 0:
			Alien.gameOver = false;
			stateManager.setState("level1");
			break;
		case 1:
			System.out.println("Exit");
			Game.INSTANCE.stop();
			break;
		}
	}
	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);
		Fonts.drawString(g, new Font("Arial", Font.BOLD, 72), Color.ORANGE, "Game Over", 80);
		
		//If a user is over the buttons we want them to 'light' up
		for(int i = 0; i < options.length; i++){
			if(i == currentSelection)
				options[i].setSelected(true);
			else
				options[i].setSelected(false);
			
			options[i].render(g);
		}
	}

	@Override
	public void exit() {}

	@Override
	public String getName() {
		return "gameOverMenu";
	}
}
