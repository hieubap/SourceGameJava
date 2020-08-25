package Main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mouse extends MouseAdapter{
	private Control control;
	public boolean turnx = true;
	
	public Mouse(Control control) {
		this.control = control;
	}
	
	public void mousePressed(MouseEvent mouse) {
		int x = mouse.getX()+control.camera.x;
		int y = mouse.getY()+control.camera.y;
		
		x = (x)/control.PIXEL;
		y = (y)/control.PIXEL;
		try {
		if(control.data[x][y] == ' ') {
		if(turnx)
			{
			control.data[x][y] = 'x';
			}
		else
			{
			control.data[x][y] = 'o';
			}
		turnx = !turnx;
		}
		
		}
		catch(ArrayIndexOutOfBoundsException arr) {
			
		}
		control.repaint();
		control.update();
		

//		System.out.println("click");
	}

}
