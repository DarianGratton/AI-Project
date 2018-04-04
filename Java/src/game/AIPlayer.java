/**
 * 
 */
package game;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
/**
 * @author Mike
 *
 */
public class AIPlayer {
	private static final int DIRECTION_MIN = 1;
	private static final int DIRECTION_MAX = 6;
	private static final long NANO_DIVISOR = 1000000000;
	private static int MAX_DEPTH = 2;
	private static Move suggestedMove = null;
	
	private static HashSet<ArrayList<Marble>> adjacentMarbles;
	
	
	
	/**
	 * This method is responsible for generating a list of possible moves given the current board state
	 * @return
	 */
	public static ArrayList<Move> genPossibleMoves(Game g, boolean aiIsBlack){
		ArrayList<Move> moves = new ArrayList<Move>();
		//Board original = Board.copyBoard(g.getBoard());
		Board currentBoard = Board.copyBoard(g.getBoard());
		Board ownMarbles = new Board();
		/* Marble m1 = null;
        Marble m2 = null;*/
		for(Marble m: currentBoard){
			if(m.isBlack() == aiIsBlack){
				ownMarbles.add(m);
			}
		}
		for(Marble m : ownMarbles){
			for(int i = DIRECTION_MIN; i <= DIRECTION_MAX; ++i){
				Move move = generateMove(g, m, i);
				if (move != null) {
					move.evaluate(g.getBoard());
					//System.out.println("before: " + move.toString());
					moves.add(move);
					//System.out.println("after: " + move.toString());
				}
			}
		}
		// Generates moves for a single marble for each marbles in the ArrayList
		/* for (int i = 0; i < currentBoard.size(); ++i) {
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
        }*/
		while(!ownMarbles.isEmpty()){
			Marble m = ownMarbles.pollFirst();
			for(Marble o : ownMarbles){
				//if(!m.equals(o)){
				for(int i = DIRECTION_MIN; i <= DIRECTION_MAX; ++i){
					Move move = generateMove(g, m, o, i);
					if (move != null) {
						move.evaluate(g.getBoard());
						//System.out.println("before: " + move.toString());
						moves.add(move);
						//System.out.println("after: " + move.toString());
					}
				}
				//}
			}
		}
		/*for(Marble m : ownMarbles){
            for(Marble o : ownMarbles){
                if(!m.equals(o)){
                    for(int i = DIRECTION_MIN; i <= DIRECTION_MAX; ++i){
                        Move move = generateMove(g, m, o, i);
                        if (move != null) {
                            //System.out.println("before: " + move.toString());
                            moves.add(move);
                            //System.out.println("after: " + move.toString());
                        }
                    }
                }
            }
        }*/
		// Generates moves for 2 or more marbles
		/*for (int i = 0; i < currentBoard.size() - 1; ++i) {
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
        }*/
		return moves;
	}
	/**
	 * This method is responsible for generating a single legal move of one marble based on a given board state
	 * @return
	 */
	public static Move generateMove(Game g, Marble m, int direction){
		Game dummy = new Game(g);
		Marble nm = Game.searchBoard(dummy.getBoard(), m.getAlpha(), m.getNumeric());
		Move displayMove = new Move(m, direction);
		Move move = new Move(nm, direction);
		/*System.out.print("before");
        System.out.println(move);*/
		if(Game.moveIsLegal(g, move)){
			// resets the board state so that the marbles in the move display origin coordinates
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
		Game dummy = new Game(g);
		Move displayMove = new Move(m1, m2, direction);
		Marble nm1 = Game.searchBoard(dummy.getBoard(), m1.getAlpha(), m1.getNumeric());
		Marble nm2 = Game.searchBoard(dummy.getBoard(), m2.getAlpha(), m2.getNumeric());
		Move move = new Move(nm1, nm2, direction);
		if(Game.moveIsLegal(g, move)){
			// resets the board state so that the marbles in the move display origin coordinates
			//g.setBoard(gameState);
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
	public static Board genResultState(Board b, Move mv){
		Game dummy = new Game(b, mv.getMovedList().get(0).isBlack());
		//Board original = Board.copyBoard(g.getBoard());
		Marble m1 = mv.getMovedList().get(0);
		m1 = Game.searchBoard(dummy.getBoard(), m1.getAlpha(), m1.getNumeric());
		Marble m2 = null;
		//System.out.println("old: " + original.toString());
		if (mv.getMovedList().size() > 1) {
			m2 = mv.getMovedList().get(1);
			if(m2 != null){
				m2 = Game.searchBoard(dummy.getBoard(), m2.getAlpha(), m2.getNumeric());
			}
		}
		if(m2 == null){// single marble move 
			//System.out.println("single");
			dummy.move(m1, mv.getDirection(), m1.isBlack());  
		} else {// multiple marble move
			/*System.out.println("double");
            System.out.println(m2.toString());*/
			//System.out.println("The program gets here sometimes");
			dummy.move(m1, m2, mv.getDirection(), m1.isBlack());   
		}
		//System.out.println("new?: " + dummy.getBoard().toString());
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
	public static HashSet<Board> genAllResults(Board b, ArrayList<Move> moves){
		HashSet<Board> states = new HashSet<Board>();
		Board singleState = null;
		for(Move mv : moves){
			singleState = Board.copyBoard(genResultState(b, mv));
			//Collections.sort(singleState, new MarbleComparator());
			//System.out.println(singleState.toString());
			if(!states.contains(singleState)){
				states.add(singleState);
			}
		}
		return states;
	}
	
//	public static int distanceFromCenter(Board b, boolean aiIsBlack) {
//
//		char color;
//		int alpha;
//		int num;
//		int centerBlack = 0;
//		int centerWhite = 0;
//		int centerTotal = 0;
//
//		for(Marble m : b) {
//			//Gets the alpha of the marble.
//			alpha = m.getAlpha();
//			//Gets the numeric of the marble.
//			num = m.getNumeric();
//			//Gets the color of the marble.
//			color = m.getColor();
//
//			//Calculates the distances from the center and adds them all together.
//			//It takes the center marble E5 and then calculates the distance accordingly.
//			//The marble on the edges will have the lowest value = 2.
//			//The marble next to it will have a value = 9 which is the highest.
//			if(aiIsBlack == false && color == 'w')
//				centerWhite += (5 - Math.abs(5 - alpha) + 5 - Math.abs(5 - num));
//			else
//				centerBlack += (5 - Math.abs(5 - alpha) + 5 - Math.abs(5 - num));
//			
//			
//		}
//		
//		if(aiIsBlack == false)  
//			centerTotal = centerWhite - centerBlack;
//		else
//			centerTotal = centerBlack - centerWhite;
//
//		return centerTotal;
//	}
//
//
//	public static int adjacentMarbles(Board b, boolean aiIsBlack) {
//		int total = 0;
//		int color;
//		int alpha;
//		int numeric;
//		for(Marble m : b) {
//			
//		}
//				
//		return total;
//	}

	public static int isAdjacent(Marble m1, Marble adjMarble, Board b, int x, int y) {
	    
		char color1 = m1.getColor();
		char color2 = adjMarble.getColor();
		Board newBoard = Board.copyBoard(b);
		
		if (color1 != color2) {
			return 0;
		}
		
		//System.out.println(m1.toString());
	
		Marble inlineMarble = newBoard.getMarbleAt(adjMarble.getAlpha() + x, adjMarble.getNumeric() + y);
		if (inlineMarble == null || inlineMarble.getColor() != adjMarble.getColor()) {
		    return 1;
		} else {
		    return 2;
		}
		
	}
	
	public static int isLine(Marble marble, Board b) {

		int num = 0;
		Board newBoard;

		for (int x = -1; x <= 1; ++x) {
            for (int y = -1; y <= 1; ++y) {
                newBoard = Board.copyBoard(b);
                if (x != 0 || y != 0) {
                    Marble adjMar = newBoard.getMarbleAt(marble.getAlpha() + x, marble.getNumeric() + y);
                    if (adjMar != null) {
                        num += isAdjacent(marble, adjMar, newBoard, x, y);
                    }
                }
            }
        }
		
		System.out.println(marble.toString());
		System.out.println(num);

		return num;
	}

	/**
	 * This method is responsible for returning a board state's evaluation based on the state of its marbles and the AI's colour
	 * @param b the current board state
	 * @param aiIsBlack true if the AI is playing black and false if it is playing white
	 * @return the board state's evaluation
	 */
	public static double evaluateBoard(Board b, boolean aiIsBlack){
		int total = 0;
		Board testBoard = Board.copyBoard(b);
		int blobTotal = 0;
		int adjTotal = 0;
		
		for(Marble m : b) {
			adjTotal += isLine(m, testBoard);
			//System.out.println(adjTotal);
			
		}
		
		//Kod - Knocked out defensive
		int Kod1 = 70;
		int Kod2 = 50;
		int Kod3 = 40;
		int Kod4 = 50;
		int Kod5 = 60;
		int Kod6 = 100;
		
		//Koo - Knocked out ofensive
		int Koo1 = 85;
		int Koo2 = 70;
		int Koo3 = 60;
		int Koo4 = 65;
		int Koo5 = 70;
		int Koo6 = 100;
		
		
		
		return total;
	}

	public static Move alphaBetaSearch(Game game, boolean aiIsBlack, int maxDepth){
		Game g = new Game(game);
		int depth = 0;
		long startTime = System.nanoTime();
		long timeTaken = 0;
		int movesMade = aiIsBlack ? g.getBlackMoves().size() : g.getWhiteMoves().size();
		Move bestMove = null;
		ArrayList<Move> moves = AIPlayer.genPossibleMoves(g, aiIsBlack);
		bestMove = moves.get(0);

		while(movesMade <= g.getAiMoveLimit() && timeTaken < g.getAiTimeLimit()){
			
		    ++depth;
			double v = maxMove(g.getBoard(), aiIsBlack, -Double.MAX_VALUE, Double.MAX_VALUE, depth, maxDepth);
			
			for (Move m : moves) {
				if (v == m.getEval()) {
					bestMove = m;
					//System.out.println(bestMove.toString());
					bestMove.setTime(timeTaken);
				}
			} 
			
			timeTaken = System.nanoTime() - startTime;
		}
		
		return bestMove;
	}

	/**    * 
	 * @param b the current board state
	 * @param aiIsBlack whether the AI is playing black pieces
	 * @param a alpha; i.e. the largest value encountered so far
	 * @param B Beta; i.e. the smallest value encountered so far
	 * @return the best possible move for the AI given the board state
	 */
	public static double maxMove(Board current, boolean aiIsBlack, double a, double B, int depth, int maxDepth){
		
	    Board currBoard = Board.copyBoard(current);
	    double currentEval = evaluateBoard(currBoard, aiIsBlack);
		if(currentEval >= 100.0 || depth >= maxDepth){ // replace true with terminal test
			return currentEval;
		}
		
		int newDepth = ++depth;		
		/* Turn time limit should go here as terminal test */
		double maxEval = -Double.MAX_VALUE;
		Game dummy = new Game(currBoard, aiIsBlack);
		ArrayList<Move> moves = AIPlayer.genPossibleMoves(dummy, aiIsBlack);
		
		//double maxValue = -Double.MAX_VALUE;
		for(int i = 0; i < moves.size(); ++i){
			// pulled out of Math.max function for re-use
			double minEval = AIPlayer.minMove(genResultState(currBoard, moves.get(i)), aiIsBlack, a, B, newDepth, maxDepth);
			// straight from pseudocode
			maxEval = Math.max(maxEval, minEval);
			if(maxEval >= B){
				//System.out.println(moves.get(i).toString());
				return maxEval;
			}
			/*if(minEval > maxValue){ // if the current result is the best we have so far
                maxValue = minEval; // set the max value to current result
                maxIndex = i; // set index of "best" move to current move
            }*/
			
			a = Math.max(a, maxEval);
		}
		// System.out.println(moves.get(maxIndex).toString());
		return maxEval;
	}
	/**
	 * 
	 * @param b the current board state
	 * @param aiIsBlack whether the AI is playing black pieces
	 * @param a alpha; i.e. the largest value encountered so far
	 * @param B Beta; i.e. the smallest value encountered so far
	 * @return the best possible move for the opponent given the board state
	 */
	public static double minMove(Board current, boolean aiIsBlack, double a, double B, int depth, int maxDepth) {
	    
	    Board currBoard = Board.copyBoard(current);
		double currentEval = evaluateBoard(currBoard, aiIsBlack);
		if(currentEval >= 100.0 || depth >= maxDepth){ // replace true with terminal test
			return currentEval;
		}
		
		int newDepth = ++depth;
		double minEval = Double.MAX_VALUE;
		Game dummy = new Game(currBoard, aiIsBlack);
		ArrayList<Move> moves = AIPlayer.genPossibleMoves(dummy, !aiIsBlack);
		
		//double minValue = 0.0;
		for(int i = 0; i < moves.size(); ++i){
			
		    // pulled out of Math.min function for re-use
			double maxEval = AIPlayer.maxMove(genResultState(currBoard, moves.get(i)), aiIsBlack, a, B, newDepth, maxDepth);
			
			// straight from pseudocode
			minEval = Math.min(minEval, maxEval);
			if(minEval <= a){
				//System.out.println(moves.get(i).toString());
				return minEval;
			}
			/*if(maxEval < minValue){ // if the current result is the worst we have so far
                minValue = maxEval; // set the min value to current result
                minIndex = i; // set index of "worst" move to current move
            }*/
			B = Math.min(B, minEval);
		}
		//System.out.println(moves.get(minIndex).toString());
		return minEval;
	}
	
	public static int getMaxDepth(){
		return MAX_DEPTH;
	}
	
	public static void setMaxDepth(int newMaxDepth){
		MAX_DEPTH = newMaxDepth;
	}
}