package game;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * 
 */

/**
 * @author Mike
 *
 */
public abstract class Gui {

	private static long turnStart;

	/**
	 * IDK if this needs to exist
	 */
	public static void startGame(){
		turnStart = System.nanoTime();
	}

	/**
	 * returns score for selected colour; i.e. number of enemy marbles knocked out
	 * @param g
	 * @param isBlack
	 * @return
	 */
	public static int displayScore(Game g, boolean isBlack){
		if(isBlack){
			return g.getBlackScore();
		} else {
			return g.getWhiteScore();
		}        
	}

	/**
	 * Method for moving inline; call THESE method from the GUI
	 * @param g the game in progress
	 * @param m1 the first/rear marble
	 * @param direction
	 * @param isBlack
	 */
	public static void moveMarbles(Game g, boolean playerIsBlack, Marble m1, int direction){
		// short circuit and only continue if the player is trying to move their own marble
		if(playerIsBlack == m1.isBlack()){
			// check to see if move is valid
			if(g.move(m1, direction)){
				g.addMoveToList(new Move(m1, direction, System.nanoTime() - turnStart));
				turnStart = System.nanoTime();
			}
		}
	}

	/**
	 * Method for moving broadside; call THESE method from the GUI
	 * @param g the game in progress
	 * @param m1
	 * @param m2
	 * @param direction
	 * @param isBlack
	 */
	public static void moveMarbles(Game g, boolean playerIsBlack, Marble m1, Marble m2, int direction, boolean isBlack){
		// short circuit and only continue if the player is trying to move their own marble
		if(playerIsBlack == isBlack){
			// check to see if move is valid
			if(g.move(m1, m2, direction)){
				g.addMoveToList(new Move(m1, m2, direction, System.nanoTime() - turnStart));
				turnStart = System.nanoTime();
			}
		}
	}

	public static void drawBoard(ArrayList<Marble> board, GameFrame frame, Graphics g){    
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				//g = frame.getGraphics();
				frame.paint(g);
				for(Marble m : board){
					drawMarble(m, frame, g);
				}
			}
			});
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void drawMarble(Marble m, GameFrame frame, Graphics g) {

		int alpha = m.getAlpha();
		int numeric = m.getNumeric();
		boolean isBlack = m.isBlack();

		ArrayList <Space> list = frame.getSpaceList();
		for(Space s : list) {
			if(s.getAlpha() == alpha && s.getNum() == numeric) {
				if(isBlack)
					g.setColor(Color.BLACK);
				else
					g.setColor(Color.WHITE);
				g.fillOval(s.getX(), s.getY(), 65, 65);
			}
			else {
				g.setColor(Color.BLACK);
				g.drawOval(s.getX(), s.getY(), 65, 65);
			}
				
		}

	}  
    public static void putMarblesInShit(ArrayList<Marble> marbles){
        char[][] board = new char[10][10];

        for(Marble m : marbles){
            if(m.isBlack()){
                board[m.getAlpha()][m.getNumeric()] = 'b';
            } else {
                board[m.getAlpha()][m.getNumeric()] = 'w';
            }
        }
        
        StringBuilder sb;
        for(int i = 9; i > 0; i--){
            for(int j = 1; j < 10; j++){
                sb = new StringBuilder();
                sb.append("[");
                sb.append(board[i][j]);
                sb.append("]");
                System.out.print(sb.toString());
            }
            System.out.println();
        }
    }
}
