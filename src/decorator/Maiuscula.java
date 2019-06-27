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
public class Maiuscula extends DecoratorTexto{
    
    public Maiuscula(Texto texto) {
        super(texto);
    }
    

    @Override
   public String getTexto() {
        return super.getTexto().toUpperCase();
        
    }

    }


   
    

