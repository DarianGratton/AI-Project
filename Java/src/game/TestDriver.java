package game;
import java.awt.Graphics;

import javax.swing.JFrame;


/**
 * @author Mike
 *
 */
public class TestDriver {

    public static Game game = new Game();

    /**
     * @param args
     */
    public static void main(String[] args) {
        final GameFrame frame;
        final Game game1 = new Game();
        
        frame = new GameFrame(game1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setVisible(true);  
        
        Graphics g = frame.getGraphics();
        frame.paintComponents(g);

        //Gui.drawBoard(test.getBoard(), frame, g);

        //        Gui.moveMarbles(test, true, test.searchBoard(1,1), 2);
        //        frame.repaint();
        //        Gui.drawBoard(test.getBoard(), frame, g);

    }
}
