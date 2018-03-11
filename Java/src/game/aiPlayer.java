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
        Marble mOrig = null;
        Marble mOrig2 = null;

        // Generates moves for a single marble for each marbles in the ArrayList
        for (int i = 0; i < currentBoard.size(); ++i) {

            mOrig = currentBoard.get(i);
            Marble mNew = new Marble(mOrig);
            if (mOrig.isBlack() == aiIsBlack) {
                int j = DIRECTION_MIN;
                while (j <= DIRECTION_MAX) {


                    Move move = generateMove(g, mNew, j);

                    if (move != null) {
                        System.out.println("before: " + move.toString());
                        moves.add(move);
                        System.out.println("after: " + move.toString());
                    }

                    j++;
                }
            }
        }

        // Generates moves for 2 or more marbles
        for (int i = 0; i < currentBoard.size() - 1; ++i) {
            for (int j = i + 1; j < currentBoard.size(); ++j) {
                        
                mOrig = currentBoard.get(i);
                mOrig2 = currentBoard.get(j);
                Marble mNew1 = new Marble(mOrig);
                Marble mNew2 = new Marble(mOrig2);
                if (mOrig.isBlack() == aiIsBlack && mOrig2.isBlack() == aiIsBlack) {
                    int k = DIRECTION_MIN;
                    while (k <= DIRECTION_MAX) {
                        
                        Move move = generateMove(g, mNew1, mNew2, k);
                                
                        if (move != null) {
                            System.out.println("before: " + move.toString());
                            moves.add(move);
                            System.out.println("after: " + move.toString());
                        }

                        k++;
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
    public static Move generateMove(Game g, Marble m, int direction){
        Move move = new Move(m, direction);

       /* System.out.print("before");
        System.out.println(move);*/
        if(g.moveIsLegal(move)){
           /* System.out.print("after");
            System.out.println(move);*/
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
    public static ArrayList<Marble> genResultState(Game g, Move mv, boolean aiIsBlack){
        Board dummy = new Board();
        



        return dummy;
    }

}
