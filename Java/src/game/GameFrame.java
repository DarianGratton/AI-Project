package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 * The GameFrame class is an JFrame that displays all the 
 * 
 * @author skull
 *
 */
public class GameFrame extends JFrame {

    /**
     * Generated serial UID.
     */
    private static final long serialVersionUID = 2986344142823166606L;
    
    private static final int PANEL_WIDTH = 200;
    private static final int PANEL_HEIGHT = 300;
    private static final int BOARD_HEIGHT = 600;
    
    // Declaring game object
    private Game game;
    
    // Declaring board object
    private Board boardLayout;
    
    // For scrolling if JPanel in History gets to big
    private JScrollPane vertical;
    
    private JButton start;
    private JButton stop;
    private JButton reset;
    private JButton pause;
    
    private GameTimer timer;

    // ArrayList to hold the spaces on the board
    private ArrayList<Space> spaceList;
    
    // Move Limit for the game 
    private int moveLimit;
    
    // Time limit for the game
    private long gameTimeLimit;
    
    // Boolean to check if the player is black or white
    private boolean aiIsBlack;

    /**
     * Constructor that creates the initial state of the board.
     * Populates the JFrame.
     */
    public GameFrame() {
        setTitle("Abalone");

        this.game = initGame();
        this.spaceList = new ArrayList<Space>();
        timer = new GameTimer();
        
        this.setLayout(new BorderLayout());
        this.add(createGamePanel(new BoardPanel(boardLayout)),
                BorderLayout.CENTER);
        this.add(createPlayerPanel(), BorderLayout.WEST);
        this.add(createMuseumPanel(), BorderLayout.EAST);
        timer.startTimer();
    }

    /**
     * Creates a JPanel containing each of the different marble teams and
     * their respective stats. 
     * 
     * @return the players JPanel with all the swing elements
     */
    private JPanel createPlayerPanel() {     
        JPanel players = new JPanel();
        players.setLayout(new BoxLayout(players, BoxLayout.PAGE_AXIS));
       
        players.add(createMarblePanel("Team White: ", 
                game.getWhiteScore()));
        
        players.add(createMarblePanel("Team Black: ", 
                game.getBlackScore()));
        
        return players;
    }
    
    /**
     * Creates the individual player cards with their stats and 
     * color.
     * 
     * @param teamColor Team color
     * @param score The team's current score
     * @return a new player panel
     */
    private JPanel createMarblePanel(String teamColor, int score) {

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));

        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        
        JLabel teamLabel = createLabel(new JLabel(), teamColor);
        panel.add(teamLabel, BorderLayout.NORTH);
        
        JPanel playerInfo = new JPanel();
        playerInfo.setLayout(new BoxLayout(playerInfo, BoxLayout.PAGE_AXIS));
        
        // Display game score
        playerInfo.add(createLabel(new JLabel(), "Total score: " + score));
        
        // Display number of moves taken per player
        playerInfo.add(createLabel(new JLabel(), "Total # of Moves: " 
                /*+ move.getMovedList().size()*/));
        
        // Display time taken per move
        playerInfo.add(createLabel(new JLabel(), "Turn Time: " 
                + game.getGameTime()));
        
        panel.add(playerInfo, BorderLayout.CENTER);
        
        return panel;
    }

    /**
     * Creates a JPanel containing the game-board itself and options pertaining
     * to game-board. 
     * 
     * Note in the future could use ArrayList
     * 
     * @param board the board layout 
     * @return the game JPanel with all the swing elements
     */
    private JPanel createGamePanel(BoardPanel board) {
        
        JPanel gameBoard = new JPanel();
        gameBoard.setPreferredSize(new Dimension(0, BOARD_HEIGHT));
        gameBoard.setLayout(new BorderLayout());
        gameBoard.add(board, BorderLayout.CENTER);
        
        JPanel gameLabels = new JPanel();
        gameLabels.setLayout(new BoxLayout(gameLabels, BoxLayout.LINE_AXIS));
        
        JPanel options = new JPanel();
        options.setLayout(new BorderLayout());
        options.add(gameLabels, BorderLayout.NORTH);
        
        gameLabels.add(createLabel(new JLabel(), "Total game time: "));
        gameLabels.add(timer);
        gameLabels.add(createLabel(new JLabel(), " Next Recommended Move: " 
                + game.getRecommended().toString()));

        ArrayList<JButton> buttons = new ArrayList<>();
        start = new JButton("Start Game");
        buttons.add(start);
        stop = new JButton("Stop Game");
        buttons.add(stop);
        pause = new JButton("Pause Game");
        buttons.add(pause);
        reset = new JButton("Reset Game");
        buttons.add(reset);
        
        for (JButton btn : buttons) {
            btn.addActionListener(new ButtonListener());
        }
        
        JPanel optionButtons = new JPanel();
        options.add(optionButtons, BorderLayout.SOUTH);

        optionButtons = new JPanel();
        options.add(optionButtons, BorderLayout.SOUTH);

        for (JButton btn : buttons) {
            optionButtons.add(btn);
        }

        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.PAGE_AXIS));
        gamePanel.add(gameBoard);
        gamePanel.add(options);
        
        return gamePanel;
    }

    /**
     * Create a JPanel that holds each JPanel for the players' 
     * move and time history.
     * 
     * @return a JPanel containing player history.
     */
    private JPanel createMuseumPanel() {

        JPanel museum = new JPanel();
        museum.setLayout(new BoxLayout(museum, BoxLayout.PAGE_AXIS));
        museum.add(createHistoryPanel(new JPanel(), 
                new JLabel("White Move History")));
        museum.add(createHistoryPanel(new JPanel(), 
                new JLabel("Black Move History")));
        
        return museum;
    }      

    /**
     * Create an history panel that contains the player's 
     * move and time history.
     * 
     * @param panel a new JPanel
     * @param label a new JLabel with team name
     * @return a JPanel containing history for a player
     */
    private JPanel createHistoryPanel(JPanel panel, JLabel label) {

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        
        panel.add(label);
        JPanel console = new JPanel();
        
        vertical = new JScrollPane(console);
        vertical.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(vertical);
        
//        for (int i = 0; i < move.getMovedList().size(); ++i) {
//            move.getMovedList().get(i).toString();
//        }           

        return panel;
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

    /**
     * Gets ArrayList of spaces.
     * 
     * @return an ArrayList of spaces
     */
    public ArrayList<Space> getSpaceList(){
        return this.spaceList;
    }
    
    /**
     * Prompts the user to enter conditions for the game.
     * 
     * @return A new game based off the user input
     */
    private Game initGame() {
        
        JPanel startPanel = new JPanel();
        startPanel.setLayout(new BoxLayout(startPanel, BoxLayout.PAGE_AXIS));
        
        ArrayList<JRadioButton> boardButtons = new ArrayList<>();
        boardButtons.add(new JRadioButton("Standard")); 
        boardButtons.add(new JRadioButton("German Daisy"));
        boardButtons.add(new JRadioButton("Belgian Daisy"));
         
        ArrayList<JRadioButton> playerButtons = new ArrayList<>();
        playerButtons.add(new JRadioButton("Black"));
        playerButtons.add(new JRadioButton("White"));
        
        ArrayList<JRadioButton> modeButtons = new ArrayList<>(); 
        modeButtons.add(new JRadioButton("Human VS Human"));
        modeButtons.add(new JRadioButton("Human VS Computer"));
        modeButtons.add(new JRadioButton("Computer VS Computer"));
        
        startPanel.add(new JLabel("Choose your initial board state: "));
        initRadioButtons(boardButtons, new ButtonGroup(), startPanel);

        startPanel.add(new JLabel("Choose your color: "));
        initRadioButtons(playerButtons, new ButtonGroup(), startPanel);
        
        startPanel.add(new JLabel("Choose mode: "));
        initRadioButtons(modeButtons, new ButtonGroup(), startPanel);
        
        JLabel gameLimit = new JLabel("Set Game Time Limit: ");
        JFormattedTextField gameTime = new JFormattedTextField();
        startPanel.add(gameLimit);
        startPanel.add(gameTime);
        
        JLabel moveTimeLimit = new JLabel("Set Move Time Limit: ");
        JFormattedTextField moveTime = new JFormattedTextField();
        startPanel.add(moveTimeLimit);
        startPanel.add(moveTime);

        int result = JOptionPane.showConfirmDialog(null, startPanel,
                "Game Settings", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {

            if (boardButtons.get(0).isSelected()) {
                boardLayout = Game.standardLayout;

            } else if (boardButtons.get(1).isSelected()) {
                boardLayout = Game.germanDaisy;

            } else if (boardButtons.get(2).isSelected()) {
                boardLayout = Game.belgianDaisy;
            }

            if (playerButtons.get(0).isSelected()) {
                aiIsBlack = true;
            } else if (playerButtons.get(1).isSelected()) {
                aiIsBlack = false;
            }
            
            moveLimit = Integer.parseInt(moveTime.getText());
            gameTimeLimit = Long.parseLong(gameTime.getText());
        }
        
        return new Game(boardLayout, aiIsBlack, moveLimit, gameTimeLimit);
    }
    
    /**
     * Adds buttons in the arraylist to the inputted button input
     * and adds it to the panel. Made to help save lines of code on
     * the gameInit method.
     * 
     * @param btns ArrayList of JRadioButtons
     * @param group Button group for the JRadioButtons
     * @param panel The panel to be added to
     */
    private void initRadioButtons(ArrayList<JRadioButton> btns, 
            ButtonGroup group, JPanel panel) {
        
        for (int i = 0; i < btns.size(); ++i) {
            btns.get(i).setActionCommand(btns.get(i).getText());
        }
        
        for (int i = 0; i < btns.size(); ++i) {
            group.add(btns.get(i));
            panel.add(btns.get(i));
        }
        
    }
    
    /**
     * @return the timer
     */
    public GameTimer getTimer() {
        return timer;
    }

    /**
     * @param timer the timer to set
     */
    public void setTimer(GameTimer timer) {
        this.timer = timer;
    }
    
    private class ButtonListener implements ActionListener {    

        @Override
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == start) {
                timer.startTimer();
            } else if (event.getSource() == stop) {
                timer.stopTimer();
                timer.resetTimer();
            } else if (event.getSource() == reset) {
                System.out.println("Reset");
            } else if (event.getSource() == pause) {
                timer.stopTimer();
            }
        }
    }
}
