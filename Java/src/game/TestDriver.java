package game;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFrame;


/**
 * @author Mike
 *
 */
public class TestDriver {

    public static Game game = new Game();
    public static final GameFrame frame = new GameFrame();

    /**
     * @param args
     */
    public static void main(String[] args){

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
