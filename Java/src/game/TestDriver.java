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
        
        for(Marble m : test.getBoard()){
            System.out.println(m.toString());
        }

    
        
        final GameFrame frame = new GameFrame();
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setVisible(true);
        
        Graphics g = frame.getGraphics();
        
        Gui.drawBoard(test.getBoard(), frame, g);
        
    }
}
