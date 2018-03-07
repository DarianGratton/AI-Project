package game;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 * @author Akemi
 * for reading specific inputs from a file and drawing it on the board.
 *
 */
public class Stage2Driver {

    public static Game game;

    /**
     * input[0] = White or Black's turn denoted by 'w' and 'b'
     * input[1] = all marble positions for this input
     */
    public static void main(String[] args) throws FileNotFoundException {
        String input1[] = {"", ""};
        boolean AIBlack;
        int moveCount;
        long time;

        int i = 0;
        File testInput = new File("Test1.input.txt");

        //Put contents of input.txt into an array input1
        Scanner inputScanner = new Scanner(testInput);
        while (inputScanner.hasNextLine()) {
            input1[i] = inputScanner.nextLine();
            i++;
        }

        //establish whose turn it is, if AI is black, etc.
        if(input1[0].equals('w')) {
            boole
        }

        Board boardFromFile = Board.inputBoard(input1[1]);
        game = new Game(boardFromFile, )

        GameFrame frame = new GameFrame(game);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setVisible(true);

        Graphics g = frame.getGraphics();
        frame.paintComponents(g);
        //Gui.drawBoard(game.getBoard(), frame, g);




        //Gui.drawBoard(test.getBoard(), frame, g);

        //        Gui.moveMarbles(test, true, test.searchBoard(1,1), 2);
        //        frame.repaint();
        //        Gui.drawBoard(test.getBoard(), frame, g);

    }


}
