package States;

import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;

public class StateManager {

	//This controls what state is current and what is being called
	private Map<String, State> map;
	private State currentState;
	
	public StateManager(){
		map = new HashMap<String, State>();
	}
	
	//Whichever state we add first will be the opening state
	public void addState(State state){
		//We use to upper case to catch any human errors
		map.put(state.getName().toUpperCase(), state);
		state.init();
		if(currentState == null){
			state.enter();
			currentState = state;
		}
	}
	
	public void setState(String name){
		State state = map.get(name.toUpperCase());
		if(state == null){
			System.err.println("State <" + name + "> does not exist!");
			return;
		}
		currentState.exit();
		state.enter();
		currentState = state;
	}
	
	//What every state were in we want to tick/ render
	public void tick(){
		currentState.tick(this);
	}
	
	public void render(Graphics2D g){
		currentState.render(g);
	}
}
