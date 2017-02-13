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
                System.out.print(bAvailability[x][y] + " ");
            }
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
