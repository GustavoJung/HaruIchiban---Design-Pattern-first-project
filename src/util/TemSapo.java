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
public class TemSapo {
      public String temSapo(int x, int y){
         String retorno = "";
         
         if(ControleTabuleiro.getInstance().getTabuleiro()[x][y].getImagem().toString().equalsIgnoreCase("imagens/sapoVermelho.png")){
             retorno = "sapoVermelho";            
         }else if(ControleTabuleiro.getInstance().getTabuleiro()[x][y].getImagem().toString().equalsIgnoreCase("imagens/sapoAmarelo.png")) {
                 retorno = "sapoAmarelo";  
        }
         return retorno;
     }
}
