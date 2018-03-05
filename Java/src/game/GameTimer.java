package game;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 * <p>This class creates an Panel of an StopWatch, which has 3 JButtons
 * that start, pause, and reset the timer.</p>
 *
 * @author A00965803
 * @version 1.0
 */
public class GameTimer extends JLabel {
    
    /** serialVersionUID of this JPanel.*/
    private static final long serialVersionUID = 123489043809L;
    
    /** Width of the JPanel.*/
    private final int width = 500;
    
    /** Height of the JPanel.*/
    private final int height = 60;
    
    /** Time of seconds in the StopWatch. */
    private int seconds;
    
    /** Time of milliseconds in the StopWatch .*/
    private int millisec;
    
    /** Create an timer object. */
    private Timer timer;
    
    /** <p>Constructor for the StopWatchPanel, setting the position of 
     * the buttons and timer while setting up the ActionListener for
     * the timer when called.</p>*/
    public GameTimer() {
        final int delay = 100;
        timer = new Timer(delay, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                final int milmax = 9;
                final int secmax = 10;
                millisec++;
                if (millisec > milmax) {
                    millisec = 0;
                    seconds++;
                }
                String secs = Integer.toString(seconds);
                String mil = Integer.toString(millisec);
                if (seconds <= secmax) {
                    secs = "0" + secs;
                }
                setFont(new Font("DIALOG_INPUT", Font.PLAIN, 15));
                setText(seconds + "." + millisec);
            }
        });
    }
    
    
    /** 
     * Starts the timer.
     */
    public void startTimer() {
        timer.start();
    }
    
    /**
     * Stops the timer.
     */
    public void stopTimer() {
        timer.stop();
    }
    
    /**
     * Resets the timer but doesn't stop it.
     */
    public void resetTimer() {
        seconds = 0;
        millisec = 0;
        final int secmax = 10;
        String secs = Integer.toString(seconds);
        String mil = Integer.toString(millisec);
        if (seconds <= secmax) {
            secs = "0" + secs;
        }
        setText(seconds + "." + millisec);
    }

    /**
     * Gets the seconds of the timer.
     * 
     * @return the seconds
     */
    public int getSeconds() {
        return seconds;
    }

    /**
     * Gets the millisecs of the timer.
     * 
     * @return the millisec
     */
    public int getMillisec() {
        return millisec;
    }
}
