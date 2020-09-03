package Primary;

import java.awt.Color;
import java.awt.Graphics;

public class Pacman {
	public static final int PIXEL = Control.PIXEL;
	public static final int SPEED = Control.SPEED;

	public int Pacmanx=13*PIXEL;
	public int Pacmany=23*PIXEL;
	public int score =0;
	public int countdot;
	public int count;
	public int counteat=0;
	public int indexSprite=0;
	private Level lv;
	public int level;
	public int live;
	public boolean dead=false;
	public boolean levelup=false;

	protected enum MoveMode {UP,DOWN,LEFT,RIGHT,NON};
	public MoveMode mode;
	public MoveMode moving;
	public Control control;

	Pacman(){
		mode = MoveMode.RIGHT;
		moving= MoveMode.NON;

		lv= new Level();
//		this.count= getcountdot();
		this.countdot=this.count;
		level=1;
		live=3;
	}

	public void move() {
		indexSprite++; // update sprite animation
		if(indexSprite == 5)
			indexSprite =0;
		
		if (!lv.checkborder(Pacmanx/PIXEL,Pacmany/PIXEL)) // check line and set movemode
			setMoveMode();
		else
			setPositionBorder();

		switch(moving) { // move
		case LEFT: this.Pacmanx-=SPEED;break;
		case RIGHT:this.Pacmanx+=SPEED;break;
		case UP:   this.Pacmany-=SPEED;break;
		case DOWN: this.Pacmany+=SPEED;break;
		case NON: if(indexSprite > 0) indexSprite--;break;
		}

	}

	public void setMoveMode() {

		boolean check=(Pacmanx%PIXEL==0)&&(Pacmany%PIXEL==0);

		int mapx=Pacmanx/PIXEL+1;
		int mapy=Pacmany/PIXEL;
		if (Pacmanx%PIXEL == 0)
			mapx --;


		if(mode!= moving) switch(mode) {
		case LEFT: if(lv.canRun(mapy,mapx-1,1)&&check) moving=mode;break;
		case RIGHT:if(lv.canRun(mapy,mapx+1,1)&&check) moving=mode;break;
		case UP: if(lv.canRun(mapy-1,mapx,1)&&check) moving = mode;break;
		case DOWN: if(lv.canRun(mapy+1,mapx,1)&&check) moving =mode;break;
		case NON: break;
		}

		switch(moving) {
		case LEFT:if(lv.checkMap(mapy,mapx-1,1)&&check) moving=MoveMode.NON;break;
		case RIGHT:if(lv.checkMap(mapy,mapx+1,1)&&check) moving=MoveMode.NON;break;
		case UP: if(lv.checkMap(mapy-1,mapx,1)&&check) moving=MoveMode.NON;break;
		case DOWN: if(lv.checkMap(mapy+1,mapx,1)&&check) moving=MoveMode.NON;break;
		case NON: break;
		}

		if(moving==MoveMode.LEFT&&mode==MoveMode.RIGHT) moving=MoveMode.RIGHT;
		else if(moving==MoveMode.RIGHT&&mode==MoveMode.LEFT) moving=MoveMode.LEFT;
		else if(moving==MoveMode.DOWN&&mode==MoveMode.UP) moving=MoveMode.UP;
		else if(moving==MoveMode.UP&&mode==MoveMode.DOWN) moving=MoveMode.DOWN;
	}

	public void setPositionBorder() {
		if(this.Pacmany==14*PIXEL)
		if(this.Pacmanx<=-PIXEL) this.Pacmanx=28*PIXEL;
		else if(this.Pacmanx>=28*PIXEL+SPEED) this.Pacmanx=-PIXEL+SPEED;

	}

	public void draw(Graphics g){
		g.setColor(Color.white);
		g.drawString("score : " + score, 100, 770);
		
		switch(moving) {
		case RIGHT:g.drawImage(Picture.pacman[indexSprite], Pacmanx,Pacmany,PIXEL,PIXEL,null);break;
		case LEFT:g.drawImage(Picture.pacman[indexSprite+10], Pacmanx,Pacmany,PIXEL,PIXEL,null);break;
		case UP:g.drawImage(Picture.pacman[indexSprite+5], Pacmanx,Pacmany,PIXEL,PIXEL,null);break;
		case DOWN:g.drawImage(Picture.pacman[indexSprite+15], Pacmanx,Pacmany,PIXEL,PIXEL,null);break;
		case NON:{
			switch(mode) {
			case RIGHT:g.drawImage(Picture.pacman[indexSprite], Pacmanx,Pacmany,PIXEL,PIXEL,null);break;
			case LEFT:g.drawImage(Picture.pacman[indexSprite+10], Pacmanx,Pacmany,PIXEL,PIXEL,null);break;
			case UP:g.drawImage(Picture.pacman[indexSprite+5], Pacmanx,Pacmany,PIXEL,PIXEL,null);break;
			case DOWN:g.drawImage(Picture.pacman[indexSprite+15], Pacmanx,Pacmany,PIXEL,PIXEL,null);break;
			}
		}
		}
		
	}
	public boolean checkDead(int x,int y) {
		if(x-PIXEL<=this.Pacmanx&&this.Pacmanx<=x+PIXEL&&y-PIXEL<=this.Pacmany&&this.Pacmany<=y+PIXEL) this.live--;
		return live == 0;
	}

	public int getcountdot() {
		int count=0;
		for(int i=0;i<31;i++)
			for(int j=0;j<29;j++) if(lv.checkMap(i, j, 0)||lv.checkMap(i,j,3)) count++;
		return count;
	}
	public void setscore(Level lv) {
		boolean check=(Pacmanx%PIXEL==0)&&(Pacmany%PIXEL==0);
		int mapx=Pacmanx/PIXEL;
		int mapy=Pacmany/PIXEL;
		if(check&&!lv.checkborder(mapx, mapy))
		if(lv.checkMap(mapy, mapx, 0)) {
		score+=10;
		this.countdot--;
		lv.setMap(mapy, mapx, 2);
		}
		else if(lv.checkMap(mapy, mapx, 3)) {
			score+=10;
			this.countdot--;
			lv.setMap(mapy, mapx, 4);
		}
	}
	public void setPosition(int x,int y) {
		this.Pacmanx=x;
		this.Pacmany=y;
	}
	public boolean getlevelup() {
		if(countdot==0) {this.countdot=this.count;return true;}
		return false;
	}
	public void setModemove(MoveMode m) {
		mode = m;
	}
//	public Bitmap getPacman(boolean i,movemode j) {
//		if(i)
//			return getImage(j);
//		return oval;
//	}
	public boolean isStop() {
		return moving == MoveMode.NON;
	}
	
}
