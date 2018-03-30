package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 * <p>Timer class which is a JLabel, this shows the time.</p>
 *
 * @author A00965803
 * @version 1.0
 */
public class GameTimer extends JLabel {
    
    /** serialVersionUID of this JPanel.*/
    private static final long serialVersionUID = 123489043809L;
    
    /** Time of seconds in the StopWatch. */
    private int seconds;
    
    /** Time of milliseconds in the StopWatch .*/
    private int millisec;
    
    /** Create an timer object. */
    private Timer timer;
    
    /** Create time container for marblePabel font **/
    private int turnTimerFontSize;
    
  //Create a color object
    private Color foregroundColor;
    
    /** <p>Constructor for the StopWatchPanel, setting the position of 
     * the buttons and timer while setting up the ActionListener for
     * the timer when called.</p>*/
    public GameTimer(String timeLabel) {
        final int fontSizeTime = 15;
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
                if (seconds <= secmax) {
                    secs = "0" + secs;
                }
                setFont(new Font("SANS_SERIF", Font.PLAIN, fontSizeTime));
                setForeground(Color.BLACK);
                setText(timeLabel + seconds + "." + millisec);
            }
        });
    }
    
    /**
     * Creates a timer and adapts font color and size to those of its panel's
     * @param c , color of the font for the respective panel
     * @param size, size of the font for the respective panel
     */
    public GameTimer(Color c, int size) {											// AH   constructor to pass in color and size of text
        final int delay = 100;
        foregroundColor = c;
        turnTimerFontSize = size;
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
                if (seconds <= secmax) {
                    secs = "0" + secs;
                }
                Font font = new Font("DIALOG_INPUT", Font.PLAIN, size);
                setFont(font);
                setText("Turn Time: " + seconds + "." + millisec);
                setForeground(foregroundColor);
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
     * Stops timer and returns the values to 0.
     */
    public void resetStopTimer() {								//AH stops the timer, and resets timer to 0.
    	stopTimer();
    	resetTimer();
    }
    /**
     * Resets the timer but doesn't stop it.
     */
    public void resetTimer() {
        seconds = 0;
        millisec = 0;
        final int secmax = 10;
        String secs = Integer.toString(seconds);
        if (seconds <= secmax) {
            secs = "0" + secs;
        }
        Font font = new Font("DIALOG_INPUT", Font.PLAIN, turnTimerFontSize);
        setFont(font);
        setText("Turn Time: " + seconds + "." + millisec);
        setForeground(foregroundColor);
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
