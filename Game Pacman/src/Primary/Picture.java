package Primary;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Picture
{
    public final int SIZEPACMAN = 40;
    public final int SIZEGHOST = 50;
    public static BufferedImage[] ghost,pacman,scare;
    public static BufferedImage eye,gameover,wingame;
    public Image background;
    public Picture() {
    	BufferedImage asset = loadImage("/pacasset.png");
    	gameover = loadImage("/gameover.png");
    	wingame = loadImage("/victory.png");
    	
    	eye = asset.getSubimage(28*4, 28*5, 28, 28);
    	
        ghost = new BufferedImage[3*4];
        
        ghost[0] = asset.getSubimage(0, 28, 28,28);
        ghost[1] = asset.getSubimage(28, 28, 28,28);
        ghost[2] = asset.getSubimage(28*2, 28, 28,28);
        
        ghost[3] = asset.getSubimage(28*5, 28, 28,28);
        ghost[4] = asset.getSubimage(28*6, 28, 28,28);
        ghost[5] = asset.getSubimage(28*5, 28, 28,28);
        
        ghost[6] = flipHor(ghost[0]);
        ghost[7] = flipHor(ghost[1]);
        ghost[8] = flipHor(ghost[2]);

        ghost[9] = asset.getSubimage(28*3, 28, 28,28);
        ghost[10] = asset.getSubimage(28*4, 28, 28,28);
        ghost[11] = asset.getSubimage(28*3, 28, 28,28);
        
        scare = new BufferedImage[6];
        scare[0] = asset.getSubimage(0*28, 28*5, 28,28);
        scare[1] = asset.getSubimage(1*28, 28*5, 28,28);
        scare[2] = asset.getSubimage(0*28, 28*5, 28,28);
        scare[3] = asset.getSubimage(2*28, 28*5, 28,28);
        scare[4] = asset.getSubimage(3*28, 28*5, 28,28);
        scare[5] = asset.getSubimage(2*28, 28*5, 28,28);
        
        pacman = new BufferedImage[5*4];
        int b = 0;
        pacman[0] = asset.getSubimage(b+0, 0, 28, 28);
        pacman[1] = asset.getSubimage(b+28*1, 0, 28, 28);
        pacman[2] = asset.getSubimage(b+28*2, 0, 28, 28);
        pacman[3] = asset.getSubimage(b+28*3, 0, 28, 28);
        pacman[4] = asset.getSubimage(b+28*4, 0, 28, 28);
        
        pacman[5] = rotation(pacman[0]);
        pacman[6] = rotation(pacman[1]);
        pacman[7] = rotation(pacman[2]);
        pacman[8] = rotation(pacman[3]);
        pacman[9] = rotation(pacman[4]);

        pacman[10] = flipHor(pacman[0]);
        pacman[11] = flipHor(pacman[1]);
        pacman[12] = flipHor(pacman[2]);
        pacman[13] = flipHor(pacman[3]);
        pacman[14] = flipHor(pacman[4]);
//
        pacman[15] = flipVer(pacman[5]);
        pacman[16] = flipVer(pacman[6]);
        pacman[17] = flipVer(pacman[7]);
        pacman[18] = flipVer(pacman[8]);
        pacman[19] = flipVer(pacman[9]);
//
//        background = BitmapFactory.decodeResource(resources,R.drawable.map);
//        background = Bitmap.createScaledBitmap(background,Control.PIXEL*28,Control.PIXEL*31,true);
    }

//    public Bitmap flipHor(Bitmap bitmap){
//        Matrix matrix = new Matrix();
//        matrix.postScale(-1,1,bitmap.getWidth()/2,bitmap.getHeight()/2);
//        return Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
//    }
//    public Bitmap flipVer(Bitmap bitmap){
//        Matrix matrix = new Matrix();
//        matrix.postScale(1,-1,bitmap.getWidth()/2,bitmap.getHeight()/2);
//        return Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
//    }
//    public Bitmap rotation(Bitmap bitmap,float rotation){
//        Matrix matrix = new Matrix();
//        matrix.postRotate(rotation);
//        return Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
//    }



    public BufferedImage loadImage(String path) {
		try {
			BufferedImage loadedImage = ImageIO.read(this.getClass().getResource(path));
			BufferedImage formatImage = new BufferedImage(loadedImage.getWidth(),
					loadedImage.getHeight(),BufferedImage.TYPE_4BYTE_ABGR);
			
			formatImage.getGraphics().drawImage(loadedImage,0,0,null);
			
			return formatImage;
		}catch(IOException e) {
			e.printStackTrace();
			return null;
		}
	}
    
    public BufferedImage flipVer(BufferedImage image) {
    	BufferedImage buffer = new BufferedImage(28, 28, BufferedImage.TYPE_INT_ARGB);
    	
    	Graphics2D g2d = buffer.createGraphics();
    	
    	AffineTransform af = AffineTransform.getScaleInstance(1, -1);
    	af.translate(0,-28);
    	
    	g2d.transform(af);
    	g2d.drawImage(image, 0, 0, null);
    	
    	return buffer;
    }
    public BufferedImage flipHor(BufferedImage image) {
    	BufferedImage buffer = new BufferedImage(28, 28, BufferedImage.TYPE_INT_ARGB);
    	
    	Graphics2D g2d = buffer.createGraphics();
    	
    	AffineTransform af = AffineTransform.getScaleInstance(-1, 1);
    	af.translate(-28,0);
    	
    	g2d.transform(af);
    	g2d.drawImage(image, 0, 0, null);
    	
    	return buffer;
    }
    public BufferedImage rotation(BufferedImage image) {
    	BufferedImage buffer = new BufferedImage(28, 28, BufferedImage.TYPE_INT_ARGB);
    	
    	Graphics2D g2d = buffer.createGraphics();
    	
    	AffineTransform af = AffineTransform.getRotateInstance(-Math.PI/2);
    	af.translate(-28,0);
    	
    	g2d.transform(af);
    	g2d.drawImage(image, 0, 0, null);
    	
    	return buffer;
    }
}
