
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
    
    public static Board copyBoard(Board b){
        Board nb = new Board();
        for(Marble m : b){
            nb.add(new Marble(m));
        }
        return nb;
    }
}
