package Primary;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Control extends JPanel {
	public static final int WIDTH = 400,
			HEIGHT = 400;
	public static Rectangle area = new Rectangle(0,0,WIDTH,HEIGHT);
	public Player play;
	private ArrayList<Gold> enemy;
	private Timer timer;
	private int time =100,total=0;
	private boolean endgame = false,wingame = false;
	private Key k;
//	private PlayWindow window;
	private JFrame frame;
	public Control() {
		
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
		newgame();
		
		
		
		setFocusable(true);
		addKeyListener(play);
		
		frame = new JFrame("DAO VANG");
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.add(this);
		frame.setVisible(true);
	}
	public static void main(String[] args) {
		new Control();
	}
	public void newgame() {
		enemy = new ArrayList<Gold>();
		total = 0;
		time = 100;
		
		for(int i=0;i<20;i++)
			enemy.add(new Gold(this));
		for(int i=0;i<10;i++)
			enemy.add(new Enemy(this));
		
		for(Gold g: enemy) {
			total += g.coin;
		}
		endgame = false;
		wingame = false;
		
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
			if(play.getcoin()>total/2)
			{
				wingame = true;
				endgame = true;
			}
			else
			{
			addKeyListener(k);
			endGame(g);
			removeKeyListener(play);
			}
		}
		
			
	}

	public ArrayList<Gold> getEnemy(){
		return enemy;
	}
	public void endGame(Graphics g) {
//		for(Gold e: enemy)
//			e.endGame();
		g.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
		g.drawString("press space to play new game", 50, 200);
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
//				window.setVisible(false);
//				new PlayWindow();
				
					removeKeyListener(k);
					addKeyListener(play);
					newgame();
					newgame = false;
				}
//				System.out.println("");
				
			}
		}
	}
}
