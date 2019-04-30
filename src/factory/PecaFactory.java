/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import model.Flor;
import model.FundoTabuleiro;
import model.Regia;
import model.Sapo;

/**
 *
 * @author 08205268940
 */
public abstract class PecaFactory {
    
    public abstract Regia criarRegia();
    public abstract Regia criarRegiaEscura();
    public abstract FundoTabuleiro criarFundoTabuleiro();
    public abstract Sapo criarSapoAmarelo();
    public abstract Sapo criarSapoVermelho();
    public abstract Flor criarFlorVermelha();
    public abstract Flor criarFlorAmarela();
    
}
