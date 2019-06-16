/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.pontuacaoRodada;

import controle.Observador;

/**
 *
 * @author mrcar
 */
public interface IPontuacaoRodada {
    
    void addObservador(Observador obs);
    
    int calculaPontosAmarelo();
    
    int calculaPontosVermelho();
    
    int calculaPontosLinhaVermelho();
     
    int calculaPontosLinhaAmarelo(); 

    int calculaPontosColunaVermelho();
    
    int calculaPontosColunaAmarelo();

    int calculaPontosDiagonalPrincipalAmarelo();
    
    int calculaPontosDiagonalPrincipalVermelho();

    int calculaPontosDiagonalSeecundariaAmarelo();
    
    int calculaPontosDiagonalSecundarioVermelho();


}
