package snake;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.InfoCmp;
import org.jline.utils.NonBlockingReader;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.Reader;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 */
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        // Create a terminal
        Terminal terminal = TerminalBuilder.builder().system(true).build();

        // Create a line reader
        //LineReader reader = LineReaderBuilder.builder().terminal(terminal).build();
        


    }
}
