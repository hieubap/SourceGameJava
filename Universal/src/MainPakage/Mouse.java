package MainPakage;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;

public class Mouse extends MouseAdapter implements MouseListener{
	public int xPosition,yPosition,tilex,tiley;
	private Rectangle2D setmap = new Rectangle2D.Float(0,0,16*24,16*10);
	private boolean settile=false,settype=false;
	
	public void mousePressed(MouseEvent mouse) {
		xPosition = mouse.getX();
		yPosition = mouse.getY();
		
		if(setmap.contains(xPosition,yPosition)) {
			tilex=xPosition/16;
			tiley=yPosition/16;
			settype=true;
		}
		settile=true;
	}
	public void mouseReleased(MouseEvent mouse) {
		
	}
	public void updateMouse(Game game) {
		if(game.getRender().isDesign()&&settype&&settile&&!setmap.contains(xPosition,yPosition)) {
			game.getMap().addTile( (xPosition+game.getCamera().x)/64*64, 
				(yPosition + game.getCamera().y)/64*64,tilex, tiley);
			settile=false;
			}
		
	}
}
