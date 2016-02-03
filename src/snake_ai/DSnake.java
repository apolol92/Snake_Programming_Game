package snake_ai;

import javafx.geometry.Point2D;
import snake.SnakeBot;
import snake.SnakeWorld;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by apolol92 on 29.01.2016.
 */
public class DSnake extends SnakeBot {
    public final int MAX_STEPS = 100;
    public final int BRANCH_SIZE = 50;
    public final int WEIGHT_TRESHOLD = 80;
    public final int WEIGHT_TRIES = 50;
    public DSnake() {

    }

    @Override
    public void run() {
        super.run();
        while(true){
            if(!next()) {
                //Game is over.. do nothing
            }
            else {
                long start = System.currentTimeMillis();
                ArrayList<Branch> branchs = MonteCarloMethod.calculateToNextApple(getSnakeWorld(), BRANCH_SIZE, MAX_STEPS);
                Branch best = getMaxBranch(branchs);
                System.out.println(System.currentTimeMillis()-start);
                for(int i = 0; i < best.history.size(); i++) {
                    if(!next()) {
                        i--;
                    }
                    else {
                        sendCommand(best.history.get(i));
                    }
                }

            }

        }

    }

    private boolean isNextGameOver(SnakeWorld.SNAKE_DIRECTION direction, SnakeWorld snakeWorld) {
        SnakeWorld test = snakeWorld.copy();
        //test.moveSnake(direction);
        return test.isGameOver();
    }

    private Branch getMaxBranch(ArrayList<Branch> branches) {
        int maxIndex = 0;
        double[] maxWeights = DirectionWeighter.weight(branches.get(0).snakeWorld,WEIGHT_TRESHOLD,WEIGHT_TRIES);
        if(branches.size()==1) {
            return branches.get(0);
        }
        for(int i = 1; i < branches.size(); i++) {
            double[] currentWeights = DirectionWeighter.weight(branches.get(i).snakeWorld,WEIGHT_TRESHOLD,WEIGHT_TRIES);
            //DirectionWeighter.printWeights(currentWeights);
            if(compareArr(maxWeights,currentWeights)==2) {
                maxWeights = currentWeights;
                maxIndex = i;
                branches.get(maxIndex).weights = maxWeights;

            }
        }
        return branches.get(maxIndex);
    }

    private int compareArr(double[] a1, double[] a2) {
        double max=0;
        for(int i = 0; i < a1.length; i++) {
            if(a1[i]>max) {
                max = a1[i];
            }
        }
        for(int i = 0; i < a2.length; i++) {
            if(a2[i]>max) {
                return 2;
            }
        }
        return 1;
    }



}
