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

        


        System.out.print("Evaluating board for black side:");
        System.out.println(AIPlayer.evaluateBoard(game.getBoard(), true));

        System.out.print("Evaluating board for white side:");
        System.out.println(AIPlayer.evaluateBoard(game.getBoard(), false));

        Move butts = AIPlayer.alphaBetaSearch(game, game.isAiBlack(), 3);
        game.setRecommended(butts);
        System.out.println(butts.toString());

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {

            while(game.isGameInSession()){
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                long start = Gui.getTurnStart();
                long current = System.nanoTime();
                if((current - start) >= game.getAiTimeLimit()){
                    Gui.killExecutor();
                }
            }

            
            
            
        });
        

    }
}
