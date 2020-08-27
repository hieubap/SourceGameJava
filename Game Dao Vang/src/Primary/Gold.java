package Primary;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Gold {
	public int x,y,w,h,coin,speedpull;
	protected Control panel;
	protected Player play;
	public boolean rip = false;
	
	public Gold(int a,int b,int c,int pull,Control p) {
		x=a;
		y=b;
		coin = c;
		speedpull = pull;
		panel =p;
		play =p.play;
		
	}
	public Gold() {
		
	}
	public Gold(Control p) {
		panel =p;
		play =p.play;
		
		x = (int)(Math.random()*(Control.WIDTH-100));
		y = 50+(int)(Math.random()*(Control.HEIGHT-100));
		w = (int)(Math.random()*100);
		h = (int)(Math.random()*100);
		coin = (x+y)/5;
		speedpull = 2000/(x+y);
	}
	public void paint(Graphics2D g) {
		g.setColor(Color.yellow);
		if(rip) {
			x = play.gethookX();
			y = play.gethookY();
			g.translate(x, y);
			g.rotate(play.phi);
			g.fillRect(-15, -15, 30, 30);
			return;
		}
		
		
		g.fillRect(x, y, 30, 30);
	}
	public boolean contain(int a,int b) {
		Rectangle rect = new Rectangle(x,y,50,50);
		if(rect.contains(a,b))
			return true;
		return false;
	}
	public void rip() {
		rip = true;
	}
	public void endGame() {
		
	}
}
