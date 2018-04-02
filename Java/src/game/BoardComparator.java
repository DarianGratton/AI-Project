package game;

import java.util.Comparator;

public class BoardComparator implements Comparator<Board>{

    @Override
    public int compare(Board b1, Board b2) {

        return (int) (b2.getEval() - b1.getEval());
    }

}
