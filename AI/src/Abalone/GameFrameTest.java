package Abalone;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class GameFrameTest extends JFrame {
    
    /**
     * Generated serial UID.
     */
    private static final long serialVersionUID = 2986344142823166606L;

    // JPanel that contains the player boards
    private JPanel players;
    
    // JPanel that contains the game board and options
    private JPanel game;
    
    // JPanels that holds player statuses
    private JPanel whiteMarbles;
    private JPanel blackMarbles;
    
    // JPanel for the Abalone game board
    private JPanel gameBoard;
    
    // JPanel for the game options (start, reset, etc)
    private JPanel options;
    
    // JPanel buttons in options
    private JPanel optionButtons;
    
    private JPanel playerInfo;
    
    // Buttons for options
    private JButton start;
    private JButton stop;
    private JButton reset;
    private JButton pause;
    
    private JLabel whiteTurnTime;
    private JLabel blackTurnTime;
    private JLabel blackMarblePlayer;
    private JLabel whiteMarblePlayer;
    private JLabel gameTime;
    private JLabel whiteTotalTurnTime; 
    private JLabel blackTotalTurnTime;
    
    /**
     * Constructor that creates the initial state of the board.
     * Populates the JFrame.
     */
    public GameFrameTest() {
        setTitle("Abalone");
        
        this.setLayout(new BorderLayout());
        this.add(createGamePanel(), BorderLayout.CENTER);
        this.add(createPlayerPanel(), BorderLayout.WEST);
       
        // For testing proposes
        gameBoard.setBorder(BorderFactory.createLineBorder(Color.black));
        options.setBorder(BorderFactory.createLineBorder(Color.black));
    }
    
    /**
     * 
     * @return the players JPanel with all the swing elements
     */
    private JPanel createPlayerPanel() {
        
        players = new JPanel();
        players.setLayout(new BoxLayout(players, BoxLayout.PAGE_AXIS));
        players.add(createMarblePanel(whiteMarbles, whiteMarblePlayer,
                whiteTurnTime, whiteTotalTurnTime));
        
        players.add(createMarblePanel(blackMarbles, blackMarblePlayer,
                blackTurnTime, blackTotalTurnTime));
        return players;
    }
    
    /**
     * 
     * @return the game JPanel with all the swing elements
     */
    private JPanel createGamePanel() {
        gameBoard = new JPanel();
        gameBoard.setPreferredSize(new Dimension(300, 600));
        
        options = new JPanel();
        options.setLayout(new BorderLayout());
        
        gameTime = new JLabel();
        gameTime.setText("Total game time: ");
        gameTime.setFont(new Font("DIALOG_INPUT", Font.PLAIN, 16));
        options.add(gameTime, BorderLayout.NORTH);
        
        start = new JButton();
        start.setText("Start");
        
        stop = new JButton();
        stop.setText("Stop");
        
        reset = new JButton();
        reset.setText("Reset");
        
        pause = new JButton();
        pause.setText("Pause");
        
        optionButtons = new JPanel();
        options.add(optionButtons, BorderLayout.SOUTH);
        
        optionButtons.add(start);
        optionButtons.add(stop);
        optionButtons.add(reset);
        optionButtons.add(pause);
        
        game = new JPanel();
        game.setLayout(new BoxLayout(game, BoxLayout.PAGE_AXIS));
        game.add(gameBoard);
        game.add(options);
        return game;
    }
    
    private JPanel createMarblePanel(JPanel panel, JLabel teamLabel, 
            JLabel turnTime, JLabel totalTurnTime) {
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(300, 300));
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        
        teamLabel = new JLabel();
        teamLabel.setText("Team White: ");
        teamLabel.setFont(new Font("DIALOG_INPUT", Font.PLAIN, 16));
        panel.add(teamLabel, BorderLayout.NORTH);
        
        playerInfo = new JPanel();
        playerInfo.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(playerInfo, BorderLayout.SOUTH);
        
        turnTime = new JLabel();
        turnTime.setText("Turn Time: ");
        turnTime.setFont(new Font("DIALOG_INPUT", Font.PLAIN, 16));
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.5;
        playerInfo.add(turnTime, c);
        
        totalTurnTime = new JLabel();
        totalTurnTime.setText("Total Turn Time: ");
        totalTurnTime.setFont(new Font("DIALOG_INPUT", Font.PLAIN, 16));
        c.gridx = 0;
        c.gridy = 1;
        c.weighty = 0.5;
        playerInfo.add(totalTurnTime, c);
        
        return panel;
    }
}
