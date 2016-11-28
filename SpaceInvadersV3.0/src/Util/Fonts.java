package Util;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class Fonts {

	//The main drawString constructor
	public static void drawString(Graphics g, Font f, Color c, String text, int x, int y){
		g.setColor(c);
		g.setFont(f);
		g.drawString(text, x, y);
		
	}
	
	//Constructor to set the text in the center of the screen
	public static void drawString(Graphics g, Font f, Color c, String text){
		FontMetrics fm = g.getFontMetrics(f);
		int x = (Constants.WIDTH - fm.stringWidth(text)) / 2;	//Horizontal center
		int y = ((Constants.HEIGHT - fm.getHeight()) / 2) + fm.getAscent();	//Vertical center
		drawString(g, f, c, text, x, y);
	}
	
	//Constructor to set the y in the center
	public static void drawString(Graphics g, Font f, Color c, String text, double x){
		FontMetrics fm = g.getFontMetrics(f);
		int y = ((Constants.HEIGHT - fm.getHeight()) / 2) + fm.getAscent();//Vertical center
		drawString(g, f, c, text, (int)x, y);
	}
	
	//Constructor to set x in the center
	public static void drawString(Graphics g, Font f, Color c, String text, int y){
		FontMetrics fm = g.getFontMetrics(f);
		int x = (Constants.WIDTH - fm.stringWidth(text)) / 2;	//Horizontal center
		drawString(g, f, c, text, x, y);
	}
}
