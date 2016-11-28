package Rendering.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;

import Util.Constants;
import Util.Fonts;

//This is my own button object class for the menus
public class Button extends Rectangle{

	//We want to change font and color when the user is on that selection
	private Font font, selectedFont;
	private Color color, selectedColor;
	private boolean selected;
	private String text;
	private int textY;
	
	public Button(String text, int textY, Font font, Font selectedFont, Color color, Color selectedColor) {
		super();
		this.text = text;
		this.textY = textY;
		this.font = font;
		this.selectedFont = selectedFont;
		this.color = color;
		this.selectedColor = selectedColor;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public void render(Graphics g){
		if(selected)
			Fonts.drawString(g, selectedFont, selectedColor, text, textY);
		else
			Fonts.drawString(g, font, color, text, textY);
		
		//This rectangle is for detection with the mouse
		FontMetrics fm = g.getFontMetrics();
		this.x = (Constants.WIDTH - fm.stringWidth(text)) / 2;
		this.y = textY - fm.getHeight();
		this.width = fm.stringWidth(text);
		this.height = fm.getHeight();
		g.drawRect(x, y, width, height);
		
	}
}
