package Rendering.Textures;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

//This is the class that loads all of the images
//The main render funcion is in this class
public class Texture {

	private final static Map<String, BufferedImage> texMap = new HashMap<String, BufferedImage>();
	private BufferedImage image;
	private String fileName;
	private int width, height;
	
	public Texture(String fileName){
		this.fileName = fileName;
		BufferedImage oldTexture = texMap.get(fileName);
		
		//If we have loaded the image before we don't wanna load it again
		if(oldTexture != null)
			this.image = oldTexture;
		else {
			//Loading the image in a try catch
			try {
				System.out.println("loading texture " + fileName);
				this.image = ImageIO.read(new File("./resources/" + fileName + ".png"));
				texMap.put(fileName, image);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		this.width = image.getWidth();
		this.height = image.getHeight();
		
	}
	
	//Main render for the whole program
	public void render(Graphics2D g, double x, double y){
		g.drawImage(image, (int) x, (int) y, null);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public BufferedImage getImage() {
		return image;
	}
}
