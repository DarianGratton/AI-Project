import java.util.ArrayList;

/**
 * 
 */

/**
 * @author Mike
 *
 */
public class Game {
    
    private ArrayList<Marble> board;
    
    // timer for total game; mostly for pausing purposes
    private long gameTime;
    
    // initial layouts to copy into game board; need to be filled in
    private static final ArrayList<Marble> standardLayout = new ArrayList<Marble>();
    private ArrayList<Marble> germanDaisy;
    private ArrayList<Marble> belgianDaisy;
    
    // marbles lost by each player, respectively
    private int blackLost;
    private int whiteLost;
    
    // list of moves as objects per player
    private ArrayList<Move> blackMoves;
    private ArrayList<Move> whiteMoves;
    
    private boolean aiIsBlack;
    
    // next move as recommended by AI
    private Move recommended;
    
    private int aiMoveLimit;
    private int humanMoveLimit;
    
    // default unit is seconds?
    private long aiTimeLimit;
    private long humanTimeLimit;
    

    /**
     * Default constructor; uses standard layout
     */
    public Game(){
        this.board = standardLayout;
        this.blackLost = 0;
        this.whiteLost = 0;
        this.blackMoves = new ArrayList<Move>();
        this.whiteMoves = new ArrayList<Move>();
        this.aiIsBlack = false;
        this.aiMoveLimit = 100;
        this.humanMoveLimit = this.aiMoveLimit;
        this.aiTimeLimit = (long) 1000.0;
        this.humanTimeLimit = this.aiTimeLimit;
    } 
    
    /**
     * @param board
     * @param blackMoves
     * @param whiteMoves
     * @param aiIsBlack
     * @param aiMoveLimit
     * @param humanMoveLimit
     * @param aiTimeLimit
     * @param humanTimeLimit
     */
    public Game(ArrayList<Marble> layout, boolean aiIsBlack,
            int aiMoveLimit, int humanMoveLimit, long aiTimeLimit, long humanTimeLimit) {
        this.board = layout;
        this.aiIsBlack = aiIsBlack;
        this.aiMoveLimit = aiMoveLimit;
        this.humanMoveLimit = humanMoveLimit;
        this.aiTimeLimit = aiTimeLimit;
        this.humanTimeLimit = humanTimeLimit;
    }

    /**
     * @return the gameTime
     */
    public long getGameTime() {
        return gameTime;
    }

    /**
     * @param gameTime the gameTime to set
     */
    public void setGameTime(long gameTime) {
        this.gameTime = gameTime;
    }

    /**
     * @return the aiIsBlack
     */
    public boolean isAiIsBlack() {
        return aiIsBlack;
    }

    /**
     * @param aiIsBlack the aiIsBlack to set
     */
    public void setAiIsBlack(boolean aiIsBlack) {
        this.aiIsBlack = aiIsBlack;
    }

    /**
     * @return the recommended
     */
    public Move getRecommended() {
        return recommended;
    }

    /**
     * @param recommended the recommended to set
     */
    public void setRecommended(Move recommended) {
        this.recommended = recommended;
    }

    /**
     * @return the aiMoveLimit
     */
    public int getAiMoveLimit() {
        return aiMoveLimit;
    }

    /**
     * @param aiMoveLimit the aiMoveLimit to set
     */
    public void setAiMoveLimit(int aiMoveLimit) {
        this.aiMoveLimit = aiMoveLimit;
    }

    /**
     * @return the humanMoveLimit
     */
    public int getHumanMoveLimit() {
        return humanMoveLimit;
    }

    /**
     * @param humanMoveLimit the humanMoveLimit to set
     */
    public void setHumanMoveLimit(int humanMoveLimit) {
        this.humanMoveLimit = humanMoveLimit;
    }

    /**
     * @return the aiTimeLimit
     */
    public double getAiTimeLimit() {
        return aiTimeLimit;
    }

    /**
     * @param aiTimeLimit the aiTimeLimit to set
     */
    public void setAiTimeLimit(long aiTimeLimit) {
        this.aiTimeLimit = aiTimeLimit;
    }

    /**
     * @return the humanTimeLimit
     */
    public long getHumanTimeLimit() {
        return humanTimeLimit;
    }

    /**
     * @param humanTimeLimit the humanTimeLimit to set
     */
    public void setHumanTimeLimit(long humanTimeLimit) {
        this.humanTimeLimit = humanTimeLimit;
    }

    /**
     * @return the blackMoves
     */
    public ArrayList<Move> getBlackMoves() {
        return blackMoves;
    }

    /**
     * @return the whiteMoves
     */
    public ArrayList<Move> getWhiteMoves() {
        return whiteMoves;
    }

    public void addMarble(Marble m){
        this.board.add(m);
    }
    
    /**
     * returns the marble at a given location, or null if there isn't one
     * @param alpha the lettered coordinate to search for
     * @param numeric the numeric coordinate to search for
     */
    public Marble searchBoard(int alpha, int numeric){
        for(Marble m : this.board){
            if((m.getAlpha() == alpha) && (m.getNumeric() == numeric)){
                return m;
            }
        }       
        return null;
    }
    
    public Marble checkAdjacent(Marble m, int direction){
        switch (direction) {
            // 1: top-left
            case 1: return this.searchBoard(m.getAlpha()+1, m.getNumeric());
            // 2: top-right
            case 2: return this.searchBoard(m.getAlpha()+1, m.getNumeric()+1);
            // 3: right
            case 3:return this.searchBoard(m.getAlpha(), m.getNumeric()+1);
            // 4: bottom-right
            case 4: return this.searchBoard(m.getAlpha()-1, m.getNumeric());
            // 5: bottom-left
            case 5: return this.searchBoard(m.getAlpha()-1, m.getNumeric()-1);
            // 6: left
            case 6: return this.searchBoard(m.getAlpha()+1, m.getNumeric()-1);  
    }
        // fallback to break if something weird happens
        return null;
    }
    
    /**
     * this version implements an inline move
     * @param moved the rear marble to move
     * @param direction the direction of the move
     */
    public boolean move(Marble moved, int direction, int pushedFriendly){
        
        Marble adjacent = null;
        boolean isBlack = moved.isBlack();
        int pushedFriend = pushedFriendly;
        
        adjacent = this.checkAdjacent(moved, direction);
        
        if(adjacent == null){
            moved.changePos(direction);
            return true;
        } 
        else if(adjacent.isBlack() == isBlack && pushedFriend < 2){
            pushedFriend++;
            if(this.move(adjacent, direction, pushedFriend)){
                moved.changePos(direction);
                return true;
            }
        }
        
        // default return
        return false;
    }
    
    /**
     * this version implements a broadside move
     * @param m1 the first marble to move
     * @param m2 the last marble to move
     * @param direction the direction of the move
     */
    public boolean move(Marble m1, Marble m2, int direction) {
        
        int diffAlpha = Math.abs(m1.getAlpha() - m2.getAlpha());
        int diffNumeric = Math.abs(m1.getNumeric() - m2.getNumeric());
        Marble m3 = null;
        Marble rear = null;
        
        // broad conditions that the move MIGHT be legal
        if((diffAlpha <= 2) && diffNumeric <= 2){
            
            // find the marble between the selected marbles, or fail if there isn't one present
            if(diffAlpha == 2 || diffNumeric == 2){
                m3 = this.searchBoard( (m1.getAlpha()+m2.getAlpha())/2 , (m1.getNumeric()+m2.getNumeric())/2);
                if(m3 == null){
                    return false;
                }
            } 
            
            // more specific success conditions, same row, same diagonal (1-4), same diagonal (2-5) respectively
            if(m1.getAlpha() == m2.getAlpha() || m1.getNumeric() == m2.getNumeric() || diffAlpha == diffNumeric){

                // shortcut to inline method if appropriate (for the purposes of pushing enemy marbles)
                if(m1.getAlpha() == m2.getAlpha()){
                    if(direction == 3){
                        rear = (m1.getNumeric() < m2.getNumeric()) ? m1 : m2;
                        return this.move(rear, direction, 0);
                    }
                    if(direction == 6){
                        rear = (m1.getNumeric() < m2.getNumeric()) ? m2 : m1;
                        return this.move(rear, direction, 0);
                    }
                }
                
                // shortcut to inline method if appropriate (for the purposes of pushing enemy marbles)
                if(m1.getNumeric() == m2.getNumeric()){
                    if(direction == 1){
                        rear = (m1.getAlpha() < m2.getAlpha()) ? m1 : m2;
                        return this.move(rear, direction, 0);
                    }
                    if(direction == 4){
                        rear = (m1.getAlpha() < m2.getAlpha()) ? m2 : m1;
                        return this.move(rear, direction, 0);
                    }
                }
                
                // shortcut to inline method if appropriate (for the purposes of pushing enemy marbles)
                if(diffAlpha == diffNumeric){
                    if(direction == 2){
                        rear = (m1.getAlpha() < m2.getAlpha()) ? m1 : m2;
                        return this.move(rear, direction, 0);
                    }
                    if(direction == 5){
                        rear = (m1.getAlpha() < m2.getAlpha()) ? m2 : m1;
                        return this.move(rear, direction, 0);
                    }
                }

                m1.changePos(direction);
                m2.changePos(direction);
                if(m3 != null){
                    m3.changePos(direction);
                }   
                return true;
            } 
        }
        // fallback case, move fails
        return false;
    }
    
    
    /**
     * @param the move to add to a move history list
     */
    public void addMoveToList(Move move){
        if(move.getMovedList().get(0).isBlack()){
            blackMoves.add(move);
        } else {
            whiteMoves.add(move);
        }
    }
    
    // black's score = number of white marbles knocked out
    public int getBlackScore(){
        return this.whiteLost; 
    }
    
    // white's score = number of black marbles knocked out
    public int getWhiteScore(){
        return this.blackLost;
    }
    
}
