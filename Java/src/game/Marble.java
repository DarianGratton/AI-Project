

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
    public Marble(int alpha, int numeric) {
        this.alpha = alpha;
        this.numeric = numeric;
        this.isActive = true;
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
        if(this.isBlack()){
            return 'b';
        } else {
            return 'w';
        }
    }
    
    public int intColour(){
        return (this.isBlack) ? 1 : 0;
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
        StringBuilder sb = new StringBuilder();
        sb.append(this.getColor());
        sb.append(this.alpha);
        sb.append(this.numeric);
        return sb.toString();
    }


    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + alpha;
        result = prime * result + (isActive ? 1231 : 1237);
        result = prime * result + (isBlack ? 1231 : 1237);
        result = prime * result + numeric;
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Marble other = (Marble) obj;
        if (alpha != other.alpha)
            return false;
        if (isActive != other.isActive)
            return false;
        if (isBlack != other.isBlack)
            return false;
        if (numeric != other.numeric)
            return false;
        return true;
    }
}
