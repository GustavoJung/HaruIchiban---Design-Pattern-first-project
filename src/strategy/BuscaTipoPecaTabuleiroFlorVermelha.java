/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strategy;

import model.FlorVermelha;
import model.Peca;
import tabuleiro.ControleTabuleiro;

/**
 *
 * @author mrcar
 */
public class BuscaTipoPecaTabuleiroFlorVermelha implements BuscaTipoPecaTabuleiro{
    int x;
    int y;
    
    public BuscaTipoPecaTabuleiroFlorVermelha(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public boolean isPeca(Peca peca) throws Exception {
        boolean retorno = false;
         
         if(ControleTabuleiro.getInstance().getTabuleiro()[x][y].getImagem().toString().equalsIgnoreCase(new FlorVermelha().getImagem().toString())){
             retorno = true; 
         }
         return retorno;
    
    }
}
