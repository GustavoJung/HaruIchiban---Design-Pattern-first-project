/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import tabuleiro.Tabuleiro;

/**
 *
 * @author 08205268940
 */
public class ColocaSapo extends TabuleiroCommand{
     
    public ColocaSapo(Tabuleiro tabuleiro, int x, int y, String cor) {
        super(tabuleiro, x,  y,cor);
    }
    
    @Override
    public void execute() {
       tabuleiroC.colocaSapo(x, y, cor);
    }

    @Override
    public void undo() {
        
    }

    @Override
    public void redo() {
    
    }
    
}
