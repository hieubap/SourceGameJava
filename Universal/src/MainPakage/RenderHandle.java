package MainPakage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class RenderHandle {
	private BufferedImage view,rotateObject;
	public Rectangle camera;
	private int[] 	pixels,pixelrotate;
	public Picture pic = new Picture();
	public Rectangle2D tiletype;
	private Sprite tilesetmap;

	private boolean design=false;
	
	public RenderHandle(int width, int height,Graphics graphic) {
		view = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		rotateObject = new BufferedImage(20, 20, BufferedImage.TYPE_INT_RGB);
		
		camera = new Rectangle(0,0,width,height);
		
		pixels= ((DataBufferInt) view.getRaster().getDataBuffer()).getData();
//		for(int i=0;i<pixels.length;i++)
//			pixels[i]=0xFF00FF;
		
		pixelrotate= ((DataBufferInt) rotateObject.getRaster().getDataBuffer()).getData();
//		for(int i=0;i<pixelrotate.length;i++)
//			pixelrotate[i]=0;
		
		
		tiletype = new Rectangle2D.Float(0,0,24*16,10*16);
		tilesetmap = new Sprite(pic.loadImage("/Image/mini_dungeon_sheet.png"));
	}
	public RenderHandle(){
		tiletype = new Rectangle2D.Float(0,0,24*16,10*16);
		
//		tiletype= new Rectangle2D[240];
//		for(int i=0;i<10;i++)
//			for(int j=0;j<24;j++)
//				tiletype[i*24+j]= new Rectangle2D.Float(j*16,i*16, 16, 16);
	}
	public void reset() {
		for(int i=0;i<pixels.length;i++)
			pixels[i]=0x91B09A;
	}
	public void renderArray(int[] renderpixel,int renderwidth,int renderheight,int 	xposition,int yposition,int xzoom,int yzoom) {
		
		for(int y=0;y<renderheight;y++) 
			for(int x=0;x<renderwidth;x++) 
				for(int yz=0;yz<yzoom;yz++)
					for(int xz=0;xz<xzoom; xz++) 
						setPixel(renderpixel[x+y*renderwidth], (x*xzoom)+ xposition+xz, (y*yzoom)+yposition+yz);
						
	}
	public void render(Graphics graphics,Game game) {
		graphics.drawImage(view,0,0,view.getWidth(),view.getHeight(),null);
		
		graphics.setColor(Color.BLUE);
		graphics.setFont(new Font(Font.SANS_SERIF, 5, 20));
		
		graphics.drawString("exp : "+game.player.experience, 1200, 20);
		graphics.drawString("life: "+game.player.life, 1200, 60);
		
		if(design)
			for(int i=0;i<10;i++)
				for(int j=0;j<24;j++) {
					graphics.drawRect(j*16,i*16, 16,16);
				}
	}
	public void renderBackground() {
		renderArray(pic.sprite[0].getPixels(), pic.sprite[0].getWidth(),
				pic.sprite[0].getHeight(), 0,0,1,1);
	}
	
	private void setPixel(int pixel, int x, int y) {
		if(x>=camera.x&&y>=camera.y&& x<=camera.x+camera.w&&y<= camera.y+camera.h) {
		int pixelIndex = (x-camera.x) + (y-camera.y)*view.getWidth();
		
		if(pixels.length > pixelIndex && pixel != Game.del)
		pixels[pixelIndex]=pixel;
		}
		
	}
	public void renderSprite(Sprite sprite,int xPosition,int yPosition,int xZoom,int yZoom) {
		renderArray(sprite.getPixels(),sprite.getWidth(),sprite.getHeight(),xPosition,yPosition,xZoom,yZoom);
		
	}
	public void renderDesign() {
		if(design) {
			renderSprite(tilesetmap, camera.x, camera.y, 1, 1);
		}
	}
	public void changeDesign() {
		if(design) {
			
			design=false;
			return;
		}
		design=true;
	}
	public boolean isDesign() {
		return design;
	}
	public void clear() {
		for(int i=0;i<pixels.length;i++)
			pixels[i]=0;
	}
	
	//
	public void renderArrayrotate(int[] renderpixel,int renderwidth,int renderheight,int 	xposition,int yposition,int xzoom,int yzoom) {
		
		for(int y=0;y<renderheight;y++) 
			for(int x=0;x<renderwidth;x++) 
				for(int yz=0;yz<yzoom;yz++)
					for(int xz=0;xz<xzoom; xz++) 
						setPixelrotate(renderpixel[x+y*renderwidth], (x*xzoom)+ xposition+xz, (y*yzoom)+yposition+yz);
						
	}
	private void setPixelrotate(int pixel, int x, int y) {
		if(x>=camera.x&&y>=camera.y&& x<=camera.x+camera.w&&y<= camera.y+camera.h) {
		int pixelIndex = (x-camera.x) + (y-camera.y)*view.getWidth();
		
		if(pixels.length > pixelIndex && pixel != Game.del)
		pixelrotate[pixelIndex]=pixel;
		}
		
	}
}