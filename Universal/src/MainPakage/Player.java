package MainPakage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Timer;

public class Player implements ActionListener{
	public final int width=64,height=64;
	public int xPosition,yPosition,direction,nummove,point,experience=0,level=0;
	private BufferedImage image,imagearrow;
	private AnimateSprite animate;
	private Sheet sheet;
	private int directionrender,mode;
	public boolean isshoot=false,dead=false;
	private boolean shoot=false;
	public boolean hastarget = false;
	private Rectangle target;
	public Timer time;
	public int life=100;
	
	public Rectangle getTarget() {
		return target;
	}
	
	public Player(int x,int y) {
		xPosition=x;
		yPosition=y;
		direction=3;
		mode=8;
		nummove=4;
		image=loadImage("/Image/oldman.png");
		
		sheet = new Sheet(image, 64,64);
		
		animate = new AnimateSprite(sheet, 5);
		animate.setPosition(200, 100);
		
		image = null;
		sheet=null;
	}
	public void update(Game game) {
		game.getCamera().x=xPosition-game.getCamera().w/2;
		game.getCamera().y=yPosition-game.getCamera().h/2;
		directionrender=direction+mode;
		
		animate.setBegin(directionrender);
		
		animate.setPosition(game.getCamera().x+200, game.getCamera().y+200);
		
		
		animate.update(game,nummove);
		
	}
	public void setTarget(int xPosition,int yPosition) {
		
		target = new Rectangle(xPosition,yPosition,64,64);
		target.generateGraphics(5, 0xFFFF00);
		hastarget =true;
		
	}
	
	public void render(RenderHandle render) {
		
		if(hastarget) {
			render.renderArray(target.getPixels(), target.w, target.h, target.x, target.y, 1,1);
		}
//		
//		render.getGraphics().drawString("gooooooood", 0, 0);
		animate.render(render, 1, 1);
	}
	public void setMode(int j) {
		mode=j;
		if(mode==0)
			nummove=13-6;
		if(mode==4)
			nummove=13-7;
		if(mode==8)
			nummove=13-8;
		if(mode==12)
			nummove=13-6;
		if(mode==16)
			nummove=13-13;
		
	}
	public void noTarget() {
		target = null;
		hastarget = false;
	}
	public void actionPerformed(ActionEvent action) {
		if(xPosition<target.x) xPosition+=2;
		else if(xPosition>target.x) xPosition-=2;
		
		if(yPosition<target.y) yPosition+=2;
		else if(yPosition>target.y) yPosition-=2;
		
		if(Math.abs(xPosition-target.x)<10&&Math.abs(yPosition-target.y)<10)
			time.stop();
	}
	
	

	private BufferedImage loadImage(String path) {
		try {
			BufferedImage loadedImage = ImageIO.read(this.getClass().getResource(path));
			BufferedImage formatImage = new BufferedImage(loadedImage.getWidth(),
					loadedImage.getHeight(),BufferedImage.TYPE_INT_RGB);
			
			formatImage.getGraphics().drawImage(loadedImage,0,0,null);
			
			return formatImage;
			
		}catch(IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	// ham rac

	public void setSkin(int i) {
		image = null;
		sheet = null;
		System.gc();
		switch(i) {
		case 0:
			{
				image=loadImage("/Image/warior.png");
				sheet= new Sheet(image,64,64);
				animate= new AnimateSprite(sheet, 10);
				break;
			}
		case 1:
		{
			image=loadImage("/Image/ad.png");
			sheet= new Sheet(image,64,64);
			animate= new AnimateSprite(sheet, 10);
			break;
		}
		case 2:
		{
			image=loadImage("/Image/spell.png");
			sheet= new Sheet(image,64,64);
			animate= new AnimateSprite(sheet, 10);
			break;
		}
		case 3:
		{
			image=loadImage("/Image/oldman.png");
			sheet= new Sheet(image,64,64);
			animate= new AnimateSprite(sheet, 10);
			break;
		}
		}

		image = null;
		sheet = null;
		System.gc();
	}
}
