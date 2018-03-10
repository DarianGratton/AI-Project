package game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Akemi on 3/6/2018.
 *
 * Run this in command line and specify one input file. The file's format must 
 * match the example given by the instructor upon release of Stage 2.
 */
public class IODriver {
    
    public static void main(String[] args) {
        Scanner scan;
        String readFile[] = {"", ""};                               //[0] = 'w' or 'b' ;;   [1] = 'A1b, B3b ....., F3w' etc
        int tracker = 0;
        ArrayList<Marble> inputMarbles = new ArrayList<Marble>();   //readFile[1] will eventually be put into this
        ArrayList<Move> outputMoves = new ArrayList<Move>();        // return from sort function
        ArrayList<Marble> outputMarbles = new ArrayList<Marble>();  // marbles representing all possible moves
        Board inputBoard = new Board();                             //inputMarbles<Marble> will eventually go in here
        boolean isBlack = false;                                    //aiIsBlack() from input file first line


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

            //read first line of file, turn string to char to pass through turnAlphaToBool

            if(turnAlphaToBool(splitInput[0].charAt(0))) {
                isBlack = true;
            }

            //Make marble objects by taking in values from splitInput. Goes in ArrayList<Marble> inputMarbles
            int count = 0;
            for(String s: splitInput) {
                inputMarbles.add(new Marble(turnAlphaToDigit(splitInput[count].charAt(0)),
                        Character.getNumericValue((splitInput[count].charAt(1))),
                        turnAlphaToBool(splitInput[count].charAt(2))));
                count++;
            }

            //For each Marble object in inputMarbles, put into inputBoard  Board object.
            for (int i = 0; i < inputMarbles.size(); ++i) {
                inputBoard.add(inputMarbles.get(i));
            }

            outputMoves = aiPlayer.genPossibleMoves(inputBoard, isBlack);


            //Create file for printing to
            PrintWriter moveWriter = new PrintWriter("Results.move");
            PrintWriter boardWriter = new PrintWriter("Results.board");

            // write to Results.move the list of moves possible for current layout
            for (Move m : outputMoves) {
                moveWriter.println(m);
                //Write to Results.board the board state space
                for(int i = 0; i < m.getMovedList().size(); ++i) {
                    outputMarbles = m.getMovedList();

                    //someString = some converting from num to letter etc.
                    // boardWriter.print( someString)
                }
            }

            //The file will not update until this is done
            boardWriter.close();
            moveWriter.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    //Turns char alphabet to corsdresponding integer number
    // 'A' = 1; 'B' = 2; 'C' = 3; 'D' = 4; ........., 'I' = 9
    private static int turnAlphaToDigit(char alpha) {
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

    //turns  1  to 'A'
    // for output to file
    private static int turnDigitToAlpha(int digit) {
        int n = 0;
        switch (digit) {
            case 1:
                n = 'A';
                break;
            case 2:
                n = 'B';
                break;
            case 3:
                n = 'C';
                break;
            case 4:
                n = 'D';
                break;
            case 5:
                n = 'E';
                break;
            case 6:
                n = 'F';
                break;
            case 7:
                n = 'G';
                break;
            case 8:
                n = 'H';
                break;
            case 9:
                n = 'I';
                break;
        }
        return n;
    }

    // 'w' = black ; 'b' = true
    private static boolean turnAlphaToBool(char alpha) {
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



    public static void writeOutput(ArrayList<Move> moves) {

    }
}
