package Primary;

import java.util.Random;

public class Min {
	private int board[][];
	private Random ran= new Random();
	private int bom;
	private int x,y;
	/* 0->8 so bom
	 * 9 co bom
	 * 11 go o trong
	 * 10 go bom
	 */
	public Min(int xx,int yy,int boom) {
		x=xx;
		y = yy;
		board= new int[xx+2][yy+2];
		
		for(int i=0;i<x+2;i++)
			for(int j=0;j<y+2;j++){
				board[i][j]=0;
			}
		
		int[][] random = new int[boom][2];
		
		for(int i=0;i<boom;i++) {
			random[i][0] = 1+Math.abs(ran.nextInt(x));
			random[i][1] = 1+Math.abs(ran.nextInt(y));
			
			for(int j=0;j<i;j++) {
				while(random[i][0]==random[j][0]&&random[i][1]==random[j][1]) {
					random[i][0] = 1+Math.abs(ran.nextInt(x));
					random[i][1] = 1+Math.abs(ran.nextInt(y));
					
				}
			}
		}
		
		for(int i=0;i<boom;i++) {
			board[random[i][0]][random[i][1]] = 9;
		}
		
		for(int i=1;i<x+1;i++)
			for(int j=1;j<y+1;j++){
				if(board[i][j]!=9) board[i][j]=getfind(i,j);
			}
		setbom();
		System.out.println("chay song min");
	}
	public void setbom() {
		int result=0;
		for(int i=1;i<x+1;i++)
			for(int j=1;j<y+1;j++)
				if(board[i][j]==9) result++;
		bom= result;
	}
	public void setboard(int a,int b,int c) {
		board[a][b]=c;
	}
	
	public boolean checkmin(int a,int b) {
		if(board[a][b]==9) return true;
		return false;
	}
	public boolean checkwin() {
		for(int i=1;i<x+1;i++)
			for(int j=1;j<y+1;j++)
			{
				if(board[i][j]==9) return false;
			}
		return true;
	}
	public int getfind(int a,int b) {
		int result=0;
		for(int i=0;i<9;i++)
			if(board[a-1+i%3][b-1+i/3]==9||board[a-1+i%3][b-1+i/3]==10) result++;
		return result;
	}
	public int getboard(int a,int b) {
		return board[a][b];
	}
	public int getbom() {
		return bom;
	}
	public boolean[][] findArea(int a,int b) {
		boolean[][] pass = new boolean[x+2][y+2];
		
		for(int i=0;i<x+2;i++)
			for(int j=0;j<y+2;j++)
				pass[i][j] = false;
//		pass[a][b] = true;
		check(a,b,pass);
		
		return pass;
	}
	public void changestate(int a,int b) {
		if(getboard(a, b)==11)
		{
			setboard(a, b, getfind(a, b));
		}
		else if(getboard(a, b)==10)
		{
			setboard(a, b, 9);
		}
		else if(getboard(a, b)==9)
		{
			setboard(a, b, 10);
		}
		else {
			setboard(a, b, 11);
		}
	}
	private void check(int a,int b,boolean[][] c) {
		if(0==a||a==x+1||0==b||b==y+1||board[a][b]>0||c[a][b]) {
			return;
		}
		c[a][b] = true;
		
			check(a+1,b,c);
			check(a-1,b,c);
			check(a,b+1,c);
			check(a,b-1,c);
			
	}
}
