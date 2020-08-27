package Devolop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Primary.Control;

public class StartWindow extends JFrame{
//	public static void main(String[] args) {
//		new StartWindow();
//		
//	}
	
	public int w=15,h=15,b=20,p = 40;
	
	private Menu menu;
	public StartWindow() {
		setTitle("Game Gỡ Mìn");
		setSize(500,400);
		setLocationRelativeTo(null);
//		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLayout(null);
		
		menu = new Menu(this);
		
		JButton button1 = new JButton("Start");
		button1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				JOptionPane.showMessageDialog(button1, "you click button massage");
				new Control(w,h,b,p);
				System.out.println(w+" "+h+" "+p);
				setVisible(false);
			}
		});
		button1.setBounds(200,50,100,50);
		
		JButton button2 = new JButton("Setting");
		button2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				menu.setVisible(true);
				setVisible(false);
//				JOptionPane.showConfirmDialog(button2, "you click button confirm");
			}
		});
		button2.setBounds(200,150,100,50);
		
		JButton button3 = new JButton("Exit");
		button3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		button3.setBounds(200,250,100,50);
		
		add(button1);
		add(button2);
		add(button3);
		
		setVisible(true);
		
	}
	

}
