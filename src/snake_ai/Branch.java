package snake_ai;

import snake.SnakeWorld;

import java.util.ArrayList;

/**
 * Created by apolol92 on 29.01.2016.
 */
public class Branch {
    public SnakeWorld snakeWorld;
    public int steps;
    public  int max_steps;
    public ArrayList<SnakeWorld.SNAKE_DIRECTION> history;
    public double[] weights;

    public Branch(SnakeWorld snakeWorld, int max_steps) {
        this.snakeWorld = snakeWorld.copy();
        this.steps = 0;
        this.max_steps = max_steps;
        this.history = new ArrayList<SnakeWorld.SNAKE_DIRECTION>();
    }
}
