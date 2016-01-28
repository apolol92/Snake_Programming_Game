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
     * Ready for new command?
     */
    public boolean ready;

    public SnakeDataTransfer() {
        this.direction = SnakeWorld.SNAKE_DIRECTION.TOP;
        this.ready = true;
    }

    public void clean() {
        this.direction = SnakeWorld.SNAKE_DIRECTION.TOP;
        this.ready = true;
    }
}
