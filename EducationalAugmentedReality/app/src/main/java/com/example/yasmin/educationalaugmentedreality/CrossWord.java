package com.example.yasmin.educationalaugmentedreality;

import java.util.ArrayList;
import java.util.List;

/**
 * Algorithm that takes list of words
 */
public class CrossWord {

    private static char[][] board = new char[10][10];
    private static List<String> words = new ArrayList<>();


    public CrossWord(){

    }

    public static void populateList(){
        words.add("DINOSAUR");
        words.add("BICYCLE");
        words.add("KEY");
        words.add("BALL");
        words.add("RABBIT");
        words.add("CHAIR");

    }
    public static void rearrange(){

    }

    public static void displayboard(){
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board.length; j++){

            }
        }
    }
}
