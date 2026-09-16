package snake;

/**
 * Hello world!
 */
public class Main {
    public static void main(String[] args) {

        Board board = new Board();
        Snake snake = new Snake();
        Food food = new Food();

        board.setBoard();
        board.initializeSnake(snake);
        board.addFood(food);
        board.renderBoard(board.getBoard());



    }

}
