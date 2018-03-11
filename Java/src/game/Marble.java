

package game;
/**
 * 
 */

/**
 * @author Mike
 *
 */
public class Marble {

    // position along lettered axis
    private int alpha;
    
    // position along numbered axis
    private int numeric;
    
    //Color = b if marble is black
    // Else color = w
    private char color;
    
    // default to true, set to false when knocked out
    private boolean isActive;
    
    // quicker than using ints to assign ownership; this may not be required depending on final state
    private boolean isBlack;
    
    
    /**
     * @param isBlack
     */
    public Marble(int alpha, int numeric, boolean isBlack) {
        this.alpha = alpha;
        this.numeric = numeric;
        this.isActive = true;
        this.isBlack = isBlack;
    }
    
    public Marble(Marble m){
        this.alpha = m.getAlpha();
        this.numeric = m.getNumeric();
        this.isActive = m.isActive();
        this.isBlack = m.isBlack();
    }
    
    /**
     * @return the alpha
     */
    public int getAlpha() {
        return alpha;
    }

    /**
     * @param alpha the alpha to set
     */
    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    /**
     * @return the numeric
     */
    public int getNumeric() {
        return numeric;
    }

    /**
     * @param numeric the numeric to set
     */
    public void setNumeric(int numeric) {
        this.numeric = numeric;
    }

    /**
     * @return the isActive
     */
    public boolean isActive() {
        return isActive;
    }


    /**
     * @param isActive the isActive to set
     */
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }


    /**
     * @return the isBlack
     */
    public boolean isBlack() {
        return isBlack;
    }


    /**
     *  Return b if color of the marble is black
     *  otherwise returns white
     * @return color
     */
    public char getColor() {
    	if(isBlack())
    		color = 'b';
    	else
    		color = 'w';
    	return color;
    }
    /**
     * @param isBlack the isBlack to set
     */
    public void setBlack(boolean isBlack) {
        this.isBlack = isBlack;
    }
    
    /**
     * @param direction the direction to move the marble, numbered 
     * clockwise starting from the top-left
     */
    public void changePos(int direction){
        switch (direction) {
            // 1: top-left
            case 1: this.alpha += 1;
                    break;
            // 2: top-right
            case 2: this.alpha += 1;
                    this.numeric += 1;
                    break;
            // 3: right
            case 3: this.numeric += 1;
                    break;
            // 4: bottom-right
            case 4: this.alpha -= 1;
                    break;
            // 5: bottom-left
            case 5: this.alpha -= 1;
                    this.numeric -= 1;
                    break;
            // 6: left
            case 6: this.numeric -= 1;
                    break;     
        }
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Marble [Color=" + getColor() + "alpha=" + alpha + ", numeric=" + numeric + ", isActive=" + isActive +  "]";
    }
    

}
