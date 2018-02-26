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
import javax.swing.JPanel;

/**
 * @author Mike
 *
 */
public class BoardPanel extends JPanel {

    private ArrayList<Space> spaceList;
    private ArrayList<Marble> marbles;
    private ArrayList<DrawMarble> drawn;  

    private static Marble m1;
    private static Marble m2;
    private static Marble m3;
    private static int direction;

    public BoardPanel(ArrayList<Marble> board){
        this.marbles = board;  
        this.spaceList = new ArrayList<Space>();
        initSpaces();
        this.drawn = new ArrayList<DrawMarble>();
        initMarbles();
        m1 = null;
        m2 = null;
        m3 = null;

        // listener for spaces
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                super.mouseClicked(me);

                for(Space s : spaceList){
                    if(s.contains(me.getPoint())){
                        System.out.println(s.toString());
                        
                        if(s != null && m1 != null && m2 != null){
                            int alphaDiff = m1.getAlpha() - s.getAlpha();
                            int numDiff = m1.getNumeric() - s.getNum();
                            direction = 0;
                            
                            System.out.println(m1.toString());
                            System.out.println(alphaDiff);
                            System.out.println(numDiff);

                            if(alphaDiff == 0){
                                if(numDiff < 0){
                                    direction = 3;
                                } else {
                                    direction = 6;
                                }
                            } else if(numDiff == 0){
                                if(alphaDiff < 0){
                                    direction = 4;
                                } else {
                                    direction = 1;
                                }
                            } else if(alphaDiff == numDiff){
                                if(alphaDiff < 0){
                                    direction = 5;
                                } else {
                                    direction = 2;
                                }
                            }
                            
                            System.out.println(direction);

                            if(direction != 0){
                                Gui.moveMarbles(TestDriver.game, m1.isBlack(), m1, m2, direction);
                                
                                // INSERT BOARD REDRAW HERE
                                
                                m1 = null;
                                m2 = null;
                                m3 = null;
                                direction = 0;
                            }
                        }
                    }
                }
            }
        });


        // listener for marbles
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                super.mouseClicked(me);

                for (DrawMarble d : drawn) {

                    if (d.contains(me.getPoint())) {//check if mouse is clicked within shape

                        if(m1 == null){
                            m1 = d.getMarble();
                        } else if(m2 == null) {
                            m2 = d.getMarble();
                        } else {
                            for(Space s : spaceList){
                                if(s.contains(me.getPoint())){
                                    Marble dir = TestDriver.game.searchBoard(s.getAlpha(), s.getNum());


                                    if(dir != null){
                                        int alphaDiff = m1.getAlpha() - dir.getAlpha();
                                        int numDiff = m1.getNumeric() - dir.getNumeric();
                                        int direction = 0;

                                        if(alphaDiff == 0){
                                            if(numDiff < 0){
                                                direction = 3;
                                            } else {
                                                direction = 6;
                                            }
                                        } else if(numDiff == 0){
                                            if(alphaDiff < 0){
                                                direction = 4;
                                            } else {
                                                direction = 1;
                                            }
                                        } else if(alphaDiff == numDiff){
                                            if(alphaDiff < 0){
                                                direction = 5;
                                            } else {
                                                direction = 2;
                                            }
                                        }

                                        if(direction != 0){

                                        }

                                    }
                                }
                            }

                        }
                        System.out.println(m1.toString());
                        if(m2 != null){
                            System.out.println(m2.toString());
                        }
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
