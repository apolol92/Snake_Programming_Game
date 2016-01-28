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
        while (SnakeGame.snakeDataTransfer.ready == false) {
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
    public void sendCommand(SnakeWorld.SNAKE_DIRECTION direction) {
        SnakeGame.snakeDataTransfer.direction = direction;
        SnakeGame.snakeDataTransfer.ready = false;
    }

    /**
     * Get a copy of the snake world
     *
     * @return copy snakeworld
     */
    public SnakeWorld getSnakeWorld() {
        SnakeWorld snakeWorld = new SnakeWorld();
        snakeWorld.snake = SnakeGame.snakeWorld.copySnake();
        snakeWorld.apple = SnakeGame.snakeWorld.copyApple();
        snakeWorld.gameOver = SnakeGame.snakeWorld.gameOver;
        snakeWorld.setScore(SnakeGame.snakeWorld.getScore());
        return snakeWorld;
    }

    /**
     * This method restarts the Snake Game
     */
    public void restartGame() {
        SnakeGame.snakeDataTransfer.clean();
        SnakeGame.snakeWorld.restart();
    }











}
