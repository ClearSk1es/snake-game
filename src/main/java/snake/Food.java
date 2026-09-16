package snake;

import java.util.Random;

public class Food {

    private int value = 1;
    private char shape = '*';
    private int[] coordinates = new int[2];
    private final Random random = new Random();

    public Food() {
    }

    public int getValue() {
        return value;
    }

    public char getShape(){
        return shape;
    }

    public void generateCoordinates(){
        int randomRow = random.nextInt(1,11);
        int randomCol = random.nextInt(1,49);

        this.coordinates[0] = randomRow;
        this.coordinates[1] = randomCol;
    }

    public int[] getCoordinates(){
        return coordinates;
    }
}
