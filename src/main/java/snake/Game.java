package snake;

public class Game {

        Board board = new Board();
        Snake snake = new Snake();
        Food food = new Food();

        public Game() {
        }

        public void start(){
            board.setBoard();
            board.initializeSnake(snake);
            String initialDirection = "right";

            boolean game = true;
            while (game){

                if (snake.checkCollision()){
                    game = false;
                }
            }

            board.addFood(food);
            board.renderBoard(board.getBoard());
        }





}
