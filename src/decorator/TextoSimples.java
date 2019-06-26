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
public class TextoSimples  implements Texto{

   private String texto;

	public TextoSimples(String texto) {
		this.texto = texto;
	}
	
	@Override
	public String getTexto() {
		return texto;
	}
	
	@Override
	public String toString() {
		return getTexto();
	}
    
}
