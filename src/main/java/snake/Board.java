package snake;

public class Board {

    private static final int rows = 12;
    private static final int columns = 50;

    char[][] board = new char[rows][columns];

    public Board() {
    }

    public void setBoard(){
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

    public void renderBoard(char[][] board){
        for (char[] row: board){
            for (char col: row){
                System.out.print(col);
            }
            System.out.println();
        }
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
                board[rowCord][colCord] = food.getShape();
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
