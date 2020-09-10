package edu.jsu.mcis;

/*
Seth Medders
Acessed: 9.10.20
*/

import java.util.*;

public class TicTacToeModel {
    
    private Mark[][] board; /* Game board */
    private boolean xTurn;  /* True if X is current player */
    private int width;      /* Size of game board */
    
    /* ENUM TYPE DEFINITIONS */
    
    /* Mark (represents X, O, or an empty square */
    
    public enum Mark {
        
        X("X"), 
        O("O"), 
        EMPTY("-");

        private String message;
        
        private Mark(String msg) {
            message = msg;
        }
        
        @Override
        public String toString() {
            return message;
        }
        
    };
    
    /* Result (represents the final state of the game: X wins, O wins, a TIE,
       or NONE if the game is not yet over) */
    
    public enum Result {
        
        X("X"), 
        O("O"), 
        TIE("TIE"), 
        NONE("NONE");

        private String message;
        
        private Result(String msg) {
            message = msg;
        }
        
        @Override
        public String toString() {
            return message;
        }
        
    };
    
    /* CONSTRUCTOR */
    
    public TicTacToeModel() {
        
        this(TicTacToe.DEFAULT_WIDTH);
        
    }
    
    /* CONSTRUCTOR */
    
    public TicTacToeModel(int width) {
        
        /* Initialize width; X goes first */
        
        this.width = width;
        xTurn = true;
        
        /* Create board (width x width) as a 2D Mark array */
        
        board = new Mark[width][width];

        /* Initialize board by filling every square with empty marks */
        
        for (int row = 0; row < width; ++row) {
            for (int col = 0; col < width; ++col) {
                board[row][col] = Mark.EMPTY;
            }
        }
        
    }

    
    public boolean makeMark(int row, int col) {

         /* Use "isValidSquare()" to check if the specified location is in range,
           and use "isSquareMarked()" to see if the square is empty!  If the
           specified location is valid, make a mark for the current player, then
           toggle "xTurn" from true to false (or vice-versa) to switch to the
           other player before returning TRUE.  Otherwise, return FALSE. */

        if (this.isValidSquare(row, col) && !this.isSquareMarked(row, col)) {

            this.board[row][col] = (this.isXTurn() ? Mark.X : Mark.O);
            
            this.xTurn = !this.xTurn;
            
            return true;
        }
        
        else return false;
        
    }

    
    private boolean isValidSquare(int row, int col) {

        /* Return TRUE if the specified location is within the bounds of the board */
        
        // INSERT YOUR CODE HERE
        
        return (row >= 0 && col >= 0) && (row < this.width && col < this.width);
        
    }

    
    private boolean isSquareMarked(int row, int col) {

        /* Return TRUE if the square at specified location is marked */
        
        return (board[row][col] != Mark.EMPTY);
            
    }

    public Mark getMark(int row, int col) {

        /* Return the mark from the square at the specified location */
        
        return (isValidSquare(row, col) ? board[row][col] : Mark.EMPTY);
        
    }

    
    public Result getResult() {
        
        /* Call "isMarkWin()" to see if X or O is the winner, if the game is a
           TIE, or if the game is not over.  Return the corresponding Result
           value */

        if (this.isMarkWin(Mark.X)) return Result.X;
        else if (this.isMarkWin(Mark.O)) return Result.O;
        else if (this.isTie()) return Result.TIE;
        else return Result.NONE;
        
    }

    private boolean isMarkWin(Mark mark) {

        /* Check the squares of the board to see if the specified mark is the
           winner */
        
        // INSERT YOUR CODE HERE

        return this.hasStreak(mark, false);

    }
    
    
    private boolean hasStreak(Mark mark, boolean countEmptySpaces) {
        
        List<Mark> marks = new ArrayList<Mark>();
        
        marks.add(mark);
        if (countEmptySpaces) marks.add(Mark.EMPTY);
        
        /* Searching our Top to bottom diagonal */
        for (int i = 0; marks.contains(board[i][i]); ++i) {
            if (i == (width-1)) return true;
        }
         
        /* Searching our Bottom to top diagonal */
        for (int i = width; marks.contains(board[i-1][width-i]); --i) {
            if (i == 1) return true;
        }
        
        /* Searching our rows */
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < width && marks.contains(board[x][y]); ++y) {
                if (y == (width-1)) return true;
            }
        }
        
        /* Searching our columns */
        for (int y = 0; y < width; ++y) {
            for (int x = 0; x < width && marks.contains(board[x][y]); ++x) {
                if (x == (width-1)) return true;
            }
        }
        
        return false;
    }

    private boolean isTie() {
        
        /* Check the squares of the board to see if the game is a tie */
        
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < width; ++y) {
                if (this.board[x][y] == Mark.EMPTY) return false;
            }
        }
        
        return true;
        
    }

    public boolean isGameover() {
        
        /* Return TRUE if the game is over */

        return (Result.NONE != getResult());
        
    }

    public boolean isXTurn() {
        
        /* Getter for xTurn */

        return xTurn;
        
    }
   
    public int getWidth() {
        
        /* Getter for width */

        return width;
        
    }
    
    @Override
    public String toString() {
        
        
        StringBuilder output = new StringBuilder("  ");

        /* Output the board contents as a string (see examples) */

        //should display our columns

        for (int i = 0; i < this.width; ++i) output.append(i);
        output.append("\n");
        
        //should display our rows

        for (int x = 0; x < width; ++x) {
            output.append(new StringBuilder("\n" + x + " "));
            
            for (int y = 0; y < width; ++y) output.append(this.board[x][y]);
        }
        
        return output.toString();
        
    }
    
}