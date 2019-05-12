/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabuleiro;

import model.FlorAmarela;
import model.FlorVermelha;
import model.FundoTabuleiro;
import model.Peca;
import model.RegiaEscura;
import model.SapoAmarelo;
import model.SapoVermelho;

/**
 *
 * @author mrcar
 */
public class Tabuleiro {
    
    private Peca[][] tabuleiro;

    public Peca[][] getTabuleiro() {
        return tabuleiro;
    }
    
    private Tabuleiro() { 
        this.tabuleiro = new Peca[5][5];
    }
	
	private static Tabuleiro instance;
	
	public synchronized static Tabuleiro getInstance() {
		if (instance == null)
			instance = new Tabuleiro();
		
		return instance;
	}

    public void colocaFlor(int x, int y, String cor) {
        System.out.println("coloca flor cor " + cor);
        if(cor.equalsIgnoreCase("Vermelho"))
            tabuleiro[x][y] = new FlorVermelha();
        else
            tabuleiro[x][y] = new FlorAmarela();
            
    }
    
    public void colocaSapo(int x, int y, String cor){
        if(cor.equalsIgnoreCase("Vermelho"))
            tabuleiro[x][y] = new SapoVermelho();
        else
            tabuleiro[x][y] = new SapoAmarelo();
            
    }
    
    public void moveNenufar(int x, int y, int key){
        Peca aux = null;
        switch(key){
            case 39:
                
                 aux = tabuleiro[x][y];
                tabuleiro[x][y] = new FundoTabuleiro();
                tabuleiro[x+1][y] = aux;
                break;
            case 37:
                
                 aux = tabuleiro[x][y];
                tabuleiro[x][y] = new FundoTabuleiro();
                tabuleiro[x-1][y] = aux;
                break;
            case 38:
               
                aux = tabuleiro[x][y];
                tabuleiro[x][y] = new FundoTabuleiro();
                tabuleiro[x][y-1] = aux;
                break;
            case 40:
              
                 aux = tabuleiro[x][y];
                tabuleiro[x][y] = new FundoTabuleiro();
                tabuleiro[x][y+1] = aux;
                break;
        }
        
    }

    public void novaRegiaEscura(int x, int y) {
        tabuleiro[x][y] = new RegiaEscura();
    }

    
    
}
