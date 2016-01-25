package snake;

import javafx.geometry.Point2D;

import java.util.ArrayList;

/**
 * Created by apolol92 on 24.01.2016.
 * Use this abstract class to create SnakeBots
 */
public abstract class SnakeBot extends Thread {

    /**
     * A reference to the Snake Game Data Transfer
     */
    private SnakeDataTransfer snakeDataTransferReference;

    /**
     * Construct the snake bot
     */
    public SnakeBot() {
        this.snakeDataTransferReference = null;
    }

    /**
     * Add Snake Game Data Transfer reference to Snake Bot (MUST DO!!!)
     * @param snakeDataTransferReference
     */
    public void setSnakeDataTransferReference(SnakeDataTransfer snakeDataTransferReference) {
        this.snakeDataTransferReference = snakeDataTransferReference;
    }

    /**
     * Wait for next game frame
     */
    public void next() {
        while(this.snakeDataTransferReference.ready==false) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Send new calculated command to game
     * @param direction
     */
    public void sendCommand(SnakeWorld.SNAKE_DIRECTION direction) {
        this.snakeDataTransferReference.direction = direction;
        this.snakeDataTransferReference.ready = false;
    }

    /**
     * Get a copy of the snake world
     * @return copy snakeworld
     */
    public SnakeWorld getSnakeWorld() {
        SnakeWorld snakeWorld = new SnakeWorld();
        snakeWorld.snake = this.snakeDataTransferReference.snakeWorld.copySnake();
        snakeWorld.apple = this.snakeDataTransferReference.snakeWorld.copyApple();
        snakeWorld.gameOver = this.snakeDataTransferReference.snakeWorld.gameOver;
        snakeWorld.setScore(this.snakeDataTransferReference.snakeWorld.getScore());
        return snakeWorld;
    }






}
