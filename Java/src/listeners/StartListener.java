package listeners;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import game.Game;
import game.Gui;
import game.Marble;
import game.TestDriver;public class StartListener implements ActionListener {    

    private ArrayList<Marble> layout;
    private int aiMoveLimit;
    private long aiTimeLimit;
    private int humanMoveLimit;
    private long humanTimeLimit;
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
        
        JTextField aiMoves = new JTextField(3);
        startPanel.add(aiMoves);
        
        JTextField humanMoves = new JTextField(3);
        startPanel.add(humanMoves);
        
        JTextField aiTime = new JTextField(3);
        startPanel.add(aiTime);
        
        JTextField humanTime = new JTextField(3);
        startPanel.add(humanTime);

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
            
            aiMoveLimit = Integer.parseInt(aiMoves.getText());
            humanMoveLimit = Integer.parseInt(humanMoves.getText());
            
            aiTimeLimit = Long.parseLong(aiTime.getText());
            humanTimeLimit = Long.parseLong(humanTime.getText());

            TestDriver.game = Gui.startGame(layout, aiIsBlack, aiMoveLimit, humanMoveLimit,
                    aiTimeLimit, humanTimeLimit);

        }
    }
}


