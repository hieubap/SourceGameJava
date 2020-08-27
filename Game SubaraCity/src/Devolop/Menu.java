package Devolop;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import Primary.Picture;

@SuppressWarnings("serial")
public class Menu extends JPanel implements ActionListener { // not use
	private int y;
	private Picture picture;
	public Timer time;
	private Rectangle2D conti = new Rectangle2D.Float(20,650,300,100);
	private Game game;
	public mouse mouse;
	private JFrame frame;
	
	public Menu(Game game) {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(500, 0, 425, 730);
		
		frame.add(this);
		frame.setVisible(true);
		this.game = game;
		y=-500;
		picture = new Picture();
		mouse = new mouse();
		
		addMouseListener(mouse);
		
		time = new Timer(10,this);
		time.start();
		
	}
	public void paintComponent(Graphics graphics) {
		
		super.paintComponent(graphics);
		
		graphics.drawImage(picture.menu,0,y,425,650,null);
	}
	public void actionPerformed(ActionEvent ae) {
		timestop();
		y+=20;
		repaint();
	}
	private void timestop() {
		if(y>50) {
			time.stop();
			
		}
	}
	public void active() {
		time.start();
	}
	public boolean isDraw() {
		if(time.isRunning())
			return true;
		return false;
	}
	public void modeMenu() {
		frame.setVisible(true);
	}
	public class mouse extends MouseAdapter{
		public void mousePressed(MouseEvent mouse) {
			System.out.println("run");
			if(conti.contains(mouse.getX(),mouse.getY()))
			{
//				if(game.play==null)
//				game.play = new Play(game);
//				else
//				game.play.continuegame();
//				frame.dispose();
				
			}
		}
	}
}
