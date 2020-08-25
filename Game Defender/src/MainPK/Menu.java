package MainPK;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Menu extends JPanel{
	private Rectangle2D start,map,exit,map1,map2,up1,up2,up3,up4;
	
	public Menu() {
		start= new Rectangle2D.Float(200, 100, 200, 100);
		map= new Rectangle2D.Float(200, 210, 200, 100);
		exit= new Rectangle2D.Float(200, 320, 200, 100);
		map1= new Rectangle2D.Float(200, 100, 200, 100);
		map2= new Rectangle2D.Float(200, 250, 200, 100);
		up1= new Rectangle2D.Float(800, 50, 150, 50);
		up2= new Rectangle2D.Float(810, 110, 150, 50);
		up3= new Rectangle2D.Float(820, 170, 150, 50);
		up4= new Rectangle2D.Float(730, 230, 185, 50);
		
	}
	
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setColor(Color.LIGHT_GRAY);
		g2d.fill(start);
		g2d.fill(map);
		g2d.fill(exit);
		g.setColor(Color.black);
		g.drawString("START", 280, 160);
		g.drawString("MAP", 280, 270);
		g.drawString("EXIT", 280, 380);
		
	}
	public void paintmap(Graphics g) {
		Graphics2D g2d= (Graphics2D) g.create();
		
		g2d.setColor(Color.LIGHT_GRAY);
		g2d.fill(map1);
		g2d.fill(map2);
		
		g.setColor(Color.black);
		g.drawString("MAP 1", 280, 150);
		g.drawString("MAP 2", 280, 300);
		
		
	}
	public boolean startcontain(int a,int b) {
		if(start.contains(a, b)) return true;
		return false;
	}
	public boolean mapcontain(int a,int b) {
		if(map.contains(a, b)) return true;
		return false;
	}
	public boolean exitcontain(int a,int b) {
		if(exit.contains(a, b)) return true;
		return false;
	}
	public boolean map1contain(int a,int b) {
		if(map1.contains(a, b)) return true;
		return false;
	}
	public boolean map2contain(int a,int b) {
		if(map2.contains(a, b)) return true;
		return false;
	}
	
	
	public boolean up1contain(int a,int b) {
		if(up1.contains(a, b)) return true;
		return false;
	}
	public boolean up2contain(int a,int b) {
		if(up2.contains(a, b)) return true;
		return false;
	}
	public boolean up3contain(int a,int b) {
		if(up3.contains(a, b)) return true;
		return false;
	}
	public boolean up4contain(int a,int b) {
		if(up4.contains(a, b)) return true;
		return false;
	}
	public void p_coin(Graphics g,int a,int b,int c,int d,int e) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setColor(Color.darkGray);
		g2d.fill(up1);
		g2d.fill(up2);
		g2d.fill(up4);
		g2d.setColor(Color.gray);
		g2d.fillRect(730, 50, 65, 50);
		g2d.fillRect(730, 110, 75, 50);
		g2d.fillRect(920, 230, 60, 50);
		
		g2d.fillRect(50, 520, 400, 50);
		
		g.setColor(Color.white);
		g.drawString("coin: "+String.valueOf(a), 740, 80);
		g.drawString("coin: "+String.valueOf(b), 740, 140);
		g.drawString(String.valueOf(e)+" coin", 930, 260);
		g.drawString("UPGRADE COIN", 820,80);
		g.drawString("UPGRADE SPEED", 830,140);
		g.drawString("SELL", 750,260);
		g.setColor(Color.BLACK);
		g.drawString("Coin/s:  "+String.valueOf(c), 70, 550);
		g.drawString("Speed:  "+String.valueOf(d), 370, 550);
		
	}
	public void p_gun(Graphics g,int a,int b,int c,int d,int e,int f,int h) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setColor(Color.darkGray);
		g2d.fill(up1);
		g2d.fill(up2);
		g2d.fill(up3);
		g2d.fill(up4);
		
		g2d.setColor(Color.gray);
		g2d.fillRect(730, 50, 65, 50);
		g2d.fillRect(730, 110, 75, 50);
		g2d.fillRect(730, 170, 85, 50);
		g2d.fillRect(920, 230, 60, 50);
		
		g2d.fillRect(50, 520, 400, 50);
		
		g.setColor(Color.white);
		g.drawString("coin: "+String.valueOf(a), 740, 80);
		g.drawString("coin: "+String.valueOf(b), 740, 140);
		g.drawString("coin: "+String.valueOf(c), 740, 200);
		g.drawString(String.valueOf(h)+" coin", 930, 260);
		g.drawString("UPGRADE DAMAGE", 820,80);
		g.drawString("UPGRADE RANGE", 830,140);
		g.drawString("UPGRADE SPEED", 840,200);
		g.drawString("SELL", 750,260);
		
		g.setColor(Color.BLACK);
		g.drawString("Damage:  "+String.valueOf(d), 70, 550);
		g.drawString("Range:  "+String.valueOf(e), 220, 550);
		g.drawString("Speed:  "+String.valueOf(f), 370, 550);
		
	}
	public void p_ice(Graphics g,int a,int b,int c,int d,int e,int f,int h) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setColor(Color.darkGray);
		g2d.fill(up1);
		g2d.fill(up2);
		g2d.fill(up3);
		g2d.fill(up4);
		g2d.setColor(Color.gray);
		g2d.fillRect(730, 50, 65, 50);
		g2d.fillRect(730, 110, 75, 50);
		g2d.fillRect(730, 170, 85, 50);
		g2d.fillRect(920, 230, 60, 50);
		
		g2d.fillRect(50, 520, 400, 50);
		
		g.setColor(Color.white);
		g.drawString("coin: "+String.valueOf(a), 740, 80);
		g.drawString("coin: "+String.valueOf(b), 740, 140);
		g.drawString("coin: "+String.valueOf(c), 740, 200);
		g.drawString(String.valueOf(h)+" coin", 930, 260);
		g.drawString("UPGRADE ENEMY", 820,80);
		g.drawString("UPGRADE RANGE", 830,140);
		g.drawString("UPGRADE SPEED", 840,200);
		g.drawString("SELL", 750,260);
		g.setColor(Color.BLACK);
		g.drawString("Enemy:  "+String.valueOf(d), 70, 550);
		g.drawString("Range:  "+String.valueOf(e), 220, 550);
		g.drawString("Speed:  "+String.valueOf(f), 370, 550);
		
	}
	public void p_laze(Graphics g,int a,int b,int c,int d,int e,int f,int h) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setColor(Color.darkGray);
		g2d.fill(up1);
		g2d.fill(up2);
		g2d.fill(up3);
		g2d.fill(up4);
		g2d.setColor(Color.gray);
		g2d.fillRect(730, 50, 65, 50);
		g2d.fillRect(730, 110, 75, 50);
		g2d.fillRect(730, 170, 85, 50);
		g2d.fillRect(920, 230, 60, 50);
		
		g2d.fillRect(50, 520, 400, 50);
		
		g.setColor(Color.white);
		g.drawString("coin: "+String.valueOf(a), 740, 80);
		g.drawString("coin: "+String.valueOf(b), 740, 140);
		g.drawString("coin: "+String.valueOf(c), 740, 200);
		g.drawString(String.valueOf(h)+" coin", 930, 260);
		g.drawString("UPGRADE DAMAGE", 820,80);
		g.drawString("UPGRADE RANGE", 830,140);
		g.drawString("UPGRADE SPEED", 840,200);
		g.drawString("SELL", 750,260);
		g.setColor(Color.BLACK);
		g.drawString("Damage:  "+String.valueOf(d), 70, 550);
		g.drawString("Range:  "+String.valueOf(e), 220, 550);
		g.drawString("Speed:  "+String.valueOf(f), 370, 550);
		
		if(f==0) {
			g2d.fillRect(730, 170, 240, 50);
			g.setColor(Color.white);
			g.drawString("MAX", 840, 200);
			
		}
	}
	public void p_space(Graphics g) {
		
	}
	
}
