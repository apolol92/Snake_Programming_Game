package main;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import snake.SnakeEngine;

/**
 * Created by apolol92 on 24.01.2016.
 */
public class Program extends Application {
    public static String GAME_TITLE = "Snake";
    public static void main(String[] args)
    {
        launch(args);
    }

    public void start(Stage theStage)
    {
        theStage.setTitle("Timeline Example");

        Group root = new Group();
        Scene theScene = new Scene( root );
        theStage.setScene(theScene);

        Canvas canvas = new Canvas(150,150);
        root.getChildren().add(canvas);


        GraphicsContext gc = canvas.getGraphicsContext2D();

        final long startNanoTime = System.nanoTime();
        //Create Engine
        SnakeEngine snakeEngine = new SnakeEngine(gc, theScene,true);
        snakeEngine.start();

        //Create Bot
        //TestBot testBot = new TestBot();
        //testBot.setSnakeDataTransferReference(snakeEngine.getSnakeDataTransfer());
        //testBot.start();
        theStage.show();


    }
}
