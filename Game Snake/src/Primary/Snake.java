package Primary;

public class Snake {
	protected enum Move{up,down,left,right};
	
	private Move move;
	
	// Stores the joints / body part locations for our snake
	private final int[] x = new int[Board.getAllDots()];
	private final int[] y = new int[Board.getAllDots()];

	private int joints = 0; // Stores # of dots / joints the snake has (starts
	                        // with 3)
	
	public void setmove(Move m) {
		this.move= m;
	}
	public int getSnakeX(int index) {
	    return x[index];
	}
	public int getSnakeY(int index) {
	    return y[index];
	}

	public void setSnakeX(int i) {
	    x[0] = i;
	}

	public void setSnakeY(int i) {
	    y[0] = i;
	}

	public boolean isMovingLeft() {
	    if(move==Move.left) return true;
		return false;
	}

	public boolean isMovingRight() {
	    if(move==Move.right) return true;
		return false;
	}

	public boolean isMovingUp() {
	    if(move == Move.up) return true;
	    return false;
	}

	public boolean isMovingDown() {
	    if(move==Move.down) return true;
	    return false;
	}

	public int getJoints() {
	    return joints;
	}

	public void setJoints(int j) {
	    joints = j;
	}

	public void move() {
	    for (int i = joints; i > 0; i--) {

	        // Moves the joints of the snake 'up the chain'
	        // Meaning, the joint of the snake all move up one
	        x[i] = x[(i - 1)];
	        y[i] = y[(i - 1)];
	    }
	    //move=move.right;
	    
	    switch(move) {
	    case up: y[0] -= Board.getDotSize();break;
	    case down: y[0] += Board.getDotSize();break;
	    case left: x[0] -= Board.getDotSize();break;
	    case right: x[0] += Board.getDotSize();break;
	    
	    }
	}
}