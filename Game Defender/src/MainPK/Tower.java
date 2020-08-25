package MainPK;

import java.awt.Color;
import java.awt.Graphics;

public class Tower extends Weapon{
	private int x,y,w,tamban,damage,type,speed,sell,support,up1,up2,up3;
	private Shot shot;
	private Laze laze;
	private Color cl;
	
	public Tower(int a,int b,int tp) {
		this.x=a;
		this.y=b;
		this.type=tp;
		
		if(type==1) {// them xu
			cl= Color.yellow;
			w=21;
			speed=100;
			damage=1;
			support=speed;
			sell=1;
			up1=5;
			up3=5;
		}
		else if(type==2) {// shoot
			cl= Color.green;
			w=8;
			tamban=50;
			damage=1;
			speed=1;
			sell=2;
			up1=5;
			up2=5;
			up3=5;
			
			shot = new Shot(x,y,speed);
		}
		else if(type==3) {// giu chan
			cl= Color.cyan;
			w=10;
			tamban=60;
			damage=1;
			speed=2;
			sell=5;
			up1=5;
			up2=5;
			up3=5;
			
		}
		else if(type==4) {// laze
			cl= Color.pink;
			w=15;
			tamban=70;
			damage=50;
			speed=25;
			sell=10;
			up1=20;
			up2=10;
			up3=20;
			
			
			laze=new Laze(x,y,speed);
		}
		else if(type==5) {//dich chuyen doi phuong
			cl= Color.gray;
			w=18;
			damage=1;
			speed=500;
			sell=15;
		}
	}
	// type 1
	public boolean addcoin() {
		if(support==0) {
			support=speed;
			return true;
		}
		support--;
		return false;
	}
	// type 2
	public Shot getShot() {
		return shot;
	}
	//type 3
	public void ice() {
		
	}
	// type 4
	public Laze getLaze() {
		return laze;
	}
	//type 5
	public boolean space() {
		if(support==0) {
			support=speed;
			return false;
		}
		else if(support==speed){
			return true;
		}
		else {
			support--;
			return false;
		}
	}
	public void loadspace() {
		support--;
	}
	////////
	public boolean is_shot(int u,int v) {
		if(Math.pow(u-x, 2)+Math.pow(v-y, 2)<=tamban*tamban) return true;
		
		return false;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.GRAY);
		g.drawOval(x-tamban, y-tamban, tamban*2, tamban*2);
		g.setColor(cl);
		g.fillOval(x-w/2, y-w/2, w,w);
	}
	public void painttime(Graphics g,int a) {
		g.drawString(String.valueOf(a), x-5, y+5);
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getDamage() {
		return damage;
	}
	public int getSell() {
		return sell;
	}
	public int getType() {
		return type;
	}
	public int gettamban() {
		return tamban;
	}
	public int getspeed() {
		return speed;
	}
	public int getup1() {
		return up1;
	}
	public int getup2() {
		return up2;
	}
	public int getup3() {
		return up3;
	}
	public int getsupport() {
		return support;
	}
	
	
	public void updamage_coin(int a,int b) {
		sell+=b/2;
		damage+=a;
		up1+=b;
	}
	public void uptamban_coin(int a,int b) {
		sell+=b/2;
		tamban+=a;
		up2+=b;
	}
	public void upspeedshot_coin(int a,int b) {
		sell+=b/2;
		speed+=a;
		up3+=b;
		shot.setSpeed(a);
	}
	public void upspeed_coin(int a,int b) {
		sell+=b/2;
		speed+=a;
		up3+=b;
	}
	public void upspeedlaze_coin(int a,int b) {
		sell+=b/2;
		speed-=a;
		up3+=b;
		laze.setTimeload(a);
	}
	public void upcoin(int a) {
		sell+=1;
		damage+=a;
	}
	public void setup1(int a) {
		up1+=a;
	}
	public void setup2(int a) {
		up2+=a;
	}
	public void setup3(int a) {
		up3+=a;
	}
	
}
