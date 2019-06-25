/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import model.Peca;
import tabuleiro.ControleTabuleiro;

/**
 *
 * @author 08205268940
 */
public class Util {

    public Util() {
    }
    
  
     public int[] getRegiaEscura(){
          Peca[][]tabuleiro = ControleTabuleiro.getInstance().getTabuleiro();
          int [] retorno = new int[2] ;
          for(int i=0; i<5; i++){
              for( int j=0; j<5; j++){
                  if(tabuleiro[i][j].getImagem().toString().equalsIgnoreCase("imagens/regiaEscura.png")){
                      //coluna
                      retorno[0] = i;
                      //linha  
                      retorno[1] = j;
                  }
              }
          }
          return retorno;
     }
     
     public int[] getSapoAmarelo(){
          Peca[][]tabuleiro = ControleTabuleiro.getInstance().getTabuleiro();
          int [] retorno = new int[2] ;
          for(int i=0; i<5; i++){
              for( int j=0; j<5; j++){
                  if(tabuleiro[i][j].getImagem().toString().equalsIgnoreCase("imagens/sapoAmarelo.png")){
                      //coluna
                      retorno[0] = i;
                      //linha  
                      retorno[1] = j;
                  }
              }
          }
          return retorno;
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
     
   
     
     
    
    
    public boolean naoSapo(int x, int y) {
        boolean retorno = true;
         
         if(ControleTabuleiro.getInstance().getTabuleiro()[x][y].getImagem().toString().equalsIgnoreCase("imagens/sapoAmarelo.png") ||
         ControleTabuleiro.getInstance().getTabuleiro()[x][y].getImagem().toString().equalsIgnoreCase("imagens/sapoVermelho.png"))
             retorno = false;
         
         return retorno;
    }

    public int randomValue(int[] p1, int nPlayer1) {
       int retorno = -1;
       for(int i=0; i<8; i++){
           if(p1[i] != nPlayer1)
               retorno = p1[i];
       }
       
       return retorno;
    }
   
    
    public boolean florVermelha(int x, int y) {
        boolean retorno = false;
         
         if(ControleTabuleiro.getInstance().getTabuleiro()[x][y].getImagem().toString().equalsIgnoreCase("imagens/florVermelha.png"))
             retorno = true;
    
         return retorno;
    }
    
}
