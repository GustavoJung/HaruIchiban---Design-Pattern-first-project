/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package builder;

import tabuleiro.ControleTabuleiro;

/**
 *
 * @author Gustavo Jung
 */
public abstract class TabuleiroBuilder {
    
    public abstract void reset();
    
    public abstract ControleTabuleiro getTabuleiro();
    
    public abstract void construirRegias();

    public abstract void construirSapos();

    public abstract void construirFundo();

    
}
