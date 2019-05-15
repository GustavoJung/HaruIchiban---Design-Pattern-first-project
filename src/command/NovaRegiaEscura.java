/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import controle.ControleJogo;

/**
 *
 * @author mrcar
 */
public class NovaRegiaEscura implements Command{

    ControleJogo controlador;
    int x,y;   
    
    public NovaRegiaEscura(ControleJogo controle, int x, int y) {
        this.controlador = controle;
        this.x = x;
        this.y = y;
    }

    @Override
    public void execute() {
        controlador.novaRegiaEscura(x,y);
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
