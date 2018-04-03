/**
 * 
 */
package game;

import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

/**
 * @author Mike
 *
 */
@SuppressWarnings("serial")
public class DrawMarble extends Ellipse2D.Double {

    private Marble marble;
    private boolean highlight;
    
    public DrawMarble(Marble m, Space s){
        super(s.getX(), s.getY(), 65, 65);
        this.marble = m;
    }
    
    public Marble getMarble(){
        return this.marble;
    }
    
    public void updatePosition(Space s){
        super.x = s.getX();
        super.y = s.getY();
        this.marble.setAlpha(s.getAlpha());
        this.marble.setNumeric(s.getNum());
    }
    
    public void setHighlight(boolean val) {
    	this.highlight = val;
    }
    
    public boolean getHighlight() {
    	return this.highlight;
    }
    
}
