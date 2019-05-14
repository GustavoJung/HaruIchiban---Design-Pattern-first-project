/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import java.util.logging.Level;
import java.util.logging.Logger;
import tabuleiro.Tabuleiro;

/**
 *
 * @author mrcar
 */
public class MoveNenufar extends TabuleiroCommand{

    int keyPressed;
    
    public MoveNenufar(Tabuleiro tabuleiro, int x, int y, String cor,int key) {
        super(tabuleiro, x, y, cor);
        this.keyPressed = key;
    }

    @Override
    public void execute() {
        try {
            tabuleiroC.moveNenufar(x, y, keyPressed);
        } catch (Exception ex) {
            Logger.getLogger(MoveNenufar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void undo() {
        try {
            tabuleiroC.moveNenufarUndo(x,y);
        } catch (Exception ex) {
            Logger.getLogger(MoveNenufar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void redo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
