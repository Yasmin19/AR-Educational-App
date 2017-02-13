/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crossword1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * 
 */
public class CrossWord2 {

    private static char[][] board = new char[10][10];
    private static boolean[][] bAvailability = new boolean[10][10];
    private static List<String> words = new ArrayList<>();
    private static HashMap<String,Boolean> loc = new HashMap<String,Boolean>();

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
                
        String word = words.get(0);//Get first word on list to place on board
        
        int length = word.length();
        Random random = new Random();
        boolean across = random.nextBoolean();
        int startx = 0;
        int starty = 0;
        
        int lettercount = 0;
        if (across) {
            startx = 4;
            starty = 0;
            
            for (int col=starty; col<word.length(); col++){
                board[startx][col] = word.charAt(lettercount);
                bAvailability[startx][col] = false;
                lettercount++;
            }
            
        }
        else{
            startx = 0;
            starty = 4;
            
            for (int row=startx; row<word.length(); row++){
                board[row][starty] = word.charAt(lettercount);
                bAvailability[row][starty] = false;
                lettercount++;
            }
        }

         word = "ORANGE";
       
        //look for any intersections
        for (int pos=0; pos<word.length(); pos++){ //Loop through each letter
            for (int row=0; row<board.length; row++){
                for (int col=0; col<board.length; col++){
                    if (board[row][col] == word.charAt(pos)){
                        if (check(word, row, col, pos))
                            break;//If return true, break out of loop
                    }
                }
            }
        }
             
        word = "RABBIT";
       
        //look for any intersections
        for (int pos=0; pos<word.length(); pos++){ //Loop through each letter
            for (int row=0; row<board.length; row++){
                for (int col=0; col<board.length; col++){
                    if (board[row][col] == word.charAt(pos)){
                        if (check(word, row, col, pos))
                            break;//If return true, break out of loop
                    }
                }
            }
        }
             
       
    
        word = "BALL";
               //look for any intersections
        for (int pos=0; pos<word.length(); pos++){ //Loop through each letter
            for (int row=board.length-1; row>=0; row--){
                for (int col=board.length-1; col>=0; col--){
                    if (board[row][col] == word.charAt(pos)){
                        if (check(word, row, col, pos))
                            break;//If return true, break out of loop
                    }
                }
            }
        }
    }
    
    public static boolean check(String word, int row, int col, int pos){
        boolean across = false;
        boolean down = false;
        int lettercount = 0;
        
        System.out.println("-------------------");
        System.out.println("------------NEW RUN----------");
        System.out.println(word);
        
        if ((col-1)>=0)
            loc.put("LEFT", true);
        else
            loc.put("LEFT", false);
        
        if ((col+1)>=0)
            loc.put("RIGHT", true);
        else
            loc.put("RIGHT", false);
        
        if ((row-1)>=0)
            loc.put("TOP", true);
        else
            loc.put("TOP", false);
        
        if ((row+1)>=0)
            loc.put("BOTTOM", true);
        else
            loc.put("BOTTOM", false);

        /***********DEBUG*******
        System.out.println("right: " + loc.get("LEFT") + " " + row + " " + word);
        System.out.println("left: " + loc.get("RIGHT") + " " + row + " " + word);
        System.out.println("top: " + loc.get("TOP") + " " + row + " " + word);
        System.out.println("bottom: " + loc.get("BOTTOM") + " " + row + " " + word);
        */

        /************************/
        System.out.println("RIGHT: " + (row) + " " + (col+1));
        
        //1st checks
        if (loc.get("LEFT") && loc.get("RIGHT")){
            if ((bAvailability[row][col+1]) && (bAvailability[row][col-1])){
                across = true;
                System.out.println("ACROSS: " + across);
            } 
        }
        else if (loc.get("LEFT")){
            if (bAvailability[row][col-1]){
                across = true;
                System.out.println("ACROSS: " + across);
            }
        }
        else if (loc.get("RIGHT")){
            if (bAvailability[row][col+1]){
                across = true;
            }
        }
        
        if (loc.get("TOP") && loc.get("BOTTOM")){
            if ((bAvailability[row-1][col]) && (bAvailability[row+1][col])){
                down = true;
            }
        }
        else if (loc.get("TOP")){
            if (bAvailability[row-1][col]){
                down = true;
            }
        }
        else if (loc.get("BOTTOM")){
            if (bAvailability[row+1][col]){
                down = true;
            }
        }
    
        
        System.out.println("Across " + across);
        System.out.println("Down " + down);
    
        
        boolean result = placeWord(word, row, col, pos, across, down, lettercount);
        if (!result)
            result = placeWord(word, row, col, pos, across, down, lettercount);
      return result;   
    }
    
    public static boolean placeWord(String word, int row, int col, int pos, boolean across, boolean down, int lettercount){
        
         if (across){    
            if ((col >= pos) //BEFORE
                    && (board.length - col >= word.length() - pos)){ //AFTER
                //3rd check
                
                System.out.println("COL: " + col + "POS " + pos);
                System.out.println(word + " has passed the board test");
                int start = col - pos;
                System.out.println("START POS: " + start);
                for (int c=start; c<start+word.length(); c++){ 
                    if (c!=pos){
                        if (!bAvailability[row][c]){
                            System.out.println("I've failed :("); 
                            return false;
                        }
                    }   
                }
                
                for (int c=start; c<start+word.length(); c++){
                    board[row][c] = word.charAt(lettercount);
                    bAvailability[row][c] = false;
                    lettercount++;
                }
            }
            return true;
            
        }
        if (down){
            if ((row >= pos)//BEFORE
                    && (board.length - row >= word.length() - pos)){ //AFTER
                //3rd check
                int start = row-pos;
                for (int r= start; r<start+word.length(); r++){
                    System.out.println(word + " has passed the board test");
                    if (r!=pos){ //Make sure not to include intersection field  
                         if (!(bAvailability[r][col])){ //Check if occupied                  
                            System.out.println("I've failed :("); 
                            return false;
                        }
                    }
                }
                for (int r= start; r<start+word.length(); r++){
                    board[r][col] = word.charAt(lettercount);
                    bAvailability[r][col] = false;
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

