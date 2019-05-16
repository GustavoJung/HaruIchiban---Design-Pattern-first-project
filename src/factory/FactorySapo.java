/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import model.Sapo;

/**
 *
 * @author mrcar
 */
public abstract class FactorySapo {
    public abstract Sapo criarSapoAmarelo();
    public abstract Sapo criarSapoVermelho();
}
