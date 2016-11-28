package Entities;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;

import Handlers.AlienHandler;
import Handlers.ShotHandler;
import Rendering.Textures.Texture;
import States.GameState;
import Util.Constants;

public class Shot extends Mob implements Constants{
	
	public static boolean didWin = false;
	public ArrayList<Alien> aliens = new ArrayList();
	public Alien alienToRemove;
	private static int deaths = 0;
	
	public Shot(Texture texture, double x, double y){
		super(texture, x, y);
		aliens = AlienHandler.getAliens();
	}
	
	@Override
	public void tick(){
		dy = -SHOT_SPEED;
		
		//If the shot went off the screen
		if(y <= 0){
			ShotHandler.removeShot(this);
			return;
		}

		Iterator it = aliens.iterator();
		
		//Checking every alien to see if there is a hit
		while(it.hasNext()){
			Alien alien = (Alien)it.next();
			//If there is a collision
			if(x >= alien.x && x <= (alien.x + ALIEN_WIDTH) &&
					y >= alien.y && y <= (alien.y + ALIEN_HEIGHT)){
				alienToRemove = alien;
				ShotHandler.removeShot(this);
				deaths++;
			}
		}
		//We remove it outside of the loop so we don't get an error
		AlienHandler.removeAlien(alienToRemove);
		
		//If the player killed all of the aliens
		if(deaths == NUMBER_OF_ALIENS_TO_DESTROY){
			didWin = true;
		}
		
		super.tick();
	}
	
	public void render(Graphics2D g){
		texture.render(g, x, y);
		
	}
}
