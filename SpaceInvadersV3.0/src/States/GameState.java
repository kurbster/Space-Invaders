package States;

import java.awt.Graphics2D;

import Entities.Alien;
import Entities.Player;
import Entities.Shot;
import Handlers.AlienHandler;
import Handlers.MissileHandler;
import Handlers.ShotHandler;
import Rendering.Textures.Texture;
import World.World;
//The main state of the program
public class GameState implements State {
	
	//An instance of player to use across all the other entities for hit detection
	public static Player player;
	private World world;
	
	//When the game starts
	@Override
	public void init() {}

	//When the user hits play
	@Override
	public void enter() {
		player = new Player(new Texture("player"), 320, 420);
		new World();
	}

	@Override
	public void tick(StateManager stateManager) {
		
		//This ends the game
		if(Alien.gameOver == true)
			stateManager.setState("gameOverMenu");
		
		//If the user won
		if(Shot.didWin == true)
			stateManager.setState("winMenu");
		
		//This makes sure there is no null pointer exception
		if(player == null && Shot.didWin == true){
			stateManager.setState("winMenu");
			return;
		}
		
		if(player == null && Alien.gameOver == true){
			stateManager.setState("gameOverMenu");
			return;
		}
		
		//Looping through each entity and ticking
		for(int i = 0; i < AlienHandler.getAliens().size(); i++){
			AlienHandler.getAliens().get(i).tick();
		}
		
		for(int i = 0; i < MissileHandler.getMissiles().size(); i++){
			MissileHandler.getMissiles().get(i).tick();
		}
		
		for(int i = 0; i < ShotHandler.getShots().size(); i++){
			ShotHandler.getShots().get(i).tick();
		}
		
		//There is only one player
		player.tick();
	}

	@Override
	public void render(Graphics2D g) {
		if(player == null)
			return;
		
		for(int i = 0; i < AlienHandler.getAliens().size(); i++){
			AlienHandler.getAliens().get(i).render(g);
		}
		
		for(int i = 0; i < MissileHandler.getMissiles().size(); i++){
			MissileHandler.getMissiles().get(i).render(g);
		}
		
		for(int i = 0; i < ShotHandler.getShots().size(); i++){
			ShotHandler.getShots().get(i).render(g);
		}
		
		player.render(g);
	}

	@Override
	public void exit() {
		MissileHandler.removeAll();
		AlienHandler.removeAll();
		ShotHandler.removeAll();
		
		player = null;
	}

	@Override
	public String getName() {
		return "level1";
	}
	
}
