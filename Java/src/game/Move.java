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
    private double time;
    private double eval; // evaluation function based on resulting board state
    
    public Move() {
        this.movedList = new ArrayList<Marble>();
        this.direction = 0;
        this.time = 0;
        this.eval = 0.0;
    }
    
    /**
     * @param moved
     * @param movedList
     * @param direction
     */
    public Move(Marble moved, int direction) {
        this.movedList = new ArrayList<Marble>();
        this.movedList.add(moved);
        this.direction = direction;
        this.eval = 0.0;
    }
    
    /**
     * @param moved
     * @param movedList
     * @param direction
     */
    public Move(Marble moved, int direction, long time) {
        this.movedList = new ArrayList<Marble>();
        this.movedList.add(moved);
        this.direction = direction;
        this.eval = 0.0;
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
        this.eval = 0.0;
    }
    
    /**
     * @param movedList
     * @param direction
     */
    public Move(Marble m1, Marble m2, int direction, long time) {
        this.movedList = new ArrayList<Marble>();
        this.movedList.add(m1);
        this.movedList.add(m2);
        this.direction = direction;
        this.eval = 0.0;
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
    public double getTime() {
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
    
    /**
     * this method generates a utility value with the evaluateBoard method and associates it with this move
     * DO NOT PASS NULL MOVES TO THIS METHOD
     * @param g the game in progress
     */
    public void evaluate(Board b){
        Board result = AIPlayer.genResultState(b, this);
        this.eval = AIPlayer.evaluateBoard(result, this.movedList.get(0).isBlack());        
    }
    
    /**
     * NOTE: unless the move has been evaluated with the above method, this value defaults to 0.0
     * @return this move's utility value
     */
    public double getEval(){
        return this.eval;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        /*if(this.movedList != null && this.movedList.get(0) != null){
            String colour = this.movedList.get(0).isBlack() ? "black" : "white";
            sb.append("Playing " + colour + ": [");
        }*/
        sb.append("[");
        
        for(Marble m: movedList) {
            sb.append(IODriver.getAlphaChar(m) + "" + m.getNumeric() +", ");        }

        sb.append("direction=" + direction +", ");
        //sb.append("\nTime: " + this.time);
        sb.append("val: " + this.eval);
        double secTime = this.time / 1000000000;
        sb.append("time: " + secTime);
        
        return sb.toString();
    }
}
