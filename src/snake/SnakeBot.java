package snake;

/**
 * Created by apolol92 on 24.01.2016.
 * Use this abstract class to create SnakeBots
 */
public abstract class SnakeBot extends Thread {

    /**
     * Wait for next game frame
     */
    public boolean next() {
        while (true) {
            if(SnakeGame.snakeDataTransfer.ready == true) {
                break;
            }
            try {
                sleep(0);
            } catch (Exception ex) {

            }
            if (SnakeGame.snakeWorld.isGameOver()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Send new calculated command to game
     *
     * @param direction
     */
    public boolean sendCommand(SnakeWorld.SNAKE_DIRECTION direction) {
        if(SnakeGame.snakeWorld.isGameOver()) {
            return false;
        }
        SnakeGame.snakeDataTransfer.direction = direction;
        SnakeGame.snakeDataTransfer.ready = false;
        return true;
    }

    /**
     * Get a copy of the snake world
     *
     * @return copy snakeworld
     */
    public SnakeWorld getSnakeWorld() {
        return SnakeGame.snakeWorld.copy();
    }

    /**
     * This method restarts the Snake Game
     */
    public void restartGame() {
        SnakeGame.snakeDataTransfer.clean();
        SnakeGame.snakeWorld.restart();
    }











}
