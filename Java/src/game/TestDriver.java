package game;
import java.awt.Graphics;
import java.util.ArrayList;

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
        
        for(Marble m : game.getBoard()){
            System.out.println(m.toString());
            
        }
        
        ArrayList<Move> moves = aiPlayer.genPossibleMoves(game, game.activeIsBlack());
        for(int i = 0; i < moves.size(); i++){
            System.out.println(moves.get(i).toString());
        }
        
        /*
        GameFrame frame = new GameFrame(game);
        
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setVisible(true);  
        
        Graphics g = frame.getGraphics();
        frame.paintComponents(g);*/

        


        //Gui.drawBoard(test.getBoard(), frame, g);

        //        Gui.moveMarbles(test, true, test.searchBoard(1,1), 2);
        //        frame.repaint();
        //        Gui.drawBoard(test.getBoard(), frame, g);

    }
}
