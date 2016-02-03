package snake_ai;

import snake.SnakeWorld;

/**
 * Created by apolol92 on 29.01.2016.
 */
public class DirectionWeighter {


    public static double[] weight(SnakeWorld snakeWorld, int threshhold, int tries) {
        double[] weights = new double[4];   //top,left,right,bottom 0 if lastDirection
        //Calculate TOP Weight
        weights[0] = MonteCarloMethod.calculateWeight(SnakeWorld.SNAKE_DIRECTION.TOP,snakeWorld,threshhold,tries);
        //Calculate LEFT Weight
        weights[1] = MonteCarloMethod.calculateWeight(SnakeWorld.SNAKE_DIRECTION.LEFT,snakeWorld,threshhold,tries);
        //Calculate RIGHT Weight
        weights[2] = MonteCarloMethod.calculateWeight(SnakeWorld.SNAKE_DIRECTION.RIGHT,snakeWorld,threshhold,tries);
        //Calculate BOTTOM Weight
        weights[3] = MonteCarloMethod.calculateWeight(SnakeWorld.SNAKE_DIRECTION.BOT,snakeWorld,threshhold,tries);
        if(snakeWorld.invertedDirection(SnakeWorld.SNAKE_DIRECTION.TOP)) {
            weights[0] = 0;
        }
        else if(snakeWorld.invertedDirection(SnakeWorld.SNAKE_DIRECTION.LEFT)) {
            weights[1] = 0;
        }
        else if(snakeWorld.invertedDirection(SnakeWorld.SNAKE_DIRECTION.RIGHT)) {
            weights[2] = 0;
        }
        else if(snakeWorld.invertedDirection(SnakeWorld.SNAKE_DIRECTION.BOT)) {
            weights[3] = 0;
        }
        return weights;
    }

    public static void printWeights(double[] weights) {
        if(weights!=null) {
            System.out.println(weights[0] + " " + weights[1] + " " + weights[2] + " " + weights[3]);
        }
    }



    public static SnakeWorld.SNAKE_DIRECTION getDirectionByWeight(double[] weights) {
        double max=0;
        int index = 0;
        SnakeWorld.SNAKE_DIRECTION direction = SnakeWorld.SNAKE_DIRECTION.TOP;
        for(int i = 0; i < weights.length; i++) {
            if(weights[i]>max) {
                index = i;
                max = weights[i];
            }
        }
        switch(index) {
            case 0:
                direction = SnakeWorld.SNAKE_DIRECTION.TOP;
                break;
            case 1:
                direction = SnakeWorld.SNAKE_DIRECTION.LEFT;
                break;
            case 2:
                direction = SnakeWorld.SNAKE_DIRECTION.RIGHT;
                break;
            case 3:
                direction = SnakeWorld.SNAKE_DIRECTION.BOT;
                break;
        }
        System.out.println("Chosed "+direction.name());
        return direction;

    }
}
