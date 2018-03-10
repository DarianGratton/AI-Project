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
    public static ArrayList<Move> genPossibleMoves(Game g, boolean aiIsBlack){
        ArrayList<Move> moves = new ArrayList<Move>();
        Board currentBoard = g.getBoard();

        // Generates moves for a single marble for each marbles in the ArrayList
        /*for (int i = 0; i < currentBoard.size(); ++i) {

            int j = DIRECTION_MIN;
            while (j <= DIRECTION_MAX) {

                if (currentBoard.get(i).isBlack() == aiIsBlack) {
                    Move move = generateMove(g, currentBoard.get(i), j);

                    if (move != null) {
                        moves.add(move);
                        System.out.println(move.toString());
                    }

                    ++j;
                }
            }
        }*/

        for(Marble m : currentBoard){
            if(m.isBlack() == aiIsBlack){
                for(int i = DIRECTION_MIN; i <= DIRECTION_MAX; i++){
                    Move mv = generateMove(g, m, i);
                }
            }
        }

        // Generates moves for 2 or more marbles
        //        for (int i = 0; i < currentBoard.size() - 1; ++i) {
        //            for (int j = 1; j < currentBoard.size(); ++j) {
        //                
        //                int k = DIRECTION_MIN;
        //                while (k <= DIRECTION_MAX) {
        //                    
        //                    if (currentBoard.get(i).isBlack() == aiIsBlack) {
        //                        Board dummyBoard = new Board(currentBoard);
        //                        Move move = generateMove(dummyBoard, currentBoard.get(i), currentBoard.get(j), k);
        //                        
        //                        if (move != null) {
        //                            moves.add(move);  
        //                        }
        //                        
        //                    }
        //                    
        //                    ++k;
        //                }
        //            }
        //        }

        return moves;
    }

    /**
     * This method is responsible for generating a single legal move of one marble based on a given board state
     * @return
     */
    public static Move generateMove(Game g, Marble m, int direction){
        Move move = new Move(m, direction);

        if(g.moveIsLegal(move)){
            return move;
        }      

        // return null if move was illegal   

        return null;
    }

    /**
     * This method is responsible for generating a single legal move of multiple marble based on a given board state
     * @return
     */
    public static Move generateMove(Game g, Marble m1, Marble m2, int direction){
        Move move = new Move(m1, m2, direction);

        if(g.moveIsLegal(move)){
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
