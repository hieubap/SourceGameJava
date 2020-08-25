package MainPakage;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Sprite {
	protected int[] pixels;
	protected int sizex,sizey;
	
	public Sprite(BufferedImage buffer) {
		sizex= buffer.getWidth();
		sizey= buffer.getHeight();
		
		pixels= new int[sizex*sizey];
		pixels= ((DataBufferInt) buffer.getRaster().getDataBuffer()).getData();
	}
	public Sprite(Sheet buffer,int startX,int startY,int w,int h) {
		sizex= w;
		sizey= h;
		
		pixels = new int[w*h];
		for(int i=0;i<h;i++)
			for(int j=0;j<w;j++)
				pixels[j+i*w]=buffer.getPixel((j+startX)+(i+startY)*buffer.getWidth());
		
	}
	public Sprite() {
		
	}
	
	public int[] getPixels() {
		return pixels;
	}
	public int getPixel(int i) {
		return pixels[i];
	}
	public int getWidth() {
		return sizex;
	}
	public int getHeight() {
		return sizey;
	}
	public void setPosition(int x,int y) {
		
	}
}
