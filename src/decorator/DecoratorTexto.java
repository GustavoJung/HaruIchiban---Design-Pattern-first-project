/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package decorator;



/**
 *
 * @author Luciano Velho
 */
public abstract class DecoratorTexto implements Texto{
    
    private Texto texto;

	public DecoratorTexto(Texto texto) {
		this.texto = texto;
	}

    @Override
    public String getTexto() {
        return texto.getTexto();
    }
  
    

   
     
	
}
