package MainPK;

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

@SuppressWarnings("serial")
public class Player extends JPanel implements ActionListener{
	private int init,limittower,coin,mapgame,life,level,k,t,check,score,up;
	private ArrayList<Tower> tower;
	private ArrayList<Enemy> ennemy;
	private Enemy cons;
	private Map map;
	private Menu menu;
	private Timer timer,time;
	private Rectangle2D backmenu;
	private boolean mn,mp,upgrade;
	
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();

		g.setColor(Color.white);
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
		
		//g.drawLine(20, 500, 600, 420);
		
	}
	public Player() {
		mn=true;
		mp=false;
		mapgame=2;
		score=0;
		t=1000;
		menu= new Menu();
		check=40;
		cons = new Enemy(-100,-100,1);
		cons.init();
		
		setFocusable(true);
		addMouseListener(new mouse());
		addKeyListener(new key());
	}
	public void initAttack(int h) {
		System.out.println("size lv 2:  " +cons.getAttack(h).size());
		for(int i=0;i<cons.getAttack(h).size();i++) {
			System.out.print("******run   ");
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
		System.out.println("------------ k=  "+k);
		
		time = new Timer(100,new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(k==0) time.stop();
				k--;
				ennemy.get(k).appear();
				System.out.println("---------------------------------------"+ennemy.size());
				
			}
		});
		
		time.start();
		timer.start();
		
		backmenu = new Rectangle2D.Float(600, 10, 100, 30);
	}
	public void actionPerformed(ActionEvent ae) {
		System.out.println("----run action");
		if(mn||mp) return;
		
		
		for(int j=0;j<ennemy.size();j++)
		if(!ennemy.isEmpty()&&ennemy.get(j).getDead()) {
			score+=ennemy.get(j).getGold();
			ennemy.remove(j);
			
		}
		
		if(ennemy.isEmpty()) {
			initAttack(level++);
			k=ennemy.size();
			time.restart();
		}
		
		// tower attack ennemy
		for(int i=0;i<tower.size();i++)
			switch(tower.get(i).getType()) {
			case 1:
				if(tower.get(i).addcoin()) coin+=tower.get(i).getDamage();
				break;
			case 2:
				for(int j=ennemy.size()-1;j>-1;j--)
				if(tower.get(i).is_shot(ennemy.get(j).getX(), ennemy.get(j).getY())) {
					if(tower.get(i).getShot().fire(ennemy.get(j).getX(), ennemy.get(j).getY())==1)
					ennemy.get(j).setLife(tower.get(i).getDamage());
					break;
				}
				break;
			case 3:
				for(int j=ennemy.size()-1;j>-1;j--)
					if(tower.get(i).is_shot(ennemy.get(j).getX(), ennemy.get(j).getY())) {
						ennemy.get(j).stop();
						break;
				}
				break;
			case 4:
				for(int j=ennemy.size()-1;j>-1;j--)
					if(tower.get(i).is_shot(ennemy.get(j).getX(), ennemy.get(j).getY())) {
						if(tower.get(i).getLaze().launch(ennemy.get(j).getX(), ennemy.get(j).getY()))
						ennemy.get(j).setLife(tower.get(i).getDamage());
						break;
				}
				break;
			case 5:
				for(int j=ennemy.size()-1;j>-1;j--)
					if(tower.get(i).space()&&
							tower.get(i).is_shot(ennemy.get(j).getX(), ennemy.get(j).getY())) {
						tower.get(i).loadspace();
						ennemy.get(j).setLife(tower.get(i).getDamage());
						break;
				}
				break;
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
		
		repaint();
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(mn) {
			menu.paint(g);
			return;
		}
		else if(mp) {
			menu.paintmap(g);
			return;
		}
		//menu.p_gun(g);
		System.out.println("upgrade: "+ upgrade);
		if(upgrade) {
			switch(tower.get(up).getType()) {
			case 1:
				menu.p_coin(g,
						tower.get(up).getup1(),tower.get(up).getup3(),
						tower.get(up).getDamage(),tower.get(up).getspeed(),
						tower.get(up).getSell());
				break;
			case 2:
				menu.p_gun(g,
						tower.get(up).getup1(),tower.get(up).getup2(),tower.get(up).getup3(),
						tower.get(up).getDamage(),tower.get(up).gettamban(),tower.get(up).getspeed(),
						tower.get(up).getSell());
				break;
			case 3:
				menu.p_ice(g,
						tower.get(up).getup1(),tower.get(up).getup2(),tower.get(up).getup3(),
						tower.get(up).getDamage(),tower.get(up).gettamban(),tower.get(up).getspeed(),
						tower.get(up).getSell());
				break;
			case 4:
				menu.p_laze(g,
						tower.get(up).getup1(),tower.get(up).getup2(),tower.get(up).getup3(),
						tower.get(up).getDamage(),tower.get(up).gettamban(),tower.get(up).getspeed(),
						tower.get(up).getSell());
				break;
			case 5:
				menu.p_space(g);
				break;
			}
		}
		map.paint(g,coin);
		draw(g);
		
		for(int i=0;i<ennemy.size();i++) {
			ennemy.get(i).paint(g);
		}
		for(int i=0;i<tower.size();i++) {
			tower.get(i).draw(g);
			switch(tower.get(i).getType()) {
			case 1:
				g.setColor(Color.black);
				tower.get(i).painttime(g, tower.get(i).getsupport());break;
			case 3:
			case 5: break;
			case 2:
				for(int j=0;j<ennemy.size();j++)
				if(!ennemy.isEmpty()&&tower.get(i).is_shot(ennemy.get(j).getX(), ennemy.get(j).getY()))
					tower.get(i).getShot().paint(g);
				break;
			case 4:
				for(int j=0;j<ennemy.size();j++)
				if(!ennemy.isEmpty()&&tower.get(i).is_shot(ennemy.get(j).getX(), ennemy.get(j).getY()))
					tower.get(i).getLaze().paint(g);
				break;
		}
		}
		System.out.println("dang chay paint");
	}
	class mouse extends MouseAdapter{
		public void mousePressed(MouseEvent e) {
			int x=e.getX();
			int y=e.getY();
			
			//System.out.println("map:"+map.getmap(a, b));
			
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
			
			if(mp||mn) return;
			
			if(x>=50&&y>=50&&map.checkarea((x-50)/31,(y-50)/31)
					&&map.getmap((y-50)/31,(x-50)/31)==2) {
				x=((x-50)/31)*31+65;
				y=((y-50)/31)*31+65;
				System.out.println("***active ****************");
				for(int i=0;i<tower.size();i++)
					if(tower.get(i).getX()==x&&tower.get(i).getY()==y) {
						up=i;
						upgrade=true;
						break;
					}
				return;
			}
			/// upgrade tower
			if(upgrade) {
				switch(tower.get(up).getType()) {
				case 1:
					if(menu.up1contain(x, y)&&coin>=tower.get(up).getup1()) {
						coin-=tower.get(up).getup1();
						tower.get(up).updamage_coin(1,5);
					}
					else if(menu.up2contain(x, y)&&coin>=tower.get(up).getup3()) {
						coin-=tower.get(up).getup3();
						tower.get(up).upspeed_coin(-1,5);
					}
					else if(menu.up4contain(x, y)) {
						coin+=tower.get(up).getSell();
						map.setmap((tower.get(up).getY()-50)/31, (tower.get(up).getX()-50)/31,1);
						tower.remove(up);
						upgrade=false;
					}
					
					break;
				case 2:
					if(menu.up1contain(x, y)&&coin>=tower.get(up).getup1()) { 
						coin-=tower.get(up).getup1();
						tower.get(up).updamage_coin(1,5);
					}
					else if(menu.up2contain(x, y)&&coin>=tower.get(up).getup2()) {
						coin-=tower.get(up).getup2();
						tower.get(up).uptamban_coin(10,5);
					}
					else if(menu.up3contain(x, y)&&coin>=tower.get(up).getup3()) { 
						coin-=tower.get(up).getup3();
						tower.get(up).upspeedshot_coin(1,5);
					}
					else if(menu.up4contain(x, y)) {
						coin+=tower.get(up).getSell();
						map.setmap((tower.get(up).getY()-50)/31, (tower.get(up).getX()-50)/31,1);
						tower.remove(up);
						upgrade=false;
					}
					
					break;
				case 3:
					if(menu.up1contain(x, y)) tower.get(up).updamage_coin(1,5);
					else if(menu.up2contain(x, y)) tower.get(up).uptamban_coin(10,5);
					else if(menu.up3contain(x, y)) tower.get(up).upspeed_coin(1,5);
					else if(menu.up4contain(x, y)) {
						coin+=tower.get(up).getSell();
						map.setmap((tower.get(up).getY()-50)/31, (tower.get(up).getX()-50)/31,1);
						tower.remove(up);
						upgrade=false;
					}
					
					break;
				case 4:
					if(menu.up1contain(x, y)&&coin>=tower.get(up).getup1()) {
						coin-=tower.get(up).getup1();
						tower.get(up).updamage_coin(50,50);
					}
					else if(menu.up2contain(x, y)&&coin>=tower.get(up).getup2()) {
						coin-=tower.get(up).getup2();
						tower.get(up).uptamban_coin(10,10);
					}
					else if(menu.up3contain(x, y)&&coin>=tower.get(up).getup3()
							&&tower.get(up).getspeed()>0) {
						coin-=tower.get(up).getup3();
						tower.get(up).upspeedlaze_coin(1,10);
					}
					else if(menu.up4contain(x, y)) {
						coin+=tower.get(up).getSell();
						map.setmap((tower.get(up).getY()-50)/31, (tower.get(up).getX()-50)/31,1);
						tower.remove(up);
						upgrade=false;
					}
					
					break;
				case 5:
					if(menu.up1contain(x, y)&&coin>=tower.get(up).getup1()) { 
						coin-=tower.get(up).getup1();
						tower.get(up).updamage_coin(1,5);
					}
					else if(menu.up2contain(x, y)&&coin>=tower.get(up).getup2()) { 
						coin-=tower.get(up).getup2();
						tower.get(up).uptamban_coin(10,5);
					}
					else if(menu.up3contain(x, y)&&coin>=tower.get(up).getup3()) { 
						coin-=tower.get(up).getup3();
						tower.get(up).upspeedshot_coin(1,5);
					}
					else if(menu.up4contain(x, y)) {
						coin+=tower.get(up).getSell();
						map.setmap((tower.get(up).getY()-50)/31, (tower.get(up).getX()-50)/31,1);
						tower.remove(up);
						upgrade=false;
					}
					
					break;
				
				}
			}
			
			///  add  tower   
			//  set cost for tower here
			if(tower.size()==limittower) {}
			else if(map.t1contain(x, y)&&coin>9) {
				init=1;
				
			}
			else if(map.t2contain(x, y)&&coin>4) {
				init=2;
				
			}
			else if(map.t3contain(x, y)&&coin>9) {
				init=3;
				
			}
			else if(map.t4contain(x, y)&&coin>19) {
				init=4;
				
			}
			else if(map.t5contain(x, y)&&coin>29) {
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
			if(mn||mp) return;
			
			int x=e.getX();
			int y=e.getY();
			
			if(x>=50&&y>=50&&map.checkarea((x-50)/31,(y-50)/31)
					&&map.getmap((y-50)/31,(x-50)/31)==1) {
				x=((x-50)/31)*31+65;
				y=((y-50)/31)*31+65;
			
				switch(init) {
					case 1: {
						tower.add(new Tower(x,y,1));
						map.setmap((y-50)/31, (x-50)/31, 2);
						coin-=10;
						break;
					}
					case 2: {
						tower.add(new Tower(x,y,2));
						map.setmap((y-50)/31, (x-50)/31, 2);
						coin-=5;
						break;
					}
					case 3: {
						tower.add(new Tower(x,y,3));
						map.setmap((y-50)/31, (x-50)/31, 2);
						coin-=10;
						break;
					}
					case 4: {
						tower.add(new Tower(x,y,4));
						map.setmap((y-50)/31, (x-50)/31, 2);
						coin-=20;
						break;
					}
					case 5: {
						tower.add(new Tower(x,y,5));
						map.setmap((y-50)/31, (x-50)/31, 2);
						coin-=30;
						break;
					}
				}
			}
			init=0;
			repaint();
			
			System.out.println("--------run drag "+String.valueOf(e.getX())+", "+ String.valueOf(e.getY()));
			System.out.println("limittower: "+limittower);
			
			System.out.println("limittower: "+limittower);
			System.out.println("");
			
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
			
			if(c=='Z') {
				ennemy.add(new Enemy(75,97,1));
				ennemy.get(ennemy.size()-1).appear();
			}
			if(c=='X') {
				ennemy.add(new Enemy(75,97,2));
				ennemy.get(ennemy.size()-1).appear();
			}
			if(c=='C') {
				ennemy.add(new Enemy(75,97,3));
				ennemy.get(ennemy.size()-1).appear();
			}
			if(c=='V') {
				ennemy.add(new Enemy(75,97,4));
				ennemy.get(ennemy.size()-1).appear();
			}
			if(c=='B') {
				ennemy.add(new Enemy(75,97,5));
				ennemy.get(ennemy.size()-1).appear();
			}
			if(c=='N') {
				ennemy.add(new Enemy(75,97,6));
				ennemy.get(ennemy.size()-1).appear();
			}
			
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
