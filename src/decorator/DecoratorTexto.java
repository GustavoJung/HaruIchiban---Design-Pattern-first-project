/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package decorator;

import controle.ControleJogo;
import controle.Observador;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Luciano Velho
 */
public abstract class DecoratorTexto implements Texto{
    
    private Texto texto;

	public DecoratorTexto(Texto texto) {
		this.texto = texto;
	}

    public String getTexto() {
        return texto.getTexto();
    }
  
    

   
     
	
}
