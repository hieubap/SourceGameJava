package MainPakage;

public class AnimateSprite extends Sprite{
	protected Sprite[] sprite;
	protected int column,line,time=0,speed,current,begin,xPosition,yPosition,end=13;
	
	public AnimateSprite(Sheet sheet,int speed) {
		this.speed=speed;
		column=sheet.getColumn();
		line= sheet.getLine();
		sprite = new Sprite[column*line];
		
		for(int i=0;i<line;i++)
			for(int j=0;j<column;j++)
				sprite[i*column+j] = new Sprite(sheet, j*64, i*64, 64,64);
		
	}
	public AnimateSprite(Sheet sheet,int speed,int length) {
		this.speed=speed;
		column=length;
		line= sheet.getLine();
		sprite = new Sprite[column*line];
		
		for(int i=0;i<line;i++)
			for(int j=0;j<column;j++)
				sprite[i*column+j] = new Sprite(sheet, j*64, i*64, 64,64);
		
	}
	public AnimateSprite() {
		
	}
	public void setBegin(int i) {
		if(i==begin)
			return;
		begin = i;
		current=begin*column;
	}
	
	public void update(Game game) {
		time++;
		setPosition(game.player.xPosition, game.player.yPosition);
		if(time>=speed) {
			time=0;
			current++;
			if(current>=(begin+1)*column)
				current=begin*column;
		}
	}
	public void update(Game game,int nummove) {
		time++;
		end=nummove;
		setPosition(game.player.xPosition, game.player.yPosition);
		if(time>=4) {
			time=0;
			current++;
			if(current>=(begin+1)*column-end)
				current=begin*column;
		}
	}
	public void render(RenderHandle render,int x,int y) {
		render.renderSprite(sprite[current],xPosition,yPosition,x,y);
	}
	public void setPosition(int x,int y) {
		xPosition=x;
		yPosition=y;
	}
}
