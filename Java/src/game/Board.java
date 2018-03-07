
package game;

import java.util.ArrayList;

/**
 * @author Mike
 *
 */
@SuppressWarnings("serial")
public class Board extends ArrayList<Marble>{

    public Board(){
        
    }
    
    public Board (Board b){
        super(b);
    }
}
