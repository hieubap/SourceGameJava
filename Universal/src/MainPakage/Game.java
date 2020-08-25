package MainPakage;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Game extends JFrame implements ActionListener {
//	public Game game;
	public static int del = 0;
	public Canvas canvas = new Canvas();
	private RenderHandle renderer;
	public Player player;
	private KeyBoard key;
	private Mouse mouse;
	private ArrayList<Thing> thing = new ArrayList<Thing>();
	private Map map;
	private ArrayList<Monster> monster = new ArrayList<Monster>();
	private File file;
	private Sheet sheetmonster,sheetmothermonster;
	public SuperPower power;
	private int target,choosetarget,addmonster=0;
	private ArrayList<Integer> monstertarget = new ArrayList<Integer>();
	private boolean initmonster=true;
	private MiniMap minimap;
	private Graphics graphics;
	private BufferStrategy buffer;
	
	
	public Game() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
//		setSize(900, 500);
		
		setLocationRelativeTo(null);
		add(canvas);
		setVisible(true);
		setTitle("Unviral");
		
		key = new KeyBoard();
		mouse= new Mouse();
		
		
		canvas.createBufferStrategy(3);
		canvas.setFocusable(true);
		
		file= new File("E:/tilesave.txt");
		map= new Map(file);
//		for(int i=0;i<50;i++)
//		monster.add(new Monster(100, 100, 1));
//		monster.setBegin(9);
		
		
		sheetmonster = new Sheet(loadImage("/Image/monster.png"), 64, 64);//
//		System.out.println("column: "+ sheetmonster.column + "; line:"+ sheetmonster.line);
		
//		System.out.println("column: "+ sheetmothermonster.column + "; line:"+ sheetmothermonster.line);
		
		
		for(int i=0;i<2;i++)
			monster.add(new Monster(i*10+950, 1280, 1,false));
		
		buffer= canvas.getBufferStrategy();
		graphics = buffer.getDrawGraphics();
		renderer = new RenderHandle(getWidth(),getHeight(),graphics);
		minimap = new MiniMap(this);
		
		Random ran = new Random();
		for(int i=0;i<20;i++)
		thing.add(new Thing(renderer.pic.sprite[3], Math.abs(ran.nextInt())%2000,
				Math.abs(ran.nextInt())%2000,16,1));
		
		player = new Player(1500,1500);
		power = new SuperPower(this);
		
		canvas.addMouseListener(mouse);
		canvas.addKeyListener(key);
		canvas.setFocusable(true);
		
	}
	public void update() {
		key.control(this);
		mouse.updateMouse(this);
		
		if(initmonster) {
			addmonster++;
			if(addmonster%100==0)
			creatmonster();
			
			if(addmonster>1000)
				initmonster=false;
		}
		
		for(int i=0;i<monster.size();i++) 
			{
			monster.get(i).update(this);
			monster.get(i).istarget(this);
			}
		
		choosetarget = -1;
//		System.out.println(monstertarget.size());
		for(int i=0;i<monstertarget.size();i++)
			if(monstertarget.get(i)!=0) {
				target = monstertarget.get(i);
				choosetarget = i;
			
			for(int j=i;j<monstertarget.size();j++)
			if(monstertarget.get(j)!=0&&monstertarget.get(j)<target) {
				target = monstertarget.get(j);
				choosetarget = j;
//				System.out.println(" ****");
			}
			
			break;
			}
		
		monstertarget.clear();
		if(choosetarget!=-1)
		player.setTarget(monster.get(choosetarget).xPosition, monster.get(choosetarget).yPosition);
		else {
			player.noTarget();
		}

		player.update(this);
		for(int i=0;i<thing.size();i++) {
			if(thing.get(i).destroy()) {
				thing.remove(i);
				continue;
			}
			thing.get(i).update(this);
		}

		minimap.update(this);
	}
	public void render() {
		buffer= canvas.getBufferStrategy();
		graphics = buffer.getDrawGraphics();
		super.paint(graphics);
		renderer.reset();
//		renderer.render(graphics);
//		renderer.renderBackground();
		map.render(renderer);
		minimap.render(renderer);
		
		power.render(renderer);
		if(renderer.isDesign())
			renderer.renderDesign();
		
		for(int i=0;i<thing.size();i++)
			thing.get(i).render(renderer);
		
		for(int i=0;i<monster.size();i++)
			monster.get(i).render(renderer, 1, 1);
//		System.out.println(thing.size());
		player.render(renderer);
//		System.out.println(renderer.isDesign());
		renderer.render(graphics,this);
		graphics.dispose();
		buffer.show();
		renderer.clear();
	}
	public static void main(String[] args) {
		Game game = new Game();
		Timer time = new Timer(15,game);
		time.start();
		
		game = null;
		System.gc();
	}
	public void actionPerformed(ActionEvent ac) {
		update();
		render();	
	}
	
	public BufferedImage loadImage(String path) {
		try {
			BufferedImage loadedImage = ImageIO.read(this.getClass().getResource(path));
			BufferedImage formatImage = new BufferedImage(loadedImage.getWidth(),
					loadedImage.getHeight(),BufferedImage.TYPE_INT_RGB);
			
			formatImage.getGraphics().drawImage(loadedImage,0,0,null);
			loadedImage = null;
			
			return formatImage;
			
		}catch(IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Rectangle getCamera() {
		return renderer.camera;
	}
	public Map getMap() {
		return map;
	}
	public RenderHandle getRender() {
		return renderer;
	}
	public Sheet getSheet(boolean i) {
		if(i) return sheetmothermonster;
		return sheetmonster;
	}
	public void setTarget(int x,int y) {
		monstertarget.add(x+y);
	}
	public ArrayList<Monster> getMonster(){
		return monster;
	}
	public ArrayList<Thing> getThing(){
		return thing;
	}
	public void addThing(int a,int b) {
		thing.add(new Thing(renderer.pic.sprite[3], a, b, 16, 1));
	}
	public void creatmonster() {
		monster.add(new Monster(950, 1280, 1, false));
	}
}
