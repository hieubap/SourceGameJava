package MainPakage;

import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.Timer;

public class SuperPower implements ActionListener {
	private int a1,a2,type,count=0,mx,my,sx,sy,l,firstX,firstY;
	public int xPosition,yPosition;
	public boolean action = false;
	private float dx,dy,dt;
	private Rectangle rectrePosition;
	private boolean r,activeskill1=false,activeskill2=false;
	private Player player;
	private Timer time;
	private Game game;
	
	private ArrayList<Arrow> arrow = new ArrayList<Arrow>();
	private BufferedImage imageArrow;
	
	public SuperPower(Game game) {
		this.player = game.player;
		this.game= game;
	}
	
	public void attack(int skill) {
			switch(skill) {
			case 0:firstSkill(player.xPosition,player.yPosition);break;
			case 1:secondSkill(player.direction);break;
			case 2:thirdSkill();break;
			}
	}
	
	public void render(RenderHandle render) {
		if(activeskill1) {
			render.renderArray(rectrePosition.getPixels(), 20, 20, firstX, firstY, 1, 1);
		}
		if(activeskill2) {
			for(int i=0;i<arrow.size();i++)
			arrow.get(i).render(render);
		}
		
//		render.renderSprite(sprite, xPosition, yPosition, xZoom, yZoom);
	}
//	public void shoot(int x,int y,int mx,int my) {
//		Timer time = new Timer(1,this);
//		time.start();
//	}
	
	private void firstSkill(int x,int y) {
		if(!activeskill1) {
			firstX=x;
			firstY=y;
			rectrePosition = new Rectangle(x, y, 20, 20);
			rectrePosition.generateGraphics(3, 0xFF00FF);
			activeskill1=true;
			System.out.println("active");
		}
		else {
			player.xPosition= firstX;
			player.yPosition=firstY;
			activeskill1=false;
			System.out.println("run skill 1");
		}
	}
	
	public void secondSkill(int direction) {
		switch(direction) {
		case 0:{
			imageArrow = loadImage("/Image/muiten-up.png");
			arrow.add(new Arrow(imageArrow, player.xPosition+20, player.yPosition+20, direction,this,game));
			break;
		}
		case 1:{
			imageArrow = loadImage("/Image/muiten.png");
			arrow.add(new Arrow(imageArrow, player.xPosition, player.yPosition+20, direction,this,game));
			break;
		}
		case 2:{
			imageArrow = loadImage("/Image/muiten-down.png");
			arrow.add(new Arrow(imageArrow, player.xPosition+20, player.yPosition+20, direction,this,game));
			break;
		}
		case 3:{
			imageArrow = loadImage("/Image/muiten-right.png");
			arrow.add(new Arrow(imageArrow, player.xPosition, player.yPosition+20, direction,this,game));
			break;
		}
	}
	activeskill2=true;
	}
	public void thirdSkill() {
		player.time = new Timer(1,player);
		player.time.start();
	}
	public void fourSkill() {
		
	}
	public void fifthSkill() {
		
	}
	public void sixSkill() {
		
	}
	public class Arrow implements ActionListener{
		private SuperPower power;
		public int xPosition,yPosition,direction;
		public Sprite sprite;
		private Timer time;
		private Game game;
		
		public Arrow(BufferedImage image,int xpo,int ypo,int direction,SuperPower supo,Game game) {
			this.power = supo;
			this.game = game;
			sprite = new Sprite(image);
			xPosition=xpo;
			yPosition=ypo;
			this.direction=direction;
			time = new Timer(1,this);
			time.start();
		}
		public void actionPerformed(ActionEvent action) {
			update();
			attackmonster();
		}
		public void update() {
			switch(direction) {
			case 0:yPosition-=3; break;
			case 1:xPosition-=3; break;
			case 2:yPosition+=3; break;
			case 3:xPosition+=3; break;
			}
		}
		public void attackmonster() {
			if(isDestroy()) {
				power.arrow.remove(this);
				this.time.stop();
			}
			for(int i=0;i<game.getMonster().size();i++) {
				if(game.getMonster().get(i).isCollision(xPosition, yPosition))
					{
					time.stop();
					power.arrow.remove(this);
					Random ran = new Random();
					
					for(int j=0;j<10;j++)
					game.addThing(game.getMonster().get(i).xPosition+ran.nextInt()%50, 
							game.getMonster().get(i).yPosition+ran.nextInt()%50);
					
					game.getMonster().remove(i);
					
					}
				if(game.getMonster().size()==0)
					{
					
					}
			}
		}
		public void render(RenderHandle render) {
			render.renderSprite(sprite, xPosition, yPosition, 1, 1);
		}
		public boolean isDestroy() {
			if(xPosition>2000||yPosition>2000||xPosition<0||yPosition<0)
				return true;
			return false;
		}
	}
	
	private BufferedImage loadImage(String path) {
		try {
			BufferedImage loadedImage = ImageIO.read(this.getClass().getResource(path));
			BufferedImage formatImage = new BufferedImage(loadedImage.getWidth(),
					loadedImage.getHeight(),BufferedImage.TYPE_INT_RGB);
			
			formatImage.getGraphics().drawImage(loadedImage,0,0,null);
			
			return formatImage;
			
		}catch(IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void shot_enemy(int xPo,int yPo,int c,int d) {
		if(time!=null&&time.isRunning())
		time.stop();
		
		l=0;
		xPosition =xPo;
		yPosition = yPo;
		sx =xPo;
		sy = yPo;
		
		mx=c;
		my=d;
		float dx=(float)Math.abs(sx-c),dy=(float)Math.abs(sy-d);
		if(dx<dy) {
			dt=dx/dy;
			r=false;
		}
		else {
			dt=dy/dx;
			r=true;
		}
//		System.out.println("dx="+dx+" dy="+dy+" dt="+dt);
		rectrePosition = new Rectangle(xPosition, yPosition, 10, 10);
		rectrePosition.generateGraphics(5, 0xFF00FF);
		
		time = new Timer(3,this);
		time.start();
		activeskill2=true;
	}
	public void actionPerformed(ActionEvent action) {
		run();
		if(Math.abs(xPosition-mx)<5&&Math.abs(yPosition-my)<5)
			{
			this.action = false;
			activeskill2=false;
			time.stop();
			time = null;
			rectrePosition=null;
			System.out.println("stop +++");
			}
			
	}
	public void run() {
//		System.out.println(xPosition+"; "+yPosition);
		l++;
		int k= (int)(l*dt);
		if(r) {
			if(xPosition<mx) xPosition++;
			else if(xPosition>mx) xPosition--;
			
			if(yPosition<my) yPosition=sy+k;
			else if(yPosition>my) yPosition=sy-k;
			}
		else {
			if(xPosition<mx) xPosition=sx+k;
			else if(xPosition>mx) xPosition=sx-k;
			
			if(yPosition<my) yPosition+=1;
			else if(yPosition>my) yPosition-=1;
		}
//		System.out.println("x="+x+" y="+y+" l="+l+" k="+k+" dt="+dt+" Sx="+sx+" sy="+sy);
	}
}
