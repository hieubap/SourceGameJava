package Main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Key extends KeyAdapter{
	public Control control;
	
	
	public Key(Control control) {
		this.control = control;
	}
	
	public void keyPressed(KeyEvent key) {
		if(!control.mouse.click) return;
			
			int code = key.getKeyCode();
			switch(code) {
		case KeyEvent.VK_1:
		case KeyEvent.VK_NUMPAD1:
			control.data[control.mouse.x + control.mouse.y*9] = 1;break;
		case KeyEvent.VK_2:
		case KeyEvent.VK_NUMPAD2:
			control.data[control.mouse.x + control.mouse.y*9] = 2;break;
		case KeyEvent.VK_3:
		case KeyEvent.VK_NUMPAD3:
			control.data[control.mouse.x + control.mouse.y*9] = 3;break;
		case KeyEvent.VK_4:
		case KeyEvent.VK_NUMPAD4:
			control.data[control.mouse.x + control.mouse.y*9] = 4;break;
		case KeyEvent.VK_5:
		case KeyEvent.VK_NUMPAD5:
			control.data[control.mouse.x + control.mouse.y*9] = 5;break;
		case KeyEvent.VK_6:
		case KeyEvent.VK_NUMPAD6:
			control.data[control.mouse.x + control.mouse.y*9] = 6;break;
		case KeyEvent.VK_7:
		case KeyEvent.VK_NUMPAD7:
			control.data[control.mouse.x + control.mouse.y*9] = 7;break;
		case KeyEvent.VK_8:
		case KeyEvent.VK_NUMPAD8:
			control.data[control.mouse.x + control.mouse.y*9] = 8;break;
		case KeyEvent.VK_9:
		case KeyEvent.VK_NUMPAD9:
			control.data[control.mouse.x + control.mouse.y*9] = 9;break;
			
		}
			control.repaint();
	}
	
}
