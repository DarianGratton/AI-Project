
package game;

import java.util.TreeSet;

/**
 * @author Mike
 *
 */
@SuppressWarnings("serial")
public class Board extends TreeSet<Marble> {

    private static MarbleComparator mc = new MarbleComparator();
    
    private double aiEvaluation;
    
    public Board() {
        super(mc);
    }
    
    public static Board copyBoard(Board b){
        Board nb = new Board();
        for(Marble m : b){
            nb.add(new Marble(m));
        }
        return nb;
    }
    
    /*public static boolean checkIfSetContains(HashSet<Board> hash, Board board) {
        
        for (Board b : hash) {
            
            int numOfSameElements = 0;
            
            if (b.size() == board.size()) {
                
                for (int i = 0; i < b.size(); ++i) {
                    if (b.get(i).getAlpha() == board.get(i).getAlpha() && b.get(i).getNumeric() == board.get(i).getNumeric()) {
                        numOfSameElements++;
                    }
                }
                
                if (numOfSameElements == b.size()) {
                    return true;
                }
                
            }
        }
        
        return false;
    }*/
    
    public void evaluate(boolean aiIsBlack){
        this.aiEvaluation = AIPlayer.evaluateBoard(this, aiIsBlack);        
    }
    
    /**
     * NOTE: unless the move has been evaluated with the above method, this value defaults to 0.0
     * @return this move's utility value
     */
    public double getEval(){
        return this.aiEvaluation;
    }
    
}
