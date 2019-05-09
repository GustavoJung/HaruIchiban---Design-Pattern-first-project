/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabuleiro;

import model.FlorAmarela;
import model.FlorVermelha;
import model.Peca;

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
        if(!cor.equalsIgnoreCase("Vermelho"))
            tabuleiro[x][y] = new FlorVermelha();
        else
            tabuleiro[x][y] = new FlorAmarela();
            
    }

    
    
}
