/**
 * 
 */
package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * @author Mike
 *
 */
public class BoardPanel extends JPanel {

    private ArrayList<Space> spaceList;
    private ArrayList<Marble> marbles;
    private ArrayList<DrawMarble> drawn;    

    public BoardPanel(ArrayList<Marble> board){
        this.marbles = board;  
        this.spaceList = new ArrayList<Space>();
        initSpaces();
        this.drawn = new ArrayList<DrawMarble>();
        initMarbles();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                super.mouseClicked(me);
                for (DrawMarble d : drawn) {

                    if (d.contains(me.getPoint())) {//check if mouse is clicked within shape

                        //we can either just print out the object class name
                        System.out.println("Clicked a "+d.getClass().getName());


                    }
                }
            }
        });
    }


    public void initSpaces(){
        int y = 0;
        int x = 0;
        int blank = 80;
        int c = 0;
        for(int j = 5; j <= 9; j++) {
            x = blank;
            y += 60;
            for(int i = 1; i <= j; i ++) {
                c = (x + blank - 160)/60;
                x += 60;
                spaceList.add(new Space(((60 - y)/60 + 9), c + 5, x, y));
            }
            blank -= 30;
        }
        c = 0;
        blank = -10;
        for(int j = 8; j >= 5; j--) {
            x = blank;
            y += 60;
            for(int i = 0; i < j; i ++) {
                c = (x - blank - 1000)/60;
                x += 60;
                spaceList.add(new Space(((60 - y)/60 + 9), c + 17, x, y));

            }
            blank += 30;
        }
    }


    public void initMarbles(){
        for(Marble m : marbles){
            for(Space s : spaceList){
                if(m.getAlpha() == s.getAlpha() && m.getNumeric() == s.getNum()){
                    DrawMarble d = new DrawMarble(m, s);
                    drawn.add(d);
                }
            }
        }
    }
    
    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        setBackground(Color.DARK_GRAY);
        Graphics2D g2d = (Graphics2D) grphcs;
        g2d.setPaint(Color.GRAY);
        for (Space s : spaceList) {
            g2d.fill(s);
        }
        for (DrawMarble d : drawn){
            System.out.println(d.toString());
            
            if(d.getMarble().isBlack()){
                g2d.setPaint(Color.BLACK);
            } else {
                g2d.setPaint(Color.WHITE);
            } 
            
            g2d.draw(d);
            g2d.fill(d);
        }
    }

    
}
