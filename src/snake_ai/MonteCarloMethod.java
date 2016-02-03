package snake_ai;

import javafx.geometry.Point2D;
import snake.SnakeWorld;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by apolol92 on 29.01.2016.
 */
public class MonteCarloMethod {

    public static ArrayList<Branch> calculateToNextApple(SnakeWorld snakeWorld, int n, int max_steps) {
        int i = 0;
        SnakeWorld copyWorld = snakeWorld.copy();
        ArrayList<Branch> branches = new ArrayList<Branch>();
        while(i<n) {
            branches.add(new Branch(copyWorld.copy(),max_steps));
            Point2D applePosition = branches.get(i).snakeWorld.copyApple();
            while(true) {
                //If game over.. remove useless game from list
                if(branches.get(i).snakeWorld.isGameOver()==true) {
                    branches.remove(i);
                    i--;
                    break;
                }
                //If snake ate apple.. perfect.. keep it :)
                if(applePosition.distance(branches.get(i).snakeWorld.copySnake().get(0).getX(),branches.get(i).snakeWorld.copySnake().get(0).getY())==0) {
                    break;
                }
                //If game running to long.. senseless.. remove it
                if(branches.get(i).steps> branches.get(i).max_steps) {
                    branches.remove(i);
                    i--;
                    break;
                }

                //Do next random step
                //Do calculation
                Random rnd = new Random();
                //Move direction
                SnakeWorld.SNAKE_DIRECTION mDirection = SnakeWorld.SNAKE_DIRECTION.LEFT;
                do {
                    int r = rnd.nextInt(4);
                    switch (r) {
                        case 0:
                            mDirection = SnakeWorld.SNAKE_DIRECTION.TOP;
                            break;
                        case 1:
                            mDirection = SnakeWorld.SNAKE_DIRECTION.LEFT;
                            break;
                        case 2:
                            mDirection = SnakeWorld.SNAKE_DIRECTION.RIGHT;
                            break;
                        case 3:
                            mDirection = SnakeWorld.SNAKE_DIRECTION.BOT;
                            break;
                    }
                }while(branches.get(i).snakeWorld.invertedDirection(mDirection)==true);
                branches.get(i).history.add(mDirection);
                branches.get(i).snakeWorld.moveSnake(mDirection);
                branches.get(i).steps++;

            }
            i++;
        }
        return branches;
    }

    public static double calculateWeight(SnakeWorld.SNAKE_DIRECTION snakeDirection, SnakeWorld snakeWorld, int threshold, int tries) {
        int max = 0;
        int i = 0;
        //Not allowed
        if(snakeWorld.invertedDirection(snakeDirection)) {
            return 0;
        }
        SnakeWorld startPosition = snakeWorld.copy();
        startPosition.moveSnake(snakeDirection);
        SnakeWorld runner = startPosition.copy();
        //Game Over at startposition
        if(runner.isGameOver()) {
            return 0;
        }
        ArrayList<Branch> branches = new ArrayList<Branch>();
        //Try n-times
        while(i < tries) {
            runner = startPosition.copy();
            branches.add(new Branch(runner,threshold));
            while(true) {
                //If game over.. remove useless game from list
                if(branches.get(i).snakeWorld.isGameOver()==true) {
                    branches.remove(i);
                    i--;
                    break;
                }
                //If game running to long.. use it
                if(branches.get(i).steps> branches.get(i).max_steps) {
                    return branches.get(i).steps;
                }

                //Do next random step
                //Do calculation
                Random rnd = new Random();
                //Move direction
                SnakeWorld.SNAKE_DIRECTION nDirection = SnakeWorld.SNAKE_DIRECTION.LEFT;
                do {
                    int r = rnd.nextInt(4);
                    switch (r) {
                        case 0:
                            nDirection = SnakeWorld.SNAKE_DIRECTION.TOP;
                            break;
                        case 1:
                            nDirection = SnakeWorld.SNAKE_DIRECTION.LEFT;
                            break;
                        case 2:
                            nDirection = SnakeWorld.SNAKE_DIRECTION.RIGHT;
                            break;
                        case 3:
                            nDirection = SnakeWorld.SNAKE_DIRECTION.BOT;
                            break;
                    }
                }while(branches.get(i).snakeWorld.invertedDirection(nDirection)==true);
                branches.get(i).history.add(nDirection);
                branches.get(i).snakeWorld.moveSnake(nDirection);
                branches.get(i).steps++;
            }
            i++;
        }

        //Get longest distance
        for(i = 0; i < branches.size(); i++) {
            if(branches.get(i).steps>max) {
                max = branches.get(i).steps;
            }
        }
        return max;
    }



}
