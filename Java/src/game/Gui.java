package game;

import java.awt.Color;

import java.awt.Component;
import java.awt.Graphics;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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
    private static Move nextMove;

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
        if(g.move(m1, direction, activeIsBlack)) {
            
            turnStart = System.nanoTime();
            
            //passes in the seconds taken into Move, and adds to total turn time in Game 
            if(activeIsBlack) {
                if (!g.isAiBlack()) {
                    display.setTime(blackTimer.getTimerAsOne() * 1000000000);
                }
            	g.setTotalTurnTime(activeIsBlack, blackTimer.getTimerAsOne());
            } else {
                if (g.isAiBlack()) {
                    display.setTime(whiteTimer.getTimerAsOne() * 1000000000);
                }
            	g.setTotalTurnTime(activeIsBlack, whiteTimer.getTimerAsOne());
            }
            if (g.isAiBlack() == g.activeIsBlack()) {
                g.addMoveToList(nextMove);
            } else {
                g.addMoveToList(display);
            }
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
    public static boolean moveMarbles(Game g, boolean activeIsBlack, Marble m1, Marble m2, int direction, GameTimer blackTimer, GameTimer whiteTimer) {

        Move mv = new Move(m1, m2, direction, System.nanoTime() - turnStart);
        Move display = new Move(mv);
        
        // check to see if move is valid
        if(g.move(m1, m2, direction, activeIsBlack)) {
            
            turnStart = System.nanoTime();
            
            //passes in the seconds taken into Move, and adds to total turn time in Game 
            if(activeIsBlack) {
                if (!g.isAiBlack()) {
                    display.setTime(blackTimer.getTimerAsOne() * 1000000000);
                }
                g.setTotalTurnTime(activeIsBlack, blackTimer.getTimerAsOne());
            } else {
                if (g.isAiBlack()) {
                    display.setTime(whiteTimer.getTimerAsOne() * 1000000000);
                }
                g.setTotalTurnTime(activeIsBlack, whiteTimer.getTimerAsOne());
            }
            
            if (g.isAiBlack() == g.activeIsBlack()) {
                g.addMoveToList(nextMove);
            } else {
                g.addMoveToList(display);
            }
            g.switchSides();
            return true;
        }

        return false;
    }

    public static void updateRecommended(Game g, boolean aiIsBlack){     

        if (doTheThing != null) {
            doTheThing.shutdownNow();
        }
        
        doTheThing = Executors.newSingleThreadExecutor();
        doTheThing.submit(() -> {
            long nanoSec = 0;
            int maxDepth = 0;
            int depthLimit = 5;
            long turnStartTest = System.nanoTime();
            while (nanoSec <= g.getAiTimeLimit() || maxDepth <= depthLimit) {
                nanoSec = System.nanoTime() - turnStartTest;
                maxDepth++;
                nextMove = AIPlayer.alphaBetaSearch(g, aiIsBlack, maxDepth, nanoSec);
                // System.out.println(nextMove.toString());
                g.setRecommended(nextMove);
                nanoSec = System.nanoTime() - turnStartTest;
                GameFrame.updateNextMove(g, nanoSec);
            }
        });       
        
    }
    
    public static void killExecutor(){
        doTheThing.shutdownNow();
    }
    
    public static ExecutorService getTheThing(){
        return doTheThing;
    }

    /**
     * @return the turnStart
     */
    public static long getTurnStart() {
        return turnStart;
    }
    
    public static long getElapsedTime(){
        return System.nanoTime() - turnStart;
    }
}
