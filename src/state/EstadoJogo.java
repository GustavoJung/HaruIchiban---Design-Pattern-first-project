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
public class EstadoJogo {
    
    private AbstState estado;
 
    public String colocaFlor(){
        estado = new EscolherFlor();
        return estado.estado(this);
    }
    
}
