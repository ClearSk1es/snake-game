package snake;

public class Food {

    private int value = 1;
    private String shape = "*";
    private int row;
    private int col;
    private int[] coordinates;

    public Food() {
    }

    public int getValue() {
        return value;
    }
}
