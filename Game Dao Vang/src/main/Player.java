package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;

public class Player implements KeyListener{
	public double phi,g = 0,per = Math.PI/625;
	private boolean startthrow = false,onetarget = false;
	private Timer time1,time2;
	private int x,y,wirelength = 35,perlength = 5,coins = 0;
	PlayPanel play;
	
	
	public Player(int a,int b,PlayPanel p) {
		phi = per;
		play = p;
		x=a;
		y=b;
		
		
		time1 = new Timer(40, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Math.cos(phi)>0 && per < 0) {
					per = -per;
				}
				else if(Math.cos(phi)<0 && per > 0) {
					per = -per;
				}
				
				g += per;
				phi += g;
				
				play.repaint();
			}
		});
		time1.start();
		time2 = new Timer(50, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(wirelength < 30 ) {
					wirelength = 30;
					perlength = 3;
					time1.start();
					time2.stop();
					startthrow = false;
					onetarget = false;

					for(int i = 0;i< play.getEnemy().size();i++)
					if(play.getEnemy().get(i).rip)
						{
						coins += play.getEnemy().get(i).coin;
						play.getEnemy().remove(i);
						}
				}
				else if( 
				!PlayWindow.area.contains(200 + (int) (wirelength*Math.cos(phi)),10 + (int) (wirelength*Math.sin(phi)))) {
					perlength = -3;
					onetarget = true;
				}
				for(int i = 0; i< play.getEnemy().size();i++)
				{
					if(play.getEnemy().get(i).contain(gethookX(), gethookY())&& !onetarget) {
					play.getEnemy().get(i).rip();
					onetarget = true;
					perlength = -play.getEnemy().get(i).speedpull;
					}
				
				}
				wirelength+=perlength;
				play.repaint();
//				System.out.println("run time 2");
			}
		});
		
	}
	public void DrawPLayer(Graphics g) {
//		System.out.println(phi);
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.translate(200, 10);
		g2d.rotate(phi);
		
		g2d.setColor(Color.red);
		
		g2d.fillRect(0, -15, 50, 30);
		if(startthrow) {
			paintStart(g);
		}
		
	}
	public void paintStart(Graphics g) {
		g.drawLine(200, 10,   200 + (int) (wirelength*Math.cos(phi)),10 + (int) (wirelength*Math.sin(phi)));
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == ' ') {
			startthrow = true;
			time1.stop();
			time2.start();
			System.out.println("key active +++++++++++++++++++++++++++");
		}
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
	public int gethookX() {
		return 200+(int)(wirelength*Math.cos(phi));
	}
	public int gethookY() {
		return 10+(int)(wirelength*Math.sin(phi));
	}
	public int getcoin() {
		return coins;
	}

	
}
