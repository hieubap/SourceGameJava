package Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import Main.Control.MODE;

public class ChooseMode extends JFrame{
	private Control control;
	
	public ChooseMode(Control control) {
		this.control = control;
		
		JButton easy = new JButton("EASY");
		easy.setBounds(100, 50, 100, 70);
		easy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				control.mode = MODE.EASY;
				newgame();
				
			}
		});
		JButton normal = new JButton("NORMAL");
		normal.setBounds(100, 150, 100, 70);
		normal.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				control.mode = MODE.NORMAL;
				newgame();
			}
		});
		
		JButton hard = new JButton("HARD");
		hard.setBounds(100, 250, 100, 70);
		hard.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				control.mode = MODE.HARD;
				newgame();
				
			}
		});
		
		add(easy);
		add(normal);
		add(hard);
		setLayout(null);
		
		setSize(300,400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		
	}
	
	public void newgame() {
		control.init();
		control.repaint();
		setVisible(false);
	}
	

}
