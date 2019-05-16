/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;


import model.FundoTabuleiro;
import model.Regia;


/**
 *
 * @author 08205268940
 */
public abstract class StaticPecaFactory {
    
    public abstract Regia criarRegia();
    public abstract Regia criarRegiaEscura();
    public abstract FundoTabuleiro criarFundoTabuleiro();
    
   
    
}
