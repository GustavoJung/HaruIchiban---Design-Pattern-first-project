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
public class BuscaTipoPecaTabuleiroSapoVermelho implements BuscaTipoPecaTabuleiro{

   private int x;
    private int y;

    public BuscaTipoPecaTabuleiroSapoVermelho() {
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
    
     public int[] getSapoVermelho(){
          Peca[][]tabuleiro = ControleTabuleiro.getInstance().getTabuleiro();
          int [] retorno = new int[2] ;
          for(int i=0; i<5; i++){
              for( int j=0; j<5; j++){
                  if(tabuleiro[i][j].getImagem().toString().equalsIgnoreCase("imagens/sapoVermelho.png")){
                      //coluna
                      retorno[0] = i;
                      //linha  
                      retorno[1] = j;
                  }
              }
          }
          return retorno;
     }

    @Override
    public boolean isPeca(Peca peca) throws Exception {
         boolean retorno = true;
         
         if(ControleTabuleiro.getInstance().getTabuleiro()[x][y].getImagem().toString().equalsIgnoreCase(peca.getImagem().toString()))
             retorno = false;
    
         return retorno;
    }
}
