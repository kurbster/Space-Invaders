package States;

import java.awt.Graphics2D;

public interface State {

	//All states have these methods
	public void init();
	public void enter();
	public void tick(StateManager stateManager);
	public void render(Graphics2D g);
	public void exit();
	public String getName();
}
