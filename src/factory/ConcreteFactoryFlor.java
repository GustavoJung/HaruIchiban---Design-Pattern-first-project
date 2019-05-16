/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import model.Flor;
import model.FlorAmarela;
import model.FlorVermelha;
/**
 *
 * @author Gustavo Jung
 */
public class ConcreteFactoryFlor extends FactoryFlor{

    private static ConcreteFactoryFlor instance;

    public synchronized static ConcreteFactoryFlor getInstance() {
        if (instance == null) {
            instance = new ConcreteFactoryFlor();
        }
        return instance;
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
