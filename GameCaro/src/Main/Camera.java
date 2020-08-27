package Main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

public class Camera extends KeyAdapter{
	public int x,y,speed;
	private Control control;
	
	public Camera(int x,int y,int speed,Control control) {
		this.control = control;
		this.x = x;
		this.y = y;
		this.speed = speed;
	}
	
	public void keyPressed(KeyEvent key)
	{
		int code = key.getKeyCode();
		switch(code) {
		case KeyEvent.VK_UP: y-=speed;break;
		case KeyEvent.VK_DOWN: y+=speed;break;
		case KeyEvent.VK_LEFT: x-=speed;break;
		case KeyEvent.VK_RIGHT: x+=speed;break;
		case KeyEvent.VK_ADD: control.PIXEL+=2;break;
		case KeyEvent.VK_SUBTRACT: control.PIXEL-=2;break;
		case KeyEvent.VK_H:{
			JOptionPane.showMessageDialog(null, 
					  "phim len :  dich man hinh len"+'\n'
					+ "phim xuong: dich man hinh xuong"+'\n'
					+ "phim trai : dich man hinh sang trai"+'\n'
					+ "phim phai : dich man hinh sang phai"+'\n'
					+ "phim + :    phong to"+'\n'
					+ "phim - :    thu nho");
			break;
		}
		}
		
		control.repaint();
	}
}
