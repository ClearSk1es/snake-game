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



    public void addFood(Food food){

        food.generateCoordinates();
        int[] coordinates = food.getCoordinates();

        int rowCord = coordinates[0];
        int colCord = coordinates[1];

        this.board[rowCord][colCord] = food.getShape();
    }

}
