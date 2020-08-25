package MainPakage;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;

public class KeyBoard extends KeyAdapter implements KeyListener,FocusListener{
	private boolean[] k = new boolean[120];
//	private int get;
	
	public void focusLost(FocusEvent fe) {
		for(int i=0;i<120;i++)
			k[i] = false;
	}
	public void focusGained(FocusEvent fe) {
		
	}
	public void keyPressed(KeyEvent key) {
		int ke= key.getKeyCode();
//		get=ke;
		
		k[ke]=true;
	}
	public void keyReleased(KeyEvent key) {
		int ke = key.getKeyCode();
		
		k[ke]=false;
	}
	public boolean isRight() {
		return k[KeyEvent.VK_RIGHT];
	}
	public boolean isLeft() {
		return k[KeyEvent.VK_LEFT];
	}
	public boolean isUp() {
		return k[KeyEvent.VK_UP];
	}
	public boolean isDown() {
		return k[KeyEvent.VK_DOWN];
	}
	
	public void control(Game game) {
		boolean onerun=true;
		if(isLeft()&&game.player.xPosition>=0) {
			game.player.xPosition-=05;
			game.player.direction=1;
			onerun=false;
		}
		else if(isRight()&&game.player.xPosition<=2000) {
			game.player.direction=3;
			game.player.xPosition+=05;
			onerun=false;
		}
		if(isUp()&&game.player.yPosition>=0) {
			game.player.yPosition-=05;
			if(onerun)
				game.player.direction=0;
		}
		else if(isDown()&&game.player.yPosition<=2000) {
			game.player.yPosition+=05;
			if(onerun)
				game.player.direction=2;
		}
		
		
		if(k[KeyEvent.VK_S]) {
			game.getMap().saveMap();
			k[KeyEvent.VK_S]=false;
		}
		if(k[KeyEvent.VK_D]) {
			game.getRender().changeDesign();
			k[KeyEvent.VK_D]=false;
		}
		
		
		
		// skill
		if(k[KeyEvent.VK_Z]) {
			game.power.attack(1);
			k[KeyEvent.VK_Z]=false;
		}
		if(k[KeyEvent.VK_X]) {
			game.power.attack(0);
			k[KeyEvent.VK_X]=false;
		}
		if(k[KeyEvent.VK_C]&&game.player.hastarget) {
			game.power.attack(2);
			k[KeyEvent.VK_C]=false;
		}
		
		
	}
}
