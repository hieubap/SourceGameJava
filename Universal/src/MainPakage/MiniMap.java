package MainPakage;

import java.util.ArrayList;

public class MiniMap {
	private Rectangle map;
	private Rectangle play;
	private ArrayList<Rectangle> monster;
	
	
	public MiniMap(Game game) {
		monster = new ArrayList<Rectangle>();
	}
	public void update(Game game) {
		map = null;
		map = new Rectangle(game.getCamera().x, game.getCamera().y, 200, 200);
		map.generateGraphics(1, 0xCC3f3f);
		
		Rectangle rect;
		
		for(int i=0;i<game.getMonster().size();i++)
		{
			int x=game.getMonster().get(i).xPosition/10;
			int y=game.getMonster().get(i).yPosition/10;
			
			rect=null;
			rect = new Rectangle(game.getCamera().x+x, game.getCamera().y+y,3,3);
			rect.generateGraphics(2, 0x602fcc);
			
			monster.add(rect);
			
//			map.getPixels()[x+y*200]=0x602fcc;
			
		}
		int x=game.player.xPosition/10;
		int y=game.player.yPosition/10;
		play =null;
		play = new Rectangle(game.getCamera().x+x, game.getCamera().y+y,9,9);
		play.generateGraphics(2, 0xFFFFFF);
		
//		map.getPixels()[x+y*200]=0xFFFFFF;
		
	}
	public void render(RenderHandle render) {
		render.renderArray(map.getPixels(), 200, 200, map.x, map.y, 1, 1);
		render.renderArray(play.getPixels(), 9, 9, play.x, play.y, 1, 1);
		
		for(int i=0;i<monster.size();i++)
			render.renderArray(monster.get(i).getPixels(), 3, 3, 
					monster.get(i).x, monster.get(i).y, 1,1);
		monster.clear();
	}
}
