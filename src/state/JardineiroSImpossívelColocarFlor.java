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
public class JardineiroSImpossívelColocarFlor extends AbstState{

    @Override
    public String estado(EstadoJogo estado) {
        return "Jardineiro S - Impossível colocar flor aqui! Coloque numa regia vazia!";
    }
    
}
