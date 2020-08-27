package Devolop;

import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Primary.Control;

public class Menu extends JFrame{
	private JTextField numbom,wd,ht;
	private Label lb,w,h;
	public JButton OK,cancel;
	private StartWindow startwindow;
	public int a,b,c,max = 30;
	public Menu(StartWindow start) {
		startwindow = start;
		lb = new Label("Bom:");
		lb.setBounds(30,50,50,30);
		w = new Label("Width:");
		w.setBounds(30,100,50,30);
		h = new Label("Height:");
		h.setBounds(30,150,50,30);
		
		numbom = new JTextField();
		numbom.setBounds(80, 55, 30, 20);
		wd = new JTextField();
		wd.setBounds(80, 105, 30, 20);
		ht = new JTextField();
		ht.setBounds(80, 155, 30, 20);
		ht.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
				b = Integer.valueOf(wd.getText());
				c = Integer.valueOf(ht.getText());
				
				if(c>10&&b>10&&b<max&&c<max) {
					numbom.setText(""+(Math.max(b, c)*2));
				}
				}
				catch(NumberFormatException ex) {
				}
			}
		});
		
		add(lb);
		add(w);
		add(h);
		
		add(wd);
		add(ht);
		add(numbom);
		
		OK = new JButton("OK");
		OK.setBounds(150, 200, 80, 40);
		OK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
				a =Integer.valueOf(numbom.getText());
				b = Integer.valueOf(wd.getText());
				c = Integer.valueOf(ht.getText());
				
				if(c<10||b<10||b>max||c>max||a>=b*c||a<Math.max(b, c)*2) {
					JOptionPane.showMessageDialog(null, "dữ liệu nhập sai");
					return;
				}
				
				JOptionPane.showMessageDialog(null, "đã thiết lập");
				startwindow.w = b;
				startwindow.h = c;
				startwindow.b = a;
				startwindow.p = (Control.WIDTH - 400)/Math.max(b,c);
				
				setVisible(false);
				startwindow.setVisible(true);
				}
				catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "dữ liệu sai định dạng");
				}
			}
		});
		
		cancel = new JButton("Cancel");
		cancel.setBounds(50, 200, 80, 40);
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				startwindow.setVisible(true);
				setVisible(false);
			}
		});
		
		add(OK);
		add(cancel);
		setTitle("Game Gỡ Mìn");
		setSize(300,400);
		setLocationRelativeTo(null);
		setLayout(null);
		setVisible(false);
	}
//	public Menu(ActionListener actionListener) {
//		
//	}
	
}
