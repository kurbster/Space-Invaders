package Entities;

import java.awt.Graphics2D;

import Rendering.Textures.Texture;
import States.GameState;

//Each entity extends the mob class 
//Mob is short for Mobile entity
public abstract class Mob {

	protected double x, y;
	protected double dx, dy;
	protected Texture texture;
	
	public Mob(Texture texture, double x, double y) {
		this.texture = texture;
		this.x = x;
		this.y = y;
	}

	public void tick(){
		x += dx;
		y += dy;
	}
	
	public abstract void render(Graphics2D g);
	
	public void move(){
		x += dx;
		y += dy;
	}
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
}
