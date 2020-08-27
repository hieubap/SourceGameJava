package Primary;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Control extends JPanel{
	public static int WIDTH = 1000, HEIGHT = 800;
	public int pixel = 30;
	private boolean[][] draw;
	private int level=0;
	private boolean win,finish;
	public int score=0,a,b,done=0,w,h;
	public boolean click[][];
	Rectangle2D backmenu;
	public JFrame frame;

	private Min bom;
	
	public void newgame() {
		for(int i=0;i<w+2;i++) {
			for(int j=0;j<h+2;j++)
			draw[i][j] = false;
		}
		done += 10;
		if(done >= w*h*3/5) {
			done -=10;
		}
		bom=new Min(w,h,done);
		
		level++;
		done=0;
		win=false;
		finish=false;
	}
	
	public Control(int w,int h,int b,int p) {
		draw= new boolean[w+2][h+2];
		pixel = p;
		this.w = w;
		this.h = h;
		this.done = b;
		
		setFocusable(true);
		addKeyListener(new key());
		addMouseListener(new Mouse());
		
		done = w*2-10;
		newgame();
		
		frame = new JFrame("Gỡ Min");
		
		frame.add(this);
		frame.setTitle("practics");
		frame.setSize(WIDTH,HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		}
	public Control(){
		w=20;
		h = 20;
//		this.run = m;
		draw= new boolean[w+2][h+2];
		
		setFocusable(true);
		addKeyListener(new key());
		addMouseListener(new Mouse());
		
		done = w*2-10;
		newgame();
		
		frame = new JFrame("Gỡ Min");
		
		frame.add(this);
		frame.setTitle("practics");
		frame.setSize(WIDTH,HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	public void draw(Graphics g) {
		Graphics2D g2d= (Graphics2D) g.create();
    	g2d.setColor(Color.lightGray);
    	g.setColor(Color.black);
    	
		for(int i=1;i<w+1;i++) // drawgrid
			for(int j=1;j<w+1;j++) {
				g.drawRect(pixel, pixel, i*pixel-1, j*pixel-1); 
				g2d.fillRect(i*pixel,j*pixel,pixel-1,pixel-1);
		}
		g.drawRect(pixel - 2, pixel-2, w*pixel+3, w*pixel+3);
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
		g.drawString("so qua bom can go:"+ String.valueOf(bom.getbom()), w*pixel+50, 100);
		g.drawString("cap do:"+ String.valueOf(level), w*pixel+50, 150);
		g.drawString("so bom da go:"+ done, w*pixel+50, 200);
		g.drawString("score: "+ score, w*pixel+50, 250);
		
}
	public void endgame(Graphics s) {
		s.setColor(Color.black);
		if(!win) {
		for(int i=1;i<w+1;i++)
			for(int j=1;j<w+1;j++)
				if(bom.checkmin(i, j)) s.fillOval(i*pixel+8, j*pixel+8, pixel/2, pixel/2);
		
		s.setColor(Color.LIGHT_GRAY);
		s.fillRect(WIDTH/2-200,HEIGHT/2, 400, 100);
		s.setColor(Color.black);
		s.drawString("nhan phim space de choi lai :((", WIDTH/2-150, HEIGHT/2+50);
		}
		else {
			s.setColor(Color.LIGHT_GRAY);
			s.fillRect(WIDTH/2-200, HEIGHT/2, 400, 80);
			s.setColor(Color.black);
			s.drawString("nhan phim space de tiep tuc choi :))", WIDTH/2-150, HEIGHT/2+50);
		}
	}
	public void play(Graphics s) {
		
		s.setColor(Color.darkGray);
		s.setFont(new Font(Font.SANS_SERIF, Font.BOLD, pixel/3));
		for(int i=1;i<w+1;i++)// main draw ********
			for(int j=1;j<h+1;j++) {
				if(draw[i][j]) {
					if(bom.getboard(i, j)==10||bom.getboard(i, j)==11) {
						s.setColor(Color.red);
						s.fillRect(i*pixel, j*pixel, pixel, pixel);
					}
					else if(bom.getboard(i, j)!=0)
					{
						s.setColor(Color.DARK_GRAY);
						s.fillRect(i*pixel,j*pixel, pixel-1, pixel-1);
						s.setColor(Color.white);
						s.drawString(bom.getboard(i, j)+"", i*pixel+pixel/3, j*pixel+pixel/3*2);
					}
					else {
						s.setColor(Color.DARK_GRAY);
						s.fillRect(i*pixel,j*pixel, pixel-1, pixel-1);
					}
				}
			}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
		play(g);
    	
		if(finish) {
    		endgame(g);
    	}
	}
	public static void main(String[] args) {
		new Control();
	}
	class Mouse extends MouseAdapter{
		public void mousePressed(MouseEvent m) 
		{
			a = m.getX()/pixel;
			b = m.getY()/pixel;
			// play game
			if(!finish&&(!bom.checkwin()||done!=bom.getbom())) 
			{
				if(a<1||b<1||b>=(h+1)||a>=(w+1)) 
				{
				}
				else if(m.getButton()==MouseEvent.BUTTON1) 
				{
					if(!draw[a][b]) 
					{
						draw[a][b] = true;
						
						if(bom.checkmin(a, b)) {
							finish=true;
							win = false;
							System.out.println("dead");
							repaint();
							return;
						}
						
						try {
							if(bom.getboard(a, b)==0) {
								boolean drawed[][] = bom.findArea(a, b);
								for(int i=1;i<w+2;i++)
									for(int j=1;j<w+2;j++)
										{
											if(drawed[i][j]&& bom.getboard(a, b)!= 0)
												draw[i][j] = drawed[i][j];
											else if(drawed[i][j])
											{
												if(bom.getboard(i-1, j-1)!=0)
												draw[i-1][j-1] = true;
												
												draw[i][j-1] = true;
												if(bom.getboard(i+1, j-1)!=0)
												draw[i+1][j-1] = true;
												
												draw[i-1][j] = true;
												
												draw[i+1][j] = true;
												if(bom.getboard(i-1, j+1)!=0)
												draw[i-1][j+1] = true;
												
												draw[i][j+1] = true;
												if(bom.getboard(i+1, j+1)!=0)
												draw[i+1][j+1] = true;
												
											}
										}
							}
						}catch(ArrayIndexOutOfBoundsException ex) {}
					}
				}
				else if(m.getButton()==MouseEvent.BUTTON3) 
				{
					if(!draw[a][b]) {
					draw[a][b] = !draw[a][b];
					bom.changestate(a,b);
					done++;
					}
					else if(bom.getboard(a, b)==11||bom.getboard(a, b)==10) {
						draw[a][b] = !draw[a][b];
						bom.changestate(a,b);
						done--;
					}
				}
			}
			
			if(bom.checkwin()) {
				finish=true;
				win=true;
			}
			System.out.println("click");
			repaint();
		}
	}
	
	class key extends KeyAdapter{
    	public void keyPressed(KeyEvent k) {
    		int e=k.getKeyCode();
    		if(e==' '&&finish) {
    			score+=done;
    			if(!bom.checkwin()) {
    				level = 0;
    				score =0;
    			}
    			newgame();
    			
    		}
    		
    		if(e=='S') {
    			newgame();
    		}
			repaint();
    	}
    }
}
