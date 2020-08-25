package Main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Control extends JPanel implements KeyListener{
	public final int WIDTH = 600,HEIGHT = 800,PIXEL = 50;
	public enum WAY {L,rL,R,rR,U,rU,D,rD,F,rF,B,rB};
	public JFrame frame;
	public Save save;
	public int[] data= {// red1 orange2 yellow3 white4 green5 blue6
			2,6,4,4,1,4,3,5,5,
			6,5,5,6,5,5,4,2,1,
			5,5,5,2,6,1,2,6,6,
			3,1,2,3,3,2,3,2,2,
			6,1,4,3,2,4,1,6,4,
			1,1,1,3,4,4,3,3,6,
	},solve;
	public byte[] block,logic;
	public Timer time;
	public int index = -1;
	
	public void initBlock() {
		block = new byte[20];
		logic = new byte[20];
		for(byte i=0;i<20;i++)
			{
			block[i] = rotation(i,(byte)0);
			logic[i] = block[i];
			}
		
		
	}
	public byte rotation(byte a,int b) {
		
		byte c = (byte)b;
		switch(b) {
		case 1:{
			c = (byte)(a>>>5);
			c++;
			if((a&31) > 7) {
				if(c >= 2)
					c =0;
				break;
			}
			if(c >= 3) c=0;
			break;
		}
		case 2:{
			c = (byte)(a>>>5);
			c--;
			if((a&31) > 7) {
				if(c <= -1)
					c =1;
				break;
			}
			if(c <= -1) c=2;
			break;
		}
		case 0:{
			c = (byte)(a>>>5);
			break;
		}
		}
		
		return (byte)((a&31)|(c<<5));
	}
	public boolean doneLogic() {
		for(int i=0;i<20;i++)
			if(block[i]!=i) return false;
		return true;
	}
	public void showLogic(Graphics g) {
		g.setColor(Color.black);
		for(int i=0;i<20;i++)
			{
			g.drawString(""+(block[i]&31), (i%10)*50, 700+(i/10)*50);
			g.drawString(""+(block[i]>>>5), (i%10)*50+20, 700+(i/10)*50);
			
			}
	}
	
	public void up() {
		byte buffer = block[0];
		block[0] = rotation(block[3], 0);
		block[3] = rotation(block[2],0);
		block[2] = rotation(block[1],0);
		block[1] = rotation(buffer,0);
		
		buffer = rotation(block[8+0], 0);
		block[8+0] = rotation(block[8+3],0);
		block[8+3] = rotation(block[8+2],0);
		block[8+2] = rotation(block[8+1],0);
		block[8+1] = rotation(buffer,0);
	}
	public void rup() {
		byte buffer = block[0];
		block[0] = rotation(block[1],0);
		block[1] = rotation(block[2],0);
		block[2] = rotation(block[3],0);
		block[3] = rotation(buffer,0);
		
		buffer = rotation(block[8+0], 0);
		block[8+0] = rotation(block[8+1],0);
		block[8+1] = rotation(block[8+2],0);
		block[8+2] = rotation(block[8+3],0);
		block[8+3] = buffer;
	}
	public void down() {
		byte buffer = block[4];
		block[4] = rotation(block[5], 0);
		block[5] = rotation(block[6],0);
		block[6] = rotation(block[7],0);
		block[7] = rotation(buffer,0);
		
		buffer = rotation(block[8+8], 0);
		block[8+8] = rotation(block[8+9],0);
		block[8+9] = rotation(block[8+10],0);
		block[8+10] = rotation(block[8+11],0);
		block[8+11] = buffer;
	}
	public void rdown() {
		byte buffer = block[4];
		block[4] = rotation(block[7], 0);
		block[7] = rotation(block[6],0);
		block[6] = rotation(block[5],0);
		block[5] = rotation(buffer,0);
		
		buffer = rotation(block[8+8], 0);
		block[8+8] = rotation(block[8+11],0);
		block[8+11] = rotation(block[8+10],0);
		block[8+10] = rotation(block[8+9],0);
		block[8+9] = buffer;
	}
	public void right() {
		byte buffer = block[1];
		block[1] = rotation(block[2], 1);
		block[2] = rotation(block[6],2);
		block[6] = rotation(block[5],1);
		block[5] = rotation(buffer,2);
		
		buffer = rotation(block[8+1], 0);
		block[8+1] = rotation(block[8+6],0);
		block[8+6] = rotation(block[8+9],0);
		block[8+9] = rotation(block[8+5],0);
		block[8+5] = buffer;
	}
	public void rright() {
		byte buffer = block[1];
		block[1] = rotation(block[5], 1);
		block[5] = rotation(block[6],2);
		block[6] = rotation(block[2],1);
		block[2] = rotation(buffer,2);
		
		buffer = rotation(block[8+1], 0);
		block[8+1] = rotation(block[8+5],0);
		block[8+5] = rotation(block[8+9],0);
		block[8+9] = rotation(block[8+6],0);
		block[8+6] = buffer;
	}
	public void left() {
		byte buffer = block[0];
		block[0] = rotation(block[3], 2);
		block[3] = rotation(block[6],1);
		block[6] = rotation(block[7],2);
		block[7] = rotation(buffer,1);
		
		buffer = rotation(block[8+3], 0);
		block[8+3] = rotation(block[8+4],0);
		block[8+4] = rotation(block[8+11],0);
		block[8+11] = rotation(block[8+7],0);
		block[8+7] = buffer;
	}
	public void rleft() {
		byte buffer = block[0];
		block[0] = rotation(block[7], 2);
		block[7] = rotation(block[6],1);
		block[6] = rotation(block[3],2);
		block[3] = rotation(buffer,1);
		
		buffer = rotation(block[8+3], 0);
		block[8+3] = rotation(block[8+7],0);
		block[8+7] = rotation(block[8+11],0);
		block[8+11] = rotation(block[8+4],0);
		block[8+4] = buffer;
	}
	public void front() {
		byte buffer = block[2];
		block[2] = rotation(block[3],1);
		block[3] = rotation(block[6],2);
		block[6] = rotation(block[5],1);
		block[5] = rotation(buffer,2);
		
		buffer = rotation(block[8+2], 1);
		block[8+2] = rotation(block[8+7],1);
		block[8+7] = rotation(block[8+10],1);
		block[8+10] = rotation(block[8+6],1);
		block[8+6] = buffer;
	}
	public void rfront() {
		byte buffer = block[2];
		block[2] = rotation(block[5],1);
		block[5] = rotation(block[6],2);
		block[6] = rotation(block[3],1);
		block[3] = rotation(buffer,2);
		
		buffer = rotation(block[8+2], 1);
		block[8+2] = rotation(block[8+7],1);
		block[8+7] = rotation(block[8+10],1);
		block[8+10] = rotation(block[8+6],1);
		block[8+6] = buffer;
	}
	public void behind() {
		byte buffer = block[0];
		block[0] = rotation(block[4], 0);
		block[4] = rotation(block[5],0);
		block[5] = rotation(block[1],0);
		block[1] = rotation(buffer,0);
		
		buffer = rotation(block[8+0], 1);
		block[8+0] = rotation(block[8+5],1);
		block[8+5] = rotation(block[8+8],1);
		block[8+8] = rotation(block[8+4],1);
		block[8+4] = buffer;
	}
	public void rbehind() {
		byte buffer = block[0];
		block[0] = rotation(block[1], 0);
		block[1] = rotation(block[5],0);
		block[5] = rotation(block[4],0);
		block[4] = rotation(buffer,0);
		
		buffer = rotation(block[8+0], 1);
		block[8+0] = rotation(block[8+4],1);
		block[8+4] = rotation(block[8+8],1);
		block[8+8] = rotation(block[8+5],1);
		block[8+5] = buffer;
	}
	
	public Control() {
		save = new Save(new File("E:/save.txt"));
		initBlock();
//		data = new int[6*9];
		solve = new int[100];
		time = new Timer(2000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				index++;
				if(index == 12)
					index = 0;
				switch(index) {
				case 0: UP();break;
				case 1: rUP();break;
				case 2: DOWN();break;
				case 3: rDOWN();break;
				case 4: LEFT();break;
				case 5: rLEFT();break;
				case 6: RIGHT();break;
				case 7: rRIGHT();break;
				case 8: FRONT();break;
				case 9: rFRONT();break;
				case 10: DOWN();break;
				case 11: rDOWN();break;
				}
				repaint();
			}
		});
		
		addKeyListener(this);
//		time.start();
		setFocusable(true);
		frame = new JFrame("Solve Rubik");
		frame.setSize(WIDTH,HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		frame.add(this);
//		test(0,2,0);

//		test();
//		init();
		long start = System.currentTimeMillis();
		for(int i=0;i<100000;i++)
			{
			
			}
		start = System.currentTimeMillis() - start;
		System.out.println("time = " + start + " ms");
		initEx();
//		solveBack();
//		System.out.println("finish");
		
//		solve[0] = 9;
//		solve[1] = 10;
//		solve[2] = 7;
//		solve[3] = 8;
//		for(int i=0;i<4;i++)
//			rotate(i,false);
//		if(done()) System.out.println("giai thanh cong");
//		showAnswer();
	}
	public void check() {
		rup();
		rright();
		rfront();
		if(doneLogic())
			System.out.println("done");
	}
	public void solveLogic() {
		byte[] buffer = new byte[20];
		for(int i=0;i<20;i++)
			buffer[i]=block[i];
		for(int i=0;i<100;i++)
			solve[i] = 0;
		int i=0;
		System.out.println("Solving logic");
		while(true) {
			while(solve[i]<=11) {
				solve[i]= next(i);
				
				for(int k = 0;k<20;k++)
					block[k] = buffer[k];
				for(int k=0;k<99;k++)
					{
					if(solve[k] == 0) break;
					rotate(k);
					}
//				for(int j=0;j<10;j++)
//					System.out.print(solve[j]+",");
//				System.out.println();
				if(doneLogic()) {
					for(int k = 0;k<20;k++)
						block[k] = buffer[k];
					System.out.print("giải thành công :))  ");
					showAnswer();
					System.out.println();
					repaint();
					return;
				}
				
			}
			solve[i] = 0;
			i++;
			while(solve[i]>=12) {
				solve[i]=0;
				i++;
				if(i>= 6) return;
//				if(i >= 99) {
//					for(int k = 0;k<6*9;k++)
//						data[k] = buffer[k];
//					System.out.println("không giải được");
//					return;
//				}
				if(i>6) System.out.println(">6");
			}
			solve[i] = next(i);
			i = 0;
			}
	}
	public void solveBack() {
		int[] buffer = new int[6*9];
		for(int i=0;i<6*9;i++)
			buffer[i]=data[i];
		for(int i=0;i<100;i++)
			solve[i] = 0;
		int i=0;
		System.out.println("Solving");
		while(true) {
			while(solve[i]<=11) {
				solve[i]= next(i);
				
				for(int k = 0;k<6*9;k++)
					data[k] = buffer[k];
				for(int k=0;k<99;k++)
					{
					if(solve[k] == 0) break;
					rotate(k);
					}
//				for(int j=0;j<10;j++)
//					System.out.print(solve[j]+",");
//				System.out.println();
				if(done()) {
					for(int k = 0;k<6*9;k++)
						data[k] = buffer[k];
					System.out.print("giải thành công :))  ");
					showAnswer();
					System.out.println();
					repaint();
					return;
				}
				
			}
			solve[i] = 0;
			i++;
			while(solve[i]>=12) {
				solve[i]=0;
				i++;
				if(i>= 6) return;
//				if(i >= 99) {
//					for(int k = 0;k<6*9;k++)
//						data[k] = buffer[k];
//					System.out.println("không giải được");
//					return;
//				}
			}
			solve[i] = next(i);
			i = 0;
			}
	}
	public int next(int j) {
		if(solve[j+1]==0) return solve[j]+1;
		if(j<100-2&&solve[j+1] == solve[j+2]) {
			if(solve[j]+1 == solve[j+1]||solve[j]+1 == solve[j+1]-6||solve[j]+1 == solve[j+1]+6)
				return solve[j]+2;
			return solve[j]+1;
		}
		if(solve[j+1] <= 6)
		{
			if(solve[j]+1 != solve[j+1])
				return solve[j]+1;
			return solve[j] + 2;
		}
		else {
			if(solve[j]+1 != solve[j+1] - 6)
				return solve[j]+1;
			return solve[j]+2;
		}
	}
	public void initEx() {
		for(int i=0;i<6*9;i++)
			switch(data[i]) {
			case 1:{
				data[i] = Color.red.getRGB();
				break;
			}
			case 2:{
				data[i] = Color.orange.getRGB();
				break;
			}
			case 3:{
				data[i] = Color.yellow.getRGB();
				break;
			}
			case 4:{
				data[i] = Color.white.getRGB();
				break;
			}
			case 5:{
				data[i] = Color.green.getRGB();
				break;
			}
			case 6:{
				data[i] = Color.blue.getRGB();
				break;
			}
			
			}
	}
	public void init() {
		for(int i=0;i<9;i++) {
			data[i] = Color.red.getRGB();
		}
		for(int i=0;i<9;i++) {
			data[i+9] = Color.green.getRGB();
		}
		for(int i=0;i<9;i++) {
			data[i+36] = Color.orange.getRGB();
		}
		for(int i=0;i<9;i++) {
			data[i+18] = Color.blue.getRGB();
		}
		for(int i=0;i<9;i++) {
			data[i+27] = Color.yellow.getRGB();
		}
		for(int i=0;i<9;i++) {
			data[i+45] = Color.white.getRGB();
		}	
	}
	public void changeColor() {
		for(int i=0;i<6*9;i++) {
			switch(data[i]) {
			case 1:{
				data[i] = Color.red.getRGB();
				break;
			}
			case 2:{
				data[i] = Color.orange.getRGB();
				break;
			}
			case 3:{
				data[i] = Color.yellow.getRGB();
				break;
			}
			case 4:{
				data[i] = Color.white.getRGB();
				break;
			}
			case 5:{
				data[i] = Color.green.getRGB();
				break;
			}
			case 6:{
				data[i] = Color.blue.getRGB();
				break;
			}
			
			}
		}
	}
	public void rotate(int i) {
		switch(solve[i]) {
		case 1: {
			left();
			break;
		}
		case 2: {
			right();
			break;
		}
		case 3: {
			up();
			break;
		}
		case 4: {
			down();
			break;
		}
		case 5: {
			front();
			break;
		}
		case 6: {
			behind();
			break;
		}
		case 7: {
			rleft();
			break;
		}
		case 8: {
			rright();
			break;
		}
		case 9: {
			rup();
			break;
		}
		case 10:{
			rdown();
			break;
		}
		case 11: {
			rfront();
			break;
		}
		case 12: {
			rbehind();
			break;
		}
		}
		
	}
	public boolean done() {
		for(int i=0;i<9;i++) {
			if(data[i] != Color.red.getRGB()) return false;
		}
		for(int i=0;i<9;i++) {
			if(data[i+9] != Color.green.getRGB()) return false;
		}
		for(int i=0;i<9;i++) {
			if(data[i+18] != Color.blue.getRGB()) return false;
		}
		for(int i=0;i<9;i++) {
			if(data[i+27] != Color.yellow.getRGB()) return false;
		}
		for(int i=0;i<9;i++) {
			if(data[i+36] != Color.orange.getRGB()) return false;
		}
		for(int i=0;i<9;i++) {
			if(data[i+45] != Color.white.getRGB()) return false;
		}
		
		
		return true;
	}
	public void showAnswer() {
		for(int i=0;i<100;i++)
			if(solve[i]!=0)
				switch(solve[i]) {
				case 1:{
					System.out.print("L, ");
					break;
				}
				case 2:{
					System.out.print("R, ");
					break;
				}
				case 3:{
					System.out.print("U, ");
					break;
				}
				case 4:{
					System.out.print("D, ");
					break;
				}
				case 5:{
					System.out.print("F, ");
					break;
				}
				case 6:{
					System.out.print("B, ");
					break;
				}
				case 7:{
					System.out.print("L', ");
					break;
				}
				case 8:{
					System.out.print("R', ");
					break;
				}
				case 9:{
					System.out.print("U', ");
					break;
				}
				case 10:{
					System.out.print("D', ");
					break;
				}
				case 11:{
					System.out.print("F', ");
					break;
				}
				case 12:{
					System.out.print("B', ");
					break;
				}
				
				}
			else break;
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics gc = g.create();
		for(int i=0;i<9;i++)//b
			{
			g.setColor(new Color(data[i+36]));
			g.fillRect(PIXEL*(i%3+1+3), PIXEL*(i/3+1), PIXEL, PIXEL);
			gc.drawRect(PIXEL*(i%3+1+3), PIXEL*(i/3+1), PIXEL, PIXEL);
			gc.drawString(""+(36+i),PIXEL*(i%3+1+3), PIXEL*(i/3+1));
			
			}
		for(int i=0;i<9;i++)//u
			{
			g.setColor(new Color(data[i+27]));
			g.fillRect(PIXEL*(i%3+1+3), PIXEL*(i/3+1+3), PIXEL, PIXEL);
			gc.drawRect(PIXEL*(i%3+1+3), PIXEL*(i/3+1+3), PIXEL, PIXEL);
			gc.drawString(""+(27+i),PIXEL*(i%3+1+3), PIXEL*(i/3+1+3));
			
			}
		for(int i=0;i<9;i++)//f
			{
			g.setColor(new Color(data[i]));
			g.fillRect(PIXEL*(i%3+1+3), PIXEL*(i/3+1+6), PIXEL, PIXEL);
			gc.drawRect(PIXEL*(i%3+1+3), PIXEL*(i/3+1+6), PIXEL, PIXEL);
			gc.drawString(""+i,PIXEL*(i%3+1+3), PIXEL*(i/3+1+6));
			
			}
		for(int i=0;i<9;i++)//d
			{
			g.setColor(new Color(data[45+i]));
			g.fillRect(PIXEL*(i%3+1+3), PIXEL*(i/3+1+9), PIXEL, PIXEL);
			gc.drawRect(PIXEL*(i%3+1+3), PIXEL*(i/3+1+9), PIXEL, PIXEL);
			gc.drawString(""+(45+i),PIXEL*(i%3+1+3), PIXEL*(i/3+1+9));
			
			}
		for(int i=0;i<9;i++)//l
			{
			g.setColor(new Color(data[18+i]));
			g.fillRect(PIXEL*(i%3+1), PIXEL*(i/3+1+6), PIXEL, PIXEL);
			gc.drawRect(PIXEL*(i%3+1), PIXEL*(i/3+1+6), PIXEL, PIXEL);
			gc.drawString(""+(i+18),PIXEL*(i%3+1), PIXEL*(i/3+1+6));
			
			}
		for(int i=0;i<9;i++)//r
			{
			g.setColor(new Color(data[9+i]));
			g.fillRect(PIXEL*(i%3+1+6), PIXEL*(i/3+1+6), PIXEL, PIXEL);
			gc.drawRect(PIXEL*(i%3+1+6), PIXEL*(i/3+1+6), PIXEL, PIXEL);
			gc.drawString(""+(9+i),PIXEL*(i%3+1+6), PIXEL*(i/3+1+6));
			
			}
		tutor(g);
		showLogic(g);
	}
	
	public static void main(String[] args) {
		new Control();
//		System.out.println("size = " + sizeof(int));
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public boolean click = true;
	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
		if(click)
		switch (code) {
		case KeyEvent.VK_U:{
			UP();
			up();
			break;
		}
		case KeyEvent.VK_D:{
			DOWN();
			down();
			break;
		}
		case KeyEvent.VK_L:{
			LEFT();
			left();
			break;
		}
		case KeyEvent.VK_R:{
			RIGHT();
			right();
			break;
		}
		case KeyEvent.VK_F:{
			FRONT();
			front();
			break;
		}
		case KeyEvent.VK_B:{
			BEHIND();
			behind();
			break;
		}
		case KeyEvent.VK_NUMPAD8:{
			rUP();
			rup();
			break;
		}
		case KeyEvent.VK_NUMPAD2:{
			rDOWN();
			rdown();
			break;
		}
		case KeyEvent.VK_NUMPAD4:{
			rLEFT();
			rleft();
			break;
		}
		case KeyEvent.VK_NUMPAD6:{
			rRIGHT();
			rright();
			break;
		}
		case KeyEvent.VK_NUMPAD5:{
			rFRONT();
			rfront();
			break;
		}
		case KeyEvent.VK_NUMPAD0:{
			rBEHIND();
			rbehind();
			break;
		}
		case KeyEvent.VK_S:{
			save.save(data);
			break;
		}
		case KeyEvent.VK_Z:{
			init();
			initBlock();
			repaint();
			break;
		}
		case KeyEvent.VK_A:{
			save.read(data);
//			init();
			changeColor();
			repaint();
			break;
		}
		case KeyEvent.VK_X:{
//			solveBack();
			solveLogic();
			repaint();
			break;
		}
		case KeyEvent.VK_C:{
			check();
			repaint();
			break;
		}
		
		
		}
		click = false;
		repaint();
	}
	public void tutor(Graphics g) {
		g.setColor(Color.black);
		g.drawString("S: Save", 10, 20);
		g.drawString("A: Read", 10, 40);
		g.drawString("S: Clear", 10, 60);
		g.drawString("X: Solve", 10, 80);
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		click = true;
	}
	
	public void UP() {
		int[] buffer = {0,0,0};
		
		buffer[0] = data[27];
		data[27] = data[33];
		data[33] = data[35];
		data[35] = data[29];
		data[29] = buffer[0];
		
		buffer[0] = data[28];
		data[28] = data[30];
		data[30] = data[34];
		data[34] = data[32];
		data[32] = buffer[0];
		
		buffer[0] = data[0];
		buffer[1] = data[1];
		buffer[2] = data[2];
		
		data[0] = data[9];
		data[1] = data[10];
		data[2] = data[11];

		data[9] = data[44];
		data[10] = data[43];
		data[11] = data[42];

		data[44] = data[18];
		data[43] = data[19];
		data[42] = data[20];
		
		data[18] = buffer[0];
		data[19] = buffer[1];
		data[20] = buffer[2];
	}
	public void rUP() {
		int[] buffer = {0,0,0};
		
		buffer[0] = data[27];
		data[27] = data[29];
		data[29] = data[35];
		data[35] = data[33];
		data[33] = buffer[0];
		
		buffer[0] = data[28];
		data[28] = data[32];
		data[32] = data[34];
		data[34] = data[30];
		data[30] = buffer[0];
		
		buffer[0] = data[0];
		buffer[1] = data[1];
		buffer[2] = data[2];
		
		data[0] = data[18];
		data[1] = data[19];
		data[2] = data[20];

		data[18] = data[44];
		data[19] = data[43];
		data[20] = data[42];

		data[44] = data[9];
		data[43] = data[10];
		data[42] = data[11];
		
		data[9] = buffer[0];
		data[10] = buffer[1];
		data[11] = buffer[2];
	}
	public void DOWN() {
		int[] buffer = {0,0,0};
		
		buffer[0] = data[45];
		data[45] = data[51];
		data[51] = data[53];
		data[53] = data[47];
		data[47] = buffer[0];
		
		buffer[0] = data[46];
		data[46] = data[48];
		data[48] = data[52];
		data[52] = data[50];
		data[50] = buffer[0];
		
		buffer[0] = data[15];
		buffer[1] = data[16];
		buffer[2] = data[17];
		
		data[15] = data[6];
		data[16] = data[7];
		data[17] = data[8];

		data[6] = data[24];
		data[7] = data[25];
		data[8] = data[26];

		data[24] = data[38];
		data[25] = data[37];
		data[26] = data[36];
		
		data[38] = buffer[0];
		data[37] = buffer[1];
		data[36] = buffer[2];
	}
	public void rDOWN() {
		int[] buffer = {0,0,0};
		
		buffer[0] = data[45];
		data[45] = data[47];
		data[47] = data[53];
		data[53] = data[51];
		data[51] = buffer[0];
		
		buffer[0] = data[46];
		data[46] = data[50];
		data[50] = data[52];
		data[52] = data[48];
		data[48] = buffer[0];
		
		buffer[0] = data[24];
		buffer[1] = data[25];
		buffer[2] = data[26];
		
		data[24] = data[6];
		data[25] = data[7];
		data[26] = data[8];

		data[6] = data[15];
		data[7] = data[16];
		data[8] = data[17];

		data[15] = data[38];
		data[16] = data[37];
		data[17] = data[36];
		
		data[38] = buffer[0];
		data[37] = buffer[1];
		data[36] = buffer[2];
	}
	public void RIGHT() {
		int[] buffer = {0,0,0};
		
		buffer[0] = data[9];
		data[9] = data[15];
		data[15] = data[17];
		data[17] = data[11];
		data[11] = buffer[0];
		
		buffer[0] = data[10];
		data[10] = data[12];
		data[12] = data[16];
		data[16] = data[14];
		data[14] = buffer[0];
		
		buffer[0] = data[38];
		buffer[1] = data[41];
		buffer[2] = data[44];
		
		data[38] = data[29];
		data[41] = data[32];
		data[44] = data[35];

		data[29] = data[2];
		data[32] = data[5];
		data[35] = data[8];

		data[2] = data[47];
		data[5] = data[50];
		data[8] = data[53];
		
		data[47] = buffer[0];
		data[50] = buffer[1];
		data[53] = buffer[2];
	}
	public void rRIGHT() {
		int[] buffer = {0,0,0};
		
		buffer[0] = data[9];
		data[9] = data[11];
		data[11] = data[17];
		data[17] = data[15];
		data[15] = buffer[0];
		
		buffer[0] = data[10];
		data[10] = data[14];
		data[14] = data[16];
		data[16] = data[12];
		data[12] = buffer[0];
		
		buffer[0] = data[47];
		buffer[1] = data[50];
		buffer[2] = data[53];
		
		data[47] = data[2];
		data[50] = data[5];
		data[53] = data[8];

		data[2] = data[29];
		data[5] = data[32];
		data[8] = data[35];

		
		data[29] = data[38];
		data[32] = data[41];
		data[35] = data[44];
		
		data[38] = buffer[0];
		data[41] = buffer[1];
		data[44] = buffer[2];
	}
	public void LEFT() {
		int[] buffer = {0,0,0};
		
		buffer[0] = data[18];
		data[18] = data[24];
		data[24] = data[26];
		data[26] = data[20];
		data[20] = buffer[0];
		
		buffer[0] = data[19];
		data[19] = data[21];
		data[21] = data[25];
		data[25] = data[23];
		data[23] = buffer[0];
		
		buffer[0] = data[45];
		buffer[1] = data[48];
		buffer[2] = data[51];
		
		data[45] = data[0];
		data[48] = data[3];
		data[51] = data[6];

		data[0] = data[27];
		data[3] = data[30];
		data[6] = data[33];

		data[27] = data[36];
		data[30] = data[39];
		data[33] = data[42];
		
		data[36] = buffer[0];
		data[39] = buffer[1];
		data[42] = buffer[2];
	}
	public void rLEFT() {
		int[] buffer = {0,0,0};

		buffer[0] = data[18];
		data[18] = data[20];
		data[20] = data[26];
		data[26] = data[24];
		data[24] = buffer[0];
		
		buffer[0] = data[19];
		data[19] = data[23];
		data[23] = data[25];
		data[25] = data[21];
		data[21] = buffer[0];
		
		buffer[0] = data[36];
		buffer[1] = data[39];
		buffer[2] = data[42];
		
		data[36] = data[27];
		data[39] = data[30];
		data[42] = data[33];

		data[27] = data[0];
		data[30] = data[3];
		data[33] = data[6];

		data[0] = data[45];
		data[3] = data[48];
		data[6] = data[51];
		
		data[45] = buffer[0];
		data[48] = buffer[1];
		data[51] = buffer[2];
	}
	public void FRONT() {
		int[] buffer = {0,0,0};

		buffer[0] = data[1];
		data[1] = data[3];
		data[3] = data[7];
		data[7] = data[5];
		data[5] = buffer[0];
		
		buffer[0] = data[0];
		data[0] = data[6];
		data[6] = data[8];
		data[8] = data[2];
		data[2] = buffer[0];
		
		buffer[0] = data[20];
		buffer[1] = data[23];
		buffer[2] = data[26];
		
		data[20] = data[45];
		data[23] = data[46];
		data[26] = data[47];

		data[45] = data[15];
		data[46] = data[12];
		data[47] = data[9];

		
		data[15] = data[35];
		data[12] = data[34];
		data[9] = data[33];
		
		data[35] = buffer[0];
		data[34] = buffer[1];
		data[33] = buffer[2];
	}
	public void rFRONT() {
		int[] buffer = {0,0,0};
		
		buffer[0] = data[1];
		data[1] = data[5];
		data[5] = data[7];
		data[7] = data[3];
		data[3] = buffer[0];
		
		buffer[0] = data[0];
		data[0] = data[2];
		data[2] = data[8];
		data[8] = data[6];
		data[6] = buffer[0];
		
		buffer[0] = data[33];
		buffer[1] = data[34];
		buffer[2] = data[35];
		
		data[33] = data[9];
		data[34] = data[12];
		data[35] = data[15];

		data[9] = data[47];
		data[12] = data[46];
		data[15] = data[45];

		
		data[47] = data[26];
		data[46] = data[23];
		data[45] = data[20];
		
		data[26] = buffer[0];
		data[23] = buffer[1];
		data[20] = buffer[2];
	}
	public void BEHIND() {
		int[] buffer = {0,0,0};
		
		buffer[0] = data[36];
		data[36] = data[42];
		data[42] = data[44];
		data[44] = data[38];
		data[38] = buffer[0];
		
		buffer[0] = data[37];
		data[37] = data[39];
		data[39] = data[43];
		data[43] = data[41];
		data[41] = buffer[0];
		
		buffer[0] = data[27];
		buffer[1] = data[28];
		buffer[2] = data[29];

		data[27] = data[11];
		data[28] = data[14];
		data[29] = data[17];
				
		data[11] = data[53];
		data[14] = data[52];
		data[17] = data[51];

		data[53] = data[24];
		data[52] = data[21];
		data[51] = data[18];

		data[24] = buffer[0];
		data[21] = buffer[1];
		data[18] = buffer[2];
	}
	public void rBEHIND() {
		int[] buffer = {0,0,0};
		
		buffer[0] = data[36];
		data[36] = data[38];
		data[38] = data[44];
		data[44] = data[42];
		data[42] = buffer[0];
		
		buffer[0] = data[37];
		data[37] = data[41];
		data[41] = data[43];
		data[43] = data[39];
		data[39] = buffer[0];
		
		buffer[0] = data[27];
		buffer[1] = data[28];
		buffer[2] = data[29];

		data[27] = data[24];
		data[28] = data[21];
		data[29] = data[18];
				
		data[24] = data[53];
		data[21] = data[52];
		data[18] = data[51];

		data[53] = data[11];
		data[52] = data[14];
		data[51] = data[17];

		data[11] = buffer[0];
		data[14] = buffer[1];
		data[17] = buffer[2];
	}
	

}
