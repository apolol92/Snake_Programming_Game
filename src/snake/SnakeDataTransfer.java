package snake;

/**
 * Created by apolol92 on 24.01.2016.
 */
public class SnakeDataTransfer {
    public SnakeWorld.SNAKE_DIRECTION direction;
    public SnakeWorld snakeWorld;
    public boolean ready;
    public SnakeDataTransfer(SnakeWorld snakeWorld) {
        this.direction = SnakeWorld.SNAKE_DIRECTION.TOP;
        this.snakeWorld = snakeWorld;
        this.ready = true;
    }
}
