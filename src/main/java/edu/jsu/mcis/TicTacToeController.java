package edu.jsu.mcis;

/*
Seth Medders
Acessed: 9.12.20
*/

import java.awt.event.*;
import javax.swing.*;

public class TicTacToeController implements ActionListener {

    private final TicTacToeModel model;
    private final TicTacToeView view;
    
    /* CONSTRUCTOR */

    public TicTacToeController(int width) {
        
        /* Initialize model, view, and width */

        model = new TicTacToeModel(width);
        view = new TicTacToeView(this, width);
        
    }
    
    public String getMarkAsString(int row, int col) {       
        return (model.getMark(row, col).toString());       
    }
   
    public TicTacToeView getView() {       
        return view;       
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        
        if (event.getSource().getClass() == JButton.class) {
            
            JButton pressedButton = ((JButton)event.getSource());
            
            int row, col;
            
            row = Character.getNumericValue(pressedButton.getName().charAt(6));
            col = Character.getNumericValue(pressedButton.getName().charAt(7));
            
            if (model.makeMark(row, col)) {
				
				view.showResult(pressedButton.getName());
                
                view.clearResult();
                
                view.updateSquares(row, col);
            }
            
            else {
                
                view.showResult("Entered location is invalid, already marked, or out of bounds.");
            }
            
            if (model.isGameover()) {
                
                view.disableSquares();
                
                view.showResult(model.getResult().name());
            }
            
        }
        
    }

}