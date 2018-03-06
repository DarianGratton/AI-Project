/**
 * 
 */
package game;

import java.util.ArrayList;

/**
 * @author Mike
 *
 */
public class aiPlayer {

    private static final int DIRECTION_MIN = 1;
    private static final int DIRECTION_MAX = 6;
    
    /**
     * This method is responsible for generating a list of possible moves given the current board state
     * @return
     */
    public static ArrayList<Move> genPossibleMoves(Board currentBoard, boolean aiIsBlack){
        ArrayList<Move> moves = new ArrayList<Move>();
        
        
        
        
        return null;
    }
    
    /**
     * This method is responsible for generating a single legal move of one marble based on a given board state
     * @return
     */
    public static Move generateMove(Board currentBoard, Marble m, int direction){
        Move move = new Move(m, direction);
        if(Game.moveIsLegal(currentBoard, move)){
            return move;
        }      
        
        // return null if move was illegal        
        return null;
    }
    
    /**
     * This method is responsible for generating a single legal move of multiple marble based on a given board state
     * @return
     */
    public static Move generateMove(Board currentBoard, Marble m1, Marble m2, int direction){
        Move move = new Move(m1, m2, direction);
        if(Game.moveIsLegal(currentBoard, move)){
            return move;
        }      
        
        // return null if move was illegal
        return null;
    }
    
    /**
     * This method is responsible for generating a single board state that results from a legal move
     * @return
     */
    public static ArrayList<Marble> genResultState(boolean aiIsBlack, Board b, Move mv){
        Board dummy = b;
        
        
        
        return dummy;
    }
    
}
