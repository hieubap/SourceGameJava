package MainPakage;

import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class Map {
	private ArrayList<Tile> tileset = new ArrayList<Tile>();
	private Sheet sheet;
	private File filesave;
	
	public Map(File filename) {
		sheet = new Sheet(loadImage("/Image/dungeon_sheet.png"), 64,64);
		filesave=filename;
		
		try {
			Scanner sc = new Scanner(filename);
			
			String line= sc.nextLine();
			
			while(sc.hasNextLine()) {
				if(line.startsWith("//"))
					continue;
			String[] split = line.split(",");
			tileset.add(new Tile(sheet.getSprite(
					Integer.parseInt(split[0]),
					Integer.parseInt(split[1])),
					Integer.parseInt(split[2]),
					Integer.parseInt(split[3]),
					Integer.parseInt(split[0]),
					Integer.parseInt(split[1])));
			line = sc.nextLine();
		}
		sc.close();
		return;
		}
		catch(IOException e) {
			e.printStackTrace();
			
		}
		catch(NoSuchElementException e) {
			e.printStackTrace();
		}
		
		
	}
	public void render(RenderHandle render) {
		Rectangle2D rect = 
				new Rectangle2D.Float(render.camera.x-64,render.camera.y-64,
						render.camera.w+64,render.camera.h+64);
		
		for(int i=0;i<tileset.size();i++)
			if(rect.contains(tileset.get(i).xPosition,tileset.get(i).yPosition))
			render.renderSprite(tileset.get(i).getSprite(), 
					tileset.get(i).xPosition,
					tileset.get(i).yPosition,
					1,1);
	}
	public void saveMap() {
		try {
			FileWriter fw = new FileWriter(filesave);
			
			for(int i=0;i<tileset.size();i++)
				fw.write(tileset.get(i).tilex+","
			+tileset.get(i).tiley+","
			+tileset.get(i).xPosition+","
			+tileset.get(i).yPosition+"\n");
			
				fw.write("// end");
				fw.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
	}
	public void addTile(int xPosition,int yPosition,int x,int y) {
		for(int i=0;i<tileset.size();i++)
			if(tileset.get(i).xPosition==xPosition&&
					tileset.get(i).yPosition==yPosition)
			{
				tileset.remove(i);
				break;
			}
		tileset.add(new Tile(sheet.getSprite(x, y),
				xPosition, yPosition, x, y));
	}
	public class Tile{
		private Sprite sprite;
		public int xPosition,yPosition,tilex,tiley;
		
		public Tile(Sprite sprite,int xPosition,int yPosition,int x,int y) {
			this.sprite=sprite;
			this.xPosition=xPosition;
			this.yPosition=yPosition;
			tilex=x;
			tiley=y;
		}
		public Sprite getSprite() {
			return sprite;
		}
	}
	
	public BufferedImage loadImage(String path) {
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
}
