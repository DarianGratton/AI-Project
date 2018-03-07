
package game;

import java.util.ArrayList;

/**
 * @author Mike
 *
 */
@SuppressWarnings("serial")
public class Board extends ArrayList<Marble>{

    /**
     * by akemi
     * @param input - Line 2 of input text file
     * @return new Board with input as marbles
     */
    public static Board inputBoard(String input){
        Board newBoard = new Board();
        ArrayList<Marble> inputMarble = new ArrayList<Marble>();
        String[] splitInput = input.split(",");

        for(String s: splitInput) {
            int i = 0;
            inputMarble.add(new Marble(turnAlphaToDigit(splitInput[i].charAt(0)), turnCharToInt(splitInput[i].charAt(1)), turnAlphatoBool(splitInput[i].charAt(2))));
            i++;
        }

        for(int i = 0; i < inputMarble.size(); ++i) {
            newBoard.add(inputMarble.get(i));
        }

        return newBoard;
    }

    public static int turnAlphaToDigit(char alpha) {
        int n = 0;
        switch(alpha) {
            case 'A':
                n = 1;
                break;
            case 'B':
                n = 2;
                break;
            case 'C':
                n = 3;
                break;
            case 'D':
                n = 4;
                break;
            case 'E':
                n = 5;
                break;
            case 'F':
                n = 6;
                break;
            case 'G':
                n = 7;
                break;
            case 'H':
                n = 8;
                break;
            case 'I':
                n = 9;
                break;
        }
        return n;
    }

    public static boolean turnAlphatoBool(char alpha) {
        boolean state = false;
        switch(alpha) {
            case 'w':
                state = false;
                break;
            case 'b':
                state = true;
                break;
        }
        return state;
    }

    public static int turnCharToInt(char alpha) {
        int n = 0;
        switch(alpha) {
            case '1':
                n = 1;
                break;
            case '2':
                n = 2;
                break;
            case '3':
                n = 3;
                break;
            case '4':
                n = 4;
                break;
            case '5':
                n = 5;
                break;
            case '6':
                n = 6;
                break;
            case '7':
                n = 7;
                break;
            case '8':
                n = 8;
                break;
            case '9':
                n = 9;
                break;
        }
        return n;

    
    public Board(){
        
    }
    
    public Board (Board b){
        super(b);
    }
}
