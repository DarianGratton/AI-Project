package game;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class HistoryPanel extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final int PANEL_WIDTH = 205;
    private static final int PANEL_HEIGHT = 300;
    
    private Game game;
    private JPanel console;
    private JScrollPane vertical;
    
    HistoryPanel(Game g, JLabel label, boolean aiIsBlack) {
        this.game = g;
        
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBorder(BorderFactory.createLineBorder(Color.black));
        
        add(label);
        console = new JPanel();
        console.setLayout(new BoxLayout(console, BoxLayout.PAGE_AXIS));
        
        vertical = new JScrollPane(console);
        vertical.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        JScrollPane scrollPane = new JScrollPane(console);
        scrollPane.setHorizontalScrollBarPolicy(
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getViewport().setPreferredSize(new Dimension(250, 400));
        
        add(vertical);
        
    }
    
    public void updateMoveHistory(boolean aiIsBlack) {
        if (aiIsBlack) {
            int lastMove = game.getBlackMoves().size() - 1;
            console.add(new JLabel(game.getBlackMoves().get(lastMove).toString()));
        } else {
            int lastMove = game.getWhiteMoves().size() - 1;
            console.add(new JLabel(game.getWhiteMoves().get(lastMove).toString()));
        }
        remove(vertical);
        vertical = new JScrollPane(console);
        vertical.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(vertical);
    }
}
