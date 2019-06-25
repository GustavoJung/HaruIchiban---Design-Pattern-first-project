/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.pontuacaoRodada;

import controle.Observador;
import util.Util;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.FlorAmarela;
import model.FlorVermelha;
import model.Peca;
import strategy.BuscaTipoPecaTabuleiro;
import strategy.BuscaTipoPecaTabuleiroFlorAmarela;
import strategy.BuscaTipoPecaTabuleiroFlorVermelha;
import tabuleiro.ControleTabuleiro;

/**
 *
 * @author mrcar
 */
public class PontuacaoRodada implements IPontuacaoRodada {

    private List<Observador> observadores = new ArrayList<>();
    private Util util;
    private String player1;
    private String player2;
    private Peca[][] tabuleiro;
    BuscaTipoPecaTabuleiro buscaPeca;

    @Override
    public int calculaPontosAmarelo() {
        return calculaPontosLinhaAmarelo() + calculaPontosColunaAmarelo() + calculaPontosDiagonalPrincipalAmarelo()
                + calculaPontosDiagonalSeecundariaAmarelo() + calculaPontosQuadradoAmarelo();
    }

    @Override
    public int calculaPontosVermelho() {
        return calculaPontosLinhaVermelho() + calculaPontosColunaVermelho() + calculaPontosDiagonalPrincipalVermelho()
                + calculaPontosDiagonalSecundarioVermelho() + calculaPontosQuadradoVermelho();
    }

    public PontuacaoRodada(String p1, String p2) {
        player1 = p1;
        player2 = p2;
        tabuleiro = ControleTabuleiro.getInstance().getTabuleiro();
        util = new Util();
    }

    @Override
    public void addObservador(Observador obs) {
        observadores.add(obs);
    }

    @Override
    public int calculaPontosLinhaVermelho() {
        int auxContaFlorVermelha = 0;
        int pontuacaoVermelha = 0;

        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro.length; j++) {
                buscaPeca = new BuscaTipoPecaTabuleiroFlorVermelha(j, i);
                try {
                    if (buscaPeca.isPeca(tabuleiro[i][j])) {
                        auxContaFlorVermelha++;
                    }
                } catch (Exception ex) {
                    Logger.getLogger(PontuacaoRodada.class.getName()).log(Level.SEVERE, null, ex);
                }
                pontuacaoVermelha = calcPontuacao(auxContaFlorVermelha);
                if(pontuacaoVermelha > 1)
                    return pontuacaoVermelha;
            }
            auxContaFlorVermelha = 0;

        }

        return pontuacaoVermelha;
    }

    @Override
    public int calculaPontosLinhaAmarelo() {
        int auxContaFlorAmarela = 0;
        int pontuacaoAmarela = 0;

        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro.length; j++) {
                try {
                    buscaPeca = new BuscaTipoPecaTabuleiroFlorAmarela(j, i);
                    if (buscaPeca.isPeca(tabuleiro[i][j])) {
                        auxContaFlorAmarela++;
                    }
                } catch (Exception ex) {
                    Logger.getLogger(PontuacaoRodada.class.getName()).log(Level.SEVERE, null, ex);
                }
                pontuacaoAmarela = calcPontuacao(auxContaFlorAmarela);
                if(pontuacaoAmarela > 1)
                    return pontuacaoAmarela;
            }
            auxContaFlorAmarela = 0;
        }

        return pontuacaoAmarela;
    }

    @Override
    public int calculaPontosColunaAmarelo() {
        int auxContaFlorAmarela = 0;
        int pontuacaoAmarela = 0;

        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro.length; j++) {
                try {
                    buscaPeca = new BuscaTipoPecaTabuleiroFlorAmarela(i, j);
                    if (buscaPeca.isPeca(tabuleiro[i][j])) {
                        auxContaFlorAmarela++;
                    }
                } catch (Exception ex) {
                    Logger.getLogger(PontuacaoRodada.class.getName()).log(Level.SEVERE, null, ex);
                }
                pontuacaoAmarela = calcPontuacao(auxContaFlorAmarela);
                if(pontuacaoAmarela > 1)
                    return pontuacaoAmarela;
            }
            auxContaFlorAmarela = 0;
        }

        return pontuacaoAmarela;
    }

    @Override
    public int calculaPontosColunaVermelho() {
        int auxContaFlorVermelha = 0;
        int pontuacaoVermelha = 0;

        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro.length; j++) {
                buscaPeca = new BuscaTipoPecaTabuleiroFlorVermelha(i, j);
                try {
                    if (buscaPeca.isPeca(tabuleiro[i][j])) {
                        auxContaFlorVermelha++;
                    }
                } catch (Exception ex) {
                    Logger.getLogger(PontuacaoRodada.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                pontuacaoVermelha = calcPontuacao(auxContaFlorVermelha);
                if(pontuacaoVermelha >1)
                     return pontuacaoVermelha;
            }
            auxContaFlorVermelha = 0;
        }

        return pontuacaoVermelha;
    }

    private int calcPontuacao(int auxContaFlor) {
        if (auxContaFlor == 5) {
            return 5;
        } else if (auxContaFlor == 4) {
            return 2;
        }
        return 0;
    }

    @Override
    public int calculaPontosDiagonalPrincipalVermelho() {
        int auxContaFlorVermelha = 0;
        int pontuacaoVermelha = 0;

        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro.length; j++) {
                if (i == j) {
                    buscaPeca = new BuscaTipoPecaTabuleiroFlorVermelha(j, i);
                    try {
                        if (buscaPeca.isPeca(tabuleiro[i][j])) {
                            auxContaFlorVermelha++;
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(PontuacaoRodada.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        pontuacaoVermelha = calcPontuacao(auxContaFlorVermelha);
        
        return pontuacaoVermelha;
    }

    @Override
    public int calculaPontosDiagonalPrincipalAmarelo() {

        int auxContaFlorAmarelo = 0;
        int pontuacaoAmarelo = 0;

        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro.length; j++) {
                if (i == j) {
                    try {
                        buscaPeca = new BuscaTipoPecaTabuleiroFlorAmarela(j, i);
                        if (buscaPeca.isPeca(tabuleiro[i][j])) {
                            auxContaFlorAmarelo++;
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(PontuacaoRodada.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }

        pontuacaoAmarelo = calcPontuacao(auxContaFlorAmarelo);

        return pontuacaoAmarelo;
    }

    @Override
    public int calculaPontosDiagonalSeecundariaAmarelo() {
        int auxContaFlorAmarelo = 0;
        int pontuacaoAmarelo = 0;

        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro.length; j++) {
                if (i + j == tabuleiro.length - 1) {
                    buscaPeca = new BuscaTipoPecaTabuleiroFlorAmarela(j, i);
                    try {
                        if (buscaPeca.isPeca(tabuleiro[i][j])) {
                            auxContaFlorAmarelo++;
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(PontuacaoRodada.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        pontuacaoAmarelo = calcPontuacao(auxContaFlorAmarelo);

        return pontuacaoAmarelo;
    }

    @Override
    public int calculaPontosDiagonalSecundarioVermelho() {
        int auxContaFlorVermelha = 0;
        int pontuacaoVermelha = 0;

        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro.length; j++) {
                if (i + j == tabuleiro.length - 1) {
                    buscaPeca = new BuscaTipoPecaTabuleiroFlorVermelha(j, i);
                    try {
                        if (buscaPeca.isPeca(tabuleiro[i][j])) {
                            auxContaFlorVermelha++;
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(PontuacaoRodada.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        pontuacaoVermelha = calcPontuacao(auxContaFlorVermelha);

        return pontuacaoVermelha;
    }

    private int calculaPontosQuadradoAmarelo() {
        int pontuacaoAmarelo = 0;

        for (int i = 0; i < tabuleiro.length - 1; i++) {
            for (int j = 0; j < tabuleiro.length - 1; j++) {
                try {
                    buscaPeca = new BuscaTipoPecaTabuleiroFlorAmarela(j, i);
                    if (buscaPeca.isPeca(tabuleiro[i][j]) && buscaPeca.isPeca(tabuleiro[i + 1][j])
                            && buscaPeca.isPeca(tabuleiro[i][j + 1]) && buscaPeca.isPeca(tabuleiro[i + 1][j + 1])) {
                        pontuacaoAmarelo = 1;
                    }
                } catch (Exception ex) {
                    Logger.getLogger(PontuacaoRodada.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return pontuacaoAmarelo;
    }

    private int calculaPontosQuadradoVermelho() {
        int pontuacaoVermelha = 0;

        for (int i = 0; i < tabuleiro.length - 1; i++) {
            for (int j = 0; j < tabuleiro.length - 1; j++) {
                buscaPeca = new BuscaTipoPecaTabuleiroFlorVermelha(j, i);
                try {
                    if (buscaPeca.isPeca(tabuleiro[i][j]) && buscaPeca.isPeca(tabuleiro[i + 1][j])
                            && buscaPeca.isPeca(tabuleiro[i][j + 1]) && buscaPeca.isPeca(tabuleiro[i + 1][j + 1])) {
                        pontuacaoVermelha = 1;
                    }
                } catch (Exception ex) {
                    Logger.getLogger(PontuacaoRodada.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return pontuacaoVermelha;
    }

}
