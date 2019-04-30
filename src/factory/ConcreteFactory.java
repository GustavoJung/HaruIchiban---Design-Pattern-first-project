/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import model.Flor;
import model.FlorAmarela;
import model.FlorVermelha;
import model.FundoTabuleiro;
import model.Regia;
import model.RegiaClara;
import model.RegiaEscura;
import model.Sapo;
import model.SapoAmarelo;
import model.SapoVermelho;

/**
 *
 * @author 08205268940
 */
public class ConcreteFactory extends PecaFactory{

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

    @Override
    public Sapo criarSapoAmarelo() {
        return new SapoAmarelo();
    }

    @Override
    public Sapo criarSapoVermelho() {
        return new SapoVermelho();
    }

    @Override
    public Flor criarFlorVermelha() {
        return new FlorVermelha();
    }

    @Override
    public Flor criarFlorAmarela() {
        return new FlorAmarela();
    }
    
}
