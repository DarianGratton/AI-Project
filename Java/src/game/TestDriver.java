package game;
import java.awt.Graphics;

import javax.swing.JFrame;


/**
 * @author Mike
 *
 */
public class TestDriver {

    /**
     * @param args
     */
    public static void main(String[] args) {

        Game game = new Game();
        GameFrame frame = new GameFrame(game);
        aiPlayer ai = new aiPlayer();

        ai.genPossibleMoves(game, game.activeIsBlack());
        
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
