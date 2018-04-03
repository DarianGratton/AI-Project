package game;
import javax.swing.*;
import java.awt.*;
import java.util.HashSet;


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
        //game.setBoard(test);

        /*for(Marble m : game.getBoard()){
            System.out.println(m.toString()); 
        }*/

        /*ArrayList<Move> moves = AIPlayer.genPossibleMoves(game, game.activeIsBlack());
        for(int i = 0; i < moves.size(); i++){
            System.out.println(moves.get(i).toString());
        }*/
        
//        Thread thread = new Thread(task);
//        thread.start();
        GameFrame frame = new GameFrame(game);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 735);
        frame.setVisible(true);  

        Graphics g = frame.getGraphics();
        frame.paintComponents(g);

        Runnable task = () -> {
            while(game.isGameInSession()) {
                
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                if (game.activeIsBlack() == game.isAiBlack()) {
                    Board copyBoard = Board.copyBoard(game.getBoard());
                    System.out.print("Evaluating board for black side:");
                    System.out.println(AIPlayer.evaluateBoard(copyBoard, true));

                    System.out.print("Evaluating board for white side:");
                    System.out.println(AIPlayer.evaluateBoard(copyBoard, false));

                    Move nextMove = AIPlayer.alphaBetaSearch(game, true);
                    game.setRecommended(nextMove);
                    frame.updateRecommendedMove();
                    System.out.println(nextMove.toString());
                } 
            }
        };
        
        Thread newThread = new Thread(task);
        newThread.start();
    }
}
