/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import tabuleiro.Tabuleiro;

/**
 *
 * @author mrcar
 */
public class NovaRegiaEscura extends TabuleiroCommand{

    public NovaRegiaEscura(Tabuleiro tabuleiro, int x, int y, String cor) {
        super(tabuleiro, x, y, cor);
    }

    @Override
    public void execute() {
        tabuleiroC.novaRegiaEscura(x,y);
    }

    @Override
    public void undo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void redo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
