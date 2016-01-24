package snake;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

/**
 * Created by apolol92 on 24.01.2016.
 * This is the snake engine. Use it to run
 */
public class SnakeEngine extends AnimationTimer {
    /**
     * Standard score labeltext
     */
    public static String LABEL_STANDARD_SCORE = "Score: ";
    /**
     * Javafx scene, used for userinput
     */
    private Scene scene;
    /**
     * Use this reference to draw on a canvas
     */
    private GraphicsContext gc;
    /**
     * This is the snakeWorld
     */
    SnakeWorld snakeWorld;
    /**
     * Delay counter
     */
    int counter;

    int failCounter;
    /**
     * Scale gameoutput
     */
    public final int SCALE = 10;
    /**
     * Which key is pressed?
     */
    public SnakeDataTransfer snakeDataTransfer;
    /**
     * Game delay
     */
    public final int EXTRA_GAME_DELAY = 10;
    /**
     * Failing delay
     */
    public final int FAILING_DELAY = 40;

    /**
     * Construct GameEngine
     * @param gc, reference on canvas GraphicsContext
     * @param scene, reference on scene
     */
    public SnakeEngine(GraphicsContext gc, Scene scene, boolean userInput) {
        this.gc = gc;
        this.scene = scene;
        this.snakeWorld = new SnakeWorld();
        this.snakeDataTransfer = new SnakeDataTransfer(this.snakeWorld);
        this.counter = 0;
        this.failCounter = 0;
        if(userInput) {
            this.scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
                                            public void handle(KeyEvent event) {
                                                switch (event.getCode().getName()) {
                                                    case "W":
                                                        snakeDataTransfer.direction = SnakeWorld.SNAKE_DIRECTION.TOP;
                                                        break;
                                                    case "A":
                                                        snakeDataTransfer.direction = SnakeWorld.SNAKE_DIRECTION.LEFT;
                                                        break;
                                                    case "D":
                                                        snakeDataTransfer.direction = SnakeWorld.SNAKE_DIRECTION.RIGHT;
                                                        break;
                                                    case "S":
                                                        snakeDataTransfer.direction = SnakeWorld.SNAKE_DIRECTION.BOT;
                                                        break;
                                                }
                                                System.out.println(event.getCode().getName());
                                            }
                                        }
            );
        }
    }

    /**
     * Use this instance to send Data or receive Data to/from Game
     * @return reference to snakeDataTransfer
     */
    public SnakeDataTransfer getSnakeDataTransfer() {
        return this.snakeDataTransfer;
    }


    /**
     * runs with 60Hz
     * @param now
     */
    @Override
    public void handle(long now) {
        snakeDataTransfer.ready = true;
        //Game Over?
        if(this.snakeWorld.gameOver) {
            failCounter++;
            drawSnakeGameOver(failCounter,snakeWorld);
            if(failCounter>=FAILING_DELAY) {
                this.snakeWorld.restart();
                failCounter = 0;
            }
        }
        else {
            //Draw
            drawSnakeGameData(this.snakeWorld);
            counter++;
            if (counter >= EXTRA_GAME_DELAY) {
                this.snakeWorld.moveSnake(snakeDataTransfer.direction);
                counter = 0;
            }
        }
    }

    /**
     * Draw the game..
     * @param snakeWorld
     */
    public void drawSnakeGameData(SnakeWorld snakeWorld) {
        gc.setFill(new Color(0.85, 0.85, 1.0, 1.0));
        gc.fillRect(0, 0, 512, 512);
        //Snake
        gc.setFill( Color.GREEN );
        for (int i = 0; i < snakeWorld.snake.size(); i++) {
            gc.fillRect(snakeWorld.snake.get(i).getX(), snakeWorld.snake.get(i).getY(), SCALE, SCALE);
        }
        //Apple
        gc.setFill( Color.RED );
        gc.fillRect(snakeWorld.apple.getX(), snakeWorld.apple.getY(), SCALE, SCALE);
        //Score
        gc.setFill(Color.BLACK);
        gc.fillText(LABEL_STANDARD_SCORE + snakeWorld.getScore(), gc.getCanvas().getWidth()/2-25, 10);

    }

    public void drawSnakeGameOver(int failCounter, SnakeWorld snakeWorld) {
        gc.setFill(new Color(0.85, 0.85, 1.0, 1.0));
        gc.fillRect(0, 0, 512, 512);
        //Score
        gc.setFill(Color.BLACK);
        gc.fillText(LABEL_STANDARD_SCORE + snakeWorld.getScore(), gc.getCanvas().getWidth() / 2 - 25, 10);
        //Game Over Text
        gc.setFill( Color.RED );
        gc.fillText("Reloading "+(int)((float)failCounter/(float)FAILING_DELAY * 100)+"%", gc.getCanvas().getWidth()/2-25, 20);
    }
}