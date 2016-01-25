package snake;

/**
 * Created by apolol92 on 24.01.2016.
 */
public class SnakeDataTransfer {
    /**
     * Contains the new direction of the snake
     */
    public SnakeWorld.SNAKE_DIRECTION direction;
    /**
     * Reference to the SnakeWorld
     */
    public SnakeWorld snakeWorld;
    /**
     * Ready for new command?
     */
    public boolean ready;

    /**
     * Constructor need a reference on the snakeWorld
     * @param snakeWorld
     */
    public SnakeDataTransfer(SnakeWorld snakeWorld) {
        this.direction = SnakeWorld.SNAKE_DIRECTION.TOP;
        this.snakeWorld = snakeWorld;
        this.ready = true;
    }
}
