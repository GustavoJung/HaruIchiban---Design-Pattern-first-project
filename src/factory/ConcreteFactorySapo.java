/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import model.Sapo;
import model.SapoAmarelo;
import model.SapoVermelho;

/**
 *
 * @author mrcar
 */
public class ConcreteFactorySapo extends FactorySapo{

    private static ConcreteFactorySapo instance;

    public synchronized static ConcreteFactorySapo getInstance() {
        if (instance == null) {
            instance = new ConcreteFactorySapo();
        }
        return instance;
    }

    
    @Override
    public Sapo criarSapoAmarelo() {
        return new SapoAmarelo();
    }

    @Override
    public Sapo criarSapoVermelho() {
        return new SapoVermelho();
    }
    
}
