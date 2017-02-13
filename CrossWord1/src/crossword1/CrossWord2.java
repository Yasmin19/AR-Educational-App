/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crossword1;

import java.util.ArrayList;
import java.util.List;

/**
 * Algorithm that takes list of words
 */
public class CrossWord2 {

    private static char[][] board = new char[10][10];
    private static boolean[][] bAvailability = new boolean[10][10];
    private static List<String> words = new ArrayList<>();

    public static void populateList() {
        words.add("DINOSAUR");
        words.add("BICYCLE");
        words.add("KEY");
        words.add("BALL");
        words.add("RABBIT");
        words.add("CHAIR");
        
        /* 
        ~~~~REARRANGED~~~~
        DINOSAUR (8)
        BICYCLE  (7)
        RABBIT   (6)
        CHAIR    (5)
        BALL     (4)
        KEY      (3)       
        */

    }

    public static void rearrange() {

        boolean sorted = false;
        //Bubble Sort
        while (!sorted) {
            for (int j = 0; j < words.size() - 1; j++) {
                sorted = true;
                if (words.get(j).length() < words.get(j + 1).length()) {
                    String temp = words.get(j + 1);
                    words.set(j + 1, words.get(j));
                    words.set(j, temp);
                    sorted = false;
                }
            }
        }

        System.out.println(words);
    }

    public static void populateBoard() {
        //SHOULD HAVE CLASS CALLED WORD THAT CONTAINS LENGTH OF WORD ETC.. ARRAY OF WORDS?????
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board.length; y++) {
                board[x][y] = '-';
            }
        }
        
        for (int x = 0; x < bAvailability.length; x++) {
            for (int y = 0; y < bAvailability.length; y++) {
                bAvailability[x][y] = true;
            }
        }
                
        String word = "DINOSAUR";  
        int length = 8;
        boolean across = true;
        boolean down = false;
        int startx = 4;
        int starty = 0;
        int endx = 0;
        int endy = 0;
        
        int lettercount = 0;
        if (across) {
            endx = startx;
            endy = starty + length;
            
            for (int col=starty; col<length; col++){
                board[startx][col] = word.charAt(lettercount);
                bAvailability[startx][col] = false;
                lettercount++;
            }
            
        }
        else{
            endx = startx + length;
            endy = starty;
        }

        
        word = "RABBIT";
        length = 6;
        
        //look for any intersections
        for (int pos=0; pos<word.length(); pos++){ //Loop through each letter
            for (int row=0; row<board.length; row++){
                for (int col=0; col<board.length; col++){
                    if (board[row][col] == word.charAt(pos)){
                        if (check("RABBIT", row, col, pos))
                            break;
                    }
                }
            }
        }
        
      
        
    }
    
    public static boolean check(String word, int row, int col, int pos){
        //String word = "RABBIT";
        boolean across = false;
        boolean down = false;
        int LEFT = col-1;
        int RIGHT = col+1;
        int TOP = row+1;
        int BOTTOM = row-1;
        
        System.out.println("right: " + LEFT + " " + row);
        System.out.println("left: " + RIGHT + " " + row);
        int lettercount = 0;
        System.out.println("I'M HERE, pos:" + pos);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
        //1st Check
        if ((bAvailability[row][RIGHT]) && (bAvailability[row][RIGHT])){
            across = true;
            System.out.println("ACROSS: " + across);
        }
        if ((bAvailability[TOP][col]) && (bAvailability[BOTTOM][col])){
            down = true;
            System.out.println("DOWN: " + down);
        }
     
        //2nd Check
        if (across){
            
            //if ()
            return true;
            
        }
        else if (down){
            System.out.println(row >= pos);
            System.out.println((board.length - row >= word.length() - pos) + " " +  (word.length() - pos - 1));
            if ((row >= pos) && (board.length - row >= word.length() - pos)){
                //3rd check
                int start = row-pos;
                for (int r= start; r<start+word.length(); r++){
                    System.out.println("I'M ALL THE WAY HERE");
                    if (!(bAvailability[r][col])){
                        if (r!=pos){
                            down = !down;
                            return false;
                        }
                    }
                }
                for (int r= start; r<start+word.length(); r++){
                    board[r][col] = word.charAt(lettercount);
                    bAvailability[r][col] = false;
                    down = !down;
                    lettercount++;
                }
            }
                
        }
                
         
        return true;
    }

    public static void displayBoard() {

        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board.length; y++) {
                System.out.print(board[x][y] + " ");
            }
            System.out.println("");
        }
        
        for (int x = 0; x < bAvailability.length; x++) {
            for (int y = 0; y < bAvailability.length; y++) {
                System.out.print(bAvailability[x][y] + "   ");
            }
            System.out.println("");
            System.out.println("");
        }
    }
}



/*
          - - - - - - - c - - 
          - - - - - - - H - -
          - - - - - - - A - - 
          - - - - - R - I - - 
          D I N O S A U R - -
          - - - - - B - - - - 
          - - - - - B A L L -  
          - - - - - I - - - - 
          - - - - - T - - - -
          - - - - - - - - - -
*/
