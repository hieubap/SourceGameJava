package MainPackage;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Play extends JPanel implements ActionListener{
	private Rectangle2D yyes,nno;
	private final int PIXEL = 80,
			PADDINGTOP=115,
			PADDINGLEFT=5;
	private JButton yes,no,y,n;
	private int delnum,targetX,targetY,year,population,timemerge,numtile;
	private int[][] map,lv;
	private boolean sb,gameover,eraser,joinblock;
	private boolean[][] merge;
	private Random rd;
	private Picture p;
	private ArrayList<Integer> xa,ya,dx,dy,dz;
	public mouse mo;
	private Timer time1,time2;
	public boolean pause =false;
//	private Menu mn;
	private Game game;
	private JFrame frame;
	private Button menu;
	
	public void actionPerformed(ActionEvent ae) {
		
		
		timemerge++;
		if(!xa.isEmpty())
		for(int i=0;i<xa.size();i++)
		{
			xa.set(i, xa.get(i)+dx.get(i));
			ya.set(i, ya.get(i)+dy.get(i));
		}
		time1stop();
		repaint();
	}
	
	public Play(Game game) {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(500, 0, 425, 730);
		
		frame.add(this);
		frame.setVisible(true);
		this.game = game;
		System.out.println("run");
		map= new int[5][5];
		lv=	 new int[5][5];
		merge= new boolean[5][5];
		rd=new Random();
		p = new Picture();
		//mn = new Menu();
		
		sb=true;
		gameover=false;
		delnum=2;
		year=2000;
		numtile=3;
		eraser=false;
		joinblock=false;
		
		mo= new mouse();
		xa= new ArrayList<Integer>();
		ya= new ArrayList<Integer>();
		dx= new ArrayList<Integer>();
		dy= new ArrayList<Integer>();
		dz= new ArrayList<Integer>();
		
		time1 = new  Timer(1, this);
		time2 = new Timer(5,new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<ya.size();i++)
					if(ya.get(i)<=dy.get(i)) ya.set(i, ya.get(i)+4);
					else {
						merge[(xa.get(i)-PADDINGLEFT)/PIXEL][(ya.get(i)-PADDINGTOP)/PIXEL]=false;
						removenew(i);
						continue;
					}
				time2stop();
				repaint();
			}
		});
		//mn.time= new Timer(5,mn);
		
		yyes=new Rectangle(PADDINGLEFT+20, PADDINGTOP+PIXEL*5+120, 155,40);
		nno=new Rectangle(PADDINGLEFT+225, PADDINGTOP+PIXEL*5+120, 155, 40);
		initmerge();
		
		yes= new JButton("yes");
		no = new JButton("no");
		y= new JButton("yes");
		n = new JButton("no");
//		Icon ico = new ImageIcon(this.getClass().getResource("/Images/buttonmenu.ico"));
		menu = new Button("");
		
		menu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DataGame.LEVEL = lv;
				DataGame.MAP = map;
				
				game.menu = new Menu(game);
				
				frame.setVisible(false);
				
			}
		});
		menu.setBounds(5, 60, 85, 50);
		
//		menu.getGraphics().drawImage(,0,0,50,50,null);
//		menu = new JButton("MENU");
//		menu.setIcon(p.buttonmenu);
		
		population=0;
		
		yes.setBounds(PADDINGLEFT+100, PADDINGTOP+PIXEL*5+110, 80, 30);
		no.setBounds(PADDINGLEFT+200, PADDINGTOP+PIXEL*5+110, 80, 30);
		y.setBounds(PADDINGLEFT+100, PADDINGTOP+PIXEL*5+110, 80, 30);
		n.setBounds(PADDINGLEFT+200, PADDINGTOP+PIXEL*5+110, 80, 30);
		
		y.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				lv[targetX][targetY]=9+up_house(targetX,targetY);
				map[targetX][targetY]=5;
				if(lv[targetX][targetY]>=5)
					delnum+=lv[targetX][targetY]-5;
				
				merge[targetX][targetY]=true;
				takenewblock();
				time2.start();
				
				joinblock=false;
				remove(y);
				remove(n);
				repaint();
			}
		});
		n.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				joinblock=false;
				remove(y);
				remove(n);
				repaint();
			}
		});
		yes.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				lv[targetX][targetY]=0;
				
				merge[targetX][targetY]=true;
				takenewblock();
				time2.start();
				
				eraser=false;
				delnum--;
				remove(yes);
				remove(no);
				repaint();
				
			}
		});
		
		no.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				eraser=false;
				remove(yes);
				remove(no);
				repaint();
			}
		});
		
		for(int i=0;i<5;i++)
			for(int j=0;j<5;j++) {
				map[i][j]=1+Math.abs(rd.nextInt())%numtile;
				lv[i][j]=1;
			}
		
		setLayout(null);
		addMouseListener(mo);
		add(menu);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintgame(g);
		if(gameover) paintgameover(g);
		if(eraser) painteraser(g);
		if(joinblock) paintJoinblock(g);
		
	}
	
	class mouse extends MouseAdapter{
		public void mousePressed(MouseEvent m) {
			if(pause)
				return;
			int xm=m.getX();
			int ym=m.getY();
			
			if(joinblock) {
				if(yyes.contains(xm, ym))
					y.doClick();
				if(nno.contains(xm, ym))
					n.doClick();
				return;
			}
			if(eraser) {
				if(yyes.contains(xm, ym))
					yes.doClick();
				if(nno.contains(xm, ym))
					no.doClick();
				return;
			}

			if(time1.isRunning()||time2.isRunning()||xm>PADDINGLEFT+PIXEL*5||ym>PADDINGTOP+PIXEL*5||xm<PADDINGLEFT||ym<PADDINGTOP) {
				return;
			}
			
			initmerge();
			clear();
			
			
			
			targetX=(xm-PADDINGLEFT)/PIXEL;
			targetY=(ym-PADDINGTOP)/PIXEL;
			
			merge(targetX,targetY,map[targetX][targetY],targetX,targetY);
			sb=true;
			
			if(map[targetX][targetY]!=0&&map[targetX][targetY]!=5) {
				year++;
				lv[targetX][targetY]=slick(targetX,targetY,map[targetX][targetY]);
			}
			process();
			
			repaint();
			sb=true;
		}
	}
	public void process() {
		// join blocks
		if(isJoin(targetX,targetY))
		{
			year++;
			joinblock=true;
			merge[targetX][targetY]=false;
			repaint();
			return;
		}
		// eraser house
		if(checkmap(targetX,targetY)&&delnum>0) {
				eraser=true;
				merge[targetX][targetY]=false;
				repaint();
				return;
		}
		// check end game and population
		int count=0;
		population=0;
		for(int i=0;i<5;i++)
			for(int j=0;j<5;j++)
		{
			if(getlevel(i,j)==10) {
				map[i][j]=0;
			}
			if(delnum==0&&checkmap(i,j)) count++;
			population+=Popular(i, j);
		}
		if(count==25) {
			gameover=true;
			repaint();
		}
		if(year>2050) numtile=4;
		// year up
		if(year%100==0) delnum++;
		merge[targetX][targetY]=false;
		time1.start();
	}
	
	public int slick(int x,int y,int z) {
		if(sb) initpass();
		
		if(x>4||y>4||x<0||y<0||map[x][y]!=z||merge[x][y])
			return 0;
		
		merge[x][y]=true;
		
		int t=lv[x][y];
		lv[x][y]=0;
		
//		write();
		return t+
		slick(x-1,y,z)+
		slick(x,y-1,z)+
		slick(x+1,y,z)+
		slick(x,y+1,z);
		
	}
	public int up_house(int x,int y) {
		if(sb) initpass();
		
		if(x>4||y>4||x<0||y<0||map[x][y]!=0||merge[x][y]) {
			return 0;
		}
		
		merge[x][y]=true;
		
		lv[x][y]=0;
		
		return 1+
		up_house(x-1,y)+
		up_house(x,y-1)+
		up_house(x+1,y)+
		up_house(x,y+1);
	}
	public void merge(int x,int y,int z,int ux,int vx) {
		
		if(sb) {
			initpass();
			merge[x][y]=true;
			merge(x-1,y,z,ux,vx);
			merge(x,y-1,z,ux,vx);
			merge(x+1,y,z,ux,vx);
			merge(x,y+1,z,ux,vx);
			
		}
		
		if(x>4||y>4||x<0||y<0||map[x][y]!=z||merge[x][y]) {
			return;
		}
		
		dz.add(map[x][y]);
		xa.add(x*PIXEL+PADDINGLEFT);
		ya.add(y*PIXEL+PADDINGTOP);
		dx.add((ux-x));
		dy.add((vx-y));
		
		//pass[x][y]=true;
		merge[x][y]=true;
		
		merge(x-1,y,z,ux,vx);
		merge(x,y-1,z,ux,vx);
		merge(x+1,y,z,ux,vx);
		merge(x,y+1,z,ux,vx);
	}
	public void takenew() {
		for(int i=0;i<5;i++) {
			for(int j=4;j>0;j--) {
				while(lv[i][j]==0) {
					for(int k=j;k>0;k--) {
						map[i][k]=map[i][k-1];
						lv[i][k]=lv[i][k-1];
					}
					map[i][0]=1+Math.abs(rd.nextInt())%numtile;
					lv[i][0]=1;
				}
			}
			if(lv[i][0]==0) {
				map[i][0]=1+Math.abs(rd.nextInt())%numtile;
				lv[i][0]=1;
			}
		}
	}
	
	public boolean isJoin(int a,int b) {
		if(map[a][b]!=0) return false;
		if(a>0&&map[a-1][b]==0) return true;
		if(b>0&&map[a][b-1]==0) return true;
		if(a<4&&map[a+1][b]==0) return true;
		if(b<4&&map[a][b+1]==0) return true;
		
		return false;
	}
	
	public boolean checkmap(int a,int b){
		int c=map[a][b];
		if(c==5) return true;
		int result=0;
		
		if(a!=0&&map[a-1][b]==c) result++;
		if(b!=0&&map[a][b-1]==c) result++;
		if(a!=4&&map[a+1][b]==c) result++;
		if(b!=4&&map[a][b+1]==c) result++;
		
		if(result==0) return true;
		return false;
	}
	public int typetile(int a,int b) {
		int c=map[a][b];
		if(0<a&&a<4&&0<b&&b<4&&map[a+1][b]==c&&map[a-1][b]==c&&map[a][b+1]==c&&map[a][b-1]==c) return 15;
		if(a<4&&0<b&&b<4&&map[a][b+1]==c&&map[a][b-1]==c&&map[a+1][b]==c) return 5;
		if(0<a&&a<4&&b<4&&map[a+1][b]==c&&map[a-1][b]==c&&map[a][b+1]==c) return 6;
		if(0<a&&0<b&&b<4&&map[a][b+1]==c&&map[a][b-1]==c&&map[a-1][b]==c) return 7;
		if(0<a&&a<4&&0<b&&map[a+1][b]==c&&map[a-1][b]==c&&map[a][b-1]==c) return 8;
		
		if(0<a&&a<4&&map[a+1][b]==c&&map[a-1][b]==c) return 9;
		if(0<b&&b<4&&map[a][b+1]==c&&map[a][b-1]==c) return 10;
		
		if(a<4&&b<4&&map[a+1][b]==c&&map[a][b+1]==c) return 1;
		if(0<a&&b<4&&map[a][b+1]==c&&map[a-1][b]==c) return 2;
		if(0<a&&0<b&&map[a][b-1]==c&&map[a-1][b]==c) return 3;
		if(a<4&&0<b&&map[a+1][b]==c&&map[a][b-1]==c) return 4;
		
		if(a<4&&map[a+1][b]==c) return 11;
		if(b<4&&map[a][b+1]==c) return 12;
		if(a>0&&map[a-1][b]==c) return 13;
		if(b>0&&map[a][b-1]==c) return 14;
		
		return 0;
	}
	public int Popular(int a,int b) {
		int c=getlevel(a,b);
		if(c==1) return 1;
		if(c==10) return 11000;
		return c*5*(int)Math.pow(2, c-2);
	}
	public int getlevel(int a,int b) {
		if(map[a][b]==5) return lv[a][b];
		
		int c=lv[a][b];
		if(c==1) return 1;
		if(c<4) return 2;
		switch(c/4) {
		case 1: return 3;
		case 2: return 4;
		case 3: return 5;
		case 4: return 6;
		case 5: return 7;
		case 6: return 8;
		case 7:	
		case 8: return 9;
		}
		
		return 10;
	}
	public void takenewblock() {
		clear();
		for(int i=0;i<5;i++)
			for(int j=4;j>-1;j--)
			if(merge[i][j]) {
				for(int k=j;k>-1;k--)
					{
					merge[i][k]=true;
					if(lv[i][k]!=0) {
						
						xa.add(i*PIXEL+PADDINGLEFT);
						dx.add(i*PIXEL+PADDINGLEFT);
						ya.add(k*PIXEL+PADDINGTOP);
						dy.add((j)*PIXEL+PADDINGTOP);
						
						j--;
					}
					}
				
//				write();
				for(int run=0;run<j+1;run++) {
					xa.add(i*PIXEL+PADDINGLEFT);
					dx.add(i*PIXEL+PADDINGLEFT);
					ya.add(PADDINGTOP-(run+1)*PIXEL);
					dy.add((j-run)*PIXEL+PADDINGTOP);
				}
			break;
			}
		takenew();
		for(int ii=0;ii<xa.size();ii++) {
			dz.add(map[(xa.get(ii)-PADDINGLEFT)/PIXEL][(dy.get(ii)-PADDINGTOP)/PIXEL]);
		}
		
	}
	public void paintgame(Graphics g) {
		g.drawImage(p.background,0,0,null);
//		if(mn.isDraw()) {
//			mn.paint(g);
//			return;
//		}
			
		
			for(int i=0;i<xa.size();i++)
				switch(dz.get(i)) {
				case 1: g.drawImage(p.tile1,xa.get(i),ya.get(i),80,80,null);break;
				case 2: g.drawImage(p.tile2,xa.get(i),ya.get(i),80,80,null);break;
				case 3: g.drawImage(p.tile3,xa.get(i),ya.get(i),80,80,null);break;
				case 4: g.drawImage(p.tile4,xa.get(i),ya.get(i),80,80,null);break;
				case 5: g.drawImage(p.tile5,xa.get(i),ya.get(i),80,80,null);break;
				case 6: g.drawImage(p.tile6,xa.get(i),ya.get(i),80,80,null);break;
				
			}
		for(int i=0;i<5;i++)
			for(int j=0;j<5;j++) {
				if(merge[i][j]) continue;
				switch(map[i][j]) {
				case 0:
					g.drawImage(p.tile5,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
				case 1: 
					switch(typetile(i, j)) {
					case 0:g.drawImage(p.tile1,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
					case 1:g.drawImage(p.a1,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
					case 2:g.drawImage(p.a2,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
					case 3:g.drawImage(p.a3,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
					case 4:g.drawImage(p.a4,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
					case 5:g.drawImage(p.a5,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
					case 6:g.drawImage(p.a6,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
					case 7:g.drawImage(p.a7,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
					case 8:g.drawImage(p.a8,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
					case 9:g.drawImage(p.a9,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
					case 10:g.drawImage(p.a10,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
					case 11:g.drawImage(p.a11,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
					case 12:g.drawImage(p.a12,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
					case 13:g.drawImage(p.a13,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
					case 14:g.drawImage(p.a14,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
					case 15:g.drawImage(p.a15,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
					
					};
					break;
				case 2: 
					switch(typetile(i, j)) {
				case 0:g.drawImage(p.tile2,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
				case 1:g.drawImage(p.b1,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
				case 2:g.drawImage(p.b2,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
				case 3:g.drawImage(p.b3,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
				case 4:g.drawImage(p.b4,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
				case 5:g.drawImage(p.b5,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
				case 6:g.drawImage(p.b6,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
				case 7:g.drawImage(p.b7,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
				case 8:g.drawImage(p.b8,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
				case 9:g.drawImage(p.b9,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
				case 10:g.drawImage(p.b10,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
				case 11:g.drawImage(p.b11,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
				case 12:g.drawImage(p.b12,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
				case 13:g.drawImage(p.b13,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
				case 14:g.drawImage(p.b14,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
				case 15:g.drawImage(p.b15,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
				
				};
				break;
				case 3: 
					switch(typetile(i, j)) {
				case 0:g.drawImage(p.tile3,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
				case 1:g.drawImage(p.c1,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
				case 2:g.drawImage(p.c2,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
				case 3:g.drawImage(p.c3,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
				case 4:g.drawImage(p.c4,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
				case 5:g.drawImage(p.c5,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
				case 6:g.drawImage(p.c6,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
				case 7:g.drawImage(p.c7,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
				case 8:g.drawImage(p.c8,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
				case 9:g.drawImage(p.c9,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
				case 10:g.drawImage(p.c10,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
				case 11:g.drawImage(p.c11,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
				case 12:g.drawImage(p.c12,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
				case 13:g.drawImage(p.c13,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
				case 14:g.drawImage(p.c14,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
				case 15:g.drawImage(p.c15,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
				
				};
				break;
				case 4: 
					switch(typetile(i, j)) {
				case 0:g.drawImage(p.tile4,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
				case 1:g.drawImage(p.d1,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
				case 2:g.drawImage(p.d2,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
				case 3:g.drawImage(p.d3,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
				case 4:g.drawImage(p.d4,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
				case 5:g.drawImage(p.d5,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
				case 6:g.drawImage(p.d6,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
				case 7:g.drawImage(p.d7,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
				case 8:g.drawImage(p.d8,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
				case 9:g.drawImage(p.d9,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
				case 10:g.drawImage(p.d10,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
				case 11:g.drawImage(p.d11,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
				case 12:g.drawImage(p.d12,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
				case 13:g.drawImage(p.d13,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
				case 14:g.drawImage(p.d14,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
				case 15:g.drawImage(p.d15,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
				
				};
				break;
				case 5: g.drawImage(p.tile6,PADDINGLEFT+i*PIXEL, PADDINGTOP+j*PIXEL,null);break;
				
				}
				int getheight = getlevel(i, j);
				switch(getheight) {
				case 1:g.drawImage(p.lv1, PADDINGLEFT+i*PIXEL+p.paddingleft(lv[i][j]), PADDINGTOP+j*PIXEL+p.paddingtop(getheight),
						p.getWidth(lv[i][j]),p.getHeight(getheight),null);
						break;
				case 2:g.drawImage(p.lv2, PADDINGLEFT+i*PIXEL+p.paddingleft(lv[i][j]), PADDINGTOP+j*PIXEL+p.paddingtop(getheight),
						p.getWidth(lv[i][j]),p.getHeight(getheight),null);
						break;
				case 3:g.drawImage(p.lv3, PADDINGLEFT+i*PIXEL+p.paddingleft(lv[i][j]), PADDINGTOP+j*PIXEL+p.paddingtop(getheight),
						p.getWidth(lv[i][j]),p.getHeight(getheight),null);
						break;
				case 4:g.drawImage(p.lv4, PADDINGLEFT+i*PIXEL+p.paddingleft(lv[i][j]), PADDINGTOP+j*PIXEL+p.paddingtop(getheight),
						p.getWidth(lv[i][j]),p.getHeight(getheight),null);
						break;
				case 5:g.drawImage(p.lv5, PADDINGLEFT+i*PIXEL+p.paddingleft(lv[i][j]), PADDINGTOP+j*PIXEL+p.paddingtop(getheight),
						p.getWidth(lv[i][j]),p.getHeight(getheight),null);
						break;
				case 6:g.drawImage(p.lv6, PADDINGLEFT+i*PIXEL+p.paddingleft(lv[i][j]), PADDINGTOP+j*PIXEL+p.paddingtop(getheight),
						p.getWidth(lv[i][j]),p.getHeight(getheight),null);
						break;
				case 7:g.drawImage(p.lv7, PADDINGLEFT+i*PIXEL+p.paddingleft(lv[i][j]), PADDINGTOP+j*PIXEL+p.paddingtop(getheight),
						p.getWidth(lv[i][j]),p.getHeight(getheight),null);
						break;
				case 8:g.drawImage(p.lv8, PADDINGLEFT+i*PIXEL+p.paddingleft(lv[i][j]), PADDINGTOP+j*PIXEL+p.paddingtop(getheight),
						p.getWidth(lv[i][j]),p.getHeight(getheight),null);
						break;
				case 9:g.drawImage(p.lv9, PADDINGLEFT+i*PIXEL+p.paddingleft(lv[i][j]), PADDINGTOP+j*PIXEL+p.paddingtop(getheight),
						p.getWidth(lv[i][j]),p.getHeight(getheight),null);
						break;
				case 10:g.drawImage(p.lv10, PADDINGLEFT+i*PIXEL+p.paddingleft(lv[i][j]), PADDINGTOP+j*PIXEL+p.paddingtop(getheight),
						p.getWidth(lv[i][j]),p.getHeight(getheight),null);
						break;
				case 11:g.drawImage(p.lv11, PADDINGLEFT+i*PIXEL+p.paddingleft(lv[i][j]), PADDINGTOP+j*PIXEL+p.paddingtop(getheight),
						p.getWidth(lv[i][j]),p.getHeight(getheight),null);
						break;
				case 12:g.drawImage(p.lv12, PADDINGLEFT+i*PIXEL+p.paddingleft(lv[i][j]), PADDINGTOP+j*PIXEL+p.paddingtop(getheight),
						p.getWidth(lv[i][j]),p.getHeight(getheight),null);
						break;
				case 13:g.drawImage(p.lv13, PADDINGLEFT+i*PIXEL+p.paddingleft(lv[i][j]), PADDINGTOP+j*PIXEL+p.paddingtop(getheight),
						p.getWidth(lv[i][j]),p.getHeight(getheight),null);
						break;
				case 14:g.drawImage(p.lv14, PADDINGLEFT+i*PIXEL+p.paddingleft(lv[i][j]), PADDINGTOP+j*PIXEL+p.paddingtop(getheight),
						p.getWidth(lv[i][j]),p.getHeight(getheight),null);
						break;
				case 15:g.drawImage(p.lv15, PADDINGLEFT+i*PIXEL+p.paddingleft(lv[i][j]), PADDINGTOP+j*PIXEL+p.paddingtop(getheight),
						p.getWidth(lv[i][j]),p.getHeight(lv[i][j]),null);
						break;
				case 16:g.drawImage(p.lv16, PADDINGLEFT+i*PIXEL+p.paddingleft(lv[i][j]), PADDINGTOP+j*PIXEL+p.paddingtop(getheight),
						p.getWidth(lv[i][j]),p.getHeight(lv[i][j]),null);
						break;
				case 17:g.drawImage(p.lv17, PADDINGLEFT+i*PIXEL+p.paddingleft(lv[i][j]), PADDINGTOP+j*PIXEL+p.paddingtop(getheight),
						p.getWidth(lv[i][j]),p.getHeight(lv[i][j]),null);
						break;
				case 18:g.drawImage(p.lv18, PADDINGLEFT+i*PIXEL+p.paddingleft(lv[i][j]), PADDINGTOP+j*PIXEL+p.paddingtop(getheight),
						p.getWidth(lv[i][j]),p.getHeight(lv[i][j]),null);
						break;
				case 19:g.drawImage(p.lv19, PADDINGLEFT+i*PIXEL+p.paddingleft(lv[i][j]), PADDINGTOP+j*PIXEL+p.paddingtop(getheight),
						p.getWidth(lv[i][j]),p.getHeight(lv[i][j]),null);
						break;
				case 20:g.drawImage(p.lv20, PADDINGLEFT+i*PIXEL+p.paddingleft(lv[i][j]), PADDINGTOP+j*PIXEL+p.paddingtop(getheight),
						p.getWidth(lv[i][j]),p.getHeight(lv[i][j]),null);
						break;
				
				};
			//g.drawString(""+lv[i][j], PADDINGLEFT+i*PIXEL,10+ PADDINGTOP+j*PIXEL);
			//g.drawString(""+getlevel(i,j), PADDINGLEFT+i*PIXEL,25+ PADDINGTOP+j*PIXEL);
			
			}
		
		g.drawImage(p.tittle,0,0,null);
		g.setFont(new Font("boid", Font.BOLD, 20));
		g.setColor(Color.white);
		g.drawString(String.valueOf(year), 340, PADDINGTOP-15);
		g.drawString(""+delnum, PADDINGLEFT+55, PADDINGTOP+PIXEL*5+45);
		g.drawString(""+population, 200, 100);
//		g.drawRect(5, 60, 85, 50);
	}
	public void paintgameover(Graphics g) {
		g.drawString("Game Over", 20, 500);
	}
	public void painteraser(Graphics g) {
		//add(yes);
		//add(no);
		
		g.drawImage(p.eraser,0,PADDINGTOP+PIXEL*5+60,410,115,null);
	}
	public void paintJoinblock(Graphics g) {
		//add(y);
		//add(n);

		g.drawImage(p.joinblock,0,PADDINGTOP+PIXEL*5+60,410,115,null);
		
	}
	public void initmerge() {
		for(int i=0;i<5;i++)
			for(int j=0;j<5;j++)
				merge[i][j]=false;
	}
	
	private void clear() {
		xa.clear();
		ya.clear();
		dx.clear();
		dy.clear();
		dz.clear();
	}
//	private void write() {
//		for(int i=0;i<5;i++) {
//			for(int j=0;j<5;j++)
//				System.out.print("      "+ merge[j][i]);
//			System.out.println();
//		}
//	}
	private void removenew(int i) {
		xa.remove(i);
		ya.remove(i);
		dx.remove(i);
		dy.remove(i);
		dz.remove(i);
	}
	private void time2stop() {
		if(xa.isEmpty()) {
			initmerge();
			time2.stop();
			repaint();
			}
	}
	private void time1stop() {
		if(timemerge==80) {
			time1.stop();
			timemerge=0;
			takenewblock();
//			write();
			time2.start();
			repaint();
		}
	}
	private void initpass() {
		for(int i=0;i<5;i++)
			for(int j=0;j<5;j++)
				merge[i][j]=false;
		
		sb=false;
		
	}
	public void continuegame() {
		frame.setVisible(true);
	}
}
