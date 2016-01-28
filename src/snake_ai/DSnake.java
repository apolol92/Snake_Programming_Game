package snake_ai;


import snake.SnakeBot;
import snake.SnakeWorld;
import snake_ai.neuro_evolution.NeuroEvolution;

/**
 * Created by apolol92 on 26.01.2016.
 */
public class DSnake extends SnakeBot {
    NeuroEvolution neuroEvolution;
    public DSnake(){
        this.neuroEvolution = new NeuroEvolution(10,4,5,2,3,1);
    }
    @Override
    public void run() {
        /*super.run();
        while(this.neuroEvolution.neuroNetworks.get(0).fitness<10) {
            System.out.println("Fitness: " +this.neuroEvolution.neuroNetworks.get(0).fitness);
            for(int i = 0; i < neuroEvolution.neuroNetworks.size(); i++) {
                SnakeGame.snakeWorld.restart();
                //waitForNextRound();
                while (true) {
                    System.out.println("Snake " + i+"/"+neuroEvolution.neuroNetworks.size()+getSnakeWorld().isGameOver());
                    this.neuroEvolution.neuroNetworks.get(i).fitness = this.getSnakeWorld().getScore();
                    if(next()==false) {
                        break;
                    }
                    double snakeX = this.getSnakeWorld().copySnake().get(0).getX();
                    double snakeY = this.getSnakeWorld().copySnake().get(0).getY();
                    double appleX = this.getSnakeWorld().copyApple().getX();
                    double appleY = this.getSnakeWorld().copyApple().getY();
                    double score = this.getSnakeWorld().getScore();
                    double[] computedDirection = new double[1];
                    this.neuroEvolution.neuroNetworks.get(i).compute(new double[]{snakeX, snakeY, appleX, appleY, score}, computedDirection);
                    //last check
                    sendCommand(translate(computedDirection[0]));

                }

            }
            System.out.println("SurviveTop");
            this.neuroEvolution.surviveTop(2);
            System.out.println("Repopulate");
            this.neuroEvolution.repopulateWithTop2();
        }
*/
    }

    private SnakeWorld.SNAKE_DIRECTION translate(double computedDirection) {
        System.out.println(computedDirection);
        if(computedDirection<=0.25) {
            return SnakeWorld.SNAKE_DIRECTION.TOP;
        }
        if(computedDirection<=0.5) {
            return SnakeWorld.SNAKE_DIRECTION.LEFT;
        }
        if(computedDirection<=0.75) {
            return SnakeWorld.SNAKE_DIRECTION.RIGHT;
        }
        return SnakeWorld.SNAKE_DIRECTION.BOT;
    }
}
