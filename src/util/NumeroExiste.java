/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author mrcar
 */
public class NumeroExiste {
    
       public boolean numExiste(int[]vetor, int num){
         boolean retorno = false;
         
         for(int i=0; i<vetor.length; i++){
             if(vetor[i] == num)
                 retorno = true;
         }
         return retorno;
     }
}
