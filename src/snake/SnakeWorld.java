package snake;
import javafx.geometry.Point2D;
import java.util.ArrayList;
import java.util.Random;
/**
 * Created by apolol92 on 24.01.2016.
 * This class represents a snake world.
 */
public class SnakeWorld {
    /**
     * Is the game runnig(false) or over(true)?
     */
    boolean gameOver;

    /**
     * Get method for gameOver
     * @return gameOver
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * How many points in the current game?
     */
    private int score;

    /**
     * Get Method for score
     * @return score
     */
    public int getScore() {
        return score;
    }

    /**
     * Set new score. Needed by restart..
     * @param score, new score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Shows the game width and height.
     * Make sure that GAME_WIDTH % SNAKE_SPEED = 0 and GAME_HEIGHT % SNAKE_SPEED = 0
     */
    public final int GAME_WIDTH=150, GAME_HEIGHT=150;

    /**
     * In which direction is the snake walking?
     */
    public enum SNAKE_DIRECTION {NOTHING,TOP,LEFT,RIGHT,BOT};
    /**
     * last snake direction
     */
    private SNAKE_DIRECTION lastDirection;
    /**
     * how fast is the snake walking
     * Make sure that GAME_WIDTH % SNAKE_SPEED = 0 and GAME_HEIGHT % SNAKE_SPEED = 0
     */
    private final int SNAKE_SPEED=10;
    /**
     * snake parts
     */
    ArrayList<Point2D> snake;
    /**
     * apple position
     */
    Point2D apple;

    /**
     * Construct the snake world
     */
    public SnakeWorld() {
        this.snake = new ArrayList<Point2D>();
        this.snake.add(new Point2D(GAME_WIDTH/2 - ((GAME_WIDTH/2)%10),GAME_WIDTH/2 - ((GAME_WIDTH/2)%10)));
        this.gameOver = false;
        this.lastDirection = SNAKE_DIRECTION.TOP;
        setNewRandomApplePosition();
    }

    /**
     * Moves the snake in the last defined direction
     */
    public void moveSnake() {
        int nX=0,nY=0;
        if(gameOver==false) {
            switch (lastDirection) {
                case TOP:
                    nX = (int) this.snake.get(0).getX();
                    nY = (int) this.snake.get(0).getY() - SNAKE_SPEED;
                    break;
                case LEFT:
                    nX = (int) this.snake.get(0).getX() - SNAKE_SPEED;
                    nY = (int) this.snake.get(0).getY();
                    break;
                case RIGHT:
                    nX = (int) this.snake.get(0).getX() + SNAKE_SPEED;
                    nY = (int) this.snake.get(0).getY();
                    break;
                case BOT:
                    nX = (int) this.snake.get(0).getX();
                    nY = (int) this.snake.get(0).getY() + SNAKE_SPEED;
                    break;
            }
            if(!snakeSelfDmg()) {
                if (!snakeCollidesWithWall()) {
                    //Add new snakepart at the beginning of the snake
                    this.snake.add(0, new Point2D(nX, nY));
                    //Remove last snakepart, if snake nothing ate
                    if (snakeCollidedWithApple()) {
                        //Set a new apple position
                        setNewRandomApplePosition();
                        //Increase score
                        this.score++;
                    } else {
                        //Remove last snakepart
                        this.snake.remove(this.snake.size() - 1);
                    }
                }
            }
        }
    }

    /**
     * Move the snake in
     * @param direction, TOP, LEFT, RIGHT, BOT
     */
    public void moveSnake(SNAKE_DIRECTION direction) {
        int nX=0,nY=0;
        if(gameOver==false) {
            //Which direction?
            if (invertedDirection(direction)) {
                moveSnake();
            } else {
                switch (direction) {
                    case TOP:
                        nX = (int) this.snake.get(0).getX();
                        nY = (int) this.snake.get(0).getY() - SNAKE_SPEED;
                        break;
                    case LEFT:
                        nX = (int) this.snake.get(0).getX() - SNAKE_SPEED;
                        nY = (int) this.snake.get(0).getY();
                        break;
                    case RIGHT:
                        nX = (int) this.snake.get(0).getX() + SNAKE_SPEED;
                        nY = (int) this.snake.get(0).getY();
                        break;
                    case BOT:
                        nX = (int) this.snake.get(0).getX();
                        nY = (int) this.snake.get(0).getY() + SNAKE_SPEED;
                        break;
                }
                this.lastDirection = direction;
                if(!snakeSelfDmg()) {
                    if (!snakeCollidesWithWall()) {
                        //Add new snakepart at the beginning of the snake
                        this.snake.add(0, new Point2D(nX, nY));
                        //Remove last snakepart, if snake nothing ate
                        if (snakeCollidedWithApple()) {
                            //Set a new apple position
                            setNewRandomApplePosition();
                            this.score++;
                        } else {
                            //Remove last snakepart
                            this.snake.remove(this.snake.size() - 1);
                        }
                    }
                }
            }
        }
    }



    /**
     * Is the new direction the inverted direction of the last direction?
     * It is used for disallow 180* turnarounds.
     * @param direction, current direction
     * @return true, if it is the inverted direction
     */
    private boolean invertedDirection(SNAKE_DIRECTION direction) {
        switch (direction) {
            case TOP:
                return lastDirection.equals(SNAKE_DIRECTION.BOT);
            case LEFT:
                return lastDirection==SNAKE_DIRECTION.RIGHT;
            case RIGHT:
                return lastDirection==SNAKE_DIRECTION.LEFT;
            case BOT:
                return lastDirection==SNAKE_DIRECTION.TOP;
        }
        return false;

    }

    /**
     * Set a new apple position randomly
     */
    private void setNewRandomApplePosition() {
        do {
            Random rnd = new Random();
            int nx = rnd.nextInt(this.GAME_WIDTH);
            int ny = rnd.nextInt(this.GAME_HEIGHT);
            nx = nx - (nx%10);
            ny = ny - (ny%10);
            this.apple = new Point2D(nx,ny);
        }while (snakeCollidedWithApple());
    }

    /**
     * @return true if snake collided with apple
     */
    public boolean snakeCollidedWithApple() {
        for(int i = 0; i < this.snake.size();i++) {
            if(this.snake.get(i).distance(this.apple.getX(),this.apple.getY())==0) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @return true if snake collided with wall
     */
    public boolean snakeCollidesWithWall() {
        if(this.snake.get(0).getX()>=this.GAME_WIDTH || this.snake.get(0).getX()<0 || this.snake.get(0).getY()>=this.GAME_HEIGHT || this.snake.get(0).getY()<0) {
            System.out.println("GAME OVER: Snake collided with wall..");
            this.gameOver = true;
            return true;
        }
        return false;
    }

    /**
     * @return true if snake collided with itself
     */
    public boolean snakeSelfDmg() {
        for(int i = 0; i < this.snake.size();i++) {
            if(i!=0) {
                if (this.snake.get(i).distance(this.snake.get(0).getX(), this.snake.get(0).getY()) == 0) {
                    this.gameOver = true;
                    System.out.println("GAME OVER: Snake collided with itself..");
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Restart game world
     */
    public void restart() {
        //Reset snake
        this.snake.removeAll(this.snake);
        this.snake.add(new Point2D(GAME_WIDTH / 2 - ((GAME_WIDTH / 2) % 10), GAME_WIDTH / 2 - ((GAME_WIDTH / 2) % 10)));
        //Start game again
        this.gameOver = false;
        //lastDirection
        this.lastDirection = SNAKE_DIRECTION.TOP;
        //Set new apple position
        setNewRandomApplePosition();
        //Reset score
        this.score = 0;
    }

    /**
     * Create a copy of the snake
     * @return snake copy
     */
    public ArrayList<Point2D> copySnake() {
        ArrayList<Point2D> snakeCopy = new ArrayList<Point2D>();
        for(int i = 0; i < this.snake.size(); i++) {
            snakeCopy.add(new Point2D(this.snake.get(i).getX(),this.snake.get(i).getY()));
        }
        return snakeCopy;
    }

    /**
     * Copy a apple
     * @return apple copy
     */
    public Point2D copyApple() {
        return new Point2D(apple.getX(),apple.getY());
    }



}
