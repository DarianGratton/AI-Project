package game;
import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


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
            frame.setSize(1000, 735);
            frame.setVisible(true);  

            Graphics g = frame.getGraphics();
            frame.paintComponents(g);
        };


        Thread thread = new Thread(board);
        thread.start();




//        System.out.print("Evaluating board for black side:");
//        System.out.println(AIPlayer.evaluateBoard(game.getBoard(), true));
//
//        System.out.print("Evaluating board for white side:");
//        System.out.println(AIPlayer.evaluateBoard(game.getBoard(), false));

//        game.setAiIsBlack(true);
//        int maxDepth = 0;
//        while (maxDepth < 5) {
//            maxDepth++;
//            Move butts = AIPlayer.alphaBetaSearch(game, game.isAiBlack(), maxDepth);
//            game.setRecommended(butts);
//            System.out.println(butts.toString());
//        }

//        ExecutorService executor = Executors.newCachedThreadPool();
//        executor.submit(() -> {
//            long start = Gui.getTurnStart();
//            long current = System.nanoTime();
//            if((current - start) >= game.getAiTimeLimit()){
//                Gui.killExecutor();
//            }
//        });

    }
}
