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
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 * The GameFrame class is an JFrame that displays all the different
 * game board components. It creates and displays the player panels and 
 * stats, the different game options and buttons, and the player 
 * move history. It also contains listeners for the buttons.
 * 
 * @author A00965803
 * @version 2018/03/10
 */
public class GameFrame extends JFrame {

    /**
     * Generated serial UID.
     */
    private static final long serialVersionUID = 2986344142823166606L;
    
    private static final int PANEL_WIDTH = 205;
    private static final int PANEL_HEIGHT = 300;
    
    // Board panel height
    private static final int BOARD_HEIGHT = 575;
    
    private ArrayList<JRadioButton> boardButtons = new ArrayList<JRadioButton>() {
        private static final long serialVersionUID = -4924548428540373984L;
    {
        add(new JRadioButton("Standard"));
        add(new JRadioButton("German Daisy"));
        add(new JRadioButton("Belgian Daisy"));
    }};
     
    private ArrayList<JRadioButton> playerButtons = new ArrayList<JRadioButton>() {
        private static final long serialVersionUID = 1593599663555850435L;

    {
        add(new JRadioButton("Black"));
        add(new JRadioButton("White"));
    }};
    
    private ArrayList<JRadioButton> modeButtons = new ArrayList<JRadioButton>() {
        private static final long serialVersionUID = -7232779521562355537L;

    {
        add(new JRadioButton("Human VS Human"));
        add(new JRadioButton("Human VS Computer"));
        add(new JRadioButton("Computer VS Computer"));
    }};
    
    // Declaring game object
    private Game game;
    
    // Declaring board object
    private Board boardLayout;
    
    // Declaring GameTimer object to keep track of the game time
    private GameTimer gameTimer;
    
    // JButton to start the game
    private JButton start;
    
    // JButton to stop the game
    private JButton stop;
    
    // JButton to reset the game
    private JButton reset;
    
    // JButton to pause the game
    private JButton pause;
    
    private JPanel museum;
    
    HistoryPanel blackHistory;
    HistoryPanel whiteHistory;

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
     * Constructor that creates the initial state of the game.
     * Populates the JFrame.
     * 
     * @param g the current game being played
     */
    public GameFrame(Game g) {
        setTitle("Abalone");
        
        //this.spaceList = new ArrayList<Space>();
        //BoardPanel board = new BoardPanel(g);

        this.game = g;
        //this.spaceList = new ArrayList<Space>();
        gameTimer = new GameTimer();
        
        this.setLayout(new BorderLayout());
        this.add(createGamePanel(new BoardPanel(this.game, this)),
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
       
        players.add(createMarblePanel("Team White", false, Color.WHITE, Color.BLACK));
        
        players.add(createMarblePanel("Team Black", true, Color.BLACK, Color.WHITE));
        
        return players;
    }
    
    /**
     * Creates the individual player cards with their stats and 
     * color.
     * 
     * @param teamColor Team color
     * @param score The team's current score
     * @param backgroundColor the background color of the player panel
     * @param fontColor the color of the font in the player panel 
     * @return a new player panel
     */
    private JPanel createMarblePanel(String teamColor, boolean aiPlayerIsBlack, 
            Color backgroundColor, Color fontColor) {
        final int paddingTop = 20;
        final int padding = 15;
        final int fontSizeTeam = 24;
        final int fontSizeScore = 60;
        final int fontSizeStats = 20;
        int score = 0;

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        panel.setBackground(backgroundColor);
        panel.setLayout(new BorderLayout());
        panel.setBorder(new EmptyBorder(paddingTop, padding, padding, padding));
        
        JLabel teamLabel = createLabel(new JLabel(), teamColor, 
                fontSizeTeam, fontColor);
        panel.add(teamLabel, BorderLayout.NORTH);
        
        JPanel playerInfo = new JPanel();
        playerInfo.setBackground(backgroundColor);
        playerInfo.setLayout(new BoxLayout(playerInfo, BoxLayout.PAGE_AXIS));
        
        // Display game score
        playerInfo.add(createLabel(new JLabel(), Integer.toString(score),
                fontSizeScore, fontColor));
        
        // Display number of moves taken per player
        playerInfo.add(createLabel(new JLabel(), "Total # of Moves: ", 
                fontSizeStats, fontColor));
        
        // Display time taken per move
        playerInfo.add(createLabel(new JLabel(), "Turn Time: " 
                + game.getGameTime(), fontSizeStats, fontColor));
        
        panel.add(playerInfo, BorderLayout.CENTER);
        
        return panel;
    }

    /**
     * Creates a JPanel containing the game-board itself and options pertaining
     * to game-board. 
     * 
     * Note in the future could use ArrayList.
     * 
     * @param board the board layout 
     * @return the game JPanel with all the swing elements
     */
    private JPanel createGamePanel(BoardPanel board) {
        final int fontSizeGameStats = 15;
        
        gameBoard = new JPanel();
        gameBoard.setPreferredSize(new Dimension(0, BOARD_HEIGHT));
        gameBoard.setLayout(new BorderLayout());
        gameBoard.add(board, BorderLayout.CENTER);
        
        JPanel gameLabels = new JPanel();
        gameLabels.setLayout(new BoxLayout(gameLabels, BoxLayout.LINE_AXIS));
        gameLabels.setBorder(new EmptyBorder(10, 10, 10, 10));
        gameLabels.setBackground(Color.WHITE);
        
        gameLabels.add(createLabel(new JLabel(), "Total game time: ", 
                fontSizeGameStats, Color.BLACK));
        gameLabels.add(gameTimer);
        gameLabels.add(createLabel(new JLabel(), " Next Recommended Move: " 
                + game.getRecommended().toString(), 
                fontSizeGameStats, Color.BLACK));
        
        JPanel options = new JPanel();
        options.setBackground(Color.WHITE);
        options.setLayout(new BorderLayout());
        options.add(gameLabels, BorderLayout.NORTH);

        ArrayList<JButton> buttons = new ArrayList<>();
        start = createButton(start, "Start Game");
        buttons.add(start);
        stop = createButton(stop, "Stop Game");
        buttons.add(stop);
        pause = createButton(pause, "Pause Game");
        buttons.add(pause);
        reset = createButton(reset, "Reset Game");
        buttons.add(reset);
        
        JPanel optionButtons = new JPanel();
        options.add(optionButtons, BorderLayout.SOUTH);

        optionButtons = new JPanel();
        optionButtons.setBackground(Color.WHITE);
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

        museum = new JPanel();
        blackHistory = new HistoryPanel(game, new JLabel("Black Move History"), aiIsBlack);
        whiteHistory = new HistoryPanel(game, new JLabel("White Move History"), aiIsBlack);
        museum.setLayout(new BoxLayout(museum, BoxLayout.PAGE_AXIS));
        museum.add(blackHistory);
        museum.add(whiteHistory);
        
        return museum;
    }      

    /**
     * Creates a JLabel that takes an label and sets it text to the 
     * inputed String parameter text. Sets the font and size.
     * 
     * @param label to display on the GUI
     * @param text to be added to the label
     * @param fontSize size of the font
     * @param fontColor color of the font
     * @return A JLabel with text
     */
    public JLabel createLabel(JLabel label, String text, 
            int fontSize, Color fontColor) {
        label = new JLabel(text);
        label.setFont(new Font("SANS_SERIF", Font.PLAIN, fontSize));
        label.setForeground(fontColor);
        
        return label;
    }
    
    public JButton createButton(JButton button, String text) {
        
        button = new JButton(text);
        button.setForeground(Color.black);
        button.setBackground(Color.white);
        LineBorder line = new LineBorder(Color.BLACK);
        EmptyBorder margin = new EmptyBorder(5, 15, 5, 15);
        CompoundBorder compound = new CompoundBorder(line, margin);
        button.setBorder(compound);
        button.addActionListener(new ButtonListener());
        
        return button;
    }
    
    /**
     * Prompts the user to enter conditions for the game and reloads
     * the game based on the results.
     */
    private void initGame() {
        
        JPanel startPanel = new JPanel();
        startPanel.setLayout(new BoxLayout(startPanel, BoxLayout.PAGE_AXIS));
        startPanel.setBackground(Color.white);
        
        JLabel boardState = createLabel(new JLabel(), "Choose your initial board state: ", 15, Color.black);
        boardState.setBorder(new EmptyBorder(10, 0, 3, 0));
        startPanel.add(boardState);
        initRadioButtons(boardButtons, new ButtonGroup(), startPanel);

        JLabel colorChose = createLabel(new JLabel(), "Choose your color: ", 15, Color.black);
        colorChose.setBorder(new EmptyBorder(10, 0, 3, 0));
        startPanel.add(colorChose);
        initRadioButtons(playerButtons, new ButtonGroup(), startPanel);
        
        JLabel choseMode = createLabel(new JLabel(), "Choose mode: ", 15, Color.black);
        choseMode.setBorder(new EmptyBorder(10, 0, 3, 0));
        startPanel.add(choseMode);
        initRadioButtons(modeButtons, new ButtonGroup(), startPanel);
        
        JLabel timeLimit = createLabel(new JLabel(), "Set Time Limit per Move: ", 15, Color.black);
        timeLimit.setBorder(new EmptyBorder(10, 0, 3, 0));
        JFormattedTextField moveTime = new JFormattedTextField();
        moveTime.setText("0");
        startPanel.add(timeLimit);
        startPanel.add(moveTime);
        
        JLabel moveLimitLabel = createLabel(new JLabel(), "Set Move Limit: ", 15, Color.black);
        moveLimitLabel.setBorder(new EmptyBorder(10, 0, 3, 0));
        JFormattedTextField moveLimitNum = new JFormattedTextField();
        moveLimitNum.setText("0");
        startPanel.add(moveLimitLabel);
        startPanel.add(moveLimitNum);

        UIManager.put("OptionPane.background", Color.white);
        UIManager.put("Panel.background", Color.white);
        
        int result = JOptionPane.showConfirmDialog(null, startPanel,
                "Game Settings", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {

            boardLayout = new Board();
            
            if (boardButtons.get(0).isSelected()) {                
                boardLayout = Board.copyBoard(Game.standardLayout);

            } else if (boardButtons.get(1).isSelected()) {
                boardLayout = Board.copyBoard(Game.germanDaisy);

            } else if (boardButtons.get(2).isSelected()) {
                boardLayout = Board.copyBoard(Game.belgianDaisy);
            }

            if (playerButtons.get(0).isSelected()) {
                aiIsBlack = true;
            } else if (playerButtons.get(1).isSelected()) {
                aiIsBlack = false;
            }
            
            this.moveLimit = Integer.parseInt(moveLimitNum.getText());
            this.timePerMove = Long.parseLong(moveTime.getText());
        }
        
        Game g = new Game(boardLayout, aiIsBlack, this.moveLimit, 
                timePerMove, gameTimer);
        
        if (g != null) {
            this.game = g;
            gameBoard.removeAll();
            gameBoard.add(new BoardPanel(game, this));
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
            btns.get(i).setBackground(Color.white);
            btns.get(i).setActionCommand(btns.get(i).getText());
        }
        
        for (int i = 0; i < btns.size(); ++i) {
            group.add(btns.get(i));
            panel.add(btns.get(i));
        }
        
    }
    
    public void updateGameFrame(boolean aiIsBlack) {
        if (aiIsBlack) {
            blackHistory.updateMoveHistory(aiIsBlack);
        } else {
            whiteHistory.updateMoveHistory(aiIsBlack);
        }
    }
    
    /**
     * Gets the game timer.
     * 
     * @return the timer
     */
    public GameTimer getTimer() {
        return gameTimer;
    }

    /**
     * Sets the game timer.
     * 
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
             * Stop game button, stops game in current state and declares 
             * a winner (probably not necessary to declare winner). 
             * All it does now is stop the game timer.
             */
            if (event.getSource() == stop) {
                gameTimer.stopTimer();
            }
            
            /**
             * Reset game button, resets the game based on the last game's 
             * settings. 
             */
            if (event.getSource() == reset) {
                gameTimer.resetTimer();
                game = new Game(boardLayout, aiIsBlack, moveLimit, 
                        timePerMove, gameTimer);
                gameBoard.removeAll();
                // gameBoard.add(new BoardPanel(game));
                repaint();
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
