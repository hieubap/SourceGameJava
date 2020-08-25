package MainPakage;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public class Thing extends Sprite{
	public int xPosition,yPosition;
	private boolean isTake=false;
	private int type,mode;
	
	public Thing(Sprite sprite,int x,int y,int type,int mode) {
		xPosition=x;
		yPosition=y;
		this.type=type;
		this.mode=mode;
		sizex= sprite.sizex;
		sizey= sprite.sizey;
		
		int size = sprite.pixels.length;
		pixels = new int[size];
		
		for(int i=0;i<size;i++)
		 this.pixels[i] = sprite.getPixel(i);
	}
	public void update(Game game) {
		if(isTake) return;
		Rectangle2D rect = new Rectangle(xPosition-game.player.width+20,
				yPosition-game.player.height+20,
				sizex+game.player.height-20,
				sizey+game.player.height-20);
		
		if(rect.contains(game.player.xPosition,game.player.yPosition)) {
			game.player.experience++;
			
			if(game.player.experience>30) {
				game.player.level=3;
			}
			else if(game.player.experience>20) {
				game.player.level=2;
			}
			else if(game.player.experience>10) {
				game.player.level=1;
			}
			game.getThing().remove(this);
			isTake=true;
		}
	}
	public void render(RenderHandle render) {
		if(isTake) {
			return;
		}
		render.renderArray(pixels, sizex,sizey, xPosition, yPosition, 1,1);
	}
	public boolean destroy() {
		return isTake;
	}
}
