package Entities;

import java.awt.Graphics2D;

import Handlers.MissileHandler;
import Rendering.Textures.Texture;
import States.GameState;
import Util.Constants;

public class Missile extends Mob implements Constants{

	public GameState state;
	public Player player;
	
	public Missile(Texture texture, double x, double y){
		super(texture, x, y);
		player = GameState.player;
	}
	
	@Override 
	public void tick(){
		//A missile object is always moving down
		dy = MISSILE_SPEED;
		
		if(y >= HEIGHT)
			MissileHandler.removeMissile(this);
		
		//Collision detection
		if(x >= player.x && x <= (player.x + PLAYER_WIDTH) && 
				y >= player.y && y <= (player.y+ PLAYER_HEIGHT)){
			Alien.gameOver = true;
		}
		
		super.tick();
	}
	
	public void render(Graphics2D g){
		texture.render(g, x, y);
	}
}
