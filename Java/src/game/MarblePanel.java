package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MarblePanel extends JPanel {
   
    private static final long serialVersionUID = 3597646416905814493L;
    private static final int PANEL_WIDTH = 205;
    private static final int PANEL_HEIGHT = 300;
    private Game game;
    
    private JLabel scoreLabel;
    private JLabel turnNumLabel;
    private JLabel totalTurnTime;
    private GameTimer turnTimer;				//AH

    MarblePanel(GameFrame frame, Game g, String teamColor, 
            boolean aiPlayerIsBlack, 
            Color backgroundColor, 
            Color fontColor) {
        this.game = g;
        
        final int paddingTop = 20;
        final int padding = 15;
        final int fontSizeTeam = 24;
        final int fontSizeScore = 60;
        final int fontSizeStats = 20;
        int score = 0;
        
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(backgroundColor);
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(paddingTop, padding, padding, padding));
        
        JLabel teamLabel = frame.createLabel(new JLabel(), teamColor, 
                fontSizeTeam, fontColor);
        this.add(teamLabel, BorderLayout.NORTH);
        
        JPanel playerInfo = new JPanel();
        playerInfo.setBackground(backgroundColor);
        playerInfo.setLayout(new BoxLayout(playerInfo, BoxLayout.PAGE_AXIS));
        
        // Display game score
        scoreLabel = frame.createLabel(new JLabel(), Integer.toString(score),
                fontSizeScore, fontColor);
        playerInfo.add(scoreLabel);
        
        // Display number of moves taken per player
        turnNumLabel = frame.createLabel(new JLabel(), "Total # of Moves: " 
        + (game.getBlackMoves().size() + game.getWhiteMoves().size()), fontSizeStats, fontColor);
        playerInfo.add(turnNumLabel);
        
        // AH - Display time taken per move	
        this.turnTimer = new GameTimer(fontColor, fontSizeStats);
        turnTimer.startTimer();
        playerInfo.add(turnTimer);
        
        //AH - Display total time taken
        totalTurnTime = frame.createLabel(new JLabel(),  "Total time: ", fontSizeStats, fontColor);
        playerInfo.add(totalTurnTime);
        
        this.add(playerInfo, BorderLayout.CENTER);
    }
    
    public void updateScoreLabel(boolean activePlayerIsBlack) {
        if (activePlayerIsBlack) {
            scoreLabel.setText(Integer.toString(game.getBlackScore()));
        } else {
            scoreLabel.setText(Integer.toString(game.getWhiteScore()));
        }      
    }
    
    public void updateTurnCount(boolean activePlayerIsBlack) {
        if (activePlayerIsBlack) {
            turnNumLabel.setText("Total # of Moves: " 
                    + game.getBlackMoves().size());
        } else {
            turnNumLabel.setText("Total # of Moves: " 
                    + game.getWhiteMoves().size());
        }
    }
    
    public void updateTotalTurnTimer(boolean activePlayerIsBlack) {
    	totalTurnTime.setText("Total time: " + game.getTotalTurnTime(activePlayerIsBlack) );
    }
    
    public void removeStats() {
        turnNumLabel.setText("Total # of Moves: " 
                    + game.getWhiteMoves().size());
        scoreLabel.setText(Integer.toString(game.getWhiteScore()));
    }

    /**
     * @return the game
     */
    public Game getGame() {
        return game;
    }

    /**
     * @param game the game to set
     */
    public void setGame(Game game) {
        this.game = game;
    }
    
    //AH
    /**
     * returns the timer belonging to the call class
     * @return
     */
    public GameTimer getTurnTimer() {
    	return this.turnTimer;
    }
}
