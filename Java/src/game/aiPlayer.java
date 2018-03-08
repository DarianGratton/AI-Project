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
    
    private Game game;
    
    public aiPlayer() {
        game = new Game();
    }
    
    /**
     * This method is responsible for generating a list of possible moves given the current board state
     * @return
     */
    public ArrayList<Move> genPossibleMoves(Board currentBoard, boolean aiIsBlack){
        ArrayList<Move> moves = new ArrayList<Move>();
        
        // Generates moves for a single marble for each marbles in the ArrayList
        for (int i = 0; i < currentBoard.size(); ++i) {
            for (int j = DIRECTION_MIN; j <= DIRECTION_MAX; ++j) {
                if (game.move(currentBoard.get(i), j, aiIsBlack)) {
                    //moves.add(new Move());
                }
            }
        }
        
        // Generates moves for 2 or more marbles
        for (int i = 0; i < currentBoard.size(); ++i) {
            for (int j = 1; j < currentBoard.size(); ++j) {
                for (int k = DIRECTION_MIN; k <= DIRECTION_MAX; ++k) {
                    if (game.move(currentBoard.get(i), k, aiIsBlack, j, i)) {
                        
                    }
                }
            }
        }
        
        return moves;
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
