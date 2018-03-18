/**
 * 
 */
package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

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
        Board original = Board.copyBoard(g.getBoard());
        Board currentBoard = Board.copyBoard(g.getBoard());
        Board ownMarbles = new Board();
        Marble m1 = null;
        Marble m2 = null;

        for(Marble m: currentBoard){
            if(m.isBlack() == aiIsBlack){
                ownMarbles.add(m);
            }
        }
        
        // Generates moves for a single marble for each marbles in the ArrayList
        for (int i = 0; i < currentBoard.size(); ++i) {
            
            m1 = currentBoard.get(i);
            if (m1.isBlack() == aiIsBlack) {
                int j = DIRECTION_MIN;
                while (j <= DIRECTION_MAX) {


                    Move move = generateMove(g, m1, j);

                    if (move != null) {
                        //System.out.println("before: " + move.toString());
                        moves.add(move);
                        //System.out.println("after: " + move.toString());
                    }

                    currentBoard = Board.copyBoard(original);
                    j++;
                }
            }
        }

        // Generates moves for 2 or more marbles
        for (int i = 0; i < currentBoard.size() - 1; ++i) {
            for (int j = i + 1; j < currentBoard.size(); ++j) {

                m1 = currentBoard.get(i);
                m2 = currentBoard.get(j);
                if (m1.isBlack() == aiIsBlack && m2.isBlack() == aiIsBlack) {
                    int k = DIRECTION_MIN;
                    while (k <= DIRECTION_MAX) {

                        Move move = generateMove(g, m1, m2, k);

                        if (move != null) {
                            moves.add(move);
                        }

                        currentBoard = Board.copyBoard(original);
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
        Board gameState = Board.copyBoard(g.getBoard());
        Move displayMove = new Move(m, direction);
        Marble dummy = new Marble(m);
        Move move = new Move(dummy, direction);

        /*System.out.print("before");
        System.out.println(move);*/
        if(Game.moveIsLegal(g, move)){
            g.getBoard().remove(dummy);
            // resets the board state so that the marbles in the move display origin coordinates
            g.setBoard(gameState);
            /*System.out.print("after");
            System.out.println(move);*/
            return displayMove;
        }      

        // return null if move was illegal   
        return null;
    }

    /**
     * This method is responsible for generating a single legal move of multiple marble based on a given board state
     * @return
     */
    public static Move generateMove(Game g, Marble m1, Marble m2, int direction){
        Board gameState = Board.copyBoard(g.getBoard());
        Move displayMove = new Move(m1, m2, direction);
        Marble dummy1 = new Marble(m1);
        Marble dummy2 = new Marble(m2);
        Move move = new Move(dummy1, dummy2, direction);

        if(Game.moveIsLegal(g, move)){
            g.getBoard().remove(dummy1);
            g.getBoard().remove(dummy2);
            // resets the board state so that the marbles in the move display origin coordinates
            g.setBoard(gameState);
            return displayMove;
        }      

        // return null if move was illegal
        return null;
    }

    /**
     * Takes a suggested move and executes it on the specified board.
     * @param g the current state of the game
     * 
     */
    public static Board genResultState(Game g, Move mv){
        Game dummy = new Game(g);

        Marble m1 = mv.getMovedList().get(0);
        Marble m2 = null;
        
        System.out.println(mv.toString());

        if (mv.getMovedList().size() > 1) {
            m2 = mv.getMovedList().get(1);
        }

        if(m2 == null){// single marble move 
            //System.out.println("single");
            dummy.move(m1, mv.getDirection(), m1.isBlack());  
            
        } else {// multiple marble move
            /*System.out.println("double");
            System.out.println(m2.toString());*/
            dummy.move(m1, m2, mv.getDirection(), m1.isBlack());   
        }
        
        
        
        /*System.out.println(success);
        if (success) {
            System.out.println("new: " + dummy.getBoard().toString());
            //System.out.println("butts");
        }*/
        return dummy.getBoard();
    }

    /**
     * Returns resulting states from an arraylist of moves (which must already be checked for legality) as a HashMap; 
     * evaluation function values will be added later as the double values in the map
     * @param g
     * @param moves
     * @return
     */
    @SuppressWarnings("unchecked")
    public static HashMap<Board, Double> genAllResults(Game g, ArrayList<Move> moves){
        HashMap<Board, Double> states = new HashMap<Board, Double>();
        Board singleState = null;

        for(Move mv : moves){
            singleState = genResultState(g, mv);
            //Collections.sort(singleState, new SortArray());
            //System.out.println(singleState.toString());
            states.put(singleState, 0.0);
        }

        return states;
    }
}
