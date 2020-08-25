package main;

import java.awt.Rectangle;

import javax.swing.JFrame;

public class PlayWindow extends JFrame{
	public static final int WIDTH = 400,
			HEIGHT = 400;
	public static Rectangle area = new Rectangle(0,0,WIDTH,HEIGHT);
	
	public PlayWindow() {
		setSize(WIDTH,HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(true);
		
		add(new PlayPanel(this));
		
		
		setVisible(true);
		
	}

	public static void main(String[] args) {
		new PlayWindow();

	}

}
