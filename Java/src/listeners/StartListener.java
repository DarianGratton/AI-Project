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

import abalone.Marble;

public class StartListener implements ActionListener {

    private ArrayList<Marble> layout;
    private boolean aiIsBlack;
    
    @Override
    public void actionPerformed(ActionEvent event) {
        JPanel startPanel = new JPanel();
        startPanel.setLayout(new BoxLayout(startPanel, BoxLayout.PAGE_AXIS));
        
        JRadioButton standardButton = new JRadioButton("Standard");
        JRadioButton germanButton = new JRadioButton("German Daisy");
        JRadioButton belgianButton = new JRadioButton("Belgian Daisy");
        JRadioButton blackButton = new JRadioButton("Black");
        JRadioButton whiteButton = new JRadioButton("White");
        
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
            System.out.println("Progress");
        }
        
        
    }

}
