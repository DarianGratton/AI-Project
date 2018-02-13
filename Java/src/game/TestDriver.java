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
        Game test = new Game(Game.belgianDaisy, true, 0, 0, 100, 100);

        /*final GameFrame frame = new GameFrame();
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setVisible(true);
      
        Gui.drawBoard(test.getBoard(), frame);
        
        System.out.println("got here");*/
        Gui.putMarblesInShit(test.getBoard());
        
        Marble m1 = test.searchBoard(1, 1);
        Gui.moveMarbles(test, true, m1, 2);
        
        System.out.println();
        Gui.putMarblesInShit(test.getBoard());
        
        Marble m2 = test.searchBoard(3, 2);
        Marble m3 = test.searchBoard(3, 3);
        Gui.moveMarbles(test, true, m2, m3, 2);
        
        System.out.println();
        Gui.putMarblesInShit(test.getBoard());
    }
}
