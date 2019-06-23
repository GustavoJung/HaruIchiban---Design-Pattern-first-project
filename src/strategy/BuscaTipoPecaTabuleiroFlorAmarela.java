/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strategy;

import model.Peca;
import tabuleiro.ControleTabuleiro;

/**
 *
 * @author mrcar
 */
public class BuscaTipoPecaTabuleiroFlorAmarela implements BuscaTipoPecaTabuleiro{
    
    private int x;
    private int y;

    public BuscaTipoPecaTabuleiroFlorAmarela() {
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    @Override
    public boolean isPeca(Peca peca) throws Exception {
        boolean retorno = true;
         
         if(ControleTabuleiro.getInstance().getTabuleiro()[x][y].getImagem().toString().equalsIgnoreCase(peca.getImagem().toString()))
             retorno = false;
    
         return retorno;
    
    }
    
}
