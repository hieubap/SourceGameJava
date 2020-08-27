package Primary;

public class Food {

private Snake snake = new Snake();
public int foodX; // Stores X pos of our food
public int foodY; // Stores Y pos of our food

// Used to determine random position of food
private final int RANDOMPOSITIONX = Board.BOARDWIDTH/Board.MOVEPIXEL;
private final int RANDOMPOSITIONY = Board.BOARDHEIGHT/Board.MOVEPIXEL;


public void createFood() {

    // Set our food's x & y position to a random position

    int location = (int) (Math.random() * RANDOMPOSITIONX);
    foodX = ((location * Board.getDotSize()));

    location = (int) (Math.random() * RANDOMPOSITIONY);
    foodY = ((location * Board.getDotSize()));

    if ((foodX == snake.getSnakeX(0)) && (foodY == snake.getSnakeY(0))) {
        createFood();
    }
}
}
