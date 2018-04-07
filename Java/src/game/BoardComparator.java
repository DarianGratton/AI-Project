package game;

import java.util.Comparator;

public class BoardComparator implements Comparator<Board>{

    @Override
    public int compare(Board b1, Board b2) {
        if(b1.getEval() > b2.getEval()){
            return -1;
        } if(b1.getEval() == b2.getEval()){
            return 0;
        } 
        return 1;
    }

}
