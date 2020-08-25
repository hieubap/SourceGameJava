package MainPK;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JButton;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class Map extends JPanel{
	private int map[][];
	private Rectangle2D t1,t2,t3,t4,t5;
	private JButton bt1,bt2;
	private int w=20,h=11,walk_ennemy;
	private int attack[];
	
	public Map(int lv) {
		map = new int[h][w];
		
		
		t1=new Rectangle2D.Float(50f, 600f, 50f, 50f);
		t2=new Rectangle2D.Float(110f, 600f, 50f, 50f);
		t3=new Rectangle2D.Float(170f, 600f, 50f, 50f);
		t4=new Rectangle2D.Float(230f, 600f, 50f, 50f);
		t5=new Rectangle2D.Float(290f, 600f, 50f, 50f);
		bt1= new JButton("Map 1");
		bt2= new JButton("Map 2");
		
		
		if(lv==1) {
			walk_ennemy=9;
			attack= new int[walk_ennemy+2];
			int atk[]= {
				624,159,97,221,624,283,97,345,621,0,0
			};
			for(int i=0;i<walk_ennemy+2;i++)
				attack[i]=atk[i];
			int mp1[][]= {
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1},
				{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
				{1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1},
				{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
				{1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				
			};

			for(int i=0;i<h;i++) 
				for(int j=0;j<w;j++) 
					map[i][j]=mp1[i][j];
		}
		else if(lv==2) {
			walk_ennemy=17;
			attack= new int[walk_ennemy+2];
			int atk[]= {
					314,159,97,221,314,283,97,345,624,283,376,221,624,159,376,97,666,0,0
				};
				for(int i=0;i<walk_ennemy+2;i++)
					attack[i]=atk[i];
			int mp2[][]= {
					{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
					{0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0},
					{1,1,1,1,1,1,1,1,0,1,0,1,1,1,1,1,1,1,1,1},
					{1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1},
					{1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1},
					{1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1},
					{1,1,1,1,1,1,1,1,0,1,0,1,1,1,1,1,1,1,1,1},
					{1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1},
					{1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1},
					{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
					{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			};

			for(int i=0;i<h;i++) 
				for(int j=0;j<w;j++) 
					map[i][j]=mp2[i][j];
		}
	}
	public int take(int a) {
		return attack[a];
	}
	public int getWalk() {
		return walk_ennemy;
	}
	public void paint(Graphics g,int coin) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setColor(Color.yellow);
		g2d.draw(t1);
		if(coin>9) g2d.fill(t1);
		g2d.setColor(Color.green);
		g2d.draw(t2);
		if(coin>4) g2d.fill(t2);
		g2d.setColor(Color.cyan);
		g2d.draw(t3);
		if(coin>9) g2d.fill(t3);
		g2d.setColor(Color.red);
		g2d.draw(t4);
		if(coin>19) g2d.fill(t4);
		g2d.setColor(Color.gray);
		g2d.draw(t5);
		if(coin>29) g2d.fill(t5);
		
		g2d.setColor(Color.lightGray);
		g.setColor(Color.black);
		
		g.drawString("COIN", 60, 630);
		g.drawString("GUN", 120, 630);
		g.drawString("ICE", 185, 630);
		g.drawString("LAZE", 240, 630);
		g.drawString("SPACE", 295, 630);
		
		
		for(int i=0;i<h;i++) {
			for(int j=0;j<w;j++) {
				//System.out.print(map[i][j]+" ");
				if(map[i][j]!=0) {
				g2d.fillRect(50+j*31, 50+i*31, 30, 30);
				g.drawRect(50+j*31, 50+i*31, 31, 31);
				}
			}
			//System.out.println();
		}
		
	}
	public boolean t1contain(int x,int y) {
		if(t1.contains(x, y)) return true;
		return false;
	}
	public boolean t2contain(int x,int y) {
		if(t2.contains(x, y)) return true;
		return false;
	}
	public boolean t3contain(int x,int y) {
		if(t3.contains(x, y)) return true;
		return false;
	}
	public boolean t4contain(int x,int y) {
		if(t4.contains(x, y)) return true;
		return false;
	}
	public boolean t5contain(int x,int y) {
		if(t5.contains(x, y)) return true;
		return false;
	}
	
	public int getmap(int a,int b) {
		return map[a][b];
	}
	public void setmap(int a,int b,int c) {
		map[a][b]=c;
	}
	public boolean checkarea(int a,int b) {
		if(0<=a&&a<=w-1&&0<=b&&b<=h-1) return true;
		return false;
	}
}
