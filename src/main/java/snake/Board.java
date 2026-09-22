package snake;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.PrintWriter;

public class Board {

    private static final int rows = 12;
    private static final int columns = 50;

    char[][] board = new char[rows][columns];

    public Board() {
    }

    public void setBoardBorders(){
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                //Setting the board structure
                if((row == 0 || row == rows-1) && (col == 0 || col == columns-1)){
                    this.board[row][col] = '+';
                } else if ((row == 0 || row == rows-1) && (col != 0 && col != columns-1)) {
                    this.board[row][col] = '-';
                } else if ((row != 0 && row != rows-1) && (col == 0 || col == columns-1)) {
                    this.board[row][col] = '|';
                } else {
                    this.board[row][col] = ' ';
                }
            }            
        }
    }

    public char[][] getBoard(){
        return this.board;
    }

    public void setBoard(Snake snake, Food food) {
        //First print board borders
        setBoardBorders();

        //Setting all my element states in a more clean way.
        char[][] currentBoard = getBoard();
        int[] currentSnakeHead = snake.getSnakeHead();
        ArrayList<int[]> currentSnake = snake.getSnakeCoordinates();
        int[] currentFood = food.getCoordinates();
        int snakeSize = snake.getSnakeSize();


        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                if (row == currentSnakeHead[0] && col == currentSnakeHead[1]){
                    currentBoard[row][col] = 'X';
                }
                if (snakeSize > 1){
                    for (int[] body : currentSnake){
                        if (row == body[0] && col == body[1]){
                            currentBoard[row][col] = 'O';
                        }
                    }
                }
                if (row == currentFood[0] && col == currentFood[1] ){
                    currentBoard[row][col] = '*';
                }
            }
        }
    }

    public void renderBoard(PrintWriter writer){
        char[][] currBoard = getBoard();

        for ( char[] row: currBoard){
            for (char col : row){
                writer.print(col);
            }
            writer.print("\r\n");
        }
    }


    public int getColumns(){
        return columns;
    }

    public int getRows(){
        return rows;
    }

    public void addFood(Food food) {
        //Verify food is assigned to an empty coordinate to not clash with snake
        boolean validLocation = false;
        while (!validLocation) {
            food.generateCoordinates();
            int[] coordinates = food.getCoordinates();
            int rowCord = coordinates[0];
            int colCord = coordinates[1];

            if (board[rowCord][colCord] == ' ') {
                validLocation = true;
            }
        }
    }

    public void initializeSnake(Snake snake){
        snake.snakeStart();
        int [] startingCords = snake.getSnakeHead();
        int startRow = startingCords[0];
        int startCol = startingCords[1];

        board[startRow][startCol] = 'X';

    }


}
