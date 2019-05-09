/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import tabuleiro.Tabuleiro;

/**
 *
 * @author mrcar
 */
public abstract class TabuleiroCommand implements Command{
    protected Tabuleiro tabuleiroC;   
    protected int x,y;
    protected String cor;
    
    public  TabuleiroCommand(Tabuleiro tabuleiro,int x, int y,String cor) {
		this.tabuleiroC = tabuleiro;
		this.x= x;
                this.y =y;   
                this.cor = cor;
	}
    
}
