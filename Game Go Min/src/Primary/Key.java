package Primary;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Key extends KeyAdapter{
	public int xcamera,ycamera;
	public float speed = 2;
	public Control play;
	
	public Key(Control playgame) {
		play = playgame;
	}
	
	public void keyPressed(KeyEvent keyevent) {
		int code = keyevent.getKeyCode();
		switch(code) {
		case KeyEvent.VK_UP: ycamera-=speed;break;
		case KeyEvent.VK_DOWN: ycamera+=speed;break;
		case KeyEvent.VK_LEFT: xcamera-=speed;break;
		case KeyEvent.VK_RIGHT: xcamera+=speed;break;
		
		}
		
	}

}
