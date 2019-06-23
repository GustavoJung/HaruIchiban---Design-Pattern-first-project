/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.pontuacaoRodada;

import controle.Observador;
import controle.Util;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.FlorAmarela;
import model.Peca;
import strategy.BuscaTipoPecaTabuleiroFlorAmarela;
import tabuleiro.ControleTabuleiro;

/**
 *
 * @author mrcar
 */
public class PontuacaoRodada implements IPontuacaoRodada {
    private List<Observador> observadores = new ArrayList<>();
    private Util util;
    private String player1;
    private String player2;
    private Peca[][] tabuleiro;
    
    @Override
    public int calculaPontosAmarelo() {     
        return   calculaPontosLinhaAmarelo() + calculaPontosColunaAmarelo() + calculaPontosDiagonalPrincipalAmarelo()
               + calculaPontosDiagonalSeecundariaAmarelo() + calculaPontosQuadradoAmarelo(); 
    }
    
    @Override
    public int calculaPontosVermelho(){
        return calculaPontosLinhaVermelho() + calculaPontosColunaVermelho() + calculaPontosDiagonalPrincipalVermelho()
             + calculaPontosDiagonalSecundarioVermelho()+ calculaPontosQuadradoVermelho();
    }
    
    
    public PontuacaoRodada(String p1, String p2){
        //util = new Util();
        player1 = p1;
        player2 = p2;
        tabuleiro = ControleTabuleiro.getInstance().getTabuleiro();
    }

    @Override
    public void addObservador(Observador obs) {
        observadores.add(obs);
    }

    @Override
    public int calculaPontosLinhaVermelho(){
        int auxContaFlorVermelha = 0; 
        int pontuacaoVermelha =0;
        
        for(int i=0; i<tabuleiro.length; i++){
            for(int j=0; j<tabuleiro.length; j++){
                if(util.florVermelha(j, i) == false){
                    auxContaFlorVermelha++;
                }
            pontuacaoVermelha = calcPontuacao(auxContaFlorVermelha);
            }
            auxContaFlorVermelha=0; 
        }
        return pontuacaoVermelha;
    }

    @Override
    public int calculaPontosLinhaAmarelo(){
        int auxContaFlorAmarela = 0;   
        int pontuacaoAmarela =0;
      
        for(int i=0; i<tabuleiro.length; i++){
            for(int j=0; j<tabuleiro.length; j++){
                try {
                    if(1>2){
//if(new BuscaTipoPecaTabuleiroFlorAmarela(j,i).isPeca(FlorAmarela) == false){
                        auxContaFlorAmarela++;
                    }
                } catch (Exception ex) {
                    Logger.getLogger(PontuacaoRodada.class.getName()).log(Level.SEVERE, null, ex);
                }
            pontuacaoAmarela = calcPontuacao(auxContaFlorAmarela);
            }
            auxContaFlorAmarela = 0; 
        }
        return pontuacaoAmarela;
    }
    
    @Override
    public int calculaPontosColunaAmarelo(){
        int auxContaFlorAmarela = 0;   
        int pontuacaoAmarela =0;
      
        for(int i=0; i<tabuleiro.length; i++){
            for(int j=0; j<tabuleiro.length; j++){
                    if(util.florAmarela(i,j) == false){
                        auxContaFlorAmarela++;
                    }
            pontuacaoAmarela = calcPontuacao(auxContaFlorAmarela);
            }
            auxContaFlorAmarela = 0; 
        }
        return pontuacaoAmarela;
    }
    
    @Override
    public int calculaPontosColunaVermelho(){
        int auxContaFlorVermelha = 0; 
        int pontuacaoVermelha =0;
        
        for(int i=0; i<tabuleiro.length; i++){
            for(int j=0; j<tabuleiro.length; j++){
                if(util.florVermelha(i, j) == false){
                    auxContaFlorVermelha++;
                }
            pontuacaoVermelha = calcPontuacao(auxContaFlorVermelha);
            }
            auxContaFlorVermelha=0; 
        }
        return pontuacaoVermelha;
    }
    
    private int calcPontuacao(int auxContaFlor) {
        if(auxContaFlor == 5){
            return 5;
        }else if(auxContaFlor == 4){
            return 2;
        }
         return 0;
    }

    @Override
    public int calculaPontosDiagonalPrincipalVermelho() {            
        int auxContaFlorVermelha = 0; 
        int pontuacaoVermelha =0;
        
        for(int i=0; i<tabuleiro.length; i++){
            for(int j=0; j<tabuleiro.length; j++){
                if(i==j){
                    if(util.florVermelha(j, i) == false){
                        auxContaFlorVermelha++;     
                    }
                }
            }
        }
        pontuacaoVermelha = calcPontuacao(auxContaFlorVermelha);

        return pontuacaoVermelha;
    }

    @Override
    public int calculaPontosDiagonalPrincipalAmarelo() {
        BuscaTipoPecaTabuleiroFlorAmarela buscaFlorAmarela = new BuscaTipoPecaTabuleiroFlorAmarela();
        util =new Util(buscaFlorAmarela);
        util.setPeca(new FlorAmarela());
        
        int auxContaFlorAmarelo = 0; 
        int pontuacaoAmarelo =0;
        
        for(int i=0; i<tabuleiro.length; i++){
            for(int j=0; j<tabuleiro.length; j++){
                if(i==j){
                    buscaFlorAmarela.setX(j);
                    buscaFlorAmarela.setY(i);
                    try {
                        if(util.getTipoPeca()){//util.florAmarela(j, i) == false){
                            auxContaFlorAmarelo++;
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(PontuacaoRodada.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }   
        }
        
        pontuacaoAmarelo += calcPontuacao(auxContaFlorAmarelo);
        auxContaFlorAmarelo=0; 
  
        return pontuacaoAmarelo;
    }

    @Override
    public int calculaPontosDiagonalSeecundariaAmarelo() {
        int auxContaFlorAmarelo = 0; 
        int pontuacaoAmarelo =0;
        
           for(int i=0; i<tabuleiro.length; i++){
            for(int j=0; j<tabuleiro.length; j++){
                if(i+j==tabuleiro.length-1){
                    if(util.florAmarela(j, i) == false){
                        auxContaFlorAmarelo++;    
                    }
                }
            }
            pontuacaoAmarelo += calcPontuacao(auxContaFlorAmarelo);
            
        }
        
        return pontuacaoAmarelo;
    }

    @Override
    public int calculaPontosDiagonalSecundarioVermelho() {
        int auxContaFlorVermelha = 0; 
        int pontuacaoVermelha =0;
        
        for(int i=0; i<tabuleiro.length; i++){
            for(int j=0; j<tabuleiro.length; j++){
                if(i+j==tabuleiro.length-1){
                    if(util.florVermelha(j, i) == false){
                        auxContaFlorVermelha++;   
                    }
                }
            }
            pontuacaoVermelha += calcPontuacao(auxContaFlorVermelha);
            
        }
        auxContaFlorVermelha=0; 
        return pontuacaoVermelha;    
    }

    private int calculaPontosQuadradoAmarelo() {
     int auxContaFlorAmarelo = 0; 
     int pontuacaoAmarelo =0;
     
      for(int i=0; i<tabuleiro.length-1; i++){
            for(int j=0; j<tabuleiro.length-1; j++){
                if(!util.florAmarela(i, j) && !util.florAmarela(i+1, j) && !util.florAmarela(i, j+1) 
                        && !util.florAmarela(i+1, j+1)){
                    pontuacaoAmarelo = 1;
                }
            }   
      }
     
      return pontuacaoAmarelo;
    }

    private int calculaPontosQuadradoVermelho() {
        int auxContaFlorVermelha = 0; 
        int pontuacaoVermelha =0;
        
        for(int i=0; i<tabuleiro.length-1; i++){
            for(int j=0; j<tabuleiro.length-1; j++){
                if(!util.florVermelha(i, j) && !util.florVermelha(i+1, j) && !util.florVermelha(i, j+1) 
                        && !util.florVermelha(i+1, j+1)){
                    pontuacaoVermelha = 1;
                }
            }   
      }  
        return pontuacaoVermelha;  
    }
    
}