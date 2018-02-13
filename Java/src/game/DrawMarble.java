/**
 * 
 */
package game;

import java.awt.geom.Ellipse2D;

/**
 * @author Mike
 *
 */
public class DrawMarble extends Ellipse2D.Double {

    private Marble marble;
    
    public DrawMarble(Marble m, Space s){
        super(s.getX(), s.getY(), 65, 65);
        this.marble = m;
    }
    
    public Marble getMarble(){
        return this.marble;
    }
    
}
