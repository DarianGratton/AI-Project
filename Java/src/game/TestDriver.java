package game;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

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

        Board test = new Board(){
            {
                add(new Marble(1, 3, true));
                add(new Marble(2, 2, true));
                add(new Marble(2, 3, true));
                add(new Marble(3, 3, true));
                add(new Marble(3, 4, true));
                add(new Marble(7, 7, true));
                add(new Marble(7, 8, true));
                add(new Marble(8, 7, true));
                add(new Marble(8, 8, true));
                add(new Marble(8, 9, true));
                add(new Marble(9, 8, true));
                add(new Marble(9, 9, true));      

                add(new Marble(1, 4, false));
                add(new Marble(1, 5, false));
                add(new Marble(2, 4, false));
                add(new Marble(2, 5, false));
                add(new Marble(2, 6, false));
                add(new Marble(3, 5, false));
                add(new Marble(3, 6, false));
                add(new Marble(7, 4, false));
                add(new Marble(7, 5, false));
                add(new Marble(8, 4, false));
                add(new Marble(8, 5, false));
                add(new Marble(8, 6, false));
                add(new Marble(9, 5, false));
                add(new Marble(9, 6, false));
            }
        };

        
        Game game = new Game();
        game.setBoard(test);
        
        /*for(Marble m : game.getBoard()){
            System.out.println(m.toString()); 
        }*/
        
        ArrayList<Move> moves = aiPlayer.genPossibleMoves(game, game.activeIsBlack());
        for(int i = 0; i < moves.size(); i++){
            System.out.println(moves.get(i).toString());
        }
        
        HashMap<Board, Double> stuff = aiPlayer.genAllResults(game, moves);
        GameFrame frame = new GameFrame(game);
        
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setVisible(true);  
        
        Graphics g = frame.getGraphics();
        frame.paintComponents(g);

        Board b1 = Board.copyBoard(Game.standardLayout);
        Board b2 = Board.copyBoard(Game.standardLayout);
        
        HashSet<Board> boards = new HashSet<Board>();
        boards.add(b1);
        
        if (!Board.checkIfSetContains(boards, b2)) {
            System.out.println("FUCK");
        }
        
        Board b3 = Board.copyBoard(Game.belgianDaisy);
        
        if (!Board.checkIfSetContains(boards, b3)) {
            System.out.println("FUCK");
        }


        //Gui.drawBoard(test.getBoard(), frame, g);

        //        Gui.moveMarbles(test, true, test.searchBoard(1,1), 2);
        //        frame.repaint();
        //        Gui.drawBoard(test.getBoard(), frame, g);

    }
}
