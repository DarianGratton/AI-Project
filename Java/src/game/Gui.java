package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;

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
    public static void startGame(){
        turnStart = System.nanoTime();
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
    public static void moveMarbles(Game g, boolean playerIsBlack, Marble m1, Marble m2, int direction, boolean isBlack){
        // short circuit and only continue if the player is trying to move their own marble
        if(playerIsBlack == isBlack){
            // check to see if move is valid
            if(g.move(m1, m2, direction)){
                g.addMoveToList(new Move(m1, m2, direction, System.nanoTime() - turnStart));
                turnStart = System.nanoTime();
            }
        }
    }
    
    public static void drawBoard(ArrayList<Marble> board, GameFrame frame, Graphics g){    
        for(Marble m : board){
            drawMarble(m, frame, g);
        }
    }
    
    public static void drawMarble(Marble m, GameFrame frame, Graphics g) {
        frame.paint(g);
    	int alpha = m.getAlpha();
        int numeric = m.getNumeric();
        boolean isBlack = m.isBlack();
        
        
  
        ArrayList <Space> list = frame.getSpaceList();
        for(Space s : list) {
        	if(s.getAlpha() == alpha && s.getNum() == numeric) {
        	    
        		if(isBlack)
            		g.setColor(Color.BLACK);
            	else
            		g.setColor(Color.WHITE);
        		g.fillOval(s.getX(), s.getY(), 65, 65);
        	}
        		
        }        
    }  
 
}
