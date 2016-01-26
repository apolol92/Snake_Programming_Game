package main;

import snake.SnakeBot;
import snake.SnakeWorld;

import java.util.Random;

/**
 * Created by apolol92 on 24.01.2016.
 */
public class TestBot extends SnakeBot {
    public TestBot() {

    }

    @Override
    public void run() {
        super.run();
        while(true) {
            //Wait for next round
            next();
            //Do calculation
            Random rnd = new Random();
            //Send Command
            System.out.println("Send Command");
            switch (rnd.nextInt(4)) {
                case 0:
                    sendCommand(SnakeWorld.SNAKE_DIRECTION.RIGHT);
                    break;
                case 1:
                    sendCommand(SnakeWorld.SNAKE_DIRECTION.TOP);
                    break;
                case 2:
                    sendCommand(SnakeWorld.SNAKE_DIRECTION.LEFT);
                    break;
                case 3:
                    sendCommand(SnakeWorld.SNAKE_DIRECTION.BOT);
                    break;
            }
        }

    }
}
