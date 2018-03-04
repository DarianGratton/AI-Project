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

import game.Board;
import game.Game;
import game.Gui;
import game.Marble;
import game.TestDriver;

public class StartListener implements ActionListener {    

    private Board boardLayout;
    private int moveLimit;
    private long gameTimeLimit;
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

            TestDriver m = new TestDriver();
            m.setGame(new Game(boardLayout, aiIsBlack, 30, 30, (long) 10000, (long) 10000));

        }
    }
}


