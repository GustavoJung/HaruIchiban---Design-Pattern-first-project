/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabuleiro;

import model.FlorAmarela;
import model.FlorVermelha;
import model.Peca;
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
        System.out.println("coloca sapo cor " + cor);
        if(cor.equalsIgnoreCase("Vermelho"))
            tabuleiro[x][y] = new SapoVermelho();
        else
            tabuleiro[x][y] = new SapoAmarelo();
            
    }

    
    
}
