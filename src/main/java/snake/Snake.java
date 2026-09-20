package snake;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Snake {

    private ArrayList<int[]> snakeCoordinates = new ArrayList<>();
    private int score;

    public void snakeStart() {
        snakeCoordinates.add(new int[]{5, 24});
    }

    public ArrayList<int[]> getSnakeCoordinates() {
        return snakeCoordinates;
    }

    public void setSnakeCoordinates(ArrayList<int[]> newSnakeCoordinates) {
        this.snakeCoordinates = newSnakeCoordinates;
    }

    public int[] getSnakeHead() {
        return snakeCoordinates.getFirst();
    }

    public int getSnakeSize(){
        return getSnakeCoordinates().size();
    }

    public boolean isValidMove(int[] newHead) {
        //Arrays.equals checks equality between 1D arrays
        if (getSnakeCoordinates().size() == 1) {
            return true;
        } else if (Arrays.equals(newHead, getSnakeCoordinates().get(1))) {
            return false;
        }
        return true;
    }

    //Snake validate legal movements
    public int[] changeDirection(String direction) {
        //If moving right, head will change column + 1

        int[] newHead = getSnakeHead().clone();
        if ("right".equals(direction)) {
            newHead[1]++;
            if (!isValidMove(newHead)) {
                newHead[1]--;
            }
            return newHead;
        } else if ("left".equals(direction)) {
            newHead[1]--;
            if (!isValidMove(newHead)) {
                newHead[1]++;
            }
            return newHead;
        } else if ("up".equals(direction)) {
            newHead[0]--;
            if (!isValidMove(newHead)) {
                newHead[0]++;
            }
            return newHead;
        } else if ("down".equals(direction)){
            newHead[0]++;
            if (!isValidMove(newHead)) {
                newHead[0]--;
            }
            return newHead;
        } else {
            return changeDirection(direction);
        }

    }

    //Move the snake in validated direction
    public void move(int[] direction) {
        //Clonning every arrray inside of original coordinates and adding to copy array
        ArrayList<int[]> currentCoordinates = new ArrayList<>(getSnakeCoordinates());
        ArrayList<int[]> copyCoordinates = new ArrayList<>();
        for (int[] coordinate : currentCoordinates) {
            copyCoordinates.add(coordinate.clone());
        }

        //Removing last element of old body
        int lastElement = copyCoordinates.size() - 1;
        copyCoordinates.remove(lastElement);

        //Setting new body head and moving body by one position in consequence
        copyCoordinates.addFirst(direction);

        //Setting snake coordinates as new modified coordinates
        setSnakeCoordinates(copyCoordinates);

    }

    public int[] addTail(String direction) {
        //Enhanced switch usage, switch can be used as an expression
        //Gets the current snake direction and creates new coordinate in the opposite side based on last tail location
        String oppositeDirection = switch (direction) {
            case "right" -> "left";
            case "left" -> "right";
            case "up" -> "down";
            case "down" -> "up";
            default -> direction;
        };
        int[] newTail = getSnakeCoordinates().getLast().clone();

        if ("left".equals(oppositeDirection)) {
            newTail[1]--;
            return newTail;
        } else if ("right".equals(oppositeDirection)) {
            newTail[1]++;
            return newTail;
        } else if ("down".equals(oppositeDirection)) {
            newTail[0]++;
            return newTail;
        } else {
            newTail[0]--;
            return newTail;
        }

    }

    public void eatFood(Food currentFood, String direction) {
        int [] foodCords = currentFood.getCoordinates();

        if (Arrays.equals(getSnakeHead(), foodCords)) {
            int [] newTail = addTail(direction);

            ArrayList<int[]> newCoordinates = new ArrayList<>(getSnakeCoordinates());
            newCoordinates.add(newTail);

            setSnakeCoordinates(newCoordinates);
        }
    }

    public boolean checkCollision(int[] newHead, Board board){
        ArrayList<int[]> currentCords = getSnakeCoordinates();
        int body = currentCords.size()-2;

        //checkin body collision, start from 2 value
        for (int coordinate = 1; coordinate < body; coordinate++ ){
            if(Arrays.equals(newHead, currentCords.get(coordinate))){
                return true;
            }
        }

        if(
            (newHead[0] == 0 || newHead[0] == board.getRows() - 1 ) ||
            (newHead[1] == 0 || newHead[1] == board.getColumns() - 1)){
                return true;
        }
        return false;
    }

}