package MainPK;

import java.awt.Color;
import java.awt.Graphics;

public class Weapon {
	class Shot {
		private int x,y,mx=0,my=0,sx,sy,l,speedshot;
		private float dt;
		private boolean r;
		private final Color cl= Color.black;
		
		public Shot(int a,int b,int ss) {
			x=a;y=b;
			sx=a;sy=b;
			speedshot=ss;
		}
		public void paint(Graphics g) {
			g.setColor(cl);
			g.fillRect(x, y, 9, 9);
		}
		public void destroy_enemy(int a,int b) {
			if(mx!=a||my!=b||(x==a&&y==b)) {
				x=sx;
				y=sy;
				shot_enemy(a, b);
			}
			run();
		}
		
		
		public void reset() {
			x=sx;
			y=sy;
			mx=sx;
			my=sy;
		}
		public int fire(int a,int b) {
			if(Math.abs(x-a)<=speedshot&&Math.abs(y-b)<=speedshot) {
				reset();
				return 1;
			}
				if(x<a) x+=speedshot;
					else if(x>a) x-=speedshot;
					
				if(y<b) y+=speedshot;
					else if(y>b) y-=speedshot;
			return 0;
		}
		
		public void run() {
			l++;
			int k= (int)(l*dt);
			if(r) {
				if(x<mx) x++;
				else if(x>mx) x--;
				
				if(y<my) y=sy+k;
				else if(y>my) y=sy-k;
				}
			else {
				if(x<mx) x=sx+k;
				else if(x>mx) x=sx-k;
				
				if(y<my) y+=1;
				else if(y>my) y-=1;
			}
			System.out.println("x="+x+" y="+y+" l="+l+" k="+k+" dt="+dt+" Sx="+sx+" sy="+sy);
			
		}
		
		public void shot_enemy(int c,int d) {
			l=0;
			mx=c;
			my=d;
			float dx=(float)Math.abs(sx-c),dy=(float)Math.abs(sy-d);
			if(dx<dy) {
				dt=dx/dy;
				r=false;
			}
			else {
				dt=dy/dx;
				r=true;
			}
			System.out.println("dx="+dx+" dy="+dy+" dt="+dt);
		}
		public void setSpeed(int a) {
			speedshot+=a;
		}
	}
	
	
	class Laze{
		private int time = 50;
		private int x,y,mx,my,timeload;
		private boolean shoot;
		public Laze(int a,int b,int c) {
			x=a;y=b;
			time=c;
			shoot=true;
		}
		public void paint(Graphics g) {
			g.setColor(Color.RED);
			if(timeload<time/2) {
				g.drawOval(x-10, y-10, 20, 20);
			}
			if(!shoot) return;
			
			g.drawLine(x, y, mx, my);
			g.drawLine(x-1, y, mx-1, my);
			g.drawLine(x+1, y, mx+1, my);
			
			
		}
		public boolean launch(int a,int b) {
			mx=a;my=b;
			System.out.println("time ---- " + time);
			if(time==0) {
				shoot=true;
				return true;
			}
			if(timeload==0) {
				timeload=time;
				shoot=true;
				return true;
			}
			else {
				timeload--;
				shoot=false;
				return false;
			}
		}
		public void setTimeload(int a) {
			time-=a;
		}
	}
}
