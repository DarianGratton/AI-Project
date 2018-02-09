package Abalone;

import javax.swing.JFrame;

public class Main {
    
    public static void main(final String[] argv) {
        //final GameFrame frame = new GameFrame();
        final GameFrameTest frame = new GameFrameTest();
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setVisible(true);
    }
    
}
