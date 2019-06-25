/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import tabuleiro.ControleTabuleiro;

/**
 *
 * @author mrcar
 */
public class NaoFundo {
   
    public boolean naoFundo(int x, int y){
         boolean retorno = true;
         
         if(ControleTabuleiro.getInstance().getTabuleiro()[x][y].getImagem().toString().equalsIgnoreCase("imagens/fundoTabuleiro.png"))
             retorno = false;
         
         return retorno;
     }

}
