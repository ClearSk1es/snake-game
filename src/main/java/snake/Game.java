package snake;
//Importing non-blocking I/O;
import org.jline.terminal.Attributes;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.InfoCmp;
import org.jline.utils.NonBlockingReader;
import java.io.IOException;


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

            // Present empty board at start of game
            board.setBoardBorders();
            // Initialize food coordinates
            board.addFood(food);
            // Initialize snake head coordinates
            snake.snakeStart();

            //Initial Render
            //Present empty board at start of game
            terminal.puts(InfoCmp.Capability.clear_screen);
            terminal.puts(InfoCmp.Capability.cursor_invisible);

            board.setBoard(snake, food);
            //Move cursor to top-left
            terminal.puts(InfoCmp.Capability.cursor_address, 0, 0);

            //Render
            board.renderBoard(terminal.writer());
            terminal.flush();

            //Initialize direction for snake at start of game
            String currentDirection = "right";

            //Game loop
            boolean game = true;
            while (true){
                //Process input - Check Input
                if (reader.available() >= 0){
                    int c = reader.read(100L);
                    if(snake.getSnakeSize() == 1){
                        currentDirection = switch (c){
                            case 'w' -> "up";
                            case 's' -> "down";
                            case 'a' -> "left";
                            case 'd' -> "right";
                            default -> currentDirection; };
                    }else{
                        currentDirection = getDirectionInput(c, currentDirection);
                    }
                }

                //Update State
                int [] moveDirection = snake.changeDirection(currentDirection);
                if (snake.checkCollision(moveDirection, board)){
                    terminal.puts(InfoCmp.Capability.cursor_normal);
                    terminal.puts(InfoCmp.Capability.cursor_address, 13, 0);

                    terminal.writer().printf("Game Over\r\n");
                    terminal.flush();

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
                //Update visual
                board.setBoard(snake, food);

                //Move cursor back to top-left
                terminal.puts(InfoCmp.Capability.cursor_address, 0, 0);

                //Render Output
                board.renderBoard(terminal.writer());
                terminal.flush();

                //Wait
                Thread.sleep(100);

            }


        }
        public static String getDirectionInput(int c, String currentDirection){
            //Return direction based on snake current direction to prevent 180° turn
            switch (c){
                case 'w':
                    if ("down".equals(currentDirection)){
                        return  "down";
                    } else {
                        return  "up";
                    }
                case 'd':
                    if ("left".equals(currentDirection)){
                        return "left";
                    } else {
                        return "right";
                    }
                case 's':
                    if ("up".equals(currentDirection)){
                        return "up";
                    } else {
                        return "down";
                    }
                case 'a':
                    if ("right".equals(currentDirection)){
                        return "right";
                    } else {
                        return "left";
                    }
                default: return currentDirection;

            }

        }
}
