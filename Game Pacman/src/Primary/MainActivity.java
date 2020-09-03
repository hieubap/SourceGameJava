package Primary;

import javax.swing.JFrame;

public class MainActivity extends JFrame {
	public static final int WIDTH = 200;
	public static final int HEIGHT = 100;
	
	public MainActivity() {
		add(new Control());
		
		setSize(1024,1600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		
	}
	
	public static void main(String[] args) {
		
		new MainActivity();
	}

}
