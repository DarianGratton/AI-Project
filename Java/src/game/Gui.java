package game;

import java.awt.Color;

import java.awt.Component;
import java.awt.Graphics;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

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

    private static long turnStart;

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
    public static boolean moveMarbles(Game g, boolean playerIsBlack, Marble m1, int direction){

        Move mv = new Move(m1, direction, System.nanoTime() - turnStart);
        // check to see if move is valid
        if(g.move(m1, direction, playerIsBlack)){
            g.addMoveToList(mv);
            g.move(m1, direction, playerIsBlack);
            turnStart = System.nanoTime();
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
    public static boolean moveMarbles(Game g, boolean playerIsBlack, Marble m1, Marble m2, int direction){


        Move mv = new Move(m1, m2, direction, System.nanoTime() - turnStart);
        // check to see if move is valid
        if(g.move(m1, m2, direction, playerIsBlack)){
            g.addMoveToList(mv);
            turnStart = System.nanoTime();
            return true;
        }

        return false;
    }


}
