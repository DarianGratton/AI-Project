package game;

import java.util.Comparator;

public class MoveComparator implements Comparator<Move>{

    @Override
    public int compare(Move m1, Move m2) {
        if(m1.getEval() > m2.getEval()){
            return -1;
        } if(m1.getEval() == m2.getEval()){
            return 0;
        } 
        return 1;
    }
    

}
