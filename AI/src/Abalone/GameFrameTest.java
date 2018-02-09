package Abalone;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
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
    
    // JPanel to hold Player info
    private JPanel playerInfo;
    
    // Button for starting the game
    private JButton start;
    
    // Button for stopping the game
    private JButton stop;
    
    // Button for resetting the game
    private JButton reset;
    
    // Button for pausing the game
    private JButton pause;
    
    // Label players current turn time
    private JLabel whiteTurnTime;
    private JLabel blackTurnTime;
    
    // Label for players side
    private JLabel blackMarblePlayer;
    private JLabel whiteMarblePlayer;
    
    // Label for total turn time of all turns taken by player
    private JLabel whiteTotalTurnTime; 
    private JLabel blackTotalTurnTime;
    
    private JLabel whiteNumMoves;
    private JLabel blackNumMoves;
    
    // Label for game time
    private JLabel gameTime;
    
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
                whiteTurnTime, whiteTotalTurnTime, whiteNumMoves));     
        players.add(createMarblePanel(blackMarbles, blackMarblePlayer,
                blackTurnTime, blackTotalTurnTime, blackNumMoves));
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
        gameTime.setFont(new Font("DIALOG_INPUT", Font.PLAIN, 15));
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
            JLabel turnTime, JLabel totalTurnTime, JLabel numTurns) {
        
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(300, 300));
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        
        teamLabel = new JLabel();
        teamLabel.setText("Team White: ");
        teamLabel.setFont(new Font("DIALOG_INPUT", Font.PLAIN, 15));
        panel.add(teamLabel, BorderLayout.NORTH);
        
        playerInfo = new JPanel();
        playerInfo.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(playerInfo, BorderLayout.SOUTH);
        
        turnTime = new JLabel();
        turnTime.setText("Turn Time: ");
        turnTime.setFont(new Font("DIALOG_INPUT", Font.PLAIN, 15));
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.5;
        playerInfo.add(turnTime, c);
        
        totalTurnTime = new JLabel();
        totalTurnTime.setText("Total Turn Time: ");
        totalTurnTime.setFont(new Font("DIALOG_INPUT", Font.PLAIN, 15));
        c.gridx = 0;
        c.gridy = 1;
        c.weighty = 0.5;
        playerInfo.add(totalTurnTime, c);
        
        numTurns = new JLabel();
        numTurns.setText("Total # of Moves: ");
        numTurns.setFont(new Font("DIALOG_INPUT", Font.PLAIN, 15));
        c.gridx = 0;
        c.gridy = 2;
        c.weighty = 0.5;
        playerInfo.add(numTurns, c);
        
        JButton history = new JButton();
        history.setText("Move History");
        c.gridx = 1;
        c.gridy = 2;
        playerInfo.add(history, c);
        
        return panel;
    }
    
    public void paint(Graphics g) {  
        super.paint(g);
        int y = 0;
        int x = 0;
        int space = 500;
        g.setColor(Color.GRAY);
        for(int j = 5; j <= 9; j++) {
            x = space;
            y += 60;
            for(int i = 0; i < j; i ++) {
                g.fillOval(x + 60, y, 65, 65);
                x += 60;
            }
            space -= 30;
        }
        space = 410;
        for(int j = 8; j >= 5; j--) {
            x = space;
            y += 60;
            for(int i = 0; i < j; i ++) {
                g.fillOval(x + 60, y, 65, 65);
                x += 60;
            }
            space += 30;
        }
        
    }

}
