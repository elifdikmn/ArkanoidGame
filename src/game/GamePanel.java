package game;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements KeyListener {
	
	ArrayList<Block> blocks;
	Block ball;
	Block paddle;
	int hardness;
	Thread thread;
	JFrame mainFrame, startScreen;
	
	
	void reset() {
		blocks =  new ArrayList<Block>();
		ball = new Block(237, 435, 35, 25, "ball.png");
		paddle = new Block(175, 480, 150, 25, "paddle.png");
		
		for(int i = 0; i<8; i++) {
			blocks.add(new Block((i*60), 0, 60, 25, "brick4.png"));
		}
		for(int i = 0; i<8; i++) {
			blocks.add(new Block((i*60), 25, 60, 25, "brick3.png"));
		}
		for(int i = 0; i<8; i++) {
			blocks.add(new Block((i*60), 50, 60, 25, "brick2.png"));
		}
		for(int i = 0; i<8; i++) {
			blocks.add(new Block((i*60), 75, 60, 25, "brick.png"));
		}
		
		addKeyListener(this);
		setFocusable(true);
	}
	
	GamePanel(JFrame main, JFrame start, int tmphardness) {
		hardness = tmphardness;
		System.out.println(hardness);
		mainFrame = main;
		startScreen = start;
		
		reset();
		
		thread = new Thread(() -> {
			while(true) {
				update();
				try {
					Thread.sleep(10);
				} catch(InterruptedException err) {
					err.printStackTrace();
				}
			}
		});
		thread.start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		blocks.forEach(block -> {
			block.draw(g,this);
		});
		ball.draw(g, this);
		paddle.draw(g,this);
	}
	
	public void update() {
		ball.x += ball.movX;
		ball.y += ball.movY;
		
		if(ball.x > (getWidth() - 25) || ball.x < 0) {
			ball.movX *= -1;
		}
		
		if(ball.y < 0 || ball.intersects(paddle) ) {
			ball.movY *= -1;
		}
		
		if(ball.y > getHeight()) {
			thread = null;
			reset();
			startScreen.setVisible(true);
			mainFrame.setVisible(false);
		}
		
		blocks.forEach(block -> {
			if(ball.intersects(block) && !block.destroyed) {
				if(block.breakable >= hardness) {
					block.destroyed = true;	
				}
				block.breakable +=1;
				ball.movY *= -1;
			}
		});
		
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT && paddle.x < (getWidth() - paddle.width)) {
			paddle.x += 15;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT && paddle.x > 0) {
			paddle.x -= 15;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
