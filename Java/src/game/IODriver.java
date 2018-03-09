package game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Akemi on 3/6/2018.
 *
 * Run this in command line and specify one input file. The file's format must match the example given by the instructor upon release of Stage 2.
 */
public class IODriver {
    public static void main(String[] args) {
        Scanner scan;
        String readFile[] = {"", ""};                               //[0] = 'w' or 'b' ;;   [1] = 'A1b, B3b ....., F3w' etc
        int tracker = 0;
        ArrayList<Marble> inputMarbles = new ArrayList<Marble>();   //readFile[1] will eventually be put into this
        ArrayList<Move> outputMoves = new ArrayList<Move>();        // return from sort function
        Board inputBoard = new Board();                             //inputMarbles<Marble> will eventually go in here


        //Check that only one file was specified
        if (args.length != 1) {
            System.err.println("Please specify one file only");
            System.exit(1);
        }

        try {
            File file = new File(args[0]);                          //grab the only argument i.e. file name and put it in file
            scan = new Scanner(file);
            while (scan.hasNextLine()) {                            //file now read into readFile
                readFile[tracker] = scan.nextLine();
                tracker++;
            }

            String[] splitInput = readFile[1].split(",");   //Split fileInput[1] by the commas and put into another array


            //Make marble objects by taking in values from splitInput. Goes in ArrayList<Marble> inputMarbles
            //turnAlphaToDigit = from 'A' - 'I', assign them numbers
            //turnCharToInt = turn char '1' to integer 1.    Is there an easier way to do this?
            //turn AlphaToBool = turn 'b' to   Marble.isBlack() = true ; w = false
            int count = 0;
            for(String s: splitInput) {
                inputMarbles.add(new Marble(turnAlphaToDigit(splitInput[count].charAt(0)),
                        turnCharToInt(splitInput[count].charAt(1)),
                        turnAlphaToBool(splitInput[count].charAt(2))));
                count++;
            }

            //For each Marble object in inputMarbles, put into inputBoard  Board object.
            for(int i = 0; i < inputMarbles.size(); ++i) {
                inputBoard.add(inputMarbles.get(i));
                //inputMarbles.get(i).toString();
            }

            /**
             * Code to get ArrayList<Moves> goes here:
             * outputMoves = genPossibleMoves(inputBoard);
             */

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    // Turn n
    // 'A' = 1; 'B' = 2; 'C' = 3; 'D' = 4; ........., 'I' = 9
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

    public static void writeOutput(ArrayList<Move> moves) {

    }
}
