/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import model.FundoTabuleiro;
import model.Regia;
import model.RegiaClara;
import model.RegiaEscura;


/**
 *
 * @author 08205268940
 */
public class ConcreteFactory extends StaticPecaFactory{

    @Override
    public Regia criarRegia() {
        return new RegiaClara();
    }

    @Override
    public Regia criarRegiaEscura() {
        return new RegiaEscura();
    }

    @Override
    public FundoTabuleiro criarFundoTabuleiro() {
        return new FundoTabuleiro();
    }
    
}
