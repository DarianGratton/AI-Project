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
     * Takes a suggested move and executes it on the specified board.
     * @param inputGameState - The game object generated from the .input file. From this we can pull the Board.
     * @param suggestedMove - Moves suggested by the genPossibleMoves method
     * @return resultingMarbles - The new board
     */
    public static ArrayList<Marble> genResultState(Game inputGameState, Move suggestedMove, boolean aiIsBlack){
        Board dummyBoard = inputGameState.getBoard();
        ArrayList<Marble> marblesMoved = suggestedMove.getMovedList();
        ArrayList<Marble> resultingMarbles = new ArrayList<Marble>();
        int directionMoved = suggestedMove.getDirection();

        //For when 1 marble moves
        if( marblesMoved.size() == 1) {
            inputGameState.move(marblesMoved.get(0), directionMoved, aiIsBlack);            //trigger Game.move method
            dummyBoard = inputGameState.getBoard();                                         //Update the Board after moving

            for(int i = 0; i < dummyBoard.size(); ++i) {
                resultingMarbles.add(dummyBoard.get(i));                                               //Add the new marbles(board layout) to an array.
            }

        }

        //For when 2 marbles move
        if(marblesMoved.size() == 2) {
            inputGameState.move(marblesMoved.get(0), marblesMoved.get(1),
                    directionMoved, aiIsBlack);
            dummyBoard = inputGameState.getBoard();                                         //Update the Board after moving
            for(int i = 0; i < dummyBoard.size(); ++i) {
                resultingMarbles.add(dummyBoard.get(i));                                               //Add the new marbles(board layout) to an array.
            }
        }
        return resultingMarbles;
    }

}
