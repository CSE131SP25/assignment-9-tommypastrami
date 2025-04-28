package assignment9;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import edu.princeton.cs.introcs.StdDraw;

public class Game {
    private Snake snake;
    private Food food;

    public Game() {
        StdDraw.enableDoubleBuffering();
        snake = new Snake();
        food = new Food();
    }

    public void play() {
        while (snake.isInbounds()) {
            int dir = getKeypress();
            if (dir > 0) snake.changeDirection(dir);

            
            //eating da food (in snake.eatfood)
            snake.move();
            if (snake.eatFood(food)) {
                food = new Food();
            }

            updateDrawing();
        }
        System.out.println("Game Over! Final length = " + snake.getLength());
    }

    private int getKeypress() {
        if (StdDraw.isKeyPressed(KeyEvent.VK_W)) return 1;
        if (StdDraw.isKeyPressed(KeyEvent.VK_S)) return 2;
        if (StdDraw.isKeyPressed(KeyEvent.VK_A)) return 3;
        if (StdDraw.isKeyPressed(KeyEvent.VK_D)) return 4;
        return -1;
    }

    
    
    //creative portion where i do onscreen scoring
    private void updateDrawing() {
        StdDraw.clear();
        // draw game objects
        snake.draw();
        food.draw();
        // draw score
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.setFont(new Font("Arial", Font.BOLD, 16));
        StdDraw.textLeft(0.02, 0.98, "Score: " + snake.getLength());

        StdDraw.pause(50);
        StdDraw.show();
    }

    public static void main(String[] args) {
        Game g = new Game();
        g.play();
    }
}
