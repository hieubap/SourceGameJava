package Primary;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;

import Primary.Snake.Move;

@SuppressWarnings("serial")
public class Board extends JPanel implements ActionListener {

public final static int MOVEPIXEL = 20;
public final static int BOARDWIDTH = 1000;
public final static int BOARDHEIGHT = 500;
public final static int PIXELSIZE = 20;
public final static int TOTALPIXELS = (BOARDWIDTH * BOARDHEIGHT)
       / (PIXELSIZE * PIXELSIZE);

private boolean inGame = true;
//private Move mode;

private Timer timer;
private static int speed = 100;

private Snake snake = new Snake();
private Food food = new Food();

public Board() {

   addKeyListener(new Keys());
   setBackground(Color.BLACK);
   setFocusable(true);

   setPreferredSize(new Dimension(BOARDWIDTH, BOARDHEIGHT));

   initializeGame();
}

//Used to paint our components to the screen
@Override
protected void paintComponent(Graphics g) {
   super.paintComponent(g);

   draw(g);
}

//Draw our Snake & Food (Called on repaint()).
void draw(Graphics g) {
   // Only draw if the game is running / the snake is alive
   if (inGame == true) {
       g.setColor(Color.green);
       g.fillRect(food.foodX, food.foodY, PIXELSIZE, PIXELSIZE); // food

       // Draw our snake.
       g.setColor(Color.RED);
       for (int i = 0; i < snake.getJoints(); i++) {
           g.fillRect(snake.getSnakeX(i), snake.getSnakeY(i),
                       PIXELSIZE, PIXELSIZE);
       }

       // Sync our graphics together
       Toolkit.getDefaultToolkit().sync();
   } else {
       endGame(g);
   }
}

void initializeGame() {
   snake.setJoints(3); // set our snake's initial size

   // Create our snake's body
   for (int i = 0; i < snake.getJoints(); i++) {
       snake.setSnakeX(BOARDWIDTH / 2);
       snake.setSnakeY(BOARDHEIGHT / 2);
   }
   // Start off our snake moving right
   snake.setmove(Move.left);

   // Generate our first 'food'
   food.createFood();

   // set the timer to record our game's speed / make the game move
   timer = new Timer(speed, this);
   timer.start();
}

//if our snake is in the close proximity of the food..
void checkFoodCollisions() {

   if ((proximity(snake.getSnakeX(0), food.foodX, 20))
           && (proximity(snake.getSnakeY(0), food.foodY, 20))) {

       System.out.println("intersection");
       // Add a 'joint' to our snake
       snake.setJoints(snake.getJoints() + 1);
       // Create new food
       food.createFood();
       System.out.println("x= "+food.foodX+ " y= "+food.foodY);
   }
}

//Used to check collisions with snake's self and board edges
void checkCollisions() {

   // If the snake hits its' own joints..
   for (int i = snake.getJoints(); i > 0; i--) {

       // Snake cant intersect with itself if it's not larger than 5
       if ((i > 5)
               && (snake.getSnakeX(0) == snake.getSnakeX(i) && (snake
                       .getSnakeY(0) == snake.getSnakeY(i)))) {
           inGame = false; // then the game ends
       }
   }

   // If the snake intersects with the board edges..
   if (snake.getSnakeY(0) >= BOARDHEIGHT-PIXELSIZE) {
       inGame = false;
   }

   if (snake.getSnakeY(0) < 0) {
       inGame = false;
   }

   if (snake.getSnakeX(0) >= BOARDWIDTH-PIXELSIZE) {
       inGame = false;
   }

   if (snake.getSnakeX(0) < 0) {
       inGame = false;
   }

   // If the game has ended, then we can stop our timer
   if (!inGame) {
       timer.stop();
   }
}

void endGame(Graphics g) {
	String message = "Game over";

   Font font = new Font("Times New Roman", Font.BOLD, 30);
   FontMetrics metrics = getFontMetrics(font);
   
   g.setColor(Color.red);
   g.setFont(font);

   g.drawString(message, (BOARDWIDTH - metrics.stringWidth(message)) / 2,
           BOARDHEIGHT / 2);
   g.setFont(new Font("Times New Roman", Font.BOLD, 20));
   g.drawString("press enter to replay",  (BOARDWIDTH - metrics.stringWidth(message)) / 2,
           BOARDHEIGHT / 3 * 2);
}

//Run constantly as long as we're in game.
@Override
public void actionPerformed(ActionEvent e) {
if (inGame == true) {

       checkFoodCollisions();
       checkCollisions();
       snake.move();
   }
   // Repaint or 'render' screen
   repaint();
}

private class Keys extends KeyAdapter {

   @Override
   public void keyPressed(KeyEvent e) {

       int key = e.getKeyCode();

       if ((key == KeyEvent.VK_LEFT) && (!snake.isMovingRight())) {
           snake.setmove(Move.left);
       }

       else if ((key == KeyEvent.VK_RIGHT) && (!snake.isMovingLeft())) {
           snake.setmove(Move.right);
       }
       else if ((key == KeyEvent.VK_UP) && (!snake.isMovingDown())) {
           snake.setmove(Move.up);
       }

       else if ((key == KeyEvent.VK_DOWN) && (!snake.isMovingUp())) {
           snake.setmove(Move.down);
       }

       if ((key == KeyEvent.VK_ENTER) && (inGame == false)) {

           inGame = true;
           initializeGame();
       }
   }
}

private boolean proximity(int a, int b, int closeness) {
   return Math.abs((long) a - b) <= closeness;
}

public static int getAllDots() {
   return TOTALPIXELS;
}

public static int getDotSize() {
   return MOVEPIXEL;
}
}