package Primary;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Enemy extends Gold {
	private int speed = 2;
	private Timer timer1;
	
	public Enemy(Control p) {
		super(p);
		timer1 = new Timer(50, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				x += speed;
				if(!Control.area.contains(x,y)) {
					speed = -speed;
					panel.repaint();
				}
			}
		});
		timer1.start();
		
	}
	
	public Enemy(int a,int b,int c,int d,Control p) {
		this.x= a;
		this.y = b;
		coin =c;
		speedpull =d;
		panel = p;
		play = p.play;
		timer1 = new Timer(50, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				x += speed;
				if(!Control.area.contains(x,y)) {
					speed = -speed;
					panel.repaint();
				}
			}
		});
		timer1.start();
		
	}
	
	
	public void paint(Graphics2D g) {
		g.setColor(Color.GREEN);
		if(rip) {
			x = play.gethookX();
			y = play.gethookY();
			g.translate(x, y);
			g.rotate(play.phi);
			g.fillRect(-30, -20, 60, 40);
			return;
		}
		
		g.fillRect(x, y, 60, 40);
	}
	@Override
	public void endGame() {
		timer1.stop();
	}
	
}
