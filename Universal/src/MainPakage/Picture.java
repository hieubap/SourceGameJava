package MainPakage;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Picture {
//	public BufferedImage background,iconamo;
//	public int[] backg;
	public Sprite[] sprite = new Sprite[8];
	
	public Picture() {
		sprite[0] = new Sprite( loadImage("/Image/rip.jpg"));
		sprite[1] = new Sprite( loadImage("/Image/iconarrow.png"));
		sprite[2] = new Sprite( loadImage("/Image/iconspear.png"));
		sprite[3] = new Sprite( loadImage("/Image/Gem/blue.png"));
		sprite[4] = new Sprite( loadImage("/Image/Gem/green.png"));
		sprite[5] = new Sprite( loadImage("/Image/Gem/pink.png"));
		sprite[6] = new Sprite( loadImage("/Image/Gem/yellow.png"));
		sprite[7] = new Sprite( loadImage("/Image/monster.png"));
		
//		background = loadImage("/Image/rip.jpg");
//		
//		backg= new int[background.getWidth()*background.getHeight()];
//		backg= ((DataBufferInt) background.getRaster().getDataBuffer()).getData();
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
