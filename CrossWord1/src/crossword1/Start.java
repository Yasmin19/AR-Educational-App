/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crossword1;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yasmin
 */
public class Start {
    private static char[][] board = new char[10][10];
    private static List<String> words = new ArrayList<>();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
	CrossWord2.populateList();
	CrossWord2.rearrange();
        CrossWord2.populateBoard();
        CrossWord2.displayBoard();
        
    }
    
 
}
