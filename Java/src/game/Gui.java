package game;

import java.awt.Color;

import java.awt.Component;
import java.awt.Graphics;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * 
 */

/**
 * @author Mike
 *
 */
public abstract class Gui {

    private static long turnStart = System.nanoTime();
    private static long elapsedTime;
    private static ExecutorService doTheThing;
    private static int maxDepth;

    /**
     * IDK if this needs to exist
     */
    public static Game startGame(Board layout, boolean aiIsBlack, 
            int humanMoveLimit, long humanTimeLimit, GameTimer time) {
        turnStart = System.nanoTime();

        return new Game(layout, aiIsBlack, humanMoveLimit, humanTimeLimit, time);
    }

    /**
     * returns score for selected colour; i.e. number of enemy marbles knocked out
     * @param g
     * @param isBlack
     * @return
     */
    public static int displayScore(Game g, boolean isBlack){
        if(isBlack){
            return g.getBlackScore();
        } else {
            return g.getWhiteScore();
        }        
    }

    /**
     * Method for moving inline; call THESE method from the GUI
     * @param g the game in progress
     * @param m1 the first/rear marble
     * @param direction
     * @param isBlack
     */
    public static boolean moveMarbles(Game g, boolean activeIsBlack, Marble m1, int direction, GameTimer blackTimer, GameTimer whiteTimer){
        Move mv = new Move(m1, direction, System.nanoTime() - turnStart);
        Move display = new Move(mv);
        
        // check to see if move is valid
        if(g.move(m1, direction, activeIsBlack)){
            
            turnStart = System.nanoTime();
            	//passes in the seconds taken into Move, and adds to total turn time in Game 
            if(activeIsBlack) {
            	display.setTime(blackTimer.getTimerAsOne());
            	g.setTotalTurnTime(activeIsBlack, blackTimer.getTimerAsOne());
            } else {
            	display.setTime(whiteTimer.getTimerAsOne());
            	g.setTotalTurnTime(activeIsBlack, whiteTimer.getTimerAsOne());
            }
            g.addMoveToList(display);
            g.switchSides();
            return true;
        }
        return false;
    }

    /**
     * Method for moving broadside; call THESE method from the GUI
     * @param g the game in progress
     * @param m1
     * @param m2
     * @param direction
     * @param isBlack
     */
    public static boolean moveMarbles(Game g, boolean activeIsBlack, Marble m1, Marble m2, int direction, GameTimer blackTimer, GameTimer whiteTimer){


        Move mv = new Move(m1, m2, direction, System.nanoTime() - turnStart);
        Move display = new Move(mv);
        
        // check to see if move is valid
        if(g.move(m1, m2, direction, activeIsBlack)){
            
            turnStart = System.nanoTime();
          //passes in the seconds taken into Move, and adds to total turn time in Game 
            if(activeIsBlack) {
            	display.setTime(blackTimer.getTimerAsOne());
            	g.setTotalTurnTime(activeIsBlack, blackTimer.getTimerAsOne());
            } else {
                display.setTime(whiteTimer.getTimerAsOne());
            	g.setTotalTurnTime(activeIsBlack, whiteTimer.getTimerAsOne());
            }
            g.addMoveToList(display);
            g.switchSides();
            return true;
        }

        return false;
    }

    public static void updateRecommended(Game g, boolean aiIsBlack){

        doTheThing = Executors.newSingleThreadExecutor();
        try {
            doTheThing.submit(() -> {
                long nanoSec = 0;
                int maxDepth = 0;
                while(nanoSec < g.getAiTimeLimit()){
                    ++maxDepth;
                    nanoSec = System.nanoTime() - turnStart;
                    g.setRecommended(AIPlayer.alphaBetaSearch(g, aiIsBlack, maxDepth));
                    /*if(nanoSec >= g.getAiTimeLimit()){
                        doTheThing.shutdownNow();
                    }*/
                }
            });
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }        
    }
    
    public static void killExecutor(){
        doTheThing.shutdownNow();
    }

    /**
     * @return the turnStart
     */
    public static long getTurnStart() {
        return turnStart;
    }
    
}
