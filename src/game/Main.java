package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class Main {
	static int hardness;
	static GamePanel panel = null;
	public static void main(String[] args) {
		JFrame frame = new JFrame("Arkanoid Game");
		
		JFrame startScreen = new JFrame();
		JButton start = new JButton("Start");
		JButton easy = new JButton("Easy");
		JButton medium = new JButton("Medium");
		JButton hard = new JButton("Hard");
		JButton help = new JButton("Help");
		JButton exit = new JButton("Exit");
		
		JFrame helpScreen = new JFrame();
		helpScreen.setSize(200, 200);
		
		JLabel hardnesslbl = new JLabel();
		hardnesslbl.setText("Hardness: ");
		
		JTextArea helptext = new JTextArea(5,20);
		
		
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		
		helptext.setText("This is help text Change in line41");
		helpScreen.add(helptext);
		
		help.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				helpScreen.setVisible(true);
				
			}
		});
		
		startScreen.setLayout(new FlowLayout());
		start.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				startScreen.setVisible(false);
				frame.setVisible(true);
			}
		});
		
		easy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				hardness = 1;
				panel = new GamePanel(frame, startScreen, hardness);
				panel.setBackground(Color.decode("#34A2B9"));
				frame.getContentPane().add(panel);
				hardnesslbl.setText("Hardness: Easy");
			}
		});
		
		medium.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				hardness = 2;
				panel = new GamePanel(frame, startScreen, hardness);
				panel.setBackground(Color.decode("#34A2B9"));
				frame.getContentPane().add(panel);
				hardnesslbl.setText("Hardness: Medium");
			}
		});
		
		hard.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				hardness = 3;
				panel = new GamePanel(frame, startScreen, hardness);
				panel.setBackground(Color.decode("#34A2B9"));
				frame.getContentPane().add(panel);
				hardnesslbl.setText("Hardness: Hard");
			}
		});
		
		startScreen.getContentPane().add(start);
		startScreen.getContentPane().add(easy);
		startScreen.getContentPane().add(medium);
		startScreen.getContentPane().add(hard);
		startScreen.getContentPane().add(help);
		startScreen.getContentPane().add(exit);
		
		startScreen.add(hardnesslbl);
		
		startScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		startScreen.setSize(590, 600);
		startScreen.setResizable(false);
		startScreen.setVisible(true);
		
		
		//Game screen
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(490, 600);
		frame.setResizable(false);
		frame.setVisible(false);
	}
}
