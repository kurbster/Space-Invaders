package Entities;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import Handlers.KeyInput;
import Handlers.ShotHandler;
import Rendering.Textures.Texture;
import States.GameState;
import Util.Constants;


public class Player extends Mob implements Constants{

	public Player(Texture texture, double x, double y) {
		super(texture, x, y);
	}

	@Override
	public void tick() {
		if(KeyInput.isKeyDown(KeyEvent.VK_UP))
			dy = -PLAYER_SPEED;
		if(KeyInput.isKeyDown(KeyEvent.VK_DOWN))
			dy = PLAYER_SPEED;
		if(KeyInput.isKeyDown(KeyEvent.VK_LEFT))
			dx = -PLAYER_SPEED;
		if(KeyInput.isKeyDown(KeyEvent.VK_RIGHT))
			dx = PLAYER_SPEED;
		
		if(KeyInput.wasKeyReleased(KeyEvent.VK_UP) || KeyInput.wasKeyReleased(KeyEvent.VK_DOWN))
			dy = 0;
		if(KeyInput.wasKeyReleased(KeyEvent.VK_LEFT) || KeyInput.wasKeyReleased(KeyEvent.VK_RIGHT))
			dx = 0;
		
		if(KeyInput.isKeyDown(KeyEvent.VK_SPACE)){
			//The player can only have one shot at a time only make a new shot if there are none on the screen
			if(ShotHandler.getShots().size() == 0)
				ShotHandler.addShot(new Shot(new Texture("shot"), x, y));
		}
		
		if(x <= 1)	//If the player is going to go off the screen to the left 
			x = 1;	//Don't let them
		
		if(x >= WIDTH - PLAYER_WIDTH)	//If the user is going to go off the right side 
			x = WIDTH - PLAYER_WIDTH;	//Don't let them
		
		if(y <= 1)		//If the user is going too high
			y = 1;		//Don't let them
		
		if(y >= HEIGHT - PLAYER_HEIGHT - 23)		//If they are going out on the bottom
			y = HEIGHT - PLAYER_HEIGHT - 23;		//Don't let them
		super.tick();
	}
	
	public void render(Graphics2D g){
		texture.render(g, x, y);
	}
}
