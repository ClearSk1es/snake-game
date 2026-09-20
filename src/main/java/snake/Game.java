package snake;
//Importing non-blocking I/O;
import java.nio.*;

public class Game {

        Board board = new Board();
        Snake snake = new Snake();
        Food food = new Food();

        public Game() {
        }

        public void start(){
            //Initialize values
            board.setBoard();
            snake.snakeStart();
            String currentDirection = "right";

            //Game loop

            boolean game = true;
            while (game){
                //Process input - Check Input

                //Update State
                int [] moveDirection = snake.changeDirection(currentDirection);
                if (snake.checkCollision(moveDirection, board)){
                    System.out.println("Game Over");
                    game = false;
                }
                snake.move(moveDirection);

                //Render Ouput


                //Wait

            }

            board.addFood(food);
            board.renderBoard(board.getBoard());
        }


}
