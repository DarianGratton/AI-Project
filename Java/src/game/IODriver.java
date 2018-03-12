package game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.HashMap;

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
        Board inputMarbles = new Board();       //readFile[1] will eventually be put into this
        ArrayList<Move> outputMoves = new ArrayList<Move>();            // return from sort function


        HashMap<Board, Double> output = new HashMap<Board, Double>();
        
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
            
            for(String s: splitInput) {
                int c1 = ((int)s.charAt(0)) - ASCII_CONSTANT;
                int c2 = Character.getNumericValue(s.charAt(1));
                inputMarbles.add(new Marble(c1, c2, turnAlphaToBool(s.charAt(2))));
                
            }
            
            for(Marble m : inputMarbles){
                System.out.println(m.toString());
            }

            Game game = new Game(inputMarbles, isBlack, 100, 100, timer);
            outputMoves = aiPlayer.genPossibleMoves(game, isBlack);


            //Create file for printing to
            PrintWriter moveWriter = new PrintWriter("Results.move");
            PrintWriter boardWriter = new PrintWriter("Results.board");
            
            
            int i = 0;
            
            for(Move m : outputMoves){
                moveWriter.println(m);
                System.out.println(i + " " + m.toString());
                ++i;
            }
            
            output = aiPlayer.genAllResults(game, outputMoves);
            
            StringBuilder mrbl;
            StringBuilder line;
            
            i = 0;
            
            for(Board b : output.keySet()){
                //Collections.sort(b, new SortArray());
                line = new StringBuilder();
                for(Marble m : b){
                    mrbl = new StringBuilder();
                    char c1 = (char)(m.getAlpha() + ASCII_CONSTANT);
                    int c2 = m.getNumeric();
                    char c3 = m.getColor();

                    mrbl.append(c1);                    
                    mrbl.append(c2);
                    mrbl.append(c3);
                    mrbl.append(',');
                    line.append(mrbl.toString());
                }  
                
                boardOutput.add(line.toString());
                System.out.println(i + " " + line.toString());
                ++i;
            }

            for(String s : boardOutput){
                boardWriter.println(s);
            }
            //The file will not update until this is done
            boardWriter.close();
            moveWriter.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

      // 'w' = false ; 'b' = true
    private static boolean turnAlphaToBool(char alpha) {
        char upper = Character.toUpperCase(alpha);
        if(upper == 'B'){
            return true;
        } else {
            return false;
        }
    }

    public static void writeOutput(ArrayList<Move> moves) {

    }
}
