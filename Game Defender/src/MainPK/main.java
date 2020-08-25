package MainPK;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class main extends JFrame{
	int a,b;
	float c;
	
	main(){
		add(new Player());
		
		setTitle("game phong thu");
		setSize(1000, 700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		main m= new main();
		m.setVisible(true);
		
	}

}
