package snake;

import java.util.ArrayList;
import java.util.Arrays;

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

    public boolean isValidMove(int[] newHead){
        //Arrays.equals checks equality between 1D arrays
        if (getSnakeCoordinates().size() == 1){
            return true;
        }
        else if (Arrays.equals(newHead, getSnakeCoordinates().get(1))){
            return false;
        }
        return true;
    }

    //Snake movements
    public void changeDirection(String direction){
        //If moving right, head will change column + 1
        //Clonning every arrray inside of original coordinates and adding to copy array
        ArrayList<int[]> currentCoordinates = new ArrayList<>(getSnakeCoordinates());
        ArrayList<int[]> copyCoordinates = new ArrayList<>();
        for(int[] coordinate : currentCoordinates){
            copyCoordinates.add(coordinate.clone());
        }
        int[] newHead = getSnakeHead().clone();
        if ("right".equals(direction)) {
            newHead[1]++;
            if (!isValidMove(newHead)){
                newHead[1]--;
            }
        }
        else if("left".equals(direction)){
            newHead[1]--;
            if (!isValidMove(newHead)){
                newHead[1]++;
            }
        }
        else if("up".equals(direction)){
            newHead[0]++;
            if (!isValidMove(newHead)){
                newHead[0]--;
            }
        }
        else {
            newHead[0]--;
            if (!isValidMove(newHead)){
                newHead[0]++;
            }
        }

        //Removing last element of old body
        int lastElement = copyCoordinates.size() - 1;
        copyCoordinates.remove(lastElement);

        //Setting new body head and moving body by one position in consequence
        copyCoordinates.addFirst(newHead.clone());

        //Setting snake coordinates as new modified coordinates
        setSnakeCoordinates(copyCoordinates);
    }

    public void move(){

    }


}
