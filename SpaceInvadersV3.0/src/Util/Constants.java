package Util;

public interface Constants {

	public static final String TITLE = "Space Invaders";
	public static final int WIDTH = 640;
	public static final int HEIGHT = WIDTH / 4 * 3;
	public static final int SHOT_SPEED = 5;
	public static final int PLAYER_SPEED = 3;
	public static final int PLAYER_WIDTH = 15;
	public static final int PLAYER_HEIGHT = 10;
	public static final int ALIEN_WIDTH = 12;
	public static final int ALIEN_HEIGHT = 12;
	public static final int ALIEN_SPEED = 2;
	public static final int MISSILE_SPEED = 3;
	public static final int SPACE = 12;
	public static final int ALIEN_BOX_WIDTH = 5 * ALIEN_WIDTH + 4 * SPACE;
	public static final int ALIEN_BOX_HEIGHT = 6 * ALIEN_HEIGHT;
	public static final int ALIEN_BOX_X = (WIDTH - ALIEN_BOX_WIDTH) / 2;
	public static final int NUMBER_OF_ALIENS_TO_DESTROY = 36;
	public static final int CHANCE_SHOOTER = 500;
	public static final int CHANCE_RUNNER = 750;
	
	
}
