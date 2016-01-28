package main;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import snake.SnakeEngine;
import snake_ai.DSnake;

/**
 * Created by apolol92 on 24.01.2016.
 */
public class Program extends Application {
    public static String GAME_TITLE = "Snake";
    public static void main(String[] args)
    {
        launch(args);
    }

    public void start(Stage theStage) {
        theStage.setTitle("Snake Game Example");
        //Create Layout and Scene
        Group root = new Group();
        Scene theScene = new Scene( root );
        theStage.setScene(theScene);
        //Create canvas
        Canvas canvas = new Canvas(150,150);
        //Add canvas
        root.getChildren().add(canvas);

        //Get graphicContext from canvas
        GraphicsContext gc = canvas.getGraphicsContext2D();

        //Create Engine
        SnakeEngine snakeEngine = new SnakeEngine(gc, theScene,true,200);
        snakeEngine.start();


        //Create Bot
        //TestBot testBot = new TestBot();
        //testBot.start();



        //Show stage
        theStage.show();


    }


}
