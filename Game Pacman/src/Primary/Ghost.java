package Primary;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Ghost {
	public final int PIXEL = Control.PIXEL;
	public int SPEED = Control.SPEED;
	enum ModeGhost {normal,chase,scare,scatter,back};
	public ModeGhost modemove = ModeGhost.normal,updatemodeGhost = ModeGhost.normal;
	public int GhostX,scatterx,countScareDown = 400;
	public int GhostY,scattery;
	public int indexSprite = 0;// animation
	public int direction=0;
	public Level lv = new Level();
	private Finder finder;
	private Pacman pacman;
	private BufferedImage[] image;
	
	public Ghost(Pacman pacman,ModeGhost mode,int x,int y,int sx,int sy){
		this.GhostX=x;
		this.GhostY=y;
		this.direction=2;
		this.modemove = ModeGhost.normal;
		finder = new Finder(lv);
		this.scatterx = sx;
		this.scattery = sy;
		this.pacman = pacman;
		this.modemove = mode;
		
		
	}

	public void move() {
		indexSprite++; // update sprite animation
		if(indexSprite == 3)
			indexSprite = 0;
		
		if(modemove != updatemodeGhost && canSetLogic()) {
			modemove = updatemodeGhost;
		}
		
		SPEED = Control.SPEED;

		switch (modemove){ // set direction to move
			case normal:{
//				System.out.println("normal");
				if (!lv.checkborder(GhostX / PIXEL, GhostY / PIXEL))
					directionOnNomalMode();
				else
					positionOnBorder();
				break;
			}
			case chase:{
//				System.out.println("chase");
				if(GhostX%PIXEL == 0 && GhostY%PIXEL == 0) {

					int Ghostx = GhostX / PIXEL;
					int Ghosty = GhostY / PIXEL;

					direction = finder.getMove(Ghostx, Ghosty, pacman.Pacmanx / PIXEL, pacman.Pacmany / PIXEL);
				}
				break;
			}
			case scare:{
//				System.out.println("scare");
				SPEED = 1;
				if (!lv.checkborder(GhostX / PIXEL, GhostY / PIXEL))
					directionOnNomalMode();
				else
					positionOnBorder();
				
				countScareDown -- ;
				if (countScareDown == 0){
					countScareDown = 400;
					
					updatemodeGhost = ModeGhost.normal;
					
					System.out.println("run here");
					return;
				}

				break;
			}
			case scatter:{
//				System.out.println("scatter");
				if(GhostX%PIXEL == 0 && GhostY%PIXEL == 0) {

					int Ghostx = GhostX / PIXEL;
					int Ghosty = GhostY / PIXEL;

					direction = finder.getMove(Ghostx, Ghosty, scatterx, scattery);
					if (Ghostx == scatterx && Ghosty == scattery){
						updatemodeGhost = ModeGhost.normal;
						return;
					}
				}
				break;
			}
			case back:{
//				System.out.println("back mode");
				SPEED = 8;

				if(GhostX%PIXEL == 0 && GhostY%PIXEL == 0) {


					int Ghostx = GhostX / PIXEL;
					int Ghosty = GhostY / PIXEL;

					direction = finder.getMove(Ghostx, Ghosty, 13,14);

					if (Ghostx == 13 && Ghosty == 14){
						SPEED = Control.SPEED;
						updatemodeGhost = ModeGhost.normal;
						countScareDown = 700;
						return;
					}
//					System.out.println(" find line");
					
				}

				break;
			}
		}

		switch(direction) { // move
		case 1: GhostX+=SPEED;break;
		case 2: GhostY+=SPEED;break;
		case 3: GhostX-=SPEED;break;
		case 4: GhostY-=SPEED;break;
		}
	}
	public void draw(Graphics g){
		if(modemove == ModeGhost.scare) {
			if(countScareDown < 100 && countScareDown % 20 < 10)
				g.drawImage(Picture.scare[indexSprite+3], GhostX,GhostY,Control.PIXEL,Control.PIXEL,null);
			else
				g.drawImage(Picture.scare[indexSprite], GhostX,GhostY,Control.PIXEL,Control.PIXEL,null);
			
		}
		else if(modemove == ModeGhost.back) {
			g.drawImage(Picture.eye, GhostX,GhostY,Control.PIXEL,Control.PIXEL,null);
		}
		else {
		switch(direction) {
		case 1: g.drawImage(Picture.ghost[indexSprite+0], GhostX,GhostY,Control.PIXEL,Control.PIXEL,null);break;
		case 2: g.drawImage(Picture.ghost[indexSprite+3], GhostX,GhostY,Control.PIXEL,Control.PIXEL,null);break;
		case 3: g.drawImage(Picture.ghost[indexSprite+6], GhostX,GhostY,Control.PIXEL,Control.PIXEL,null);break;
		case 4: g.drawImage(Picture.ghost[indexSprite+9], GhostX,GhostY,Control.PIXEL,Control.PIXEL,null);break;
		}
		}
	}
	public void directionOnNomalMode() {
		int Ghostx=GhostX/PIXEL+1; // get logic in map
		int Ghosty=GhostY/PIXEL;
		if (GhostX%PIXEL == 0)
			Ghostx--;

		boolean check=(GhostX%PIXEL==0)&&(GhostY%PIXEL==0);
		if(check)
		if(lv.checkMap(Ghosty,Ghostx,3)||lv.checkMap(Ghosty,Ghostx,4)) {
			getRandomdirectionOnNomalMode();
			return;
		}

		switch(direction) {
		case 1: if(lv.checkMap(Ghosty,Ghostx+1,1)&&check) getRandomdirectionOnNomalMode();break;
		case 2: if(lv.checkMap(Ghosty+1,Ghostx,1)&&check) getRandomdirectionOnNomalMode();break;
		case 3: if(lv.checkMap(Ghosty,Ghostx-1,1)&&check) getRandomdirectionOnNomalMode();break;
		case 4: if(lv.checkMap(Ghosty-1,Ghostx,1)&&check) getRandomdirectionOnNomalMode();break;
		}
	}
	private void getRandomdirectionOnNomalMode() {
		int count=0;
		int oldmode=0;
		int Ghostx=GhostX/PIXEL;
		int Ghosty=GhostY/PIXEL;

		oldmode = (direction+2)%4;

		int dir[]= {0,0,0,0};

		for(int i=0;i<4;i++) {
			boolean check=false;

			switch(i) {
			case 0: if(lv.canRun(Ghosty,Ghostx+1,1)) check=true;break;
			case 1: if(lv.canRun(Ghosty+1,Ghostx,1)) check=true;break;
			case 2: if(lv.canRun(Ghosty,Ghostx-1,1)) check=true;break;
			case 3: if(lv.canRun(Ghosty-1,Ghostx,1)) check=true;break;

			}

			if((oldmode!=i+1)&&check) {
			dir[count]=i+1;
			count++;}
		}
		int ch = Math.abs((int)(Math.random()*count));

		direction=dir[ch];

	}
	public void positionOnBorder() {
		if(this.GhostY==14*PIXEL)
		if(this.GhostX<=-PIXEL) this.GhostX=28*PIXEL;
		else if(this.GhostX>=28*PIXEL+SPEED) this.GhostX=-PIXEL+SPEED;

	}
	public void setPositionGhost(int x,int y) {
		this.GhostX=x;
		this.GhostY=y;
	}

	public boolean checkDead(int x,int y) {
		return (x-PIXEL<=this.GhostX&&this.GhostX<=x+PIXEL&&y-PIXEL<=this.GhostY&&this.GhostY<=y+PIXEL);
	}
	
	public void setModemove(ModeGhost modemove){
		this.updatemodeGhost = modemove;
		if(modemove == ModeGhost.scare)
			countScareDown = 400;
	}
	public boolean canSetLogic() {
		return ((GhostX%PIXEL == 0 )&&( GhostY % PIXEL == 0));
	}
	public BufferedImage getImage(int direction,int indexSprite) {
		return image[direction*3+indexSprite];
	}
}
