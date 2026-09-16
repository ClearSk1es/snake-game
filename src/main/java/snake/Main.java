package snake;

/**
 * Hello world!
 */
public class Main {
    public static void main(String[] args) {

        Board board = new Board();
        Food food = new Food();
        board.setBoard();
        board.addFood(food);
        board.renderBoard(board.getBoard());



    }

}
