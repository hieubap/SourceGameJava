package MainPK;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;
/*
 * chu y clear array se clear ca aray nam trong mang 2 chieu
 * 
 */
@SuppressWarnings("serial")
public class Enemy extends JPanel{
	private int sx,sy,x,y,w,life,t,plife,type,k,gold;
	private boolean dead,appear,stop;
	private Color cl;
	private ArrayList<Enemy> a1,a2,a3,a4,a5,a6,a7,a8,a9,a10,a11,a12,a13;
	
	public void init() {
		a1 = new ArrayList<Enemy>();
		a2 = new ArrayList<Enemy>();
		a3 = new ArrayList<Enemy>();
		a4 = new ArrayList<Enemy>();
		a5 = new ArrayList<Enemy>();
		a6 = new ArrayList<Enemy>();
		a7 = new ArrayList<Enemy>();
		a8 = new ArrayList<Enemy>();
		a9 = new ArrayList<Enemy>();
		a10= new ArrayList<Enemy>();
		a11= new ArrayList<Enemy>();
		a12= new ArrayList<Enemy>();
		a13= new ArrayList<Enemy>();
		
		
		System.out.println("----------- init enemy");
		
		// lv1
		for(int i=0;i<40;i++)
			a1.add(new Enemy(75, 94, 1));
		System.out.println("----------- init 0 : "+ a1.size());
		
		// lv2
		for(int i=0;i<20;i++)
			a2.add(new Enemy(75,94,1));
		for(int i=0;i<40;i++)
			a2.add(new Enemy(75, 94, 2));
		
		// lv 3
		for(int i=0;i<30;i++) {
			a3.add(new Enemy(75,94,1));
			a3.add(new Enemy(75, 94, 2));
		}
		
		// lv 4
		for(int i=0;i<10;i++)
			a4.add(new Enemy(75,94,1));
		for(int i=0;i<20;i++)
			a4.add(new Enemy(75, 94, 2));
		for(int i=0;i<30;i++)
			a4.add(new Enemy(75, 94, 3));
		
		// lv 5
		for(int i=0;i<10;i++)
			a5.add(new Enemy(75,94,1));
		for(int i=0;i<20;i++)
			a5.add(new Enemy(75, 94, 2));
		for(int i=0;i<30;i++)
			a5.add(new Enemy(75, 94, 3));
		
		// lv 6
		a6.add(new Enemy(57, 94, 4));
		
		// lv 7
		for(int i=0;i<1;i++)
			a7.add(new Enemy(75,94,1));
		for(int i=0;i<2;i++)
			a7.add(new Enemy(75, 94, 2));
		for(int i=0;i<10;i++)
			a7.add(new Enemy(75, 94, 3));
		for(int i=0;i<2;i++)
			a7.add(new Enemy(75, 94, 4));
		
		// lv 8
		for(int i=0;i<5;i++)
			a8.add(new Enemy(75,94,1));
		for(int i=0;i<5;i++)
			a8.add(new Enemy(75, 94, 2));
		for(int i=0;i<5;i++)
			a8.add(new Enemy(75, 94, 3));
		for(int i=0;i<5;i++)
			a8.add(new Enemy(75, 94, 4));
		
		// lv 9
		for(int i=0;i<5;i++)
			a9.add(new Enemy(75,94,2));
		for(int i=0;i<10;i++)
			a9.add(new Enemy(75, 94, 3));
		for(int i=0;i<5;i++)
			a9.add(new Enemy(75, 94, 4));
		for(int i=0;i<1;i++)
			a9.add(new Enemy(75, 94, 5));
		
		// lv 10
		for(int i=0;i<10;i++)
			a10.add(new Enemy(75,94, 3));
		for(int i=0;i<10;i++)
			a10.add(new Enemy(75, 94, 4));
		for(int i=0;i<10;i++)
			a10.add(new Enemy(75, 94, 5));
		
		// lv 11
		for(int i=0;i<1;i++)
			a11.add(new Enemy(75,94, 6));
		
		// lv 12
		for(int i=0;i<20;i++)
			a12.add(new Enemy(75,94, 4));
		for(int i=0;i<20;i++)
			a12.add(new Enemy(75,94, 5));
		for(int i=0;i<5;i++)
			a12.add(new Enemy(75,94, 6));
		
		// lv 13
		
		for(int i=0;i<20;i++)
			a13.add(new Enemy(75,94, 4));
		for(int i=0;i<20;i++)
			a13.add(new Enemy(75,94, 5));
		for(int i=0;i<20;i++)
			a13.add(new Enemy(75,94, 6));
			
	}
	public Enemy(int a,int b,int tp) {
		k=0;
		x=a;
		y=b;
		sx=a;
		sy=b;
		type=tp;
		appear=false;
		stop=false;
		
		
		switch(type) {
		case 1:
			w=14;
			life=5;
			cl= Color.gray;
			gold=1;
			break;
		case 2:
			w=17;
			life=15;
			cl=Color.green;
			gold=2;
			break;
		case 3:
			w=20;
			life=30;
			cl= new Color(145,60,96);
			gold=3;
			break;
		case 4:
			w=23;
			life=500;
			cl=Color.yellow;
			gold=4;
			break;
		case 5:
			w=23;
			life=1000;
			cl=Color.orange;
			gold=4;
			break;
		case 6:
			w=25;
			life=10000;
			cl=Color.red;
			gold=4;
			break;
		
		}
		
		plife=life/w+1;
		dead=false;
		t=1;
	}
	public void appear() {
		appear=true;
	}
	public void setk() {
		k++;
	}
	public int getk() {
		return k;
	}
	public void resetk() {
		k=0;
	}
	public int getGold() {
		return gold;
	}
	public void paint(Graphics g) {
		if(!appear) return;
		
		g.setColor(cl);
		g.fillRect(x-w/2, y-w/2, w, w);
		if(life>=0) {
		g.setColor(Color.red);
		g.drawLine(x-w/2-2, y-w/2-4, x-w/2+life/plife+1, y-w/2-4);
		}
	}
	public void reset() {
		x=sx;
		y=sy;
	}
	public boolean go(int a,int b) {
		if(life<0) dead=true;
		if(!appear||stop)
			return false;
		if(a==0) {
			reset();
			t=1;
			return true;
		}
		switch(t) {
		case 1: 
			x++;
			if(x==a) {
				if(y<b) t=2;
				else t=4;
				return true;
			}
			break;
		case 2: 
			y++;
			if(y==a) {
				if(x<b) t=1;
				else t=3;
				return true;
			}
			break;
		case 3: 
			x--;
			if(x==a) {
				if(y<b) t=2;
				else t=4;
				return true;
			}
			break;
		case 4: 
			y--;
			if(y==a) {
				if(x<b) t=1;
				else t=3;
				return true;
			}
			break;
		}
		return false;
	}
	public void setLife(int a) {
		life-=a;
	}
	public void stop() {
		stop=true;
	}
	public boolean getDead() {
		return dead;
	}
	public int getX() {
		return x;
	}
	public void setT(int a) {
		t=a;
	}
	public int getY() {
		return y;
	}
	
	public ArrayList<Enemy> getAttack(int a){
		switch(a) {
		case 1: return a1;
		case 2: return a2;
		case 3: return a3;
		case 4: return a4;
		case 5: return a5;
		case 6: return a6;
		case 7: return a7;
		case 8: return a8;
		case 9: return a9;
		case 10: return a10;
		case 11: return a11;
		case 12: return a12;
		case 13: return a13;
		}
		return a1;
	}
	public Enemy getEnemy(int a,int b) {
		switch(a) {
		case 1: return a1.get(b);
		case 2: return a2.get(b);
		case 3: return a3.get(b);
		case 4: return a4.get(b);
		case 5: return a5.get(b);
		case 6: return a6.get(b);
		case 7: return a7.get(b);
		case 8: return a8.get(b);
		case 9: return a9.get(b);
		case 10: return a10.get(b);
		case 11: return a11.get(b);
		case 12: return a12.get(b);
		case 13: return a13.get(b);
		}
		return a13.get(b);
	}
	public Enemy geta1(int a){
		return a1.get(a);
	}
	public Enemy geta2(int a){
		return a2.get(a);
	}
	public Enemy geta3(int a){
		return a3.get(a);
	}
	public Enemy geta4(int a){
		return a4.get(a);
	}
	public Enemy geta5(int a){
		return a5.get(a);
	}
	public Enemy geta6(int a){
		return a6.get(a);
	}
	public Enemy geta7(int a){
		return a7.get(a);
	}
	public Enemy geta8(int a){
		return a8.get(a);
	}
	public Enemy geta9(int a){
		return a9.get(a);
	}
	public Enemy geta10(int a){
		return a10.get(a);
	}
	public Enemy geta11(int a){
		return a11.get(a);
	}
	public Enemy geta12(int a){
		return a12.get(a);
	}
	public Enemy geta13(int a){
		return a13.get(a);
	}
}
