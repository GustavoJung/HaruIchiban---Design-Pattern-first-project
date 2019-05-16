/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import model.Flor;

/**
 *
 * @author mrcar
 */
public abstract class FactoryFlor {
    public abstract Flor criarFlorVermelha();
    public abstract Flor criarFlorAmarela();
}
