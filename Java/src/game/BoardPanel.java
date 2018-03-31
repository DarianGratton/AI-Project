/**
 * 
 */
package game;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * @author Mike
 *
 */
@SuppressWarnings("serial")
public class BoardPanel extends JPanel {

    private static final int ASCII = 64;
    
    private ArrayList<Space> spaceList;
    private ArrayList<DrawMarble> drawn;  

    private Marble m1;
    private Marble m2;
    private Marble m3;
    private int direction;
    private boolean marbleClicked;
    private Game game;
    private Board b;
    private GameFrame frame;
    private MarblePanel blackMarblePanel;					//AH
    private MarblePanel whiteMarblePanel;					//AH
    private GameTimer blackTurnTimer;						//AH
    private GameTimer whiteTurnTimer;						//AH
    
    public BoardPanel(Game g, GameFrame frame){
        this.game = g;
        this.frame = frame;
        this.spaceList = new ArrayList<Space>();
        initSpaces();
        this.drawn = new ArrayList<DrawMarble>();
        this.b = g.getBoard();
        drawMarbles(b);
        marbleClicked = false;
        m1 = null;
        m2 = null;
        m3 = null;
        direction = 0;
        setLayout(null);
        blackMarblePanel 	= frame.getBlackMarblePanel();				//AH
        whiteMarblePanel 	= frame.getWhiteMarblePanel();				//AH
        blackTurnTimer 		= blackMarblePanel.getTurnTimer();			//AH
        whiteTurnTimer		= whiteMarblePanel.getTurnTimer();			//AH
        addMouseListener(new MarbleListener());
        addMouseListener(new SpaceListener());
    }


    public void initSpaces(){
        int y = -40;
        int x = 0;
        int blank = 80;
        int c = 0;
        for(int j = 5; j <= 9; j++) {
            x = blank;
            y += 60;
            for(int i = 1; i <= j; i ++) {
                c = (x + blank - 160)/60;
                x += 60;
                spaceList.add(new Space(((20 - y)/60 + 9), c + 5, x, y));
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
                spaceList.add(new Space(((20 - y)/60 + 9), c + 17, x, y));

            }
            blank += 30;
        } 
        
        for (Space s : spaceList) {
            int alphaInt = s.getAlpha() + ASCII;
            char alphaChar = (char) alphaInt;
            String alpha   = Character.toString(alphaChar);
            String numeric = Integer.toString(s.getNum());
            int x1 = (int) s.getX();
            int y2 = (int) s.getY();
            
            JLabel coordinates = new JLabel(alpha + ", " + numeric);
            coordinates.setFont(new Font("SANS_SERIF", Font.PLAIN, 18));
            coordinates.setForeground(Color.ORANGE);
            add(coordinates);
            
            Insets insets = getInsets();
            Dimension size = coordinates.getPreferredSize();
            coordinates.setBounds(x1 + 18, y2 + 18, size.width, size.height);
        }
    }


    public void drawMarbles(Board b){
        this.drawn = new ArrayList<DrawMarble>();
        for(Marble m : b){
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
        setBackground(new Color(218, 222, 229));
        Graphics2D g2d = (Graphics2D) grphcs;
        g2d.setPaint(Color.GRAY);
        for (Space s : spaceList) {
            g2d.fill(s);
        }
        for (DrawMarble d : drawn){

            if(d.getMarble().isBlack()){
                g2d.setPaint(Color.BLACK);
            } else {
                g2d.setPaint(Color.WHITE);
            }
            
            g2d.draw(d);
            g2d.fill(d);
        }
    }

    public ArrayList<Space> getSpaceList(){
        return spaceList;
    }

    public Space getSpace(Marble m){
        for(Space s : this.spaceList){
            if(m.getAlpha() == s.getAlpha() && m.getNumeric() == s.getNum()){
                return s;
            }
        }
        return null;
    }
    
    public class MarbleListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            marbleClicked = false;

            if (game.isGameInSession()) {
                for (DrawMarble d : drawn) {
    
                    if (d.contains(mouseEvent.getPoint())) {//check if mouse is clicked within shape
    
                        if(m1 == null){
                            m1 = d.getMarble();
                            direction = 0;
                            marbleClicked = true;
                        } else if(m2 == null) {
                            m2 = d.getMarble();
                            direction = 0;
                            marbleClicked = true;
                        } else if(m3 == null){
                            m3 = d.getMarble();
                            marbleClicked = true;
                        } else {
                            marbleClicked = false;
                        }
                        
                        
    
    /*
                        if(m1 != null){
                            System.out.println(m1.toString());
                        }
                        if(m2 != null){
                            System.out.println(m2.toString());
                        }*/
                    }
                }
            }
        }

        @Override
        public void mouseEntered(MouseEvent arg0) {
 
        }

        @Override
        public void mouseExited(MouseEvent arg0) {

        }

        @Override
        public void mousePressed(MouseEvent arg0) {

        }

        @Override
        public void mouseReleased(MouseEvent arg0) {
            
        }
        
    }
    
    public class SpaceListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            
            if (game.isGameInSession()) {
                if(game.getTurnCount() == game.getAiMoveLimit() && game.getTurnCount() != 0)
                {
                    game.setGameInSession(false);
                    System.out.println("turnLimit reached");
                }
                for(Space s : spaceList){
                    if(s.contains(e.getPoint())){
                        //System.out.println(s.toString());


                        boolean currActiveTeam = game.activeIsBlack();

                        if((s != null && m1 != null && !marbleClicked) || (s != null && m3 != null)){
                            int alphaDiff = m1.getAlpha() - s.getAlpha();
                            int numDiff = m1.getNumeric() - s.getNum();
                            direction = 0;

                            if(alphaDiff == 0){
                                if(numDiff < 0){
                                    direction = 3;
                                } else {
                                    direction = 6;
                                }
                            } else if(numDiff == 0){
                                if(alphaDiff > 0){
                                    direction = 4;
                                } else {
                                    direction = 1;
                                }
                            } else if(alphaDiff == numDiff){
                                if(alphaDiff > 0){
                                    direction = 5;
                                } else {
                                    direction = 2;
                                }
                            }

                            if(direction != 0){
                                if(m2 == null){
                                    m2 = game.checkAdjacent(m1, direction);
                                }

                                // single marble move
                                if((m2 == null && Gui.moveMarbles(game, game.activeIsBlack(), m1, direction, blackTurnTimer, whiteTurnTimer))
                                        // double/triple marble move
                                        || (m2 != null) && Gui.moveMarbles(game, game.activeIsBlack(), m1, m2, direction, blackTurnTimer, whiteTurnTimer)){
                                	if(game.activeIsBlack()) {
                                		
                                		whiteTurnTimer.resetStopTimer();
                                		blackTurnTimer.startTimer();
                                	} else {
                                		blackTurnTimer.resetStopTimer();
                                		whiteTurnTimer.startTimer();
                                	}
                                    drawMarbles(b);
                                    repaint();
                                    frame.updateGameFrame(currActiveTeam);
                                    game.setTurnCount();
                                }
                            }

                            // reset variables
                            m1 = null;
                            m2 = null;
                            m3 = null;
                            direction = 0;
                        }
                    }
                }
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            
        }

        @Override
        public void mouseExited(MouseEvent e) {
            
        }

        @Override
        public void mousePressed(MouseEvent e) {
            
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            
        }
        
    }
}
