package game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Akemi on 3/6/2018.
 */
public class IODriver {
    public static void main(String[] args) {
        Scanner scan;
        String readFile[] = {"", ""};
        int tracker = 0;
        ArrayList<Marble> inputMarbles = new ArrayList<Marble>();
        Board inputBoard = new Board();


        //Check that only one file was specified
        if (args.length != 1) {
            System.err.println("Please specify one file only");
            System.exit(1);
        }

        try {
            File file = new File(args[0]);
            scan = new Scanner(file);
            while (scan.hasNextLine()) {
                readFile[tracker] = scan.nextLine();
                tracker++;
            }

            String[] splitInput = readFile[1].split(",");

            //Make marbles from input
            tracker = 0;
            for(String s: splitInput) {
                inputMarbles.add(new Marble(turnAlphaToDigit(splitInput[tracker].charAt(0)),
                        turnCharToInt(splitInput[tracker].charAt(1)), turnAlphaToBool(splitInput[tracker].charAt(2))));
                tracker++;
            }

            for(int i = 0; i < inputMarbles.size(); ++i) {
                inputBoard.add(inputMarbles.get(i));
                //inputMarbles.get(i).toString();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static int turnAlphaToDigit(char alpha) {
        int n = 0;
        switch (alpha) {
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

    public static boolean turnAlphaToBool(char alpha) {
        boolean state = false;
        switch (alpha) {
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
        switch (alpha) {
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
    }
}
