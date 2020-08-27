package Main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mouse extends MouseAdapter{
	public int x,y,pixel;
	public boolean click = false;
	private Control control;
	
	public Mouse(Control control) {
		this.control = control;
		pixel = control.PIXEL;
	}
	
	public void mousePressed(MouseEvent mouse) {
		x = mouse.getX();
		y = mouse.getY();
		if(x < 50 || y < 50 || x > 49 + pixel*9 || y > 49 + pixel*9)
			{
			click = false;
			control.repaint();
			return;
			}
		
		x = (x-50)/pixel;
		y = (y-50)/pixel;
		System.out.println("click **");
		
		click = true;
		control.repaint();
	}

}
