package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Control extends JPanel{
	public static enum MODE {EASY,NORMAL,HARD};
	public static final int PIXEL = 50;
	
	public MODE mode = MODE.EASY;
	public JButton checkans,answer,newgame;
	public JFrame frame;
	public ChooseMode choosemode;
//	public Memory memo;
	
	public int[] data,ans
	= {
			0,0,0, 1,0,0, 0,9,0,
			0,0,0, 0,0,4, 8,0,0,
			8,0,7, 3,0,0, 0,0,0,
			
			0,2,0, 0,9,0, 1,0,0,
			4,0,0, 0,2,6, 0,0,0,
			0,6,0, 0,0,0, 0,0,0,
			
			0,0,5, 0,0,0, 0,0,4,
			0,3,4, 0,0,0, 0,0,1,
			0,0,0, 0,0,0, 9,6,2,
			};
	public boolean consts[];
	public Mouse mouse;
	public Key key;
	public boolean check = false;
	
	public Control() {
		
		mouse = new Mouse(this);
		key = new Key(this);
		
		consts = new boolean[9*9];
		
		data = new int[9*9];
		ans = new int[9*9];
		
		setLayout(null);
		checkans = new JButton("Check");
		checkans.setFocusable(false);
		checkans.setBounds(100,550,100,70);
		checkans.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				check = !check;
				repaint();
			}
		});
		answer = new JButton("Answer");
		answer.setFocusable(false);
		answer.setBounds(210,550,100,70);
		answer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<81;i++)
					data[i] = ans[i];
				check = false;
				repaint();
			}
		});
		
		newgame = new JButton("New Game");
		newgame.setFocusable(false);
		newgame.setBounds(320,550,100,70);
		newgame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				choosemode.setVisible(true);
			}
		});
		
		add(checkans);
		add(answer);
		add(newgame);
		
		frame = new JFrame();
		
		frame.add(this);
		frame.setTitle("Sudoku");
		frame.setSize(600,700);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		addMouseListener(mouse);
		addKeyListener(key);
		setFocusable(true);
		
//		init();
//		Ans();
		System.out.println("finish init");
		choosemode = new ChooseMode(this);
	}
	public void Ans() {

		for(int i=0;i<81;i++)
			if(ans[i]>0)
				consts[i] = true;
			else
				consts[i] = false;
		solveBack();
	}
	public void init() {
		check = false;
		for(int i=0;i<81;i++) {
			ans[i] = 0;
			consts[i] = false;
		}
		
		ranBox(0);
		ranBox(4);
		ranBox(8);
		
		long start = System.currentTimeMillis();
		solveBack();
		start = start - System.currentTimeMillis();
		System.out.println("time = " + start + " ms");
		
		for(int i=0;i<81;i++) {
			consts[i] = true;
		}
		int appear = 0;
		switch(mode) {
		case EASY: appear = 38;break;
		case NORMAL: appear = 28;break;
		case HARD: appear = 23;break;
		
		}
		
		for(int i=0;i<81;i++)
			data[i] = ans[i];
		
		Random ran = new Random();
		ArrayList<Integer> array = new ArrayList<Integer>();
		for(int i=0;i<81;i++)
			array.add(i);
		
		for(int i=0;i<appear;i++)
			array.remove(ran.nextInt(81-i));
		
		for(int i=0;i<81-appear;i++) {
			consts[array.get(i)] = false;
			data[array.get(i)] = 0;
		}
	}
	public void ranBox(int in) {
		Random ran = new Random();
		int x = (in%3)*3;
		int y = (in/3)*3;
		
		ArrayList<Integer> array = new ArrayList<Integer>();
		for(int i=0;i<9;i++)
			array.add(i);
		
		for(int i=0;i<9;i++)
		{ 
			int index = ran.nextInt(9-i);
			ans[x+i%3 + (y+i/3)*9] = array.get(index)+1;
			consts[x+i%3 + (y+i/3)*9] = true;
			array.remove(index);
		}
	}
	public boolean done() {
		boolean a = true;
		for(int i=0;i<9*9;i++)
			if(a) a &= consts[i];
			else break;
		return false;
	}
	public void solveBack() { // backstrack
		int count = 0;
		for(int i=0;i<81;i++) {
//			repaint();
			if(consts[i]) continue;
			ans[i] ++;
			while(Repeat(i%9, i/9)) {
				ans[i]++;
				count++;
				while(ans[i] >= 10) {
					ans[i] = 0;
					i--;
					while(consts[i]) {
						i--;
					}
					ans[i] ++;
					}
			}
		}
		System.out.println("count = " + count);
	}
	public boolean Repeat(int x,int y) {
		for(int i=0;i<9;i++)
			if((ans[x+y*9] == ans[x+i*9] && y != i)||(ans[x+y*9] == ans[i+y*9] && x != i))
				return true;
		
		int xx = (x/3)*3;
		int yy = (y/3)*3;
		for(int i=0;i<x%3+(y%3)*3;i++)
			if(ans[x+y*9] == ans[xx+i%3 + (yy+i/3)*9] && (xx+i%3) != x && (yy + i/3) != y)
				return true;
		return false;
	}
	
	public void paintComponent(Graphics g) {
//		System.out.println("click = " + mouse.click);
		super.paintComponent(g);
		
		g.setColor(Color.LIGHT_GRAY);
		if(mouse.click)
			g.fillRect(mouse.x*PIXEL + 50, mouse.y*PIXEL + 50, PIXEL, PIXEL);
		
		if(check)
			drawCheck(g.create(), 50, 50);
		
		drawAreaPlay(g, 50, 50);
//		answer.draw(g);
		
		System.out.println("repaint");
		
	}
	public void drawAreaPlay(Graphics g,int x,int y) {
		
		for(int i=0;i<9*9;i++)
			g.drawRect(x+(i%9)*PIXEL, y+(i/9)*PIXEL, PIXEL, PIXEL);
		
		
		g.setColor(Color.red);
		for(int i=0;i<9;i++)
			g.drawRect(x+(i%3)*PIXEL*3, y+(i/3)*PIXEL*3, PIXEL*3, PIXEL*3);
		
		g.setColor(Color.black);
		g.setFont(new Font(Font.DIALOG, Font.BOLD, PIXEL/3));
		for(int i=0;i<9*9;i++)
			if(data[i]!=0) {
				if(consts[i]) g.setColor(Color.gray);
				else g.setColor(Color.black);
				g.drawString(""+data[i], x+(i%9)*PIXEL+PIXEL/2-PIXEL/16, y+(i/9)*PIXEL+PIXEL/2+PIXEL/16);
			}
		g.setColor(Color.green);
		for(int i=1;i<4;i++)
		g.drawRect(x-i, y-i, 9*PIXEL+i*2, 9*PIXEL+i*2);
	}
	public void drawCheck(Graphics g,int x,int y) {
		Color red = new Color(255,60,60); 
		Color green = new Color(100,250,60); 
		
		
		for(int i=0;i<81;i++)
			{
			if(ans[i] != data[i]) {
				if(data[i] != 0) 
				{
					g.setColor(red);
					g.fillRect(x+(i%9)*PIXEL, y+(i/9)*PIXEL, PIXEL, PIXEL);
				}
			}
			else {
				if(data[i] != 0 && !consts[i])
				{
					g.setColor(green);
					g.fillRect(x+(i%9)*PIXEL, y+(i/9)*PIXEL, PIXEL, PIXEL);
				}	
			}
			}	
//		g.setColor(Color.black);
	}
	
	public static void main(String[] args) {
		Control c = new Control();
		
	}
}
