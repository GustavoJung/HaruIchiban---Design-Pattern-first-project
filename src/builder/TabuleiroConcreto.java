/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package builder;

import factory.ConcreteFactory;
import factory.ConcreteFactorySapo;
import tabuleiro.ControleTabuleiro;

/**
 *
 * @author gustavo Jung
 */
public class TabuleiroConcreto extends TabuleiroBuilder{
    private ControleTabuleiro tabuleiro;
    private ConcreteFactory factory = new ConcreteFactory();
    
    @Override
    public void reset() {
        this.tabuleiro = ControleTabuleiro.getInstance();
    }

    @Override
    public ControleTabuleiro getTabuleiro() {
        return this.tabuleiro;
    }

    @Override
    public void construirRegias() {
        tabuleiro.getTabuleiro()[0][0] = factory.criarRegia();
        tabuleiro.getTabuleiro()[2][0] = factory.criarRegia();
        tabuleiro.getTabuleiro()[4][0] = factory.criarRegia();
        tabuleiro.getTabuleiro()[1][1] = factory.criarRegia();
        tabuleiro.getTabuleiro()[3][1] = factory.criarRegiaEscura();
        tabuleiro.getTabuleiro()[0][2] = factory.criarRegia();
        tabuleiro.getTabuleiro()[1][2] = factory.criarRegia();
        tabuleiro.getTabuleiro()[3][2] = factory.criarRegia();
        tabuleiro.getTabuleiro()[4][2] = factory.criarRegia();
        tabuleiro.getTabuleiro()[1][3] = factory.criarRegia();
        tabuleiro.getTabuleiro()[2][3] = factory.criarRegia();
        tabuleiro.getTabuleiro()[0][4] = factory.criarRegia();
        tabuleiro.getTabuleiro()[2][4] = factory.criarRegia();
        tabuleiro.getTabuleiro()[4][4] = factory.criarRegia();
        
    }

    @Override
    public void construirSapos() {
        tabuleiro.getTabuleiro()[2][1] = ConcreteFactorySapo.getInstance().criarSapoAmarelo();
        tabuleiro.getTabuleiro()[3][3] = ConcreteFactorySapo.getInstance().criarSapoVermelho();
    }

    @Override
    public void construirFundo() {
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                if(getTabuleiro().getTabuleiro()[i][j] == null){
                        tabuleiro.getTabuleiro()[i][j] = factory.criarFundoTabuleiro();
                }
            }
        }
    }

   
    
}
