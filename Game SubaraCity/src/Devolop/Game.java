package Devolop;

import Primary.Picture;
import Primary.Play;

@SuppressWarnings("serial")
public class Game{
	public Menu menu;
	public Play play;
	
	public Game() {

		
//		play = new Play(this);
		menu = new Menu(this);
	}
	public static void main(String[] args) {
//		Game gm= new Game();
		new Picture();
		new Play();
//		gm.setVisible(true);
		
	}
}