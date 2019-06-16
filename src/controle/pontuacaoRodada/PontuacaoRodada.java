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
import model.Peca;
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
       return calculaPontosLinhaAmarelo() + calculaPontosColunaAmarelo() + calculaPontosDiagonalPrincipalAmarelo()
               + calculaPontosDiagonalSeecundariaAmarelo();
        
    }
    
    @Override
    public int calculaPontosVermelho(){
        return calculaPontosLinhaVermelho() + calculaPontosColunaVermelho() + calculaPontosDiagonalPrincipalVermelho()
                + calculaPontosDiagonalSecundarioVermelho();
    }
    
    
    public PontuacaoRodada(String p1, String p2){
        util = new Util();
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
            }
            pontuacaoVermelha = calcPontuacao(auxContaFlorVermelha);
            
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
                    if(util.florAmarela(j,i) == false){
                        auxContaFlorAmarela++;
                    }
            }
            pontuacaoAmarela = calcPontuacao(auxContaFlorAmarela);
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
            }
            pontuacaoAmarela = calcPontuacao(auxContaFlorAmarela);
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
            }
            pontuacaoVermelha = calcPontuacao(auxContaFlorVermelha);
            
            auxContaFlorVermelha=0; 
        }
        return pontuacaoVermelha;
    }
    
    private int calcPontuacao(int auxContaFlor) {
        if(auxContaFlor == 4){
            return 2;
        }else if(auxContaFlor == 5){
            return auxContaFlor;
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
                        System.out.println("AUMENTO 1 ONDE DEVE CHEGAR A 4");
                    }
                }
            }
        }
        pontuacaoVermelha = calcPontuacao(auxContaFlorVermelha);
        System.out.println("Pontuacao vermelho = " + pontuacaoVermelha);

        auxContaFlorVermelha=0; 
        return pontuacaoVermelha;
    }

    @Override
    public int calculaPontosDiagonalPrincipalAmarelo() {
        System.out.println("diagonal amarela");
        int auxContaFlorAmarelo = 0; 
        int pontuacaoAmarelo =0;
        
        for(int i=0; i<tabuleiro.length; i++){
            for(int j=0; j<tabuleiro.length; j++){
                if(i==j){
                    if(util.florVermelha(j, i) == false){
                        auxContaFlorAmarelo++;
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
        
        for(int i=tabuleiro.length-1; i>=0; i--){
            for(int j=tabuleiro.length-1; j>=0; j--){
                if(i==j){
                    if(util.florVermelha(i, j) == false){
                        auxContaFlorAmarelo++;
                    }
                }
            }
            pontuacaoAmarelo += calcPontuacao(auxContaFlorAmarelo);
            
        }
            auxContaFlorAmarelo=0; 
        
        return pontuacaoAmarelo;
    }

    @Override
    public int calculaPontosDiagonalSecundarioVermelho() {
        int auxContaFlorVermelha = 0; 
        int pontuacaoVermelha =0;
        
        for(int i=tabuleiro.length-1; i>=0; i--){
            for(int j=tabuleiro.length; j>=0; j--){
                if(i==j){
                    if(util.florVermelha(i, j) == false){
                        auxContaFlorVermelha++;
                    }
                }
            }
            pontuacaoVermelha += calcPontuacao(auxContaFlorVermelha);
            
        }
        auxContaFlorVermelha=0; 
        
        return pontuacaoVermelha;    
    }

    
}