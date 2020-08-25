package MainPakage;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public class Monster extends AnimateSprite{
	private final int rangeplayer = 500;
	private final int timechang=600;
	private int direction,timechange=0,timestop=0,life;
	private Random ran = new Random();
	private Sprite sprite;
	private boolean stop=false;
//	private Rectangle range;
	
	private boolean dead=false,mother,attack=false;
	
	public Monster(int xPosition,int yPosition,int type,boolean mother) {
		this.mother= mother;
		sprite = new Sprite();
		column = 13;
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		
		direction=Math.abs(ran.nextInt())%4;
		begin=8+direction;
		current=begin*column;
		speed=15;
	}
	public void istarget(Game game) {
		Rectangle2D check = new Rectangle2D.Float(xPosition-rangeplayer,yPosition-rangeplayer,
				rangeplayer*2+64,rangeplayer*2+64);
		if(check.contains(game.player.xPosition,game.player.yPosition)) {
			game.setTarget(Math.abs(xPosition- game.player.xPosition),
					Math.abs(yPosition - game.player.yPosition));
//			System.out.println(" +++");
		}
		else {
			game.setTarget(0, 0);
		}
		
	}
	public boolean isCollision(int x,int y) {
		Rectangle2D check = new Rectangle2D.Float(xPosition,yPosition,
				64,64);
		if(check.contains(x,y)) {
			
			return true;
		}
		return false;
	}
	
	public void update(Game game) {
		
		Rectangle2D rect = null;
		switch(direction) {
		case 0:
			rect = new Rectangle2D.Float(xPosition-200,
					yPosition-200,
					464,300);
			break;
		case 1:
			rect = new Rectangle2D.Float(xPosition-300,
					yPosition-200,
					300,464);
			break;
		case 2:
			rect = new Rectangle2D.Float(xPosition-200,
					yPosition+64,
					464,300);
			break;
		case 3:
			rect = new Rectangle2D.Float(xPosition+64,
					yPosition-200,
					300,464);
			break;
		}
		
		if(rect.contains(game.player.xPosition,game.player.yPosition)) {
			attack=true;
		}
		else attack = false;
		
		rect = null;
		rect = new Rectangle2D.Float(xPosition+10,yPosition+10,64,64);
		
		if(stop) {
			timestop++;
			if(timestop>100)
				{
				timestop=0;
				stop =false;
				}
			return;
		}
		
		if(attack&&rect.contains(game.player.xPosition, game.player.yPosition)) {
			game.player.life-=3;
			if(game.player.life<0)
				game.player.dead=true;
			attack =false;
			stop =true;
			
		}
		rect = null;
		
//		if(life<0) {
//			dead=true;
//			return;
//		}
		time++;
		timechange++;
		end=4;
		
		
		if(timechange>=timechang) {
			
			timechange=0;
			direction=Math.abs(ran.nextInt())%4;
			begin=8+direction;
			current=begin*column;
		}
		if(time>=8) {
			time=0;
			current++;
			
			if(current>=(begin+1)*column-end)
				current=begin*column;
			sprite=null;
			sprite = game.getSheet(false).getSprite(current);
		}
//		System.out.println(attack);
		if(attack) {
			if(xPosition<game.player.xPosition)
				{
				direction=3;
				xPosition+=3;
				}
			else if(xPosition>game.player.xPosition)
				{
				direction=1;
				xPosition-=3;
				}
			if(yPosition<game.player.yPosition)
				{
				direction=2;
				yPosition+=3;
				}
			else if(yPosition>game.player.yPosition)
				{
				direction=0;
				yPosition-=3;
				}
		}
		else 
		switch(direction) {
		case 0:{
			if(yPosition>0)
			yPosition-=1;break;
		}
		case 1:{
			if(xPosition>0)
			xPosition-=1;break;
		}
		case 2:{
			if(yPosition<2000)
			yPosition+=1;break;
		}
		case 3:{
			if(xPosition<2000)
			xPosition+=1;break;
		}
		}
	}
	public void render(RenderHandle render,int x,int y) {
//		render.renderArray(range.getPixels(), 464, 464, xPosition-200, yPosition-200, 1, 1);
		render.renderSprite(sprite, xPosition,yPosition, x,y);
//		Rectangle rect = new Rectangle(xPosition-200,
//				yPosition-200,464,200);
//		rect.generateGraphics(1, 0x00FF00);
//		render.renderArray(rect.getPixels(), 464, 200, xPosition-200, yPosition+64, 1,1);
	}
	public boolean isDead() {
		return dead;
	}
	
}
