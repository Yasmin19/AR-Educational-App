package com.example.yasmin.educationalaugmentedreality;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Algorithm that takes list of words
 */
public class CrossWord {

    private static char[][] board = new char[10][10];
    private static boolean[][] bAvailability = new boolean[10][10];
    private static ArrayList<String> words = new ArrayList<>();
    public static HashMap<String, Integer> wordPos = new HashMap<>();
    public static HashMap<String, String> wordOrien = new HashMap<>();
    private static HashMap<String,Boolean> loc = new HashMap<String,Boolean>();
    private static boolean cont = true;
    public static int wordLength = 0;
    public static String selectedWord = "";
    public static String wordOrientation = "DOWN";


    public static void populateList() {


       words.addAll(Items.getWordList());

        for (int i=0; i<words.size(); i++){
            Log.d("CROSSWORD", ""+ words.get(i));
        }

        words.add("IGLOO");

/*
        words.add("BICYCLE");
        words.add("DINOSAUR");
        words.add("BALL");
        words.add("PHONE");
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

    }

    public static void populateBoard() {

        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board.length; y++) {
                board[x][y] = ' ';
            }
        }

        for (int x = 0; x < bAvailability.length; x++) {
            for (int y = 0; y < bAvailability.length; y++) {
                bAvailability[x][y] = true;
            }
        }

        String word = words.get(0);//Get first word on list to place on board

        Random random = new Random();
        boolean across = random.nextBoolean();
        int startx = 0;
        int starty = 0;

        int lettercount = 0;


        /*****/
        across = false;
        /*****/

        if (across) {
            startx = 4;
            starty = (Math.round((10 - word.length())/2)) - 1;

            for (int col=starty; col<word.length()+starty; col++){
                board[startx][col] = word.charAt(lettercount);
                bAvailability[startx][col] = false;
                lettercount++;
            }

        }
        else{
            startx = (Math.round((10 - word.length())/2)) - 1;
            starty = 4;

            for (int row=startx; row<word.length()+startx; row++){
                board[row][starty] = word.charAt(lettercount);
                bAvailability[row][starty] = false;
                lettercount++;
            }
        }


        for (int i=1; i<words.size(); i++){
            search(words.get(i));
        }
    }

    public static void search(String word){
        Random random = new Random();
        boolean top = random.nextBoolean();

        cont = true;
        if (top){
            //look for any intersections
            for (int row=0; row<board.length; row++){
                for (int col=0; col<board.length; col++){
                    if (cont){
                        for (int pos=0; pos<word.length(); pos++){ //Loop through each letter
                            if (board[row][col] == word.charAt(pos)){
                                if (check(word, row, col, pos))
                                    break;//If return true, break out of loop
                            }
                        }
                    }
                }
            }
        }
        else{
            //look for any intersections
            for (int row=board.length-1; row>=0; row--){
                for (int col=board.length-1; col>=0; col--){
                    if (cont){
                        for (int pos=0; pos<word.length(); pos++){ //Loop through each letter
                            if (board[row][col] == word.charAt(pos)){
                                if (check(word, row, col, pos))
                                    break;//If return true, break out of loop
                            }
                        }
                    }
                }
            }
        }


    }

    public static boolean check(String word, int row, int col, int pos){
        boolean across = false;
        boolean down = false;
        int lettercount = 0;


        if ((col-1)>=0)
            loc.put("LEFT", true);
        else
            loc.put("LEFT", false);

        if ((col+1)<10)
            loc.put("RIGHT", true);
        else
            loc.put("RIGHT", false);

        if ((row-1)>=0)
            loc.put("TOP", true);
        else
            loc.put("TOP", false);

        if ((row+1)<10)
            loc.put("BOTTOM", true);
        else
            loc.put("BOTTOM", false);

        //2nd checks
        if (loc.get("LEFT") && loc.get("RIGHT")){
            if ((bAvailability[row][col+1]) && (bAvailability[row][col-1])){
                across = true;
            }
        }
        else if (loc.get("LEFT")){
            if (bAvailability[row][col-1]){
                across = true;
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
                int start = col - pos;
                for (int c=start; c<start+word.length(); c++){
                    if (!bAvailability[row][c]){
                        if (c!=col){
                            return false;
                        }
                    }
                }
                wordPos.put(word, getPos(row, start));
                wordOrien.put(word, "ACROSS");
                for (int c=start; c<start+word.length(); c++){
                    board[row][c] = word.charAt(lettercount);
                    bAvailability[row][c] = false;
                    lettercount++;
                }
            }
            cont = false;
            return true;
        }

        if (down){
            if ((row >= pos)//BEFORE
                    && (board.length - row >= word.length() - pos)){ //AFTER
                //3rd check
                int start = row-pos;
                for (int r= start; r<start+word.length(); r++){
                    if (!(bAvailability[r][col])){ //Check if occupied
                        if (r!=row){ //Make sure not to include intersection field  {
                            return false;
                        }
                    }

                }
                wordPos.put(word, getPos(row, start));
                wordOrien.put(word, "DOWN");
                for (int r= start; r<start+word.length(); r++){
                    board[r][col] = word.charAt(lettercount);
                    bAvailability[r][col] = false;
                    lettercount++;
                }
            }
        }
        cont = false;
        return true;
    }

    public static int getPos(int row, int col){
        return Integer.parseInt(String.valueOf(row) + String.valueOf(col));
    }


    public static char getItem(int pos){

        String posStr;
        char rowStr, colStr;
        int row, col;
        posStr = String.valueOf(pos);

        if (pos < 10){
            posStr = String.format("%02d", pos);
        }

        rowStr = posStr.charAt(0);
        colStr = posStr.charAt(1);
        row = Character.getNumericValue(rowStr);
        col = Character.getNumericValue(colStr);

        return board[row][col];
    }

    public static int checkPosition(int pos){

        for (String word: wordPos.keySet()) {
            if ((wordOrien.get(word)).equals("ACROSS")) {
                if ((wordPos.get(word) <= pos) && ((wordPos.get(word) + word.length()) >= pos)) {
                    wordLength = word.length();
                    wordOrientation = "ACROSS";
                    selectedWord = word;
                    Log.d("SELECTED WORD", ""+word);
                    Log.d("WORDLENGTH", "WORD LENGTH: " + wordLength);
                    return wordPos.get(word);
                }
            }
            else {
                if ((wordPos.get(word) <= pos) && (wordPos.get(word) + word.length() * 10 >= pos)) {
                    wordLength = word.length();
                    wordOrientation = "DOWN";
                    selectedWord = word;
                    Log.d("SELECTEDDOWN", ""+word);
                    return wordPos.get(word);
                }
            }
        }

        return 0;
    }
}
