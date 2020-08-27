package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Control extends JPanel{
	public int PIXEL = 40,WIDTH = 700,HEIGHT = 700,scorex = 0,scoreo = 0;
	public JFrame frame;
	public char[][] data;
	public Mouse mouse;
	public Camera camera;
	
	public Control() {
		
		camera = new Camera(50*PIXEL/2-WIDTH/2, 50*PIXEL/2-HEIGHT/2, 10,this);
		mouse = new Mouse(this);
		frame = new JFrame();
		frame.setSize(WIDTH,HEIGHT);
		frame.setTitle("CARO");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		frame.add(this);
		data = new char[50][50];
		for(int i=0;i<50;i++)
			for(int j=0;j<50;j++)
				data[i][j] = ' ';
		
		addMouseListener(mouse);
		addKeyListener(camera);
		setFocusable(true);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(int i = 0;i < 50 ; i++)
			for(int j= 0;j < 50;j++)
			{
				g.setColor(Color.black);
				g.drawRect(i*PIXEL  - camera.x, j*PIXEL - camera.y, PIXEL,PIXEL);
				if(data[i][j] == 'x')
					{
					drawX(g, i*PIXEL-camera.x, j*PIXEL-camera.y);
					}
				if(data[i][j] == 'o') {
					drawO(g, i*PIXEL-camera.x, j*PIXEL-camera.y);
				}
				}
		
		if(mouse.turnx) {
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(600, 600, 100, 50);
			g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
			g.setColor(Color.red);
			g.drawString("TURN X", 610, 640);
		}
		else {
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(600, 600, 100, 50);
			g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
			g.setColor(Color.black);
			g.drawString("TURN O", 610, 640);
		}
		
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(10, 600, 100, 50);
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		g.setColor(Color.red);
		g.drawString(""+scorex, 30, 640);
		g.setColor(Color.black);
		g.drawString(": "+scoreo, 60, 640);
		
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(200, 600, 160, 50);
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		g.setColor(Color.black);
		g.drawString("press H to Help", 210, 630);
	}
	public void drawX(Graphics g,int x,int y) {
		g.setColor(Color.red);
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
		{
			g.drawLine(x+6+i, y+5+1, x+PIXEL-5-3+j, y+PIXEL-5-1);
			g.drawLine(PIXEL+x-5-2+i, y+5+1, x+5+j, y+PIXEL-5-1);
			
			
		}
		
	}
	public void drawO(Graphics g,int x,int y) {
		g.setColor(Color.black);
		for(int i=0;i<4;i++)
			for(int j=0;j<4;j++)
		{
			g.drawOval(x+i+3, y+3+j, PIXEL-6-i*2, PIXEL-6-j*2);
		}
		
	}
	
	
	
	public void update() {
		if(check('x')) {
			System.out.println("x thang");
			scorex++;
			JOptionPane.showMessageDialog(null, "X chiến thắng");
			for(int i=0;i<50*50;i++) {
				data[i%50][i/50] = ' ';
			}
			repaint();
		}
		if(check('o')) {
			System.out.println("o thang");
			scoreo++;
			JOptionPane.showMessageDialog(null, "O chiến thắng");
			for(int i=0;i<50*50;i++) {
				data[i%50][i/50] = ' ';
			}
			repaint();
		}
	}
	
	public boolean check(char character) {
		for(int i=0;i<50;i++)
			for(int j=0;j<50;j++) {
				if(data[i][j] != ' ') {
					
					// left
					int count = 0;
					for(int k=0;k<5;k++)
					{
						if(i-4 > 0)
						{
							if(data[i-k][j] == character) count ++;
							else break;
						}
					}
					if(count >= 5) return true;
					

					// right
					count = 0;
					for(int k=0;k<5;k++)
					{
						if(i+4 < 49)
						{
							if(data[i+k][j] == character) count ++;
							else break;
						}
					}
					if(count >= 5) return true;

					// up
					count = 0;
					for(int k=0;k<5;k++)
					{
						if(j-4 > 0)
						{
							if(data[i][j-k] == character) count ++;
							else break;
						}
					}
					if(count >= 5) return true;

					// down
					count = 0;
					for(int k=0;k<5;k++)
					{
						if(j+4 < 49)
						{
							if(data[i][j+k] == character) count ++;
							else break;
						}
					}
					if(count >= 5) return true;
					
					// X1
					count = 0;
					for(int k=0;k<5;k++)
					{
						if(j+4 < 50 && i+4 < 50)
						{
							if(data[i+k][j+k] == character) count ++;
							else break;
						}
					}
					if(count >= 5) return true;
					
					// X2
					count = 0;
					for(int k=0;k<5;k++)
					{
						if(j-4 >= 0 && i+4 < 50)
						{
							if(data[i+k][j-k] == character) count ++;
							else break;
						}
					}
					if(count >= 5) return true;
					
				}
			}
		return false;
	}

	public static void main(String[] args) {
		new Control();
	}
}
