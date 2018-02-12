package abalone;
import javax.swing.JFrame;


/**
 * @author Mike
 *
 */
public class TestDriver {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Game test = new Game();
        Marble m1 = new Marble(5, 5, true);
        Marble m2 = new Marble(6, 5, true);
        Marble m3 = new Marble(7, 5, true);
        Marble m4 = new Marble(8, 5, true);
        
        test.addMarble(m1);
        test.addMarble(m2);
        test.addMarble(m3);
        //test.addMarble(m4);
        
//        System.out.println(m1.toString());
//        System.out.println(m2.toString());
//        System.out.println(m3.toString());
//        System.out.println(m4.toString());
        
        // test.move(m1, 1, 0); <- this was an inline move to test
        
        test.move(m1, m2, 1);
        
        
//        System.out.println(m1.toString());
//        System.out.println(m2.toString());
//        System.out.println(m3.toString());
//        System.out.println(m4.toString());
        
        final GameFrame frame = new GameFrame();
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setVisible(true);
        
        
        
    }
}
