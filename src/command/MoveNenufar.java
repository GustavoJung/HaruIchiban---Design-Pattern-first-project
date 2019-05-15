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
public class MoveNenufar implements Command{

    ControleJogo controlador;
    int keyPressed;
    int x,y;
  
    public MoveNenufar(ControleJogo controle, int x, int y,int key) {
        this.keyPressed = key;
        this.controlador = controle;
        this.x = x;
        this.y = y;
    }

    @Override
    public void execute() {  
       controlador.moveCells(y, x, keyPressed); 
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
