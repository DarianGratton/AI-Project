/**
 * 
 */
package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

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
    private static Game g = new Game();
	private static final ArrayList<Marble> RING1 = new ArrayList<Marble>() {
		{
			add(new Marble(5, 5, true));
			add(new Marble(5, 5, false));
		}
	};

	private static final ArrayList<Marble> RING2 = new ArrayList<Marble>() {
		{
			add(new Marble(5, 4, true));
			add(new Marble(5, 4, false));
			add(new Marble(6, 5, true));
			add(new Marble(6, 5, false));
			add(new Marble(6, 6, true));
			add(new Marble(6, 6, false));
			add(new Marble(5, 6, true));
			add(new Marble(5, 6, false));
			add(new Marble(4, 5, true));
			add(new Marble(4, 5, false));
			add(new Marble(4, 4, true));
			add(new Marble(4, 4, false));
		}
	};
	private static final ArrayList<Marble> RING3 = new ArrayList<Marble>() {
		{
			add(new Marble(7, 5, true));
			add(new Marble(7, 7, false));
			add(new Marble(7, 6, true));
			add(new Marble(7, 6, false));
			add(new Marble(7, 7, true));
			add(new Marble(7, 7, false));
			add(new Marble(6, 7, true));
			add(new Marble(6, 7, false));
			add(new Marble(5, 7, true));
			add(new Marble(5, 7, false));
			add(new Marble(4, 6, true));
			add(new Marble(4, 6, false));
			add(new Marble(3, 5, true));
			add(new Marble(3, 5, false));
			add(new Marble(3, 4, true));
			add(new Marble(3, 4, false));
			add(new Marble(3, 3, true));
			add(new Marble(3, 3, false));
			add(new Marble(4, 3, true));
			add(new Marble(4, 3, false));
			add(new Marble(5, 3, true));
			add(new Marble(5, 3, false));
			add(new Marble(6, 4, true));
			add(new Marble(6, 4, false));
		}
	};
	private static final ArrayList<Marble> RING4 = new ArrayList<Marble>() {
		{
			add(new Marble(8, 5, true));
			add(new Marble(8, 5, false));
			add(new Marble(8, 6, true));
			add(new Marble(8, 6, false));
			add(new Marble(8, 7, true));
			add(new Marble(8, 7, false));
			add(new Marble(8, 8, true));
			add(new Marble(8, 8, false));
			add(new Marble(7, 8, true));
			add(new Marble(7, 8, false));
			add(new Marble(6, 8, true));
			add(new Marble(6, 8, false));
			add(new Marble(5, 8, true));
			add(new Marble(5, 8, false));
			add(new Marble(4, 7, true));
			add(new Marble(4, 7, false));
			add(new Marble(3, 6, true));
			add(new Marble(3, 6, false));
			add(new Marble(2, 5, true));
			add(new Marble(2, 5, false));
			add(new Marble(2, 4, true));
			add(new Marble(2, 4, false));
			add(new Marble(2, 3, true));
			add(new Marble(2, 3, false));
			add(new Marble(2, 2, true));
			add(new Marble(2, 2, false));
			add(new Marble(3, 2, true));
			add(new Marble(3, 2, false));
			add(new Marble(4, 2, true));
			add(new Marble(4, 2, false));
			add(new Marble(5, 2, true));
			add(new Marble(5, 2, false));
			add(new Marble(8, 4, true));
			add(new Marble(8, 4, false));
			add(new Marble(6, 3, true));
			add(new Marble(6, 3, false));
		}
	};
	private static final ArrayList<Marble> RING5 = new ArrayList<Marble>(){
		{
			add(new Marble(9, 5, true));
			add(new Marble(9, 5, false));
			add(new Marble(9, 6, true));
			add(new Marble(9, 6, false));
			add(new Marble(9, 7, true));
			add(new Marble(9, 7, false));
			add(new Marble(9, 8, true));
			add(new Marble(9, 8, false));
			add(new Marble(9, 9, true));
			add(new Marble(9, 9, false));
			add(new Marble(8, 9, true));
			add(new Marble(8, 9, false));
			add(new Marble(7, 9, true));
			add(new Marble(7, 9, false));
			add(new Marble(6, 9, true));
			add(new Marble(6, 9, false));
			add(new Marble(5, 9, true));
			add(new Marble(5, 9, false));
			add(new Marble(4, 8, true));
			add(new Marble(4, 8, false));
			add(new Marble(3, 7, true));
			add(new Marble(3, 7, false));
			add(new Marble(2, 6, true));
			add(new Marble(2, 6, false));
			add(new Marble(1, 5, true));
			add(new Marble(1, 5, false));
			add(new Marble(1, 4, true));
			add(new Marble(1, 4, false));
			add(new Marble(1, 3, true));
			add(new Marble(1, 3, false));
			add(new Marble(1, 2, true));
			add(new Marble(1, 2, false));
			add(new Marble(1, 1, true));
			add(new Marble(1, 1, false));
			add(new Marble(2, 1, true));
			add(new Marble(2, 1, false));
			add(new Marble(3, 1, true));
			add(new Marble(3, 1, false));
			add(new Marble(4, 1, true));
			add(new Marble(4, 1, false));
			add(new Marble(5, 1, true));
			add(new Marble(5, 1, false));
			add(new Marble(6, 2, true));
			add(new Marble(6, 2, false));
			add(new Marble(7, 2, true));
			add(new Marble(7, 2, false));
			add(new Marble(8, 2, true));
			add(new Marble(8, 2, false));
		}
	};

    /**
     * This method is responsible for generating a list of possible moves given the current board state
     * @return
     */
    public static ArrayList<Move> genPossibleMoves(Game g, boolean aiIsBlack){
        ArrayList<Move> moves = new ArrayList<Move>();
        Board currentBoard = Board.copyBoard(g.getBoard());
        Board ownMarbles = new Board();

        for (Marble m: currentBoard) {
            if (m.isBlack() == aiIsBlack) {
                ownMarbles.add(m);
            }
        }

        for(Marble m : ownMarbles){
            for(int i = DIRECTION_MIN; i <= DIRECTION_MAX; ++i){
                Move move = generateMove(g, m, i);
                if (move != null) {
                    //move.evaluate(g.getBoard());
                    moves.add(move);
                }
            }
        }

        while(!ownMarbles.isEmpty()){
            Marble m = ownMarbles.pollFirst();
            for(Marble o : ownMarbles){
                //if(!m.equals(o)){
                for(int i = DIRECTION_MIN; i <= DIRECTION_MAX; ++i){
                    Move move = generateMove(g, m, o, i);
                    if (move != null) {
                        //move.evaluate(g.getBoard());
                        //System.out.println("before: " + move.toString());
                        moves.add(move);
                        //System.out.println("after: " + move.toString());
                    }
                }
                //}
            }
        }

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
    
    public static int isAdjacent(Marble m1, Marble adjMarble, Board b, int x, int y) {
        
        char color1 = m1.getColor();
        char color2 = adjMarble.getColor();
        Board newBoard = Board.copyBoard(b);
        
        if (color1 != color2) {
            return 0;
        }
        
        int number = 1;
    
        Marble inlineMarble = Game.searchBoard(newBoard, adjMarble.getAlpha() + x, adjMarble.getNumeric() + y);
//        if (inlineMarble == null || inlineMarble.getColor() != adjMarble.getColor()) {
//            return 1;
//        } else {
//            return 2;
//        }
//        
        while (inlineMarble != null && inlineMarble.getColor() == adjMarble.getColor()) {
            number++;
            adjMarble = inlineMarble;
            inlineMarble = Game.searchBoard(newBoard, adjMarble.getAlpha() + x, adjMarble.getNumeric() + y);
        }
        
        return number;  
    }
    
    public static int isLine(Marble marble, Board b) {
        
        int num = 0;
            
        for (int i = DIRECTION_MIN; i <= DIRECTION_MAX; i++) {
            Marble adjMar = Game.checkAdjacent(b, marble, i);
            while (adjMar != null && adjMar.getColor() == marble.getColor()) {
                num++;
                adjMar = Game.checkAdjacent(b, adjMar, i);
            }
        }
            
//        System.out.println(marble.toString());
//        System.out.println(num);
    
        return num;
    }

    public static int distanceFromCenter(Marble m) {
		if(RING1.contains(m)) {
			return 1;
		} else if(RING2.contains(m)) {
			return 2;
		} else if(RING3.contains(m)) {
			return 3;
		} else if(RING4.contains(m)) {
			return 4;
		} else
			return 5;
	}

	private static int getUtilityDefensiveCenter(Marble m) {
		int centerDistance = distanceFromCenter(m);


		switch(centerDistance) {
		case 1: 
			return 50;
		case 2:
			return 40;
		case 3:
			return 30;
		case 4:
			return 20;
		case 5:
			return 3;
		}
		return 0;
	}
	
	private static int getUtilityOffensiveCenter(Marble m) {
		int centerDistance = distanceFromCenter(m);


		switch(centerDistance) {
		case 1: 
			return 50;
		case 2:
			return 38;
		case 3:
			return 35;
		case 4:
			return 25;
		case 5:
			return 20;
		}
		return 0;
	}
	
	private static int getUtilityDefensiveKO(boolean aiIsBlack) {
		
		int blackMarbles = 14 - g.getWhiteScore();
		int whiteMarbles = 14 - g.getBlackScore();

		if(aiIsBlack) {
			switch(blackMarbles) {
			case 13:
				return 70;
			case 12:
				return 50;
			case 11:
				return 40;
			case 10:
				return 50;
			case 9: 
				return 60;
			case 8:
				return 100;
			}
		}
		else {
			switch(blackMarbles) {
			case 13:
				return 70;
			case 12:
				return 50;
			case 11:
				return 40;
			case 10:
				return 50;
			case 9: 
				return 60;
			case 8:
				return 100;
			}
		}
		return 0;
	}

	private static int getUtilityOffensiveKO(boolean aiIsBlack) {
		
		int blackMarbles = 14 - g.getWhiteScore();
		int whiteMarbles = 14 - g.getBlackScore();

		if (aiIsBlack) {
			switch(blackMarbles) {
			case 13:
				return 85;
			case 12:
				return 70;
			case 11:
				return 60;
			case 10:
				return 65;
			case 9: 
				return 70;
			case 8:
				return 100;
			}
		}
		else {
			switch(blackMarbles) {
			case 13:
				return 85;
			case 12:
				return 70;
			case 11:
				return 60;
			case 10:
				return 65;
			case 9: 
				return 70;
			case 8:
				return 100;
			}
		}
		return 0;
	}
	/**
	 * This method is responsible for returning a board state's evaluation based on the state of its marbles and the AI's colour
	 * @param b the current board state
	 * @param aiIsBlack true if the AI is playing black and false if it is playing white
	 * @return the board state's evaluation
	 */
	public static double evaluateBoard(Board b, boolean aiIsBlack) {
		int total = 0;
		int centreDistance = 0;
		double utilValBlack= 0.0;
		double utilValWhite = 0.0;
		boolean isOnOffensive = true;
		Board testBoard = Board.copyBoard(b);
		int blobTotal = 0;
		int adjTotal = 0;
		int whiteScore = g.getWhiteScore();
		int blackScore = g.getBlackScore();
		int scoreDiff = 0;
		
		// If AI is black, score has to be evaluated differently
		if(aiIsBlack) {
			scoreDiff = blackScore - whiteScore;
			if(scoreDiff == 0 || scoreDiff <= -2 || scoreDiff == 1) {
				isOnOffensive = true;
			} else {
				isOnOffensive = false;
			}
		} else {
			scoreDiff = whiteScore - blackScore;
			if(scoreDiff == 0 || scoreDiff <= -2 || scoreDiff == 1) {
				isOnOffensive = true;
			} else {
				isOnOffensive = false;
			}
		}

		if(!isOnOffensive) {
			
			for(Marble m : b) {
				//System.out.println(adjTotal);
			    adjTotal += isLine(m, testBoard);

				if(m.isBlack()) {
					utilValBlack += getUtilityDefensiveCenter(m);
				}
				else {
					utilValWhite += getUtilityDefensiveCenter(m);
				}
			}

			//System.out.println("----------FOR DEFENSIVE--------------------------------------" + (utilValBlack) + ": "+ ( utilValWhite) + ": " + getUtilityDefensiveKO(aiIsBlack));
			return ((utilValBlack - utilValWhite) + getUtilityDefensiveKO(aiIsBlack));
		} else {
			
			for(Marble m : b) {
				//System.out.println(adjTotal);
			    adjTotal += isLine(m, testBoard);

				if(m.isBlack()) {
					utilValBlack += getUtilityOffensiveCenter(m);
				}
				else {
					utilValWhite += getUtilityOffensiveCenter(m);
				}
			}
			
			//System.out.println("-----------FOR OFFENSIVE--------------------------------------" + (utilValBlack) + ": "+ ( utilValWhite) + ": " + getUtilityOffensiveKO(aiIsBlack));
			return ((utilValWhite - utilValBlack) +  getUtilityOffensiveKO(aiIsBlack));
		}
	}
        
//    /**
//     * This method is responsible for returning a board state's evaluation based on the state of its marbles and the AI's colour
//     * @param b the current board state
//     * @param aiIsBlack true if the AI is playing black and false if it is playing white
//     * @return the board state's evaluation
//     */
//    public static double evaluateBoard(Board b, boolean aiIsBlack){
//        double eval = 0;
//
//        // variables to modify for evaluation purposes
//        double ownMarbleVal = 1.0;
//        double oppMarbleVal = 1.0;
//
//        double centerMod = 2.0;
//        double ring1Mod = 1.5;
//        double ring2Mod = 1.0;
//        double ring3Mod = 0.75;
//        double ring4Mod = 0.5;
//
//        int dist;
//        double posMod = 1.0;
//
//        double firstKO = 10.0;
//        double secondKO = 20.0;
//        double thirdKO = 40.0;
//        double fourthKO = 70.0;
//        double fifthKO = 150.0;
//        double sixthKO = 1000.0;
//        
//        double hexBonus = 50.0;
//
//        // counter for enemy marbles still in play, could get this from game score;
//        // but I don't know if this method needs to be passed the whole game
//        int oppMarbles = 0;
//
//        for(Marble m : b){ // checks each marble present on the board
//            dist = AIPlayer.distanceFromCenter(m);
//            switch (dist){
//            case 0: posMod = centerMod;
//            break;
//            case 1: posMod = ring1Mod;
//            break;
//            case 2: posMod = ring2Mod;
//            break;
//            case 3: posMod = ring3Mod;
//            break;
//            case 4: posMod = ring4Mod;
//            break;
//            }
//
//            if(m.isBlack() == aiIsBlack){ // do positive things for friendly marbles
//                eval += (ownMarbleVal * posMod);
//                if(isHexCluster(b, m)){
//                    eval += hexBonus;
//                }
//
//            } else { // do negative things for opposing marbles
//                if(posMod <= 3){
//                    eval -= (oppMarbleVal * posMod);
//                } else {
//                    eval += (oppMarbleVal * posMod);
//                }
//                if(isHexCluster(b, m)){
//                    eval -= hexBonus;
//                }
//
//                ++oppMarbles;
//            }
//        }
//
//        if(oppMarbles < 14){ // at least one marble knocked out
//            eval += firstKO;
//        }
//        if(oppMarbles < 13){ // at least two marbles knocked out
//            eval += secondKO;
//        }
//        if(oppMarbles < 12){ // at least three marbles knocked out
//            eval += thirdKO;
//        }
//        if(oppMarbles < 11){ // at least four marbles knocked out
//            eval += fourthKO;
//        }
//        if(oppMarbles < 10){ // at least five marbles knocked out
//            eval += fifthKO;
//        }
//        if(oppMarbles < 9){ // six marbles knocked out a.k.a. victory state
//            eval += sixthKO;
//        }
//
//        //System.out.println(eval);
//        return eval;
//    }
    
    public static boolean isHexCluster(Board b, Marble center){
        boolean surrounded = true;
        
        for(int i = DIRECTION_MIN; i <= DIRECTION_MAX; ++i){
            if(Game.checkAdjacent(b, center, i) == null || Game.checkAdjacent(b, center, i).isBlack() != center.isBlack()){
                surrounded = false;
            }
        }
        
        return surrounded;
    }

    public static Move alphaBetaSearch(Game game, boolean aiIsBlack, int maxDepth, long totalTime) {
        Game g = new Game(game);
        int depth = 0;
        long startTime = System.nanoTime();
        long timeTaken = 0;
        int movesMade = aiIsBlack ? g.getBlackMoves().size() : g.getWhiteMoves().size();
        Move bestMove = null;
        ArrayList<Move> moves = AIPlayer.genPossibleMoves(g, aiIsBlack);
        Collections.sort(moves, new MoveComparator());
        bestMove = moves.get(0);
        
        while (movesMade <= g.getAiMoveLimit() && timeTaken < g.getAiTimeLimit() && depth != maxDepth) {

            ++depth;
            double v = maxMove(g.getBoard(), aiIsBlack, -Double.MAX_VALUE, Double.MAX_VALUE, depth, maxDepth);
            
            for(Move m : moves){
                if(m.getEval() == v){
                    bestMove = m;
                }

                long newTime = timeTaken + totalTime;
                bestMove.setTime(newTime);
            }

            //System.out.println(bestMove.toString());
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

        double currentEval = evaluateBoard(current, aiIsBlack);
        if(currentEval >= 100.0 || depth > maxDepth) { // replace true with terminal test
            return currentEval;
        }
        depth++;
        
        /* Turn time limit should go here as terminal test */
        //System.out.println("Entered maxMove: " + currentEval);
        double maxEval = -Double.MAX_VALUE;
        
        Game dummy = new Game(current, aiIsBlack);
        ArrayList<Move> moves = AIPlayer.genPossibleMoves(dummy, aiIsBlack);
        Collections.sort(moves, new MoveComparator());
        
        //double maxValue = -Double.MAX_VALUE;

        for(int i = 0; i < moves.size(); ++i){
            // pulled out of Math.max function for re-use
            double minEval = AIPlayer.minMove(genResultState(current, moves.get(i)), aiIsBlack, a, B, depth, maxDepth);

            // straight from pseudocode
            maxEval = Math.max(maxEval, minEval);
            if(maxEval >= B){
                //System.out.println(moves.get(i).toString());
                return maxEval;
            }

            a = Math.max(a, maxEval);
        }

        //System.out.println(moves.get(maxIndex).toString());
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
    public static double minMove(Board current, boolean aiIsBlack, double a, double B, int depth, int maxDepth){
        
        double currentEval = evaluateBoard(current, aiIsBlack);
        if(currentEval >= 1000.0 || depth >= maxDepth){ // replace true with terminal test
            return currentEval;
        }
        depth++;

        //System.out.println("Entered minMove: " + currentEval);
        double minEval = Double.MAX_VALUE;
        Game dummy = new Game(current, aiIsBlack);
        ArrayList<Move> moves = AIPlayer.genPossibleMoves(dummy, !aiIsBlack);

        for(Move m : moves){
            m.setEval(current, aiIsBlack);
        }
        
        Collections.sort(moves, new MoveComparator().reversed());

        //double minValue = 0.0;

        for(int i = 0; i < moves.size(); ++i){
            // pulled out of Math.min function for re-use
            double maxEval = AIPlayer.maxMove(genResultState(current, moves.get(i)), aiIsBlack, a, B, depth, maxDepth);

            // straight from pseudocode
            minEval = Math.min(minEval, maxEval);
            if(minEval <= a){
               //System.out.println(moves.get(i).toString());
                return minEval;
            }

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
