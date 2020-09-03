package Primary;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import Primary.Ghost.ModeGhost;
import Primary.Pacman.MoveMode;

public class Control extends JPanel implements ActionListener{
    public static final int PIXEL = 24;
    public static final int SPEED = 3;
    public static final int WIDTH = 700, HEIGHT = 840;
    public JFrame frame;

    private Pacman pm;
	private ArrayList<Ghost> ghost;
	private ArrayList<Point> powerfood;
	private Timer timer,delayEat;
	public long timeplay = 0;
	boolean eatghost = false,finish = false;
	public Picture picture = new Picture();
	private Level lv;
	
    
	public Control() {
    	pm=new Pacman();
        ghost = new ArrayList<Ghost>();
        int[] a = {1,1,1,29,26,1,26,29};

        for(int i=0;i<4;i++) {
        	Ghost gh0 = new Ghost(pm, Ghost.ModeGhost.normal, (12+i) * PIXEL, 14 * PIXEL,a[i*2],a[i*2+1]);
            ghost.add(gh0);
        }

        powerfood = new ArrayList<Point>();
        powerfood.add(new Point(1*PIXEL,3*PIXEL));
        powerfood.add(new Point(1*PIXEL,28*PIXEL));
        powerfood.add(new Point(26*PIXEL,3*PIXEL));
        powerfood.add(new Point(26*PIXEL,28*PIXEL));
        
        setmodemove(ModeGhost.scatter);

        lv=new Level();
        timer = new Timer(30, this);
        timer.start();
        
        delayEat = new Timer(800, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				repaint();
				delayEat.stop();
				timer.start();
			}
		});
        setVisible(true);
        setFocusable(true);
        addKeyListener(new Key());
        
        frame = new JFrame("Pacman");
//        frame.setUndecorated(true);
//        frame.setBackground(new Color(0,0,0,0));
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        
        frame.add(this);
        
        frame.setVisible(true);
    }
    public static void main(String[] args) {
    	new Control();
    }

    public void resetPosition(){
        int i=0;
        for (Ghost gh : ghost) {
            gh.setPositionGhost((12 + i) * PIXEL, 13 * PIXEL);
            i++;
        }
            pm.setPosition(13*PIXEL,23*PIXEL);
    }
    public void setmodemove(Ghost.ModeGhost modemove){
        for (Ghost gh : ghost) {
            	gh.setModemove(modemove);
        }
    }
    
    public void update() {
    	if(finish)
    		return;
    	if(!isScare())
    	timeplay ++; // time play
    	if(timeplay > 1000)
    		timeplay = 0;
    	
    	if (timeplay == 700){ // set mode ghost
            setmodemove(Ghost.ModeGhost.chase);
        }
        else if (timeplay == 50){
            setmodemove(Ghost.ModeGhost.scatter);
        }
    	
		if(!pm.dead)
		{
		pm.move();
		pm.setscore(lv);
		
		for (Ghost gh : ghost){ // check ghost colision pacman
		    gh.move();
		    if (gh.checkDead(pm.Pacmanx,pm.Pacmany)){
		        if (gh.modemove == Ghost.ModeGhost.scare){
		        	
		        	pm.counteat++;
		        	pm.score += 200*pm.counteat;
		        	eatghost = true;
		        	
		            gh.updatemodeGhost = Ghost.ModeGhost.back;
		            gh.GhostX = (gh.GhostX/PIXEL)*PIXEL;
                    gh.GhostY = (gh.GhostY/PIXEL)*PIXEL;
                    
                    delayEat.start();
                    timer.stop();

                }
		        else if (gh.modemove != Ghost.ModeGhost.back && gh.modemove != Ghost.ModeGhost.scare){
		            resetPosition();
		            pm.live --;
                }
            }
		}
		if(!checkScareAllGhost()) {
			pm.counteat = 0;
		}

		int i = powerfood.size();
		for(int j = 0;j<powerfood.size();j++){ // pacman eat power food
		    Rectangle rect = new Rectangle(powerfood.get(j).x,powerfood.get(j).y,PIXEL,PIXEL );
		    if (rect.contains(pm.Pacmanx+PIXEL/2,pm.Pacmany+PIXEL/2)){
		    	
		    	setmodemove(ModeGhost.scare);
		    	
                i = j;
                
            };
        }
		if(i!=powerfood.size())
		powerfood.remove(i);

		if(pm.live==0) {
			pm.dead=true;
			finish = true;
		}
		if(lv.checkWin()) {
			finish = true;
		}
		}
	}
    @Override
	public void paintComponent(Graphics g) {
    	super.paintComponent(g);
	    
    	setBackground(Color.black);
	    g.setColor(Color.white);
	    
    	g.drawString("Time : " + timeplay, 10, 770);
    	if(isChase()) {
    		g.setColor(Color.red);
    		g.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
    	    g.drawString("GHOST CHASE PACMAN", 250, 780);
    	}
	    
	    
		for(int y=0;y<31;y++)
			for(int x=0;x<28;x++) {
				if(lv.checkMap(y, x, 1)) {
					g.setColor(Color.darkGray);
					g.fillRect(x*PIXEL, y*PIXEL, PIXEL, PIXEL);
				}
				else if(lv.checkMap(y, x, 0)||lv.checkMap(y, x, 3)) {
					g.setColor(Color.white);
					g.fillOval(x*PIXEL+5, y*PIXEL+5, PIXEL/3, PIXEL/3);
					}
				
			}

		g.setColor(Color.blue);
        for(Point point: powerfood){
        	g.fillRect(point.x+3,point.y+3,PIXEL*2/3,PIXEL*2/3);
        }
        
		pm.draw(g);
		for(int i=0;i<pm.live;i++)
			g.drawImage(Picture.pacman[0], 500 + 30*i, 750, null);
		
        for (Ghost gs : ghost){
            gs.draw(g);
        }
        
        if(finish) {
        	if(pm.dead)
	    	drawEndGame(g);
        	else
        		drawWinGame(g);
	    }

    }
    public void drawWinGame(Graphics g) {
    	g.drawImage(Picture.wingame, 50, 300, null);
    }
    public void drawEndGame(Graphics g) {
    	g.drawImage(Picture.gameover, 50, 300, null);
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		update();
		repaint();
		
	}
	
	public class Key extends KeyAdapter{
		public void keyPressed(KeyEvent k) {
			int e = k.getKeyCode();
			if(e == KeyEvent.VK_UP) {
				pm.setModemove(MoveMode.UP);
			}
			if(e == KeyEvent.VK_DOWN) {
				pm.setModemove(MoveMode.DOWN);
			}
			if(e == KeyEvent.VK_LEFT) {
				pm.setModemove(MoveMode.LEFT);
			}
			if(e == KeyEvent.VK_RIGHT) {
				pm.setModemove(MoveMode.RIGHT);
			}
			
			
		}
	}
	public boolean checkScareAllGhost() {
		for(Ghost gh : ghost) {
			if(gh.modemove == ModeGhost.scare)
				return true;
		}
		return false;
	}
	public boolean isScare(){
		for(Ghost g: ghost) {
			if(g.modemove == ModeGhost.scare)
				return true;
		}
		return false;
	}
	public boolean isChase(){
		for(Ghost g: ghost) {
			if(g.modemove == ModeGhost.chase)
				return true;
		}
		return false;
	}
	
}
