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
	public static Game startGame(Board layout, boolean aiIsBlack, int aiMoveLimit, int humanMoveLimit,
			long aiTimeLimit, long humanTimeLimit){
		turnStart = System.nanoTime();

		return new Game(layout, aiIsBlack, aiMoveLimit, humanMoveLimit,
				aiTimeLimit, humanTimeLimit);
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
	public static void moveMarbles(Game g, boolean playerIsBlack, Marble m1, int direction){
		// short circuit and only continue if the player is trying to move their own marble
		if(playerIsBlack == m1.isBlack()){
			// check to see if move is valid
			if(g.move(m1, direction)){
				g.addMoveToList(new Move(m1, direction, System.nanoTime() - turnStart));
				turnStart = System.nanoTime();
			}
		}
	}

	/**
	 * Method for moving broadside; call THESE method from the GUI
	 * @param g the game in progress
	 * @param m1
	 * @param m2
	 * @param direction
	 * @param isBlack
	 */
	public static void moveMarbles(Game g, boolean playerIsBlack, Marble m1, Marble m2, int direction){
		// short circuit and only continue if the player is trying to move their own marble
		if(playerIsBlack == m1.isBlack()){
			// check to see if move is valid
			if(g.move(m1, m2, direction)){
				g.addMoveToList(new Move(m1, m2, direction, System.nanoTime() - turnStart));
				turnStart = System.nanoTime();
			}
		}
	}

	
}
