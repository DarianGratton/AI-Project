
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import listeners.StartListener;

public class GameFrame extends JFrame {
    
    /**
     * Generated serial UID.
     */
    private static final long serialVersionUID = 2986344142823166606L;
    
    private Game gameG;
    private Move move;
    
    // For scrolling if JPanel in History gets to big
    private JScrollPane vertical;
    
    // List of all moves taken
    private ArrayList<String> moveList = new ArrayList<>(); 
    
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
    
    // JPanel to hold history of everything (moves, time)
    private JPanel museum;
    
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
    
    // Label for number of moves taken
    private JLabel whiteNumMoves;
    private JLabel blackNumMoves;
    
    /**
     * Constructor that creates the initial state of the board.
     * Populates the JFrame.
     */

    public GameFrame() {
        setTitle("Abalone");
        
        this.setLayout(new BorderLayout());
        this.add(createGamePanel(), BorderLayout.CENTER);
        this.add(createPlayerPanel(), BorderLayout.WEST);
        this.add(createMuseumPanel(), BorderLayout.EAST);
       
        // For testing proposes
        gameBoard.setBorder(BorderFactory.createLineBorder(Color.black));
        options.setBorder(BorderFactory.createLineBorder(Color.black));
    }
    
    /**
     * Creates a JPanel containing each marble team and their different
     * player info.
     * 
     * @return the players JPanel with all the swing elements
     */
    private JPanel createPlayerPanel() {
        
        players = new JPanel();
        players.setLayout(new BoxLayout(players, BoxLayout.PAGE_AXIS));
        
        players.add(createMarblePanel(whiteMarbles, whiteMarblePlayer,
                "Team White: ", whiteTurnTime, whiteTotalTurnTime, whiteNumMoves));
        
        players.add(createMarblePanel(blackMarbles, blackMarblePlayer,
                "Team Black: ", blackTurnTime, blackTotalTurnTime, blackNumMoves));
        
        return players;
    }
    
    /**
     * Creates a JPanel containing the game-board itself and options pertaining
     * to game-board. 
     * 
     * @return the game JPanel with all the swing elements
     */
    private JPanel createGamePanel() {
        gameBoard = new JPanel();
        gameBoard.setPreferredSize(new Dimension(300, 600));
        
        JPanel gameLabels = new JPanel();
        gameLabels.setLayout(new BoxLayout(gameLabels, BoxLayout.LINE_AXIS));
        
        options = new JPanel();
        options.setLayout(new BorderLayout());
        options.add(gameLabels, BorderLayout.NORTH);
        gameLabels.add(createLabel(new JLabel(), "Total game time: "));
        gameLabels.add(createLabel(new JLabel(), " Next Recommended Move: " /*+ gameG.getRecommended().toString()*/));
        
        start = createButton(start, "Start Game", new StartListener());
        stop  = createButton(stop, "Stop Game", null);
        reset = createButton(reset, "Reset Game", null);
        pause = createButton(pause, "Pause Game", null);
        
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
    
    private JPanel createMuseumPanel() {
        
        museum = new JPanel();
        museum.setLayout(new BoxLayout(museum, BoxLayout.PAGE_AXIS));
        museum.add(createHistoryPanel(new JPanel(), new JLabel("White Move History")));
        museum.add(createHistoryPanel(new JPanel(), new JLabel("Black Move History")));
        
        return museum;
    }    
    
    private JPanel createHistoryPanel(JPanel panel, JLabel label) {
        
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setPreferredSize(new Dimension(200, 300));
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        
        panel.add(label);
        JPanel console = new JPanel();
        
        vertical = new JScrollPane(console);
        vertical.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(vertical);
        
        for (int i = 0; i < move.getMovedList().size(); ++i) {
            move.getMovedList().get(i).toString();
        }           

        return panel;
    }
    
    /**
     * Creates the individual player cards with their stats and 
     * color.
     * 
     * @param panel Player Panel
     * @param teamLabel Team Color label
     * @param teamColor Team color
     * @param turnTime turn time taken per player
     * @param totalTurnTime sum of all turn time
     * @param numTurns number of Turns player has taken
     * @return
     */
    private JPanel createMarblePanel(JPanel panel, JLabel teamLabel, 
            String teamColor, JLabel turnTime, JLabel totalTurnTime, 
            JLabel numTurns) {
        
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(200, 300));
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        
        teamLabel = createLabel(teamLabel, teamColor);
        teamLabel.setBorder(new EmptyBorder( 0, 0, 20, 0));
        panel.add(teamLabel, BorderLayout.NORTH);
        
        playerInfo = new JPanel();
        panel.add(playerInfo, BorderLayout.CENTER);
        playerInfo.setLayout(new BoxLayout(playerInfo, BoxLayout.PAGE_AXIS));

        // Display game score
        playerInfo.add(createLabel(new JLabel(), "Total score: "));
        
        // Display number of moves taken per player
        playerInfo.add(createLabel(numTurns, "Total # of Moves: "));
        
        // Display time taken per move
        playerInfo.add(createLabel(turnTime, "Turn Time: "));
        
        // Sum of all the players turn times
        playerInfo.add(createLabel(totalTurnTime, "Total Turn Time: "));
               
        return panel;
    }
    
    /**
     * Creates a button and adds a listener
     * 
     * @param button
     * @param text
     * @param listener
     * @return
     */
    public JButton createButton(JButton button, String text, ActionListener listener) {
        button = new JButton(text);
        button.addActionListener(listener);
        
        return button; 
    }
    
    /**
     * Creates a JLabel that takes an label and sets it text to the 
     * inputed String parameter text. Sets the font and size.
     * 
     * @param label to display on the GUI
     * @param text to be added to the label
     * @return A JLabel with text
     */
    public JLabel createLabel(JLabel label, String text) {
        label = new JLabel();
        label.setText(text);
        label.setFont(new Font("DIALOG_INPUT", Font.PLAIN, 15));
        
        return label;
    }
    
    public void paint(Graphics g) {  
        super.paint(g);
        int y = 0;
        int x = 0;
        int space = 300;
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
        space = 210;
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
