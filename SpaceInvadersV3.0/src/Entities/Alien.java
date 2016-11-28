package Entities;

import java.awt.Graphics2D;
import java.util.ArrayList;

import java.util.Random;

import Handlers.AlienHandler;
import Handlers.MissileHandler;
import Handlers.ShotHandler;
import KirbyKuzniaSpaceInvaders.Game;
import Rendering.Textures.Texture;
import States.GameState;
import Util.AlienType;
import Util.Constants;
import Util.Tuple;
import World.World;

public class Alien extends Mob implements Constants{

	private static int direction = -1;
	public static boolean gameOver = false;
	
	public boolean released = false;
	public double startY;
	
	public Player player;
	public AlienType type;
	public Tuple alienID;
	
	public ArrayList<Alien> aliens = new ArrayList();
	
	Random generator = new Random();
	
	public Alien(Texture texture, double x, double y, Tuple id, AlienType type){
		super(texture, x, y);
		
		this.startY = y;
		this.alienID = id;
		this.type = type;
		
		player = GameState.player;
		aliens = AlienHandler.getAliens();
	}
	
	@Override
	public void tick(){
		double playerX = player.getX();
		double playerY = player.getY();
		//The basic side to side movement only for aliens still in the block
		if(!released){
			if(x >= WIDTH && direction != -1)	//If one alien his the side of the screen they all change 
				direction = -1;					//Thats why direction is static so there is one instance across all aliens
			if(x <= 0 && direction == -1)
				direction = 1;

			dx = direction;
		}
		
		//If the alien is released from the formation
		if(released){
			//Once a runner is attacking they are always going down
			dy = ALIEN_SPEED;
			
			if(x > playerX)	//If the alien is to the right of the player
				dx = -1;			//Go left
			
			if(x < playerX)	//If the alien is to the left of the player
				dx = 1;				//Go right
			
			if(x == playerX)
				dx = 0;
			
			if(y > playerY)	//If the alien is past the player
				dx = 0;				//It goes straight down till the bottom of the screen
			
			if(y > HEIGHT){//If a runner alien ran off the screen without being shot we want to put him back in the formation
				//To do this each alien has a specific ID based on what column and row it started in
				//In each column there is a shooter alien who stays in the formation
				//We need to find a shooter alien in the same column as our run away and set their x's equal
				
				int runAwayColumn, alienColumn = 0;
				double runAwayAlienX = 0;
				Tuple runAwayAlienID = this.getID();			//Run away aliens ID
				runAwayColumn = runAwayAlienID.getColumn();		//It's column
				
				for(Alien a: aliens){
					alienColumn = a.alienID.getColumn();
					
					if((alienColumn == runAwayColumn) && (a.type == AlienType.SHOOTER))
						runAwayAlienX = a.getX();
				}
				
				this.setY(startY);			//The formation moves horizontally so the y before it left is the same now
				this.setX(runAwayAlienX);	//The new x is based on the shooters in it's column
				dx = 0;						//Stop moving in the direction they were
				dy = 0;
				
				this.setReleased(false);	//The alien is no longer released and is back in the form
				
			}//End of if y > HEIGHT
		}//End of if released
		
		int chanceToAttackShooter = generator.nextInt(CHANCE_SHOOTER);
		int chanceToAttackRunner = generator.nextInt(CHANCE_RUNNER);
		//If a runner hasn't been released
		if(this.type == AlienType.RUNNER){
			
			if(chanceToAttackRunner == 1){
				this.setReleased(true);
				
				if(x > playerX)	//If the alien is to the right of the player
					dx = -ALIEN_SPEED;			//Go left
				
				if(x < playerX)	//If the alien is to the left of the player
					dx = ALIEN_SPEED;				//Go right
				
			}//End of if
			
			//If an alien runs into and kills the player
			if(x >= playerX && x <= (playerX + PLAYER_WIDTH) &&
					y >= playerY && y <= (playerY + PLAYER_HEIGHT)){
				gameOver = true;
			}//End of if 
		}//End of if
		
		if(this.type == AlienType.SHOOTER){
			if(chanceToAttackShooter == 1){
				MissileHandler.addMissile(new Missile(new Texture("bomb"), x, y));
				
			}
		}
	
		super.tick();
	}
	
	public void render(Graphics2D g){
		texture.render(g, x, y);	
		
	}
	
	public Tuple getID(){
		return alienID;
	}
	
    public boolean isReleased(){
    	return released;
    }
    
    public void setReleased(boolean released){
    	this.released = released;
    }
}





