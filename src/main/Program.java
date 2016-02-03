package main;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import snake.SnakeEngine;
import snake.SnakeWorld;
import snake_ai.Branch;
import snake_ai.DSnake;
import snake_ai.DirectionWeighter;
import snake_ai.MonteCarloMethod;

import java.util.ArrayList;

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
        SnakeEngine snakeEngine = new SnakeEngine(gc, theScene,true,100);
        snakeEngine.start();

        //DSnake dSnake = new DSnake();
        //dSnake.start();

        //Create Bot
        //TestBot testBot = new TestBot();
        //testBot.start();
        /*long startMs = System.currentTimeMillis();
        ArrayList<Branch> branches = MonteCarloMethod.calculateToNextApple(new SnakeWorld(), 10, 1000);
        System.out.println((System.currentTimeMillis() - startMs));
        System.out.println(branches.size());
        startMs = System.currentTimeMillis();
        for(int i = 0; i < branches.size(); i++) {
            double[] weights = DirectionWeighter.weight(branches.get(i).snakeWorld,500,100);
            DirectionWeighter.printWeights(i, weights);
        }
        System.out.println((System.currentTimeMillis() - startMs));*/
        //Show stage
        theStage.show();


    }




}
