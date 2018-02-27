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

    /**
     * This method is responsible for generating a list of possible moves given the current board state
     * @return
     */
    public static ArrayList<Move> genPossibleMoves(Board currentBoard){
        
        return null;
    }
    
    /**
     * This method is responsible for generating a single board state that results from a legal move
     * @return
     */
    public static ArrayList<Marble> genResultState(boolean aiIsBlack, Marble m1, Move v1){
        
        return null;
    }
    
    /**
     * This method is responsible for looping through available marbles and generating all possible moves
     * @return
     */
    public static ArrayList<ArrayList<Marble>> genAllStates(boolean aiIsBlack, Game g, ArrayList<Move> legalMoves){
        ArrayList<Board> results = new ArrayList<Board>();
        
        for(Marble m : g.getBoard()){
            if(m.isBlack() == aiIsBlack){
                
            }
        }
        
        
        
        return null;
    }
}
