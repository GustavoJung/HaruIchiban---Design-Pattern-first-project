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
    public String JardineiroSPosicioneSapo(){
        estado = new JardineiroSPosicionoSapo();
        return estado.estado(this);
    }
    
    public String jardineiroJSelecionaRegia(){
        estado = new JardineiroJSelecionearegia();
        return  estado.estado(this);
    }
    public String jardineiroSImpossívelColocarFlor(){
        estado = new JardineiroSImpossívelColocarFlor();
        return estado.estado(this);
    }
    public String jardineiroJImpossívelColocarosapo(){
        estado = new JardineiroJImpossívelColocarosapo();
        return estado.estado(this);
    }
    public String jardineiroSImpossíveltornaremrégiaescura(){
        estado = new JardineiroSImpossíveltornaremrégiaescura();
        return estado.estado(this);
    }
    public String fimdaRodada(){
        estado = new FimdaRodada();
        return estado.estado(this);
    }
    public String JardineiroSImpossívelcolocarosapo(){
        estado = new JardineiroJImpossívelColocarosapo();
        return estado.estado(this);
    }
    
}