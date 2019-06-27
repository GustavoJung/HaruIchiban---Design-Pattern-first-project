/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

import util.Util;

/**
 *
 * @author Luciano Velho
 */
public class EscolherFlor  extends AbstState{
    
    @Override
    public String estado(EstadoJogo estado) {
       return "Jardineiro Senior Posicione sua Flor";
    }
  
    
}
