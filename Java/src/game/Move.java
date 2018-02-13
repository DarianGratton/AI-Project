package game;
import java.util.ArrayList;

/**
 * 
 */

/**
 * @author Mike
 *
 */
public class Move {
    
    private ArrayList<Marble> movedList;
    private int direction;
    private long time;
    
    /**
     * @param moved
     * @param movedList
     * @param direction
     */
    public Move(Marble moved, int direction) {
        this.movedList = new ArrayList<Marble>();
        this.movedList.add(moved);
        this.direction = direction;
    }
    
    /**
     * @param movedList
     * @param direction
     */
    public Move(Marble m1, Marble m2, int direction) {
        this.movedList = new ArrayList<Marble>();
        this.movedList.add(m1);
        this.movedList.add(m2);
        this.direction = direction;
    }


    /**
     * @return the movedList
     */
    public ArrayList<Marble> getMovedList() {
        return movedList;
    }

    /**
     * @param movedList the movedList to set
     */
    public void setMovedList(ArrayList<Marble> movedList) {
        this.movedList = movedList;
    }

    /**
     * @return the direction
     */
    public int getDirection() {
        return direction;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }

    /**
     * @return the time
     */
    public long getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(long time) {
        this.time = time;
    }   
    
    /**
     * @param moved the marble to add to the list
     */
    public void addMovedToList(Marble moved){
        this.movedList.add(moved);
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("Move [");
        
        for(Marble m: movedList) {
            sb.append("Marble [alpha=" + m.getAlpha() + ", numeric=" + m.getNumeric() + "] ");
        }
        
        sb.append("direction=" + direction + ", time=" + time + "]");
        
        return sb.toString();
    }
}
