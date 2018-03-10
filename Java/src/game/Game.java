package game;

import java.util.ArrayList;

/**
 * @author Mike
 *
 */
public class Game {

    private Board board;

    // timer for total game; mostly for pausing purposes
    private long startTime;
    private long gameTime;

    // initial layouts to copy into game board; need to be filled in
    @SuppressWarnings("serial")
    public static final Board standardLayout = new Board() {
        {
            add(new Marble(1, 1, true));
            add(new Marble(1, 2, true));
            add(new Marble(1, 3, true));
            add(new Marble(1, 4, true));
            add(new Marble(1, 5, true));
            add(new Marble(2, 1, true));
            add(new Marble(2, 2, true));
            add(new Marble(2, 3, true));
            add(new Marble(2, 4, true));
            add(new Marble(2, 5, true));
            add(new Marble(2, 6, true));
            add(new Marble(3, 3, true));
            add(new Marble(3, 4, true));
            add(new Marble(3, 5, true));        

            add(new Marble(7, 5, false));
            add(new Marble(7, 6, false));
            add(new Marble(7, 7, false));
            add(new Marble(8, 4, false));
            add(new Marble(8, 5, false));
            add(new Marble(8, 6, false));
            add(new Marble(8, 7, false));
            add(new Marble(8, 8, false));
            add(new Marble(8, 9, false));
            add(new Marble(9, 5, false));
            add(new Marble(9, 6, false));
            add(new Marble(9, 7, false));
            add(new Marble(9, 8, false));
            add(new Marble(9, 9, false));
        }
    };

    @SuppressWarnings("serial")
    public static Board germanDaisy = new Board() {
        {
            add(new Marble(2, 1, true));
            add(new Marble(2, 2, true));
            add(new Marble(3, 1, true));
            add(new Marble(3, 2, true));
            add(new Marble(3, 3, true));
            add(new Marble(4, 2, true));
            add(new Marble(4, 3, true));
            add(new Marble(6, 7, true));
            add(new Marble(6, 8, true));
            add(new Marble(7, 7, true));
            add(new Marble(7, 8, true));
            add(new Marble(7, 9, true));
            add(new Marble(8, 8, true));
            add(new Marble(8, 9, true));        

            add(new Marble(2, 5, false));
            add(new Marble(2, 6, false));
            add(new Marble(3, 5, false));
            add(new Marble(3, 6, false));
            add(new Marble(3, 7, false));
            add(new Marble(4, 6, false));
            add(new Marble(4, 7, false));
            add(new Marble(6, 3, false));
            add(new Marble(6, 4, false));
            add(new Marble(7, 3, false));
            add(new Marble(7, 4, false));
            add(new Marble(7, 5, false));
            add(new Marble(8, 4, false));
            add(new Marble(8, 5, false));
        }
    };

    @SuppressWarnings("serial")
    public static final Board belgianDaisy = new Board() {
        {
            add(new Marble(1, 1, true));
            add(new Marble(1, 2, true));
            add(new Marble(2, 1, true));
            add(new Marble(2, 2, true));
            add(new Marble(2, 3, true));
            add(new Marble(3, 2, true));
            add(new Marble(3, 3, true));
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

    // marbles lost by each player, respectively
    private int blackLost;
    private int whiteLost;

    // list of moves as objects per player
    private ArrayList<Move> blackMoves;
    private ArrayList<Move> whiteMoves;

    // sets AI color
    private boolean aiIsBlack;

    private boolean activePlayerIsBlack;

    // next move as recommended by AI
    private Move recommended;

    private int aiMoveLimit;
    private int humanMoveLimit;

    // default unit is seconds?
    private long aiTimeLimit;
    private long humanTimeLimit;

    private GameTimer time;

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
        this.startTime = System.nanoTime();
        this.recommended = new Move();
        this.activePlayerIsBlack = true;
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
    public Game(Board layout, boolean aiIsBlack, int moveLimit, 
            long timeLimit, GameTimer timer) {
        this.board = layout;
        this.blackLost = 0;
        this.whiteLost = 0;
        this.blackMoves = new ArrayList<Move>();
        this.whiteMoves = new ArrayList<Move>();
        this.aiIsBlack = aiIsBlack;
        this.aiMoveLimit = moveLimit;
        this.humanMoveLimit = moveLimit;
        this.aiTimeLimit = timeLimit;
        this.humanTimeLimit = timeLimit;
        this.startTime = System.nanoTime();
        this.recommended = new Move();
        this.activePlayerIsBlack = true;
        this.time = timer;
    }

    public Board getBoard(){
        return board;
    }

    /**
     * @return the startTime
     */
    public long getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime() {
        this.startTime = System.nanoTime();
    }

    /**
     * @return the time
     */
    public GameTimer getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(GameTimer time) {
        this.time = time;
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
    public void setGameTime() {
        this.gameTime = System.nanoTime() - this.getStartTime();
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
     * This method returns true when the acting player is playing black and false otherwise
     * @return
     */
    public boolean activeIsBlack(){
        return this.activePlayerIsBlack;
    }

    /**
     * This method switches the active side of play
     */
    public void switchSides(){
        this.activePlayerIsBlack = !this.activePlayerIsBlack;
    }

    /**
     * I don't expect to use this method except for debug; changes the active player boolean to an input; use switchSides instead if possible
     * @param isBlack
     */
    public void setActiveIsBlack(boolean isBlack){
        this.activePlayerIsBlack = isBlack;
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
    public Marble searchBoard(Board b, int alpha, int numeric){
        for(Marble m : b){
            if((m.getAlpha() == alpha) && (m.getNumeric() == numeric)){
                return m;
            }
        }     
        return null;
    }

    public Marble checkAdjacent(Marble m, int direction){
        switch (direction) {
        // 1: top-left
        case 1: return searchBoard(this.getBoard(), m.getAlpha()+1, m.getNumeric());
        // 2: top-right
        case 2: return searchBoard(this.getBoard(), m.getAlpha()+1, m.getNumeric()+1);
        // 3: right
        case 3:return searchBoard(this.getBoard(), m.getAlpha(), m.getNumeric()+1);
        // 4: bottom-right
        case 4: return searchBoard(this.getBoard(), m.getAlpha()-1, m.getNumeric());
        // 5: bottom-left
        case 5: return searchBoard(this.getBoard(), m.getAlpha()-1, m.getNumeric()-1);
        // 6: left
        case 6: return searchBoard(this.getBoard(), m.getAlpha(), m.getNumeric()-1);  
        }
        // fallback to break if something weird happens
        return null;
    }

    /**
     * this version implements an inline move
     * @param moved the rear marble to move
     * @param direction the direction of the move
     */
    public boolean move(Marble moved, int direction, boolean activeIsBlack){

        Marble adjacent = null;
        boolean isBlack = activeIsBlack;
        int pushedFriend = 0;
        int pushedEnemy = 0;

        // cut off the method if player attempts to move other player's marbles directly
        if(moved.isBlack() != isBlack){
            return false;
        }

        adjacent = this.checkAdjacent(moved, direction);

        if(adjacent == null){
            moved.changePos(direction);
            return true;
        } 
        else if(adjacent.isBlack() == isBlack && pushedFriend < 2){
            pushedFriend++;
            if(this.move(adjacent, direction, isBlack, pushedFriend, pushedEnemy)){
                moved.changePos(direction);
                return true;
            }
        }

        // default return
        return false;
    }

    /**
     * this version implements an inline move, using an extra parameter for the recursive calls
     * @param moved the rear marble to move
     * @param direction the direction of the move
     */
    public boolean move(Marble moved, int direction, boolean activeIsBlack, int pushedFriendly, int pushedOpponent){

        Marble adjacent = null;
        boolean isBlack = activeIsBlack;
        int pushedFriend = pushedFriendly;
        int pushedEnemy = pushedOpponent;



        adjacent = this.checkAdjacent(moved, direction);

        // adjacent space is empty
        if(adjacent == null){
            moved.changePos(direction);
            sumito(moved, isBlack);
            return true;
        }  

        // pushing friendly marble(s)
        if(adjacent.isBlack() == isBlack && pushedFriend < 2){
            pushedFriend++;
            if(this.move(adjacent, direction, isBlack, pushedFriend, pushedEnemy)){
                moved.changePos(direction);
                return true;
            }
        }

        // pushing first enemy marble
        if(adjacent.isBlack() != isBlack && pushedFriend > 0 && pushedEnemy <= pushedFriend){

            pushedEnemy++;
            System.out.println(pushedEnemy);
            if(this.move(adjacent, direction, isBlack, pushedFriend, pushedEnemy)){
                moved.changePos(direction);
                return true;
            }
        }

        // if an enemy marble has already been pushed
        if(adjacent.isBlack() == isBlack && pushedEnemy > 0 && pushedEnemy < pushedFriend){

            pushedEnemy++;
            if(this.move(adjacent, direction, isBlack, pushedFriend, pushedEnemy)){
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
    public boolean move(Marble m1, Marble m2, int direction, boolean activeIsBlack) {

        int diffAlpha = Math.abs(m1.getAlpha() - m2.getAlpha());
        int diffNumeric = Math.abs(m1.getNumeric() - m2.getNumeric());
        boolean isBlack = activeIsBlack;
        Marble m3 = null;
        Marble rear = null;

        // cut method short if the marbles selected do not match player colour
        if(m1.isBlack() != isBlack || m2.isBlack() != isBlack){
            return false;
        }

        // broad conditions that the move MIGHT be legal
        if((diffAlpha <= 2) && diffNumeric <= 2){

            // find the marble between the selected marbles, or fail if there isn't one present
            if(diffAlpha == 2 || diffNumeric == 2){
                m3 = searchBoard(this.getBoard(),  Math.abs((m1.getAlpha()+m2.getAlpha()))/2 , Math.abs((m1.getNumeric()+m2.getNumeric()))/2 );
                if(m3 == null || m3.isBlack() != m1.isBlack()){
                    return false;
                }
            } 

            // more specific success conditions, same row, same diagonal (1-4), same diagonal (2-5) respectively
            if(m1.getAlpha() == m2.getAlpha() || m1.getNumeric() == m2.getNumeric() || diffAlpha == diffNumeric){

                // shortcut to inline method if appropriate (for the purposes of pushing enemy marbles)
                if(m1.getAlpha() == m2.getAlpha()){
                    if(direction == 3){
                        rear = (m1.getNumeric() < m2.getNumeric()) ? m1 : m2;
                        return this.move(rear, direction, isBlack);
                    }
                    if(direction == 6){
                        rear = (m1.getNumeric() < m2.getNumeric()) ? m2 : m1;
                        return this.move(rear, direction, isBlack);
                    }
                }

                // shortcut to inline method if appropriate (for the purposes of pushing enemy marbles)
                if(m1.getNumeric() == m2.getNumeric()){
                    if(direction == 1){
                        rear = (m1.getAlpha() < m2.getAlpha()) ? m1 : m2;
                        return this.move(rear, direction, isBlack);
                    }
                    if(direction == 4){
                        rear = (m1.getAlpha() < m2.getAlpha()) ? m2 : m1;
                        return this.move(rear, direction, isBlack);
                    }
                }

                // shortcut to inline method if appropriate (for the purposes of pushing enemy marbles)
                if(diffAlpha == diffNumeric){
                    if(direction == 2){
                        rear = (m1.getAlpha() < m2.getAlpha()) ? m1 : m2;
                        return this.move(rear, direction, isBlack);
                    }
                    if(direction == 5){
                        rear = (m1.getAlpha() < m2.getAlpha()) ? m2 : m1;
                        return this.move(rear, direction, isBlack);
                    }
                }

                // first case: only 2 marbles
                if((checkAdjacent(m1, direction) == null && checkAdjacent(m2, direction) == null && m3 == null) 
                        // second case: 3 marbles involved
                        || checkAdjacent(m1, direction) == null && checkAdjacent(m2, direction) == null && checkAdjacent(m3, direction) == null){
                    m1.changePos(direction);
                    m2.changePos(direction);
                    if(m3 != null){
                        m3.changePos(direction);
                    }   
                    return true;
                }      
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

    /**
     * This method is responsible for checking a possible move for legality; i.e. within the board or knocking out a single enemy marble
     * @return
     */
    public boolean moveIsLegal(Move m){
        boolean isLegal = false;
        Board dummy = new Board(this.getBoard());
        
        Marble m1 = null;
        Marble m2 = null;
        
        m1 = m.getMovedList().get(0);
        m1 = searchBoard(dummy, m1.getAlpha(), m1.getNumeric());
        
        if(m.getMovedList().size() > 1){
            m2 = m.getMovedList().get(1);
            m2 = searchBoard(dummy, m2.getAlpha(), m2.getNumeric());
        }

        if(m2 == null && move(m1, m.getDirection(), m1.isBlack()) // single marble move
                // multiple marble move
                || m2 != null && move(m1, m2, m.getDirection(), m1.isBlack())){ 
            isLegal = true;
        }

        return isLegal;
    }

    /**
     * this move checks for sumito and removes the knocked out marble from the board collection
     * @param m
     * @return
     */
    public boolean sumito(Marble m, boolean activeIsBlack){
        //this is to prevent you from booting out your own marbles, though as is it needs to be called elsewhere to validate

        int alpha = m.getAlpha();
        int num = m.getNumeric();
        int numMin = Math.max(alpha - 4, 1);
        int numMax = Math.min(alpha + 4, 9);
        System.out.println("in sumito, numMin: " + numMin + " numMax: " + numMax);
        if (num < numMin || num > numMax) {
            System.out.println("You've activated my trap card Yugi");
            if (m.isBlack()) {
                this.blackLost++;
            } else {
                this.whiteLost++;
            }
            this.getBoard().remove(m);
            return true;
        }

        return false;
    }

}
