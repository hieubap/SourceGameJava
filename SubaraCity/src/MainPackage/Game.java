package MainPackage;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Game{
	public Menu menu;
	public Play play;
	
	public Game() {
//		setTitle("SubaraCity");
//		setBounds(500, 0, 425, 730);
////		setSize(425, 730);
////		setLocationRelativeTo(null);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		getContentPane().setLayout(null);

		menu = new Menu(this);
//		add(menu);
//		menu.setBounds(0, 0, 425, 730);
	}
	public static void main(String[] args) {
		Game gm= new Game();
//		gm.setVisible(true);
		
	}
}