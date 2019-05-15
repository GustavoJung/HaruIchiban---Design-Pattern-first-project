/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import controle.ControleJogo;

/**
 *
 * @author 08205268940
 */
public class ColocaSapo implements Command{
     
    ControleJogo controlador;
    int x,y;
    String cor;
    
    public ColocaSapo(ControleJogo controle, int x, int y, String cor) {
        this.controlador = controle;
        this.x = x;
        this.y = y;
        this.cor= cor;
    }
    
    @Override
    public void execute() {
       controlador.posicionaSapo(cor, x, y);
    }

    @Override
    public void undo() {
        
    }

    @Override
    public void redo() {
    
    }
    
}
