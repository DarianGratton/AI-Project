
package game;

import java.util.TreeSet;

/**
 * @author Mike
 *
 */
@SuppressWarnings("serial")
public class Board extends TreeSet<Marble>{

    private static MarbleComparator mc = new MarbleComparator();
    
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
    
}
