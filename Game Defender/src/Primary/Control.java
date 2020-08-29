package Primary;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

import Devolop.Setup;
import Devolop.Tutorial;
import Tower.Element;
import Tower.Tower;

@SuppressWarnings("serial")
public class Control extends JPanel implements ActionListener{
	private int init,limittower,coin,mapgame,life,level,k,t,check,score,up;
	private ArrayList<Tower> tower;
	private ArrayList<Enemy> ennemy;
	private Enemy cons;
	private Map map;
//	private Menu menu;
	private Setup menu;
	private Timer timer,time;
	private Rectangle2D backmenu;
	private boolean mn,mp,tutorial = false,upgrade,fusion=false;
	
	public void drawScreen(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();

		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(10, 18, 300, 20);
		g.fillRect(400, 500, 20, 20);
		
		g2d.setColor(Color.pink);
		g2d.fill(backmenu);
		g2d.setColor(Color.cyan);
		g2d.fillRect(50, 490, life*30, 20);
		
		g.setColor(Color.black);
		g.drawString("coin : "+String.valueOf(coin), 10, 30);
		g.drawString("score : "+String.valueOf(score), 80, 30);
		g.drawString(String.valueOf(life)+"/30", 450, 505);
		g.drawString("LIFE", 10, 505);
		g.drawString("BACKMENU", 610, 30);
		
	}
	public static void drawElement(Graphics g) {
		
		g.setColor(WindowGame.colorElement[0]);
		g.fillOval(800, 100, 50, 50);
		
		g.setColor(WindowGame.colorElement[1]);
		g.fillOval(800-(int)(80*(1+Math.cos(72*Math.PI/180))), 100+(int)(80*Math.sin(72*Math.PI/180)), 50, 50);
		g.drawLine(800-(int)(80*(1+Math.cos(72*Math.PI/180)))+25, 100+(int)(80*Math.sin(72*Math.PI/180))+25,800+25,100+25);
		
		g.setColor(WindowGame.colorElement[2]);
		g.fillOval(800-(int)(160*(1+Math.cos(72*Math.PI/180))*Math.cos(72*Math.PI/180)), 100+(int)(160*(1+Math.cos(72*Math.PI/180))*Math.sin(72*Math.PI/180)), 50, 50);
		g.drawLine(25+800-(int)(160*(1+Math.cos(72*Math.PI/180))*Math.cos(72*Math.PI/180)),25+100+(int)(160*(1+Math.cos(72*Math.PI/180))*Math.sin(72*Math.PI/180)),
				800-(int)(80*(1+Math.cos(72*Math.PI/180)))+25, 100+(int)(80*Math.sin(72*Math.PI/180))+25);
		
		g.setColor(WindowGame.colorElement[3]);
		g.fillOval(800+(int)(160*(1+Math.cos(72*Math.PI/180))*Math.cos(72*Math.PI/180)), 100+(int)(160*(1+Math.cos(72*Math.PI/180))*Math.sin(72*Math.PI/180)), 50, 50);
		g.drawLine(800+(int)(160*(1+Math.cos(72*Math.PI/180))*Math.cos(72*Math.PI/180))+25, 100+(int)(160*(1+Math.cos(72*Math.PI/180))*Math.sin(72*Math.PI/180))+25,
				800-(int)(160*(1+Math.cos(72*Math.PI/180))*Math.cos(72*Math.PI/180))+25, 100+(int)(160*(1+Math.cos(72*Math.PI/180))*Math.sin(72*Math.PI/180))+25);
		
		g.setColor(WindowGame.colorElement[4]);
		g.fillOval(800+(int)(80*(1+Math.cos(72*Math.PI/180))), 100+(int)(80*Math.sin(72*Math.PI/180)), 50, 50);
		g.drawLine(800+(int)(80*(1+Math.cos(72*Math.PI/180)))+25, 100+(int)(80*Math.sin(72*Math.PI/180))+25,
				800+(int)(160*(1+Math.cos(72*Math.PI/180))*Math.cos(72*Math.PI/180))+25, 100+(int)(160*(1+Math.cos(72*Math.PI/180))*Math.sin(72*Math.PI/180))+25);
		
		g.setColor(WindowGame.colorElement[0]);
		g.drawLine(800+25, 100+25, 800+25+(int)(80*(1+Math.cos(72*Math.PI/180))), 100+(int)(80*Math.sin(72*Math.PI/180))+25);
		
		g.setColor(Color.gray);
		g.drawOval(800, 100, 50, 50);
		g.drawOval(800-(int)(80*(1+Math.cos(72*Math.PI/180))), 100+(int)(80*Math.sin(72*Math.PI/180)), 50, 50);
		g.drawOval(800+(int)(80*(1+Math.cos(72*Math.PI/180))), 100+(int)(80*Math.sin(72*Math.PI/180)), 50, 50);
		g.drawOval(800-(int)(160*(1+Math.cos(72*Math.PI/180))*Math.cos(72*Math.PI/180)), 100+(int)(160*(1+Math.cos(72*Math.PI/180))*Math.sin(72*Math.PI/180)), 50, 50);
		g.drawOval(800+(int)(160*(1+Math.cos(72*Math.PI/180))*Math.cos(72*Math.PI/180)), 100+(int)(160*(1+Math.cos(72*Math.PI/180))*Math.sin(72*Math.PI/180)), 50, 50);
		
	}
	public Control() {
		mn=true;
		mp=false;
		mapgame=2;
		score=0;
		t=1000;
		menu= new Setup(this);
		check=20;
		Element[] el = {Element.Fire};
		cons = new Enemy(-100,-100,el);
		cons.init();
		
		setFocusable(true);
		addMouseListener(new mouse());
		addKeyListener(new key());
	}
	public void initAttack(int h) {
//		System.out.println("size lv 2:  " +cons.getAttack(h).size());
		for(int i=0;i<cons.getAttack(h).size();i++) {
			ennemy.add(cons.getEnemy(h, i));
			
		}
	}
	public void start(int m) {
		tower = new ArrayList<Tower>();
		coin=50;
		limittower=10;
		life=30;
		upgrade=false;
		up=0;
		level=1;
		
		map= new Map(m);
		ennemy = new ArrayList<Enemy>();
		
		initAttack(level);
		k=ennemy.size();
		
		timer = new Timer(check,this);
		time = new Timer(4000,new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				k--;
				if(k==0) {
					time.stop();
				}
				ennemy.get(k).appear();
			}
		});
		
		time.start();
		timer.start();
		
		backmenu = new Rectangle2D.Float(600, 10, 100, 30);
	}
	public void actionPerformed(ActionEvent ae) {
		if(mn||mp) return;
		
		
		for(int j=0;j<ennemy.size();j++)
		if(!ennemy.isEmpty()&&ennemy.get(j).getDead()) {
			coin+=ennemy.get(j).getGold();
			score+=ennemy.get(j).getGold()*10;
			ennemy.remove(j);
			
		}
		
		if(ennemy.isEmpty()) {
			initAttack(level++);
			k=ennemy.size();
			time.restart();
		}
		
		// ennemy walk
		for(int i=0;i<ennemy.size();i++) {
			if(ennemy.get(i).go(map.take(ennemy.get(i).getk()), map.take(ennemy.get(i).getk()+1)))
				if(ennemy.get(i).getk()!=map.getWalk()) ennemy.get(i).setk();
				else {
					ennemy.get(i).resetk();
					life--;
				}
		}
		// tower attack ennemy
		for(int i=0;i<tower.size();i++)
		{
			tower.get(i).getWeapon().loadEnegy();
			for(int j=ennemy.size()-1;j>-1;j--)
			{
//				System.out.println("j=" +j + " size = " + ennemy.size());
				if(tower.get(i).onRange(ennemy.get(j).getX(), ennemy.get(j).getY())&&tower.get(i).canFightElement(ennemy.get(j).getElement()))
					{// error 4 out 4   1 out 1
					if(tower.get(i).getWeapon().fullEnegy())
						{
						tower.get(i).getWeapon().fire(ennemy.get(j).getX(), ennemy.get(j).getY());
						ennemy.get(j).decreaseLife(tower.get(i).getDamage());
						tower.get(i).shot = true;
						repaint();
						}
					break;
				}
				else {
					tower.get(i).shot = false;
				}
			}
		}
		if(menu.up) {
			tower.add(new Tower(towerUpgrade.getX(), towerUpgrade.getY(), menu.element));
			menu.up = false;
			upgrade = false;
			fusion = false;
			
		}
		
		repaint();	
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		System.out.println("....");
		
		if(mn) {
			menu.paint(g);
			return;
		}
		else if(tutorial) {
			menu.drawback(g);
			Tutorial.draw(g);
			return;
		}
		else if(mp) {
			menu.paintmap(g);
			return;
		}
		map.paint(g,coin);
		drawScreen(g);
		
		for(int i=0;i<ennemy.size();i++) {
			ennemy.get(i).paint(g);
		}
		for(int i=0;i<tower.size();i++) {
			tower.get(i).draw(g);
//			for(int j=0;j<ennemy.size();j++)
//				if(!ennemy.isEmpty()&&tower.get(i).onRange(ennemy.get(j).getX(), ennemy.get(j).getY()))
			if(tower.get(i).shot)
			tower.get(i).getWeapon().paint(g);
			
		}
		if(upgrade) {
			
			if(fusion) {
				menu.drawFusion(g);
			}
			else
				menu.p_laze(g,tower.get(up));
			tower.get(up).drawRange(g);
		}
		else {
			drawElement(g);
		}
		if(menu.fusioning) {
			menu.paintUpTower(g);
		}
	}
	public Tower towerUpgrade;
	class mouse extends MouseAdapter{
		public void mousePressed(MouseEvent e) {
			int x=e.getX();
			int y=e.getY();
			if(tutorial) {
				if(menu.removecontain(x, y)) {
					tutorial = false;
					mn = true;
					repaint();
				}
				return;
			}
			if(mn) {
				if(menu.startcontain(x, y)) {
					start(mapgame);
					mn=false;
				}
				else if(menu.mapcontain(x, y)) {
					mp=true;
					mn=false;
					repaint();
				}
				else if(menu.tutorialcontain(x, y)) {
					tutorial = true;
					mn = false;
					repaint();
				}
				else if(menu.exitcontain(x, y)) {
					System.exit(0);
				}
			}
			else if(mp) {
				if(menu.map1contain(x, y)) {
					mapgame=1;
					mn=true;
					mp=false;
					repaint();
				}
				else if(menu.map2contain(x, y)) {
					mapgame=2;
					mn=true;
					mp=false;
					repaint();
				}
			}
			
			if(mp||mn||tutorial) return;
			
			if(x>=50&&y>=50&&map.checkarea((x-50)/31,(y-50)/31)
					&&map.getmap((y-50)/31,(x-50)/31)==2) {
				x=((x-50)/31)*31+65;
				y=((y-50)/31)*31+65;
				System.out.println("*********** active ****************");
				for(int i=0;i<tower.size();i++)
					if(tower.get(i).getX()==x&&tower.get(i).getY()==y) {
						if(upgrade && i!=up && fusion) {
							if( menu.sacrifice.size()>=tower.get(up).getLevelElement()+1)
								break;
							boolean add = true;
							for(Tower t1 : menu.sacrifice) {
								if(t1.color.getRGB() == tower.get(i).color.getRGB())
									{
									add = false;
									break;
									}
							}
							if(add)
								{
								menu.sacrifice.add(tower.get(i));
								if(menu.sacrifice.size()>= tower.get(up).getLevelElement()+1) {
									menu.canfusion = true;
								}
								}
							break;
						}
						else if(upgrade && fusion) {
							break;
						}
						
						if(!menu.sacrifice.isEmpty())
							{
							menu.sacrifice.removeAll(menu.sacrifice);
							System.out.println(" --- ");
							}
						up=i;
						upgrade=true;
						break;
					}
				return;
			}
			
			/// upgrade tower
			if(upgrade) 
			{
				if(!fusion)
			{
					if(menu.up1contain(x, y)&&coin>=tower.get(up).getup1()&&tower.get(up).getDamage()<tower.get(up).getMaxDamage()) {
						coin-=tower.get(up).getup1();
						tower.get(up).upDamage_Coin(20,50);
					}
					else if(menu.up2contain(x, y)&&coin>=tower.get(up).getup2()&&tower.get(up).getRange()<tower.get(up).getMaxRange()) {
						coin-=tower.get(up).getup2();
						tower.get(up).upRange_Coin(20,10);
					}
					else if(menu.up3contain(x, y)&&coin>=tower.get(up).getup3()&&tower.get(up).getSpeed()>tower.get(up).getMaxSpeed()
							&&tower.get(up).getSpeed()>0) {
						coin-=tower.get(up).getup3();
						tower.get(up).upSpeed_Coin(1,10);
					}
					else if(menu.up4contain(x, y)) {
						coin+=tower.get(up).getSell();
						map.setmap((tower.get(up).getY()-50)/31, (tower.get(up).getX()-50)/31,1);
						tower.remove(up);
						upgrade=false;
					}
					else if(menu.fusionmodecontain(x, y)) {
						fusion = true;
						if(menu.sacrifice.isEmpty())
						menu.sacrifice.add(tower.get(up));
					}
					else {
						upgrade =false;
						fusion = false;
						menu.sacrifice.removeAll(tower);
					}
			}
			else {
				if(menu.backcontain(x, y)) {
					fusion = false;
				}
				else if(menu.removecontain(x, y)) {
					menu.sacrifice.remove(menu.sacrifice.size()-1);
					if(menu.sacrifice.size()<tower.get(up).getLevelElement()+1) {
						menu.canfusion = false;
					}
				}
				else if(menu.fusioncontain(x, y)&&menu.canfusion) {
					System.out.println("run here");
					menu.startUp(tower.get(up).getX(), tower.get(up).getY());
					towerUpgrade = new Tower(tower.get(up).getX(), tower.get(up).getY(), tower.get(up).elementn);
					tower.removeAll(menu.sacrifice);
					menu.fusioning = true;
				}
			}
		}
			///  add  tower   
			//  set element for tower here
			if(tower.size()==limittower) {}
			else if(map.t1contain(x, y)&&coin>9) {
				init=1;
				
			}
			else if(map.t2contain(x, y)&&coin>10) {
				init=2;
				
			}
			else if(map.t3contain(x, y)&&coin>9) {
				init=3;
				
			}
			else if(map.t4contain(x, y)&&coin>10) {
				init=4;
				
			}
			else if(map.t5contain(x, y)&&coin>9) {
				init=5;
				
			}
			
			if(backmenu.contains(x, y)) {
				timer.stop();
				mn=true;
				mp=false;
			}
			
			
			repaint();
			
		}
		public void mouseReleased(MouseEvent e) {
			if(mn||mp||tutorial) return;
			
			int x=e.getX();
			int y=e.getY();
			
			if(x>=50&&y>=50&&map.checkarea((x-50)/31,(y-50)/31)
					&&map.getmap((y-50)/31,(x-50)/31)==1) {
				x=((x-50)/31)*31+65;
				y=((y-50)/31)*31+65;
			
				switch(init) {
				case 5: {
					Element[] el = {Element.Wind};
					tower.add(new Tower(x,y,el));
					map.setmap((y-50)/31, (x-50)/31, 2);
					coin-=30;
					break;
				}	
				case 3: {
					Element[] el = {Element.Earth};
					tower.add(new Tower(x,y,el));
					map.setmap((y-50)/31, (x-50)/31, 2);
					coin-=10;
					break;
				}
				case 4: {
						Element[] el = {Element.Thunder}; 
						tower.add(new Tower(x,y,el));
						map.setmap((y-50)/31, (x-50)/31, 2);
						coin-=10;
						break;
					}
				case 2: {
						Element[] el = {Element.Water};
						tower.add(new Tower(x,y,el));
						map.setmap((y-50)/31, (x-50)/31, 2);
						coin-=5;
						break;
					}
				case 1: {
						Element[] el = {Element.Fire};
						tower.add(new Tower(x,y,el));
						map.setmap((y-50)/31, (x-50)/31, 2);
						coin-=20;
						break;
					}
				}
				if(init!=0) {
					upgrade =false;
					fusion = false;
					menu.sacrifice.removeAll(menu.sacrifice);
				}
			}
			init=0;
			repaint();
			
//			System.out.println("--------run drag "+String.valueOf(e.getX())+", "+ String.valueOf(e.getY()));
//			System.out.println("limittower: "+limittower);
			System.out.println("tower length = " + tower.size());
	}
	}
	class key extends KeyAdapter{
		public void keyPressed(KeyEvent p) {
			System.out.println("init key");
			if(mn||mp) return;
			int c= p.getKeyCode();
			
			if(c=='Q') System.exit(0);
			if(c=='R') timer.restart();
			if(c=='P') timer.stop();
			
//			if(c=='Z') {
//				ennemy.add(new Enemy(75,97,1));
//				ennemy.get(ennemy.size()-1).appear();
//			}
//			if(c=='X') {
//				ennemy.add(new Enemy(75,97,2));
//				ennemy.get(ennemy.size()-1).appear();
//			}
//			if(c=='C') {
//				ennemy.add(new Enemy(75,97,3));
//				ennemy.get(ennemy.size()-1).appear();
//			}
//			if(c=='V') {
//				ennemy.add(new Enemy(75,97,4));
//				ennemy.get(ennemy.size()-1).appear();
//			}
//			if(c=='B') {
//				ennemy.add(new Enemy(75,97,5));
//				ennemy.get(ennemy.size()-1).appear();
//			}
//			if(c=='N') {
//				ennemy.add(new Enemy(75,97,6));
//				ennemy.get(ennemy.size()-1).appear();
//			}
			
			if(c=='D') coin+=5000;
			
			
			if(c=='A') limittower++;
			if(c==' ') {
				upgrade=false;
				tower.clear();
			}
			
			repaint();
		}
	}
}
