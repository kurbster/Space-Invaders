package KirbyKuzniaSpaceInvaders;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import Entities.Player;
import Handlers.AlienHandler;
import Handlers.KeyInput;
import Handlers.MissileHandler;
import Handlers.MouseInput;
import Handlers.ShotHandler;
import Rendering.Textures.Texture;
import States.GameOverState;
import States.GameState;
import States.MenuState;
import States.StateManager;
import States.WinState;
import Util.Constants;
import World.World;

public class Game extends Canvas implements Runnable{

	private boolean running;
	private Texture texture;
	
	private StateManager stateManager;
	//Making an instance of the game so we can quit it
	public static Game INSTANCE;
	
	public Game(){
		//Adding the listeners so we can read the users input
		addKeyListener(new KeyInput());
		MouseInput mi = new MouseInput();
		addMouseListener(mi);
		addMouseMotionListener(mi);
		
		//Adding the different states
		stateManager = new StateManager();
		//We added the menu state first for the menu will be first
		stateManager.addState(new MenuState());
		stateManager.addState(new GameState());
		stateManager.addState(new GameOverState());
		stateManager.addState(new WinState());
		
		INSTANCE = this;
	}
	
	//Each tick is like another run of the program
	public void tick(){
		//The stateManager will control which state is current therefore which tick is being called
		stateManager.tick();
	}
	
	public void render(){
		//BufferStrategy makes the game smoother
		BufferStrategy bs = getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		
		//The background will just be solid black
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);
		
		stateManager.render(g2d);
		
		g.dispose();
		bs.show();
		
	}
	
	public void start(){
		if(running) return;
		running = true;
		new Thread(this, "SpaceIvadersMain").start();
	}
	
	public void stop(){
		if(!running) return;
		running = false;
	}
	
	public void run(){
		requestFocus();
		//We want 60 ticks per second
		double target = 60.0;
		//There is one billion nano seconds in one second
		double nsPerTick = 1000000000.0 / target;
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double unprocessed = 0.0;
		int fps = 0;
		int tps = 0;
		boolean canRender = false;
		
		while(running){
			long now = System.nanoTime();
			unprocessed += (now - lastTime) / nsPerTick;
			lastTime = now;
			
			if(unprocessed >= 1.0){
				tick();
				MouseInput.update();
				KeyInput.update();
				unprocessed--;
				tps++;
				canRender = true;
			}//End of if
			else canRender = false;
			
			try{
				Thread.sleep(1);
			} catch(InterruptedException e){
				e.printStackTrace();
			}//End of try catch
			
			if(canRender){
				render();
				fps++;
			}//End of if
			
			if(System.currentTimeMillis() - 1000 > timer){
				timer += 1000;
				System.out.printf("FPS: %d | TPS %d\n", fps, tps);
				fps = 0;
				tps = 0;
				
			}//End of if
		}//End of while running
		
		System.exit(0);
		
	}//End of run
}//End of class















