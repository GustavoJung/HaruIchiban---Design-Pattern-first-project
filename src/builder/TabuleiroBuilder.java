/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package builder;

import tabuleiro.Tabuleiro;

/**
 *
 * @author mrcar
 */
public abstract class TabuleiroBuilder {
    
    public abstract void reset();
    
    public abstract Tabuleiro getTabuleiro();
    
    public abstract void construirRegias();

    public abstract void construirSapos();

    public abstract void construirFundo();

    public abstract void build();
}
