package game;
/**
 * 
 */

/**
 * @author Mike
 *
 */
public class Gui {

    /**
     * IDK if this needs to exist
     */
    public static void startGame(){
        
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
            if(g.move(m1, direction)){
                g.addMoveToList(new Move(m1, direction));
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
            if(g.move(m1, m2, direction)){
                g.addMoveToList(new Move(m1, m2, direction));
            }
        }
    }
    
    
    
}
