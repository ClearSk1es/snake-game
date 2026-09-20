package snake;
//Importing non-blocking I/O;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.InfoCmp;
import org.jline.utils.NonBlockingReader;

import java.io.IOException;
import java.io.Reader;

public class Game {

        Board board = new Board();
        Snake snake = new Snake();
        Food food = new Food();

        public Game() {
        }

        public void start() throws IOException, InterruptedException {
            //Initialize values

            // Create a terminal
            Terminal terminal = TerminalBuilder.builder().system(true).build();
            // Get a non-blocking reader
            NonBlockingReader reader = terminal.reader();

            board.addFood(food);
            board.renderBoard(snake, food);
            board.setBoard();
            snake.snakeStart();
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
                    terminal.writer().println("Game Over");
                    terminal.close();
                    break;

                }
                snake.move(moveDirection);

                //Render Ouput
                board.renderBoard(snake, food);

                //Wait
                terminal.puts(InfoCmp.Capability.clear_screen);

                //Move cursor to position(---)
                terminal.puts(InfoCmp.Capability.cursor_address, 2, 2);
                Thread.sleep(500);

            }


        }


}
