/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strategy;

import model.FlorAmarela;
import model.Peca;


/**
 *
 * @author mrcar
 */
public class BuscaTipoPecaTabuleiroFlorAmarela implements BuscaTipoPecaTabuleiro{


    public BuscaTipoPecaTabuleiroFlorAmarela() {
    }
    
    
    @Override
    public boolean isPeca(Peca p) throws Exception {
        boolean retorno = false;
         
        if(p.getImagem().toString().equalsIgnoreCase(new FlorAmarela().getImagem().toString())){
            retorno = true;
        }
         return retorno;
    
      
    }
    
}
