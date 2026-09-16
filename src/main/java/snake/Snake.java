package snake;

import java.util.ArrayList;

public class Snake {

    private ArrayList<Character> snake = new ArrayList<>();
    private int score;


    public void eatFood(Food food){
        this.score += food.getValue();
        this.snake.add('0');
    }

}
