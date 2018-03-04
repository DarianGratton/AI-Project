package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * <p>This class creates an Panel of an StopWatch, which has 3 JButtons
 * that start, pause, and reset the timer.</p>
 *
 * @author A00965803
 * @version 1.0
 */
public class GameTimer {
    
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
    
    /** Label for the StopWatch time. */
    private JLabel timeLabel;
    
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
                System.out.println(seconds + "." + millisec);
                timeLabel.setText(seconds + "." + millisec);
            }
        });
    }
    
    public void startTimer() {
        timer.start();
    }
    
    public void stopTimer() {
        timer.start();
    }
    
    public void resetTimer() {
        seconds = 0;
        millisec = 0;
        final int secmax = 10;
        String secs = Integer.toString(seconds);
        String mil = Integer.toString(millisec);
        if (seconds <= secmax) {
            secs = "0" + secs;
        }
        System.out.println(seconds + "." + millisec);
        timeLabel.setText(seconds + "." + millisec);
    }
    
    public String toString() {
        return seconds + "." + millisec;
    }
}
