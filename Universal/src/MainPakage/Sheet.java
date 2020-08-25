package MainPakage;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Sheet {
	private int[] pixels;
	private BufferedImage image;
	public int column,line,sizexsheet,sizeysheet;
	private Sprite[] loadSprite;
	
	
	public Sheet(BufferedImage buffer,int a,int b) {
		image = buffer;
		
		
		sizexsheet= buffer.getWidth();
		sizeysheet= buffer.getHeight();
		pixels = new int[sizexsheet*sizeysheet];
		pixels = ((DataBufferInt) buffer.getRaster().getDataBuffer()).getData();
		
		column=sizexsheet/a;
		line= sizeysheet/b;
		loadSprite = new Sprite[column*line];
		
		for(int i=0;i<line;i++)
			for(int j=0;j<column;j++)
				loadSprite[i*column+j] = new Sprite(this,j*a,i*b,a,b);
//		for(int i=0;i<buffer.getHeight()();i++)
//			for(int j=0;j<buffer.getWidth();j+=)
//			sprites[j+i*spritesheet.column] = new Sprite(spritesheet,j*spritesheet.spritesizex,i*spritesheet.spritesizey,
//					spritesheet.spritesizex,spritesheet.spritesizey,0);
	}
	public Sprite getSprite(int i,int j) {
		return loadSprite[i+j*column];
	}
	public Sprite getSprite(int i) {
		return loadSprite[i];
	}
	public int getWidth() {
		return sizexsheet;
	}
	public int getHeight() {
		return sizeysheet;
	}
	public int getColumn() {
		return column;
	}
	public int getLine() {
		return line;
	}
	public int getPixel(int i) {
		return pixels[i];
	}
	
}
