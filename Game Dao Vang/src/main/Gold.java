package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Gold {
	public int x,y,coin,speedpull;
	protected PlayPanel panel;
	protected Player play;
	public boolean rip = false;
	
	public Gold(int a,int b,int c,int pull,PlayPanel p) {
		x=a;
		y=b;
		coin = c;
		speedpull = pull;
		panel =p;
		play =p.play;
		
	}
	public Gold() {
		
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
