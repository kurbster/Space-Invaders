package World;

import java.util.ArrayList;

import Entities.Alien;
import Entities.Missile;
import Entities.Player;
import Handlers.AlienHandler;
import Rendering.Textures.Texture;
import States.GameState;
import Util.AlienType;
import Util.Constants;
import Util.Tuple;
//This world class is for initializing the aliens
//This adds them to their handler and puts them in their formation
public class World implements Constants{
	
	public World(){		
		//Creating the army of aliens
		int alienX, alienY;
		for(int row = 0; row <= 5; row++){ //Create 6 rows
			alienY = row * (ALIEN_HEIGHT + SPACE); //Every alien has that same y and each row has space in between
			
			//For every row we want 6 column of aliens
			for(int column = 0; column <= 5; column++){
				//ALIEN_BOX_X is a x so the box of aliens is in the middle of the screen and the first row of aliens start there
				alienX = ALIEN_BOX_X + ALIEN_WIDTH + column * (SPACE + ALIEN_WIDTH);
				
				Tuple <Integer, Integer> alienID = new Tuple(row,column);	//Creating a tuple object for every alien with their (row, column)
				
				switch(row){
				//For these rows we want a runner type alien
				case 0: case 2: case 4:
					AlienHandler.addAlien(new Alien(new Texture("alien"), alienX, alienY, alienID, AlienType.RUNNER));
					break;
				//For these rows we want a shooter type alien
				case 1: case 3: case 5:
					AlienHandler.addAlien(new Alien(new Texture("alien2"), alienX, alienY, alienID, AlienType.SHOOTER));
					break;
				}//End of switch
			}//End of inner for loop
		}//End of outer for loop
	}//End of World constructor
}//End of class
