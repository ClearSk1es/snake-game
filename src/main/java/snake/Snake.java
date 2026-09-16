package snake;

import java.util.ArrayList;
import java.util.List;

public class Snake {

    private ArrayList<int[]> snakeCoordinates = new ArrayList<>();
    private int score;

    public void snakeStart(){
        snakeCoordinates.add(new int[]{5,24});
    }

    public ArrayList<int[]> getSnakeCoordinates(){
        return snakeCoordinates;
    }

    public void setSnakeCoordinates(ArrayList<int[]> newSnakeCoordinates){
        this.snakeCoordinates = newSnakeCoordinates;
    }

    public int[] getSnakeHead(){
        return snakeCoordinates.getFirst();
    }

    //Snake movements
    public void moveRight(){
        //If moving right, head will change column + 1
        //Clonning every arrray inside of original coordinates and adding to copy array
        ArrayList<int[]> currentCoordinates = new ArrayList<>(getSnakeCoordinates());
        ArrayList<int[]> copyCoordinates = new ArrayList<>();
        for(int[] coordinate : currentCoordinates){
            copyCoordinates.add(coordinate.clone());
        }

        ArrayList<int[]> newCoordinates = new ArrayList<>();

        int [] newHead = getSnakeHead().clone();
        newHead[1]++;

        //Setting new body head
        newCoordinates.add(newHead);
        //Removing last element of old body
        int lastElement = copyCoordinates.size() - 1;
        copyCoordinates.remove(lastElement);

        //Moving body
        for (int[] element : copyCoordinates){
            //Possible refactor in one line for adding all elements
            snakeCoordinates.add(element);
        }


    }

}
