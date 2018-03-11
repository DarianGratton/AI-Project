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
    private static final int ASCII_CONSTANT = 64;
    
    public static void main(String[] args) {
        Scanner scan;
        String readFile[] = {"", ""};                                   //[0] = 'w' or 'b' ;;   [1] = 'A1b, B3b ....., F3w' etc
        int tracker = 0;
        ArrayList<Marble> inputMarbles = new ArrayList<Marble>();       //readFile[1] will eventually be put into this
        ArrayList<Move> outputMoves = new ArrayList<Move>();            // return from sort function
        ArrayList<Marble> outputMarbles = new ArrayList<Marble>();      // marbles representing all possible moves
        ArrayList<Marble> copyOutputMarbles = new ArrayList<Marble>();  // new array for sorting results to go in
        Board inputBoard = new Board();                                 //inputMarbles<Marble> will eventually go in here
        boolean isBlack = false;                                        //aiIsBlack() from input file first line
        GameTimer timer = new GameTimer();                              //to put into Game object
        ArrayList<String> boardOutput = new ArrayList<String>();        //this String will be written to the Results.board.


        //Check that only one file was specified
        if (args.length != 1) {
            System.err.println("Please specify one file only");
            System.exit(1);
        }

        try {
            File file = new File(args[0]);                          //grab the only argument i.e. file name and put it in file
            scan = new Scanner(file);
            while (scan.hasNextLine()) {                            //file now read into readFile
                readFile[tracker] = scan.nextLine().toUpperCase();
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
                int c1 = ((int)splitInput[count].charAt(0)) - ASCII_CONSTANT;
                int c2 = Character.getNumericValue((splitInput[count].charAt(1)));
                inputMarbles.add(new Marble(c1, c2, turnAlphaToBool(splitInput[count].charAt(2))));
                count++;
            }

            //For each Marble object in inputMarbles, put into inputBoard  Board object.
            for (int i = 0; i < inputMarbles.size(); ++i) {
                inputBoard.add(inputMarbles.get(i));
            }

            System.out.println(inputMarbles);
            Game game = new Game(inputBoard, isBlack, 100, 100, timer);
            outputMoves = aiPlayer.genPossibleMoves(game, isBlack);


            //Create file for printing to
            PrintWriter moveWriter = new PrintWriter("Results.move");
            PrintWriter boardWriter = new PrintWriter("Results.board");

            // write to Results.move the list of moves possible for current layout
            for (Move m : outputMoves) {
                moveWriter.println(m);
                //Write to Results.board the board state space
                for(int i = 0; i < m.getMovedList().size(); ++i) {
                    outputMarbles = aiPlayer.genResultState(game, m, isBlack);
//                    Collections.sort(outputMarbles, new ArrayList<Marble>());

                    //Now convert each marble to its original format e.g. A1w , B9b.. etc
                    for(Marble n : outputMarbles) {
                        char c1 = (char)(n.getAlpha() + ASCII_CONSTANT);
                        int c2 = n.getNumeric();
                        char c3 = n.getColor();

                        boardOutput.add(c1 + "" + c2 + c3 );
                    }
                    boardWriter.println(boardOutput);
                }
            }

            //The file will not update until this is done
            boardWriter.close();
            moveWriter.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

      // 'w' = black ; 'b' = true
    private static boolean turnAlphaToBool(char alpha) {
        boolean state = false;
        switch (alpha) {
            case 'W':
                state = false;
                break;
            case 'B':
                state = true;
                break;
        }
        return state;
    }

    public static void writeOutput(ArrayList<Move> moves) {

    }
}
