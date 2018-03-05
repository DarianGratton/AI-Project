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

    //Board for Stage 2
    public static Board inputBoard;

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
     * @param turn - either 'w' or 'b'
     * @param array - of inputs
     * Constructor to place file input
     */
    public Game(char turn, String[] array) {
        if(!(turn == 'w' || turn == 'b')) {
            System.out.println("Invalid input");
        }
        turnInputToMarbles(array);
        this.board = inputBoard;
        this.blackMoves = new ArrayList<Move>();
        this.whiteMoves = new ArrayList<Move>();
        this.aiMoveLimit = 100;
        this.humanMoveLimit = this.aiMoveLimit;
        this.aiTimeLimit = (long) 1000.0;
        this.humanTimeLimit = this.aiTimeLimit;
        this.startTime = System.nanoTime();
        this.recommended = new Move();

        if(turn == 'w') {
            this.aiIsBlack = true;
            this.activePlayerIsBlack = false;
        } else {
            this.aiIsBlack = false;
            this.activePlayerIsBlack = true;
        }
    }

    /**
     * Turns String of coordinates into layout
     * @param array
     * @return board object
     */
    public void turnInputToMarbles(String[] array) {
        inputBoard = new Board() {
            {
                add(new Marble(turnAlphaToDigit(array[0].charAt(0)), turnCharToInt(array[0].charAt(1)), turnAlphatoBool(array[0].charAt(2))));
                add(new Marble(turnAlphaToDigit(array[0].charAt(1)), turnCharToInt(array[1].charAt(1)), turnAlphatoBool(array[1].charAt(2))));
                add(new Marble(turnAlphaToDigit(array[0].charAt(2)), turnCharToInt(array[2].charAt(1)), turnAlphatoBool(array[2].charAt(2))));
                add(new Marble(turnAlphaToDigit(array[0].charAt(3)), turnCharToInt(array[3].charAt(1)), turnAlphatoBool(array[3].charAt(2))));
                add(new Marble(turnAlphaToDigit(array[0].charAt(4)), turnCharToInt(array[4].charAt(1)), turnAlphatoBool(array[4].charAt(2))));
                add(new Marble(turnAlphaToDigit(array[0].charAt(5)), turnCharToInt(array[5].charAt(1)), turnAlphatoBool(array[5].charAt(2))));
                add(new Marble(turnAlphaToDigit(array[0].charAt(6)), turnCharToInt(array[6].charAt(1)), turnAlphatoBool(array[6].charAt(2))));
                add(new Marble(turnAlphaToDigit(array[0].charAt(7)), turnCharToInt(array[7].charAt(1)), turnAlphatoBool(array[7].charAt(2))));
                add(new Marble(turnAlphaToDigit(array[0].charAt(8)), turnCharToInt(array[8].charAt(1)), turnAlphatoBool(array[8].charAt(2))));
                add(new Marble(turnAlphaToDigit(array[0].charAt(9)), turnCharToInt(array[9].charAt(1)), turnAlphatoBool(array[9].charAt(2))));
                add(new Marble(turnAlphaToDigit(array[0].charAt(10)), turnCharToInt(array[10].charAt(1)), turnAlphatoBool(array[10].charAt(2))));
                add(new Marble(turnAlphaToDigit(array[0].charAt(11)), turnCharToInt(array[11].charAt(1)), turnAlphatoBool(array[11].charAt(2))));
                add(new Marble(turnAlphaToDigit(array[0].charAt(12)), turnCharToInt(array[12].charAt(1)), turnAlphatoBool(array[12].charAt(2))));
                add(new Marble(turnAlphaToDigit(array[0].charAt(13)), turnCharToInt(array[13].charAt(1)), turnAlphatoBool(array[13].charAt(2))));
                add(new Marble(turnAlphaToDigit(array[0].charAt(14)), turnCharToInt(array[14].charAt(1)), turnAlphatoBool(array[14].charAt(2))));
                add(new Marble(turnAlphaToDigit(array[0].charAt(15)), turnCharToInt(array[15].charAt(1)), turnAlphatoBool(array[15].charAt(2))));
                add(new Marble(turnAlphaToDigit(array[0].charAt(16)), turnCharToInt(array[16].charAt(1)), turnAlphatoBool(array[16].charAt(2))));
                add(new Marble(turnAlphaToDigit(array[0].charAt(17)), turnCharToInt(array[17].charAt(1)), turnAlphatoBool(array[17].charAt(2))));
                add(new Marble(turnAlphaToDigit(array[0].charAt(18)), turnCharToInt(array[18].charAt(1)), turnAlphatoBool(array[18].charAt(2))));
                add(new Marble(turnAlphaToDigit(array[0].charAt(19)), turnCharToInt(array[19].charAt(1)), turnAlphatoBool(array[19].charAt(2))));
                add(new Marble(turnAlphaToDigit(array[0].charAt(20)), turnCharToInt(array[20].charAt(1)), turnAlphatoBool(array[20].charAt(2))));
                add(new Marble(turnAlphaToDigit(array[0].charAt(21)), turnCharToInt(array[21].charAt(1)), turnAlphatoBool(array[21].charAt(2))));
                add(new Marble(turnAlphaToDigit(array[0].charAt(22)), turnCharToInt(array[22].charAt(1)), turnAlphatoBool(array[22].charAt(2))));
                add(new Marble(turnAlphaToDigit(array[0].charAt(23)), turnCharToInt(array[23].charAt(1)), turnAlphatoBool(array[23].charAt(2))));
                add(new Marble(turnAlphaToDigit(array[0].charAt(24)), turnCharToInt(array[24].charAt(1)), turnAlphatoBool(array[24].charAt(2))));
                add(new Marble(turnAlphaToDigit(array[0].charAt(25)), turnCharToInt(array[25].charAt(1)), turnAlphatoBool(array[25].charAt(2))));
            }
        };
    }

    /**
     *  there's no error checking either for this one, asusming file input is done correctly
     * @param alpha - chatAt input
     * @return corresponding number coordiate to alpha character
     */
    public int turnAlphaToDigit(char alpha) {
        int n = 0;
        switch(alpha) {
            case 'A':
                n = 1;
                break;
            case 'B':
                n = 2;
                break;
            case 'C':
                n = 3;
                break;
            case 'D':
                n = 4;
                break;
            case 'E':
                n = 5;
                break;
            case 'F':
                n = 6;
                break;
            case 'G':
                n = 7;
                break;
            case 'H':
                n = 8;
                break;
            case 'I':
                n = 9;
                break;
        }
        return n;
    }

    /**
     *  there's no error checking, assuming that file input is done correctly
     * @param alpha - chatAt input
     * @return true if isBlack() = true for marble
     */
    public boolean turnAlphatoBool(char alpha) {
        boolean state = false;
        switch(alpha) {
            case 'w':
                state = false;
                break;
            case 'b':
                state = true;
                break;
        }
        return state;
    }

    /**
     * turns char '1' to integer 1... p sure there's a function for this but .
     * @param alpha
     * @return
     */
    public int turnCharToInt(char alpha) {
        int n = 0;
        switch(alpha) {
            case '1':
                n = 1;
                break;
            case '2':
                n = 2;
                break;
            case '3':
                n = 3;
                break;
            case '4':
                n = 4;
                break;
            case '5':
                n = 5;
                break;
            case '6':
                n = 6;
                break;
            case '7':
                n = 7;
                break;
            case '8':
                n = 8;
                break;
            case '9':
                n = 9;
                break;
        }
        return n;

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
        case 6: return this.searchBoard(m.getAlpha(), m.getNumeric()-1);  
        }
        // fallback to break if something weird happens
        return null;
    }

    /**
     * this version implements an inline move
     * @param moved the rear marble to move
     * @param direction the direction of the move
     */
    public boolean move(Marble moved, int direction){

        Marble adjacent = null;
        boolean isBlack = moved.isBlack();
        int pushedFriend = 0;

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
     * this version implements an inline move, using an extra parameter for the recursive calls
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
                m3 = this.searchBoard( Math.abs((m1.getAlpha()+m2.getAlpha()))/2 , Math.abs((m1.getNumeric()+m2.getNumeric()))/2 );
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
                        return this.move(rear, direction);
                    }
                    if(direction == 6){
                        rear = (m1.getNumeric() < m2.getNumeric()) ? m2 : m1;
                        return this.move(rear, direction);
                    }
                }

                // shortcut to inline method if appropriate (for the purposes of pushing enemy marbles)
                if(m1.getNumeric() == m2.getNumeric()){
                    if(direction == 1){
                        rear = (m1.getAlpha() < m2.getAlpha()) ? m1 : m2;
                        return this.move(rear, direction);
                    }
                    if(direction == 4){
                        rear = (m1.getAlpha() < m2.getAlpha()) ? m2 : m1;
                        return this.move(rear, direction);
                    }
                }

                // shortcut to inline method if appropriate (for the purposes of pushing enemy marbles)
                if(diffAlpha == diffNumeric){
                    if(direction == 2){
                        rear = (m1.getAlpha() < m2.getAlpha()) ? m1 : m2;
                        return this.move(rear, direction);
                    }
                    if(direction == 5){
                        rear = (m1.getAlpha() < m2.getAlpha()) ? m2 : m1;
                        return this.move(rear, direction);
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
    public boolean moveIsLegal(){
        
        return true;
    }

}
