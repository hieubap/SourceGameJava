package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class PlayPanel extends JPanel {
	public Player play;
	private ArrayList<Gold> enemy;
	private Timer timer;
	private int time =100;
	private boolean endgame = false,wingame = false;
	private Key k;
	private PlayWindow window;
	
	public PlayPanel(PlayWindow pw) {
		window = pw;
		play = new Player(200, 0,this);
		k = new Key();
		timer = new Timer(1000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(time == 0) {
					timer.stop();
					endgame = true;
					return;
				}
					
				time --;
				repaint();
			}
		});
		timer.start();
		
		enemy = new ArrayList<Gold>();
		
		enemy.add(new Enemy(0, 160,10,12,this));
		enemy.add(new Enemy(30, 50,20,9, this));
		enemy.add(new Enemy(300, 300,30,9, this));
		enemy.add(new Enemy(100, 290,10,9, this));
		enemy.add(new Enemy(250, 250,10,9, this));
		enemy.add(new Gold(80, 220, 30,6, this));
		enemy.add(new Gold(280, 120, 30,6, this));
		enemy.add(new Gold(200, 230, 30,06, this));
		enemy.add(new Gold(340, 210, 30,06, this));
		
		
		
		setFocusable(true);
		addKeyListener(play);
		
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(Color.LIGHT_GRAY);
		play.DrawPLayer((Graphics2D) g.create());
		for(Gold e: enemy)
		e.paint((Graphics2D) g.create());
		
		g.drawString(""+ play.getcoin(), 20,30);
		g.drawString(""+time, 100, 30);
		if(enemy.isEmpty()) {
			wingame = true;
			endgame = true;
		}
		if(endgame) {
			addKeyListener(k);
			endGame(g);
			removeKeyListener(play);
		}
		
			
	}

	public ArrayList<Gold> getEnemy(){
		return enemy;
	}
	public void endGame(Graphics g) {
//		for(Gold e: enemy)
//			e.endGame();
		g.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 50));
		if(wingame) {
			g.drawString("YOU WIN", 50, 200);
			return;
		}
		
		g.drawString("GAME OVER", 50, 250);
		
	}
	public void winGame() {
		
	}
	
	class Key extends KeyAdapter {
		public boolean newgame = true;
		public void keyPressed(KeyEvent a) {
			
			if(a.getKeyCode()==' ') {
				if(newgame) {
				window.setVisible(false);
				new PlayWindow();
				newgame = false;
				}
				System.out.println(".......................");
				
			}
		}
	}
}
