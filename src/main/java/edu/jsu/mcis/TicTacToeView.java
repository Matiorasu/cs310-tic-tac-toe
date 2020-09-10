package edu.jsu.mcis;

/*
Seth Medders
Acessed: 9.10.20
*/

import java.util.Scanner;

public class TicTacToeView {
    
    private final Scanner keyboard;
    
    /* CONSTRUCTOR */
	
    public TicTacToeView() {
        
        /* Initialize scanner (for console keyboard) */
        
        keyboard = new Scanner(System.in);
        
    }
	
    public TicTacToeMove getNextMove(boolean isXTurn) {
        
        /* Prompt the player to enter the row and the column of their next move.
           Return as a TicTacToeMove object. */

        System.out.println("\nPlayer " + (isXTurn ? "1 (X)" : "2 (O)") + " Move:");
        System.out.println("Enter the row and column numbers, separated by a space: ");
        
        return new TicTacToeMove(keyboard.nextInt(), keyboard.nextInt());
    }

    public void showInputError() {

        System.out.println("Entered location is invalid, already marked, or out of bounds.");

    }

    public void showResult(String r) {

        System.out.println(r + "!");

    }
    
    public void showBoard(String board) {
        
        System.out.println("\n\n" + board);
        
    }
	
}