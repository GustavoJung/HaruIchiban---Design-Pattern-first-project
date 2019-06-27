/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

/**
 *
 * @author Luciano Velho
 */
public class JardineiroSImpossívelcolocarosapo extends AbstState{

    @Override
    public String estado(EstadoJogo estado) {
        return "Jardineiro S - Impossível colocar o sapo! Coloque numa regia clara vazia!";
    }
    
}
