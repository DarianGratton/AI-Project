package abalone;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameFrame extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private JPanel players;
    private JPanel game;
    private JPanel whiteMarbles;
    private JPanel blackMarbles;
    private JPanel gameBoard;
    private JPanel options;
    
    public GameFrame() {
        setTitle("Abalone");
        players = new JPanel();
        game = new JPanel();
        whiteMarbles = new JPanel();
        blackMarbles = new JPanel();
        gameBoard = new JPanel();
        options = new JPanel();
        
        this.setLayout(new BorderLayout());
        add(players, BorderLayout.WEST);
        add(game, BorderLayout.CENTER);
        
        players.setLayout(new GridLayout(0, 1));
        players.setPreferredSize(new Dimension(300, 300));
        
        players.add(whiteMarbles);
        players.add(blackMarbles);
        
        game.setLayout(new GridLayout(0, 1));
        gameBoard.setPreferredSize(new Dimension(1000, 1000));
        game.add(gameBoard);
        game.add(options);
        
        whiteMarbles.setBorder(BorderFactory.createLineBorder(Color.black));
        blackMarbles.setBorder(BorderFactory.createLineBorder(Color.black));
        gameBoard.setBorder(BorderFactory.createLineBorder(Color.black));
        options.setBorder(BorderFactory.createLineBorder(Color.black));
    }
}
