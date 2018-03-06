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
    
    private GameTimer gameTimer;
    private GameTimer turnTimer;

    // ArrayList to hold the spaces on the board
    // private ArrayList<Space> spaceList;
    
    // Move Limit for the game 
    private int moveLimit;
    
    // Time limit for the game
    private long timePerMove;
    
    // Boolean to check if the player is black or white
    private boolean aiIsBlack;
    
    private JPanel gameBoard;

    /**
     * Constructor that creates the initial state of the board.
     * Populates the JFrame.
     */


    public GameFrame(Game g) {
        setTitle("Abalone");
        
        //this.spaceList = new ArrayList<Space>();
        //BoardPanel board = new BoardPanel(g);

        this.game = new Game();
        //this.spaceList = new ArrayList<Space>();
        gameTimer = new GameTimer();
        
        this.setLayout(new BorderLayout());
        this.add(createGamePanel(new BoardPanel(g)),
                BorderLayout.CENTER);
        this.add(createPlayerPanel(), BorderLayout.WEST);
        this.add(createMuseumPanel(), BorderLayout.EAST);
        gameTimer.startTimer();
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
        
        gameBoard = new JPanel();
        gameBoard.setPreferredSize(new Dimension(0, BOARD_HEIGHT));
        gameBoard.setLayout(new BorderLayout());
        gameBoard.add(board, BorderLayout.CENTER);
        
        JPanel gameLabels = new JPanel();
        gameLabels.setLayout(new BoxLayout(gameLabels, BoxLayout.LINE_AXIS));
        
        JPanel options = new JPanel();
        options.setLayout(new BorderLayout());
        options.add(gameLabels, BorderLayout.NORTH);
        
        gameLabels.add(createLabel(new JLabel(), "Total game time: "));
        gameLabels.add(gameTimer);
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
     *//*
    public ArrayList<Space> getSpaceList(){
        return this.spaceList;
    }*/
    
    /**
     * Prompts the user to enter conditions for the game.
     * 
     * @return A new game based off the user input
     */
    private void initGame() {
        
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
        
        JLabel timeLimit = new JLabel("Set Time Limit per Move: ");
        JFormattedTextField moveTime = new JFormattedTextField();
        moveTime.setText("0");
        startPanel.add(timeLimit);
        startPanel.add(moveTime);
        
        JLabel moveLimitLabel = new JLabel("Set Move Limit: ");
        JFormattedTextField moveLimit = new JFormattedTextField();
        moveLimit.setText("0");
        startPanel.add(moveLimitLabel);
        startPanel.add(moveLimit);

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
            
            this.moveLimit = Integer.parseInt(moveLimit.getText());
            this.timePerMove = Long.parseLong(moveTime.getText());
        }
        
        Game g = new Game(boardLayout, aiIsBlack, this.moveLimit, timePerMove, gameTimer);
        
        if (g != null) {
            this.game = g;
            gameBoard.removeAll();
            gameBoard.add(new BoardPanel(g));
            repaint();
        }
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
        return gameTimer;
    }

    /**
     * @param timer the timer to set
     */
    public void setTimer(GameTimer timer) {
        this.gameTimer = timer;
    }
    
    private class ButtonListener implements ActionListener {    

        @Override
        public void actionPerformed(ActionEvent event) {
            
            /**
             *  Start game button, stops timer and creates a new game as per the
             *  game settings input. 
             */
            if (event.getSource() == start) {
                gameTimer.stopTimer();
                gameTimer.resetTimer();
                initGame();
                gameTimer.startTimer();
            } 
            
            /**
             * Stop game button, stops game in current state and declares a winner
             * (probably not necessary to declare winner). All it does now is stop 
             * the game timer.
             */
            if (event.getSource() == stop) {
                gameTimer.stopTimer();
            }
            
            /**
             * Reset game button, resets the game based on the last game's settings. 
             */
            if (event.getSource() == reset) {
                gameTimer.resetTimer();
                game = new Game(boardLayout, aiIsBlack, moveLimit, timePerMove, gameTimer);
            }
            
            /**
             * Pause game button, pauses the game in it's current state.
             */
            if (event.getSource() == pause) {
                gameTimer.stopTimer();
            }
        }
    }
}
