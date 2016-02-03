package snake;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

/**
 * Created by apolol92 on 24.01.2016.
 * This is the snake engine. Use it to run
 */
public class SnakeEngine extends Thread {
    /**
     * Standard score labeltext
     */
    public static String LABEL_STANDARD_SCORE = "Score: ";
    private boolean userInput;
    /**
     * Javafx scene, used for userinput
     */
    private Scene scene;
    /**
     * Use this reference to draw on a canvas
     */
    private GraphicsContext gc;

    int failCounter;
    /**
     * Scale gameoutput
     */
    public final int SCALE = 10;

    /**
     * Delay in ms between ticks
     */
    public static int tick_delay_ms;
    /**
     * Construct GameEngine
     * @param gc, reference on canvas GraphicsContext
     * @param scene, reference on scene
     */
    public SnakeEngine(GraphicsContext gc, Scene scene, boolean userInput, int tick_delay_ms) {
        this.gc = gc;
        this.scene = scene;
        SnakeEngine.tick_delay_ms = tick_delay_ms;
        SnakeGame.snakeWorld.restart();
        SnakeGame.snakeDataTransfer.clean();
        this.userInput = userInput;
        if(userInput) {
            this.scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
                                            public void handle(KeyEvent event) {
                                                switch (event.getCode().getName()) {
                                                    case "W":
                                                        SnakeGame.snakeDataTransfer.direction = SnakeWorld.SNAKE_DIRECTION.TOP;
                                                        SnakeGame.snakeDataTransfer.ready = false;
                                                        break;
                                                    case "A":
                                                        SnakeGame.snakeDataTransfer.direction = SnakeWorld.SNAKE_DIRECTION.LEFT;
                                                        SnakeGame.snakeDataTransfer.ready = false;
                                                        break;
                                                    case "D":
                                                        SnakeGame.snakeDataTransfer.direction = SnakeWorld.SNAKE_DIRECTION.RIGHT;
                                                        SnakeGame.snakeDataTransfer.ready = false;
                                                        break;
                                                    case "S":
                                                        SnakeGame.snakeDataTransfer.direction = SnakeWorld.SNAKE_DIRECTION.BOT;
                                                        SnakeGame.snakeDataTransfer.ready = false;
                                                        break;
                                                    case "Enter":
                                                        SnakeGame.snakeDataTransfer.clean();
                                                        SnakeGame.snakeWorld.restart();
                                                        System.out.println("Enter OK");
                                                        break;
                                                    case "Q":
                                                        if(SnakeEngine.tick_delay_ms>20) {
                                                            SnakeEngine.tick_delay_ms -= 20;
                                                        }
                                                        break;
                                                    case "E":
                                                        SnakeEngine.tick_delay_ms += 20;
                                                        break;


                                                }
                                                System.out.println(event.getCode().getName());
                                            }
                                        }
            );
        }
        else {
            this.scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
                                            public void handle(KeyEvent event) {
                                                switch (event.getCode().getName()) {
                                                    case "Ctrl":
                                                        if(SnakeEngine.tick_delay_ms>20) {
                                                            SnakeEngine.tick_delay_ms -= 20;
                                                        }
                                                        break;
                                                    case "Shift":
                                                        SnakeEngine.tick_delay_ms += 20;
                                                        break;


                                                }
                                                System.out.println(event.getCode().getName());
                                            }
                                        }
            );
        }
    }


    /**
     * Wait ms
     * @param ms
     */
    public void waitMs(int ms) {
        long start_ms = System.currentTimeMillis();
        long current_ms;
        do {
            current_ms = System.currentTimeMillis();
        }while(Math.abs(current_ms-start_ms)<=ms);
    }

    /**
     * runs
     */
    @Override
    public void run() {
        while(true) {
            //Game Over?
            //Ready for next command
            if(!SnakeGame.snakeWorld.isGameOver()) {
                SnakeGame.snakeDataTransfer.ready = true;
                drawSnakeGameData(SnakeGame.snakeWorld);
                waitMs(tick_delay_ms);
                //while(SnakeGame.snakeDataTransfer.ready) {
                    //waitMs(1);
                //}
                //Draw
                drawSnakeGameData(SnakeGame.snakeWorld);
                if(SnakeGame.snakeDataTransfer.ready==false && this.userInput==true) {
                    SnakeGame.snakeWorld.moveSnake(SnakeGame.snakeDataTransfer.direction);
                }
                else if(this.userInput==true){
                    SnakeGame.snakeWorld.moveSnake();
                }
                if(this.userInput==false && SnakeGame.snakeDataTransfer.ready==false) {
                    SnakeGame.snakeWorld.moveSnake(SnakeGame.snakeDataTransfer.direction);
                }
            }
            else {
                waitMs(tick_delay_ms);
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
        gc.setFill(Color.GREEN);
        for (int i = 0; i < snakeWorld.snake.size(); i++) {
            gc.fillRect(snakeWorld.snake.get(i).getX(), snakeWorld.snake.get(i).getY(), SCALE, SCALE);
        }
        //Apple
        gc.setFill( Color.RED );
        gc.fillRect(snakeWorld.apple.getX(), snakeWorld.apple.getY(), SCALE, SCALE);
        //Score
        gc.setFill(Color.BLACK);
        gc.fillText(LABEL_STANDARD_SCORE + snakeWorld.getScore(), gc.getCanvas().getWidth()/2-25, 15);

    }

    /**
     * Draw Game Over on the screen
     * @param failCounter, progress
     * @param snakeWorld, needed for score..(TODO: only int)
     */
    public void drawSnakeGameOver(int failCounter, SnakeWorld snakeWorld) {
        gc.setFill(new Color(0.85, 0.85, 1.0, 1.0));
        gc.fillRect(0, 0, 512, 512);
        //Score
        gc.setFill(Color.BLACK);
        gc.fillText(LABEL_STANDARD_SCORE + snakeWorld.getScore(), gc.getCanvas().getWidth() / 2 - 25, 15);
        //Game Over Text
        gc.setFill( Color.RED );
        gc.fillText("Game Over![ENTER]", gc.getCanvas().getWidth() / 2 - 25, 28);
    }

    public void restart(){
        SnakeGame.snakeDataTransfer.clean();
        SnakeGame.snakeWorld.restart();
    }


}
