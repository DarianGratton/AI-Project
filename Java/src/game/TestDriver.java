package game;
import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


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
        //game.setBoard(test);

        /*for(Marble m : game.getBoard()){
            System.out.println(m.toString()); 
        }*/

        /*ArrayList<Move> moves = AIPlayer.genPossibleMoves(game, game.activeIsBlack());
        for(int i = 0; i < moves.size(); i++){
            System.out.println(moves.get(i).toString());
           
        }*/

        Runnable board = () -> {
            GameFrame frame = new GameFrame(game);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1040, 735);
            frame.setVisible(true);  

            Graphics g = frame.getGraphics();
            frame.paintComponents(g);
        };


        Thread thread = new Thread(board);
        thread.start();

    }
}
