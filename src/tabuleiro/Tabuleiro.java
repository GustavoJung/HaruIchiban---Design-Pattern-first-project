/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabuleiro;

import model.Flor;
import model.FlorAmarela;
import model.FlorVermelha;
import model.FundoTabuleiro;
import model.Peca;
import model.RegiaEscura;
import model.SapoAmarelo;
import model.SapoVermelho;

/**
 *
 * @author mrcar
 */
public class Tabuleiro {

    private Peca[][] tabuleiro;
    private boolean stateMovement = true;

    public boolean getStateMovement() {
        return stateMovement;
    }

    public Peca[][] getTabuleiro() {
        return tabuleiro;
    }

    private Tabuleiro() {
        this.tabuleiro = new Peca[5][5];
    }

    private static Tabuleiro instance;

    public synchronized static Tabuleiro getInstance() {
        if (instance == null) {
            instance = new Tabuleiro();
        }
        return instance;
    }

    public void colocaFlor(int x, int y, String cor) {
        if (cor.equalsIgnoreCase("Vermelho")) {
            tabuleiro[x][y] = new FlorVermelha();
        } else {
            tabuleiro[x][y] = new FlorAmarela();
        }
    }

    public void colocaSapo(int x, int y, String cor) {
        if (cor.equalsIgnoreCase("Vermelho")) {
            tabuleiro[x][y] = new SapoVermelho();
        } else {
            tabuleiro[x][y] = new SapoAmarelo();
        }

    }

    public void moveNenufar(int x, int y, int key) throws Exception {
        Peca aux = null;
        switch (key) {
            case 39:
                moveDireita(x, y);
                break;
            case 37:
                moveEsquerda(x, y);
                break;
            case 38:

                aux = tabuleiro[x][y];
                tabuleiro[x][y] = new FundoTabuleiro();
                tabuleiro[x][y - 1] = aux;
                break;
            case 40:

                aux = tabuleiro[x][y];
                tabuleiro[x][y] = new FundoTabuleiro();
                tabuleiro[x][y + 1] = aux;
                break;
        }
    }

    public void novaRegiaEscura(int x, int y) {
        tabuleiro[x][y] = new RegiaEscura();
    }

    private void moveDireita(int x, int y) throws Exception {
        int aux1 = x;
        Peca aux2 = null;
        Peca aux = tabuleiro[aux1][y];

        if (aux1 + 1 < 5) {
            aux2 = tabuleiro[aux1 + 1][y];
        }

        if (aux2 != null) {
            if (aux2.getImagem().toString().equalsIgnoreCase("imagens/fundoTabuleiro.png")) {
                tabuleiro[x][y] = new FundoTabuleiro();
                tabuleiro[x + 1][y] = aux;
            } else if (x + 1 > 5) {
                stateMovement = false;
            } else {
                int auxiliar = 0;

                for (int i = x; i < 5; i++) {
                    if (!tabuleiro[i][y].getImagem().toString().equalsIgnoreCase("imagens/fundoTabuleiro.png")) {
                        auxiliar++;
                    }
                }

                if (x + auxiliar >= 5) {
                    stateMovement = false;
                } else {
                    int cont = 0;
                    while (cont < auxiliar) {
                        tabuleiro[aux1 + 1][y] = aux;
                        aux = aux2;

                        if (aux1 + 1 < 4) {
                            aux1++;
                            aux2 = tabuleiro[aux1 + 1][y];
                        }
                        cont++;
                        tabuleiro[x][y] = new FundoTabuleiro();
                    }
                }
            }
        } else {
            stateMovement = false;
        }
    }

    private void moveEsquerda(int x, int y) throws Exception {
        int aux1 = x;
        Peca aux2 = null;
        Peca aux = tabuleiro[aux1][y];

        if (aux1 - 1 >= 0) {
            aux2 = tabuleiro[aux1 - 1][y];
        }
        if (aux2 != null) {
            if (aux2.getImagem().toString().equalsIgnoreCase("imagens/fundoTabuleiro.png")) {
                tabuleiro[x][y] = new FundoTabuleiro();
                tabuleiro[x - 1][y] = aux;
            } else if (x - 1 < 0) {
                stateMovement = false;
            } else {
                
                int auxiliar = 5;
                for (int i = x; i >= 0; i--) {
                    if (!tabuleiro[i][y].getImagem().toString().equalsIgnoreCase("imagens/fundoTabuleiro.png")) {
                        auxiliar--;
                    }
                }
            System.out.println("auxiliar " + auxiliar + " x " + x);
                if (x - auxiliar < 0) {
                    stateMovement = false;
                } else {
                    int cont = 0;
                    while (cont <= auxiliar) {
                        tabuleiro[aux1 - 1][y] = aux;
                        aux = aux2;

                        if (aux1 - 1 > 0) {
                            aux1--;
                            aux2 = tabuleiro[aux1][y];
                        }
                        cont++;
                        tabuleiro[x][y] = new FundoTabuleiro();
                    }
                }
            }
        } else {
            stateMovement = false;
        }
    }
}
