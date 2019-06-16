/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import model.Peca;
import tabuleiro.ControleTabuleiro;

/**
 *
 * @author 08205268940
 */
public class Util {
     public String numeroExtenso(int numero){
        String retorno = "";
        switch(numero){
            case 1:
                retorno = "Um";
                break;
            case 2:
                retorno ="Dois";
            break;
            case 3:
                retorno ="Tres";
            break;
            case 4:
                retorno ="Quatro";
            break;
            case 5:
                retorno ="Cinco";
            break;
            case 6:
                retorno = "Seis";
            break;
            case 7:
                retorno ="Sete";
            break;
            case 8:
                retorno ="Oito";
                break;
        }
        return retorno;
    }
 
     public boolean numExiste(int[]vetor, int num){
         boolean retorno = false;
         
         for(int i=0; i<vetor.length; i++){
             if(vetor[i] == num)
                 retorno = true;
         }
         return retorno;
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
     
     public String temSapo(int x, int y){
         String retorno = "";
         
         if(ControleTabuleiro.getInstance().getTabuleiro()[x][y].getImagem().toString().equalsIgnoreCase("imagens/sapoVermelho.png")){
             retorno = "sapoVermelho";            
         }else if(ControleTabuleiro.getInstance().getTabuleiro()[x][y].getImagem().toString().equalsIgnoreCase("imagens/sapoAmarelo.png")) {
                 retorno = "sapoAmarelo";  
        }
         return retorno;
     }
     
     public boolean naoFundo(int x, int y){
         boolean retorno = true;
         
         if(ControleTabuleiro.getInstance().getTabuleiro()[x][y].getImagem().toString().equalsIgnoreCase("imagens/fundoTabuleiro.png"))
             retorno = false;
         
         return retorno;
     }

    public boolean naoFlor(int x, int y) {
        boolean retorno = true;
         
         if(ControleTabuleiro.getInstance().getTabuleiro()[x][y].getImagem().toString().equalsIgnoreCase("imagens/florAmarela.png") ||
            ControleTabuleiro.getInstance().getTabuleiro()[x][y].getImagem().toString().equalsIgnoreCase("imagens/florVermelha.png")){
             retorno = false;
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
   
    public boolean florAmarela(int x, int y) {
        boolean retorno = true;
         
         if(ControleTabuleiro.getInstance().getTabuleiro()[x][y].getImagem().toString().equalsIgnoreCase("imagens/florAmarela.png"))
             retorno = false;
    
         return retorno;
    }
    
    public boolean florVermelha(int x, int y) {
        boolean retorno = true;
         
         if(ControleTabuleiro.getInstance().getTabuleiro()[x][y].getImagem().toString().equalsIgnoreCase("imagens/florVermelha.png"))
             retorno = false;
    
         return retorno;
    }
    
}
