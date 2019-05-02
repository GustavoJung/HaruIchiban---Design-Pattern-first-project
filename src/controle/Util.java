/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

/**
 *
 * @author 08205268940
 */
public class Util {
     public String numeroExtenso(int numero){
        String retorno = "";
        switch(numero){
            case 1:
                retorno = "Um";
                break;
            case 2:
                retorno ="Dois";
            break;
            case 3:
                retorno ="Tres";
            break;
            case 4:
                retorno ="Quatro";
            break;
            case 5:
                retorno ="Cinco";
            break;
            case 6:
                retorno = "Seis";
            break;
            case 7:
                retorno ="Sete";
            break;
            case 8:
                retorno ="Oito";
                break;
        }
        return retorno;
    }
 
}
