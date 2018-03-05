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
        String input2[] = {"", ""};
        int i = 0;
        File firstTestInput = new File("Test1.input.txt");
        File secondTestInput = new File("Test2.input.txt");

        //Put contents of Test1.input.txt into an array input1
        Scanner inputScanner = new Scanner(firstTestInput);
        while (inputScanner.hasNextLine()) {
            input1[i] = inputScanner.nextLine();
            i++;
        }

        //Put contents of Test2.input.txt into an array input2
        i = 0;
        inputScanner = new Scanner(secondTestInput);
        while (inputScanner.hasNextLine()) {
            input2[i] = inputScanner.nextLine();
            i++;
        }
        //Split the layout of the board by the commas
        String[] input1ToCoord = input1[1].split(",");
        String[] input2ToCoord = input2[1].split(",");

        game = new Game(input1[0].charAt(0), input1ToCoord);
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
