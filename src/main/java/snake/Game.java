package snake;
//Importing non-blocking I/O;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Attributes;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.InfoCmp;
import org.jline.utils.NonBlockingReader;
import java.io.PrintWriter;

import java.io.IOException;
import java.io.Reader;

public class Game {

        //Creating the objects
        Board board = new Board();
        Snake snake = new Snake();
        Food food = new Food();

        public Game() {
        }

        public void start() throws IOException, InterruptedException {
            // Create a terminal
            Terminal terminal = TerminalBuilder.builder().system(true).build();
            //Changing to raw mode terminal
            Attributes originalAttributes = terminal.enterRawMode();
            // Get a non-blocking reader
            NonBlockingReader reader = terminal.reader();

            //Present empty board at start of game
            board.setBoardBorders();
            //Initialize food coordinates
            board.addFood(food);
            //Initialize snake head coordinates
            snake.snakeStart();
            //Render board with all its components

            board.setBoard(snake, food);
            board.renderBoard(terminal.writer());
            terminal.flush();


            //Initialize direction for snake at start of game
            String currentDirection = "right";

            //Game loop
            boolean game = true;
            while (true){
                //Process input - Check Input
                if (reader.available() > 0){
                    int c = reader.read();
                    currentDirection = switch (c){
                        case 'w' -> "up";
                        case 's' -> "down";
                        case 'a' -> "left";
                        case 'd' -> "right";
                        default -> currentDirection;
                    };
                }

                //Update State
                int [] moveDirection = snake.changeDirection(currentDirection);
                if (snake.checkCollision(moveDirection, board)){
                    terminal.writer().printf("Game Over");
                    terminal.flush();
                    //Returning terminal to original values when closing
                    terminal.setAttributes(originalAttributes);
                    terminal.close();
                    break;
                }
                snake.move(moveDirection);
                //Check if the snake head coordinate equals food location coordinates
                if(snake.eatFood(food, currentDirection)){
                    //Generate new location for food
                    board.addFood(food);
                }

                //Move cursor to position(---)
                terminal.puts(InfoCmp.Capability.cursor_address,5, 0);
                terminal.flush();

                //Render Ouput
                board.setBoard(snake, food);
                board.renderBoard(terminal.writer());
                terminal.flush();
                //Wait

                Thread.sleep(1000);

            }


        }


}
