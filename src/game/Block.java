package game;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Block extends Rectangle {
	Image pic;
	boolean destroyed;
	int breakable = 1;
	int movX, movY;
	
	
	Block(int a, int b, int w, int h, String s) {
		this.x = a;
		this.y = b;
		this.width = w;
		this.height = h;
		
		movX = 3;
		movY = 3;
		
		try {
			pic = ImageIO.read(new File("src/"+s));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics g, Component c) {
		if(!destroyed) {
			g.drawImage(pic, this.x, this.y, this.width, this.height, c);
		}
	}
}
