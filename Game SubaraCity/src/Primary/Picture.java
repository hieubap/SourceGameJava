package Primary;

import java.awt.Image;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Picture {
	public static Image im,tile1,tile2,tile3,tile4,tile5,tile6,background,eraser,joinblock,tittle,menu,
	
	lv1,lv2,lv3,lv4,lv5,lv6,lv7,lv8,lv9,lv10,lv11,lv12,lv13,lv14,lv15,lv16,lv17,lv18,lv19,lv20,
	a1,a2,a3,a4,a5,a6,a7,a8,a9,a10,a11,a12,a13,a14,a15,
	b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15,
	c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14,c15,
	d1,d2,d3,d4,d5,d6,d7,d8,d9,d10,d11,d12,d13,d14,d15;
	
	public Icon buttonmenu;
	
	public Picture() {
		background=new ImageIcon(loadImage("/Images/background.jpg")).getImage();
		buttonmenu=new ImageIcon(loadImage("/Images/buttonmenu.ico"));
		
		tile1=new ImageIcon(loadImage("/Images/tile1.png")).getImage();
		tile2=new ImageIcon(loadImage("/Images/tile2.png")).getImage();
		tile3=new ImageIcon(loadImage("/Images/tile3.png")).getImage();
		tile4=new ImageIcon(loadImage("/Images/tile4.png")).getImage();
		tile5=new ImageIcon(loadImage("/Images/tile5.png")).getImage();
		tile6=new ImageIcon(loadImage("/Images/tile6.png")).getImage();
		
		eraser = new ImageIcon(loadImage("/Images/eraser.jpg")).getImage();
		joinblock = new ImageIcon(loadImage("/Images/joinblock.jpg")).getImage();
		tittle= new ImageIcon(loadImage("/Images/tittle.jpg")).getImage();
		menu = new ImageIcon(loadImage("/Images/menu.jpg")).getImage();
		
		a1=new ImageIcon(loadImage("/Images/a1.png")).getImage();
		a2=new ImageIcon(loadImage("/Images/a2.png")).getImage();
		a3=new ImageIcon(loadImage("/Images/a3.png")).getImage();
		a4=new ImageIcon(loadImage("/Images/a4.png")).getImage();
		a5=new ImageIcon(loadImage("/Images/a5.png")).getImage();
		a6=new ImageIcon(loadImage("/Images/a6.png")).getImage();
		a7=new ImageIcon(loadImage("/Images/a7.png")).getImage();
		a8=new ImageIcon(loadImage("/Images/a8.png")).getImage();
		a9=new ImageIcon(loadImage("/Images/a9.png")).getImage();
		a10=new ImageIcon(loadImage("/Images/a10.png")).getImage();
		a11=new ImageIcon(loadImage("/Images/a11.png")).getImage();
		a12=new ImageIcon(loadImage("/Images/a12.png")).getImage();
		a13=new ImageIcon(loadImage("/Images/a13.png")).getImage();
		a14=new ImageIcon(loadImage("/Images/a14.png")).getImage();
		a15=new ImageIcon(loadImage("/Images/a15.png")).getImage();
		

		b1=new ImageIcon(loadImage("/Images/b1.png")).getImage();
		b2=new ImageIcon(loadImage("/Images/b2.png")).getImage();
		b3=new ImageIcon(loadImage("/Images/b3.png")).getImage();
		b4=new ImageIcon(loadImage("/Images/b4.png")).getImage();
		b5=new ImageIcon(loadImage("/Images/b5.png")).getImage();
		b6=new ImageIcon(loadImage("/Images/b6.png")).getImage();
		b7=new ImageIcon(loadImage("/Images/b7.png")).getImage();
		b8=new ImageIcon(loadImage("/Images/b8.png")).getImage();
		b9=new ImageIcon(loadImage("/Images/b9.png")).getImage();
		b10=new ImageIcon(loadImage("/Images/b10.png")).getImage();
		b11=new ImageIcon(loadImage("/Images/b11.png")).getImage();
		b12=new ImageIcon(loadImage("/Images/b12.png")).getImage();
		b13=new ImageIcon(loadImage("/Images/b13.png")).getImage();
		b14=new ImageIcon(loadImage("/Images/b14.png")).getImage();
		b15=new ImageIcon(loadImage("/Images/b15.png")).getImage();
		
		c1=new ImageIcon(loadImage("/Images/c1.png")).getImage();
		c2=new ImageIcon(loadImage("/Images/c2.png")).getImage();
		c3=new ImageIcon(loadImage("/Images/c3.png")).getImage();
		c4=new ImageIcon(loadImage("/Images/c4.png")).getImage();
		c5=new ImageIcon(loadImage("/Images/c5.png")).getImage();
		c6=new ImageIcon(loadImage("/Images/c6.png")).getImage();
		c7=new ImageIcon(loadImage("/Images/c7.png")).getImage();
		c8=new ImageIcon(loadImage("/Images/c8.png")).getImage();
		c9=new ImageIcon(loadImage("/Images/c9.png")).getImage();
		c10=new ImageIcon(loadImage("/Images/c10.png")).getImage();
		c11=new ImageIcon(loadImage("/Images/c11.png")).getImage();
		c12=new ImageIcon(loadImage("/Images/c12.png")).getImage();
		c13=new ImageIcon(loadImage("/Images/c13.png")).getImage();
		c14=new ImageIcon(loadImage("/Images/c14.png")).getImage();
		c15=new ImageIcon(loadImage("/Images/c15.png")).getImage();
		
		d1=new ImageIcon(loadImage("/Images/d1.png")).getImage();
		d2=new ImageIcon(loadImage("/Images/d2.png")).getImage();
		d3=new ImageIcon(loadImage("/Images/d3.png")).getImage();
		d4=new ImageIcon(loadImage("/Images/d4.png")).getImage();
		d5=new ImageIcon(loadImage("/Images/d5.png")).getImage();
		d6=new ImageIcon(loadImage("/Images/d6.png")).getImage();
		d7=new ImageIcon(loadImage("/Images/d7.png")).getImage();
		d8=new ImageIcon(loadImage("/Images/d8.png")).getImage();
		d9=new ImageIcon(loadImage("/Images/d9.png")).getImage();
		d10=new ImageIcon(loadImage("/Images/d10.png")).getImage();
		d11=new ImageIcon(loadImage("/Images/d11.png")).getImage();
		d12=new ImageIcon(loadImage("/Images/d12.png")).getImage();
		d13=new ImageIcon(loadImage("/Images/d13.png")).getImage();
		d14=new ImageIcon(loadImage("/Images/d14.png")).getImage();
		d15=new ImageIcon(loadImage("/Images/d15.png")).getImage();
		
		lv1=new ImageIcon(loadImage("/Images/removeIMG/level1.png")).getImage();
		lv2=new ImageIcon(loadImage("/Images/removeIMG/level2.png")).getImage();
		lv3=new ImageIcon(loadImage("/Images/removeIMG/level3.png")).getImage();
		lv4=new ImageIcon(loadImage("/Images/removeIMG/level4.png")).getImage();
		lv5=new ImageIcon(loadImage("/Images/removeIMG/level5.png")).getImage();
		lv6=new ImageIcon(loadImage("/Images/removeIMG/level6.png")).getImage();
		lv7=new ImageIcon(loadImage("/Images/removeIMG/level7.png")).getImage();
		lv8=new ImageIcon(loadImage("/Images/removeIMG/level8.png")).getImage();
		lv9=new ImageIcon(loadImage("/Images/removeIMG/level9.png")).getImage();
		lv10=new ImageIcon(loadImage("/Images/removeIMG/level10.png")).getImage();
		lv11=new ImageIcon(loadImage("/Images/removeIMG/level11.png")).getImage();
		lv12=new ImageIcon(loadImage("/Images/removeIMG/level12.png")).getImage();
		lv13=new ImageIcon(loadImage("/Images/removeIMG/level13.png")).getImage();
		lv14=new ImageIcon(loadImage("/Images/removeIMG/level14.png")).getImage();
		lv15=new ImageIcon(loadImage("/Images/removeIMG/level15.png")).getImage();
		lv16=new ImageIcon(loadImage("/Images/removeIMG/level16.png")).getImage();
		lv17=new ImageIcon(loadImage("/Images/removeIMG/level17.png")).getImage();
		lv18=new ImageIcon(loadImage("/Images/removeIMG/level18.png")).getImage();
		lv19=new ImageIcon(loadImage("/Images/removeIMG/level19.png")).getImage();
		lv20=new ImageIcon(loadImage("/Images/removeIMG/level20.png")).getImage();
		
	}
	public int paddingleft(int i) {
		return 0;
	}
	public int paddingtop(int i) {
		if(i==1||i==2||i==3||i==4) return -10;
		if(i==5||i==6||i==7||i==8) return -40;
		if(i==9||i==10) return -60;
		if(i==11) return -80;
		if(i==12) return -80;
		if(i==14) return -120;
		if(i==13) return -120;
		if(i==15||i==16) return -120;
		if(i==19) return -110;
		if(i==17||i==18||i==20) return -120;
		
		System.out.println("----------");
		return 0;
	}
	public int getWidth(int i) {
		return 85;
	}
	public int getHeight(int i) {
		if(i==1||i==2||i==3||i==4) {
			return 80;
		}
		if(i==5||i==6||i==7||i==8) {
			return 105;
		}
		if(i==9||i==10||i==11||i==12) {
			return 147;
		}
		if(i==13||i==14||i==15||i==16) return 200;
		if(i==17||i==18||i==19||i==20) return 200;
		
			
		return 0;
	}
	public URL loadImage(String path) {
		return this.getClass().getResource(path);
	}
}
