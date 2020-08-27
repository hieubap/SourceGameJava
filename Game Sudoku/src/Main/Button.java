package Main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Button extends MouseAdapter{
	public int x,y,width,height;
	public Button(int x,int y,int w,int h){
		this.x = x;
		this.y = y;
		width = w;
		height = h;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.darkGray);
		g.drawRect(x-1, y-1, width+2, height+2);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(x, y, width, height);
	}
	
	public void mouseClicked(MouseEvent mouse) {
		System.out.println("mouse e");
	}

}
