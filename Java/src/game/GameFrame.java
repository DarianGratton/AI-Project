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
    
    // For scrolling if JPanel in History gets to big
    private JScrollPane vertical;
    
    // ArrayList to hold the spaces on the board
    private ArrayList<Space> spaceList;
    
    private Board boardLayout;
    private int moveLimit;
    private long gameTimeLimit;
    private boolean aiIsBlack;

    /**
     * Constructor that creates the initial state of the board.
     * Populates the JFrame.
     */
    public GameFrame() {
        setTitle("Abalone");
        
        this.game = initGame();
        this.spaceList = new ArrayList<Space>();

        this.setLayout(new BorderLayout());
        this.add(createGamePanel(new BoardPanel(boardLayout)), BorderLayout.CENTER);
        this.add(createPlayerPanel(), BorderLayout.WEST);
        this.add(createMuseumPanel(), BorderLayout.EAST);
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
        gameLabels.add(createLabel(new JLabel(), " Next Recommended Move: " 
                + game.getRecommended().toString()));

        JButton start = new JButton("Start Game");
        start.addActionListener(new StartListener());
        JButton stop  = new JButton("Stop Game");
        stop.addActionListener(null);
        JButton reset = new JButton("Reset Game");
        reset.addActionListener(null);
        JButton pause = new JButton("Pause Game");
        pause.addActionListener(null);
        
        JPanel optionButtons = new JPanel();
        options.add(optionButtons, BorderLayout.SOUTH);

        optionButtons = new JPanel();
        options.add(optionButtons, BorderLayout.SOUTH);

        optionButtons.add(start);
        optionButtons.add(stop);
        optionButtons.add(reset);
        optionButtons.add(pause);

        JPanel game = new JPanel();
        game.setLayout(new BoxLayout(game, BoxLayout.PAGE_AXIS));
        game.add(gameBoard);
        game.add(options);
        
        return game;
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
    
    private Game initGame() {
        
        JPanel startPanel = new JPanel();
        startPanel.setLayout(new BoxLayout(startPanel, BoxLayout.PAGE_AXIS));
        
        JRadioButton standardButton = new JRadioButton("Standard");
        standardButton.setActionCommand(standardButton.getText());
        
        JRadioButton germanButton = new JRadioButton("German Daisy");
        germanButton.setActionCommand(germanButton.getText());
        
        JRadioButton belgianButton = new JRadioButton("Belgian Daisy");
        belgianButton.setActionCommand(standardButton.getText());
        
        JRadioButton blackButton = new JRadioButton("Black");
        blackButton.setActionCommand(blackButton.getText());
        
        JRadioButton whiteButton = new JRadioButton("White");
        whiteButton.setActionCommand(whiteButton.getText());
        
        JRadioButton humanVsHuman = new JRadioButton("Human VS Human");
        humanVsHuman.setActionCommand(humanVsHuman.getText());
        
        JRadioButton humanVsComputer = new JRadioButton("Human VS Computer");
        humanVsComputer.setActionCommand(humanVsComputer.getText());

        JRadioButton comVsCom = new JRadioButton("Computer VS Computer");
        comVsCom.setActionCommand(comVsCom.getText());
        
        ButtonGroup boardGroup = new ButtonGroup();
        boardGroup.add(standardButton);
        boardGroup.add(germanButton);
        boardGroup.add(belgianButton);

        ButtonGroup playerGroup = new ButtonGroup();
        playerGroup.add(blackButton);
        playerGroup.add(whiteButton);
        
        ButtonGroup modeGroup = new ButtonGroup();
        modeGroup.add(humanVsHuman);
        modeGroup.add(humanVsComputer);
        modeGroup.add(comVsCom);

        startPanel.add(new JLabel("Choose your initial board state: "));
        startPanel.add(standardButton);
        startPanel.add(germanButton);
        startPanel.add(belgianButton);
        
        startPanel.add(new JLabel("Choose your color: "));
        startPanel.add(blackButton);
        startPanel.add(whiteButton);     
        
        startPanel.add(new JLabel("Choose mode: "));
        startPanel.add(humanVsHuman);
        startPanel.add(humanVsComputer);
        startPanel.add(comVsCom);
        
        JLabel gameLimit = new JLabel("Set Game Time Limit: ");
        JTextField gameTime = new JTextField(3);
        startPanel.add(gameLimit);
        startPanel.add(gameTime);
        
        JLabel moveTimeLimit = new JLabel("Set Move Time Limit: ");
        JTextField moveTime = new JTextField(2);
        startPanel.add(moveTimeLimit);
        startPanel.add(moveTime);

        int result = JOptionPane.showConfirmDialog(null, startPanel,
                "Game Settings", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {

            if (standardButton.isSelected()) {
                boardLayout = Game.standardLayout;
                System.out.println("standardButton isSelected");

            } else if (germanButton.isSelected()) {
                boardLayout = Game.germanDaisy;

            } else if (belgianButton.isSelected()) {
                boardLayout = Game.belgianDaisy;
            }

            if (blackButton.isSelected()) {
                aiIsBlack = true;
            } else if (whiteButton.isSelected()) {
                aiIsBlack = false;
            }
            
            moveLimit = Integer.parseInt(moveTime.getText());
            gameTimeLimit = Long.parseLong(gameTime.getText());
        }
        
        return new Game(boardLayout, aiIsBlack, moveLimit, moveLimit, gameTimeLimit, gameTimeLimit);
    }
    
    private class StartListener implements ActionListener {    

        @Override
        public void actionPerformed(ActionEvent event) {
            game = initGame();
        }
    }
    
//    ArrayList<JRadioButton> radiobuttons = new ArrayList<>();
//    radiobuttons.add(new JRadioButton("Standard"));
//    radiobuttons.add(new JRadioButton("German Daisy"));
//    radiobuttons.add(new JRadioButton("Belgian Daisy"));
//    radiobuttons.add(new JRadioButton("Black"));
//    radiobuttons.add(new JRadioButton("White"));
//    radiobuttons.add(new JRadioButton("Human VS Human"));
//    radiobuttons.add(new JRadioButton("Human VS Computer"));
//    radiobuttons.add(new JRadioButton("Computer VS Computer"));
//    
//    for (int i = 0; i < radiobuttons.size(); ++i) {
//        radiobuttons.get(i).setActionCommand(radiobuttons.get(i).getText());
//    }
}
