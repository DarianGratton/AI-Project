package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import game.Game;
import game.Marble;

public class StartListener implements ActionListener {

    private static final int AI_MOVE_LIMIT = 100;
    private static final long AI_TIME_LIMIT = 1000;
    private static final int HUMAN_MOVE_LIMIT = 100;
    private static final long HUMAN_TIME_LIMIT = 1000;
    
    private static ArrayList<Marble> layout;
    private static Game game = new Game();
    private boolean aiIsBlack;
    
    @Override
    public void actionPerformed(ActionEvent event) {
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
        
        ButtonGroup boardGroup = new ButtonGroup();
        boardGroup.add(standardButton);
        boardGroup.add(germanButton);
        boardGroup.add(belgianButton);
        
        ButtonGroup playerGroup = new ButtonGroup();
        playerGroup.add(blackButton);
        playerGroup.add(whiteButton);
        
        startPanel.add(new JLabel("Choose your initial board state: "));
        startPanel.add(standardButton);
        startPanel.add(germanButton);
        startPanel.add(belgianButton);
        
        startPanel.add(new JLabel("Choose your color: "));
        startPanel.add(blackButton);
        startPanel.add(whiteButton);
    
        int result = JOptionPane.showConfirmDialog(null, startPanel,
            "Game Settings", JOptionPane.OK_CANCEL_OPTION);
    
        if (result == JOptionPane.OK_OPTION) {
            
            if (standardButton.isSelected()) {
                layout = Game.standardLayout;
                
            } else if (germanButton.isSelected()) {
                layout = Game.germanDaisy;
                
            } else if (belgianButton.isSelected()) {
                layout = Game.belgianDaisy;
                
            }
            
            if (blackButton.isSelected()) {
                aiIsBlack = true;
                
            } else if (whiteButton.isSelected()) {
                aiIsBlack = false;
                
            } 
            
            game = new Game(layout, aiIsBlack, AI_MOVE_LIMIT, HUMAN_MOVE_LIMIT,
                     AI_TIME_LIMIT, HUMAN_TIME_LIMIT);
            
        }
        
        
    }

}
