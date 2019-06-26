/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabuleiro;

import factory.ConcreteFactoryFlor;
import factory.ConcreteFactorySapo;
import model.FundoTabuleiro;
import model.Peca;
import model.RegiaEscura;


/**
 *
 * @author Gustavo Jung
 */
public class ControleTabuleiro {

    private Peca[][] tabuleiro;
    private boolean stateMovement = true;

    private static ControleTabuleiro instance;

    public synchronized static ControleTabuleiro getInstance() {
        if (instance == null) {
            instance = new ControleTabuleiro();
        }
        return instance;
    }

    public boolean getStateMovement() {
        return stateMovement;
    }

    public Peca[][] getTabuleiro() {
        return tabuleiro;
    }

    private ControleTabuleiro() {
        this.tabuleiro = new Peca[5][5];
    }
    
    public void colocaFlor(int x, int y, String cor) {
        if (cor.equalsIgnoreCase("Vermelho")) {
            tabuleiro[x][y] = ConcreteFactoryFlor.getInstance().criarFlorVermelha();
        } else {
            tabuleiro[x][y] = ConcreteFactoryFlor.getInstance().criarFlorAmarela();
        }
    }

    public void colocaSapo(int x, int y, String cor) {
        if (cor.equalsIgnoreCase("Vermelho")) {
            tabuleiro[x][y] = ConcreteFactorySapo.getInstance().criarSapoVermelho();
        } else {
            tabuleiro[x][y] = ConcreteFactorySapo.getInstance().criarSapoAmarelo();
        }

    }

    public void moveNenufar(int x, int y, int key) {
        Peca aux = null;
        switch (key) {
            case 39:
                moveDireita(x, y);
                break;
            case 37:
                moveEsquerda(x, y);
                break;
            case 38:
                moveParaCima(x, y);
                break;
            case 40:
                moveParaBaixo(x, y);
                break;
        }
    }

    public void novaRegiaEscura(int x, int y) {
        tabuleiro[x][y] = new RegiaEscura();
    }

    private void moveDireita(int x, int y) {
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
                stateMovement = true;
            } else if (x + 1 > 5) {
                stateMovement = false;
            } else {
                int auxiliar = 0;

                for (int i = x; i < 5; i++) {
                    if (!tabuleiro[i][y].getImagem().toString().equalsIgnoreCase("imagens/fundoTabuleiro.png")) {
                        auxiliar++;
                    }else
                        break;
                }
                if (x + auxiliar >= 5) {
                    stateMovement = false;
                } else {
                    int cont = 0;
                    int cont2 = auxiliar;
                    while (cont < cont2) {
                        tabuleiro[x + auxiliar][y] = tabuleiro[x+auxiliar-1][y];
                        cont++;
                        auxiliar--;
                    }
                    tabuleiro[x][y] = new FundoTabuleiro();
                    stateMovement = true;
                }
            }
        } else {
            stateMovement = false;
        }
        
    }

    private void moveEsquerda(int x, int y) {
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
                stateMovement = true;
            } else if (x - 1 < 0) {
                stateMovement = false;
            } else {
                
                int auxiliar = 0;
                for (int i = x; i > 0; i--) {
                    if (!tabuleiro[i][y].getImagem().toString().equalsIgnoreCase("imagens/fundoTabuleiro.png")) {
                        auxiliar++;
                    }else{
                        break;
                    }
                }
                if (x - auxiliar < 0) {
                    stateMovement = false;
                } else {
                    int cont = 0;
                    int cont2 = auxiliar;
                    while (cont < cont2) {
                        tabuleiro[x - auxiliar][y] = tabuleiro[x-auxiliar+1][y];
                        cont++;
                        auxiliar--;
                    }
                    tabuleiro[x][y] = new FundoTabuleiro();
                    stateMovement = true;
                }
            }
        } else {
            stateMovement = false;
        }
    }
    
    private void moveParaBaixo( int x ,int y){
           int aux1 = y;
        Peca aux2 = null;
        Peca aux = tabuleiro[x][aux1];

        if (aux1 + 1 < 5) {
            aux2 = tabuleiro[x][aux1+1];
        }
        
     if (aux2 != null) {
            if (aux2.getImagem().toString().equalsIgnoreCase("imagens/fundoTabuleiro.png")) {
                tabuleiro[x][y] = new FundoTabuleiro();
                tabuleiro[x][y + 1] = aux;
                stateMovement = true;
            } else if (y + 1 > 5) {
                stateMovement = false;
            } else {
                int auxiliar = 0;

                for (int i = y; i < 5; i++) {
                    if (!tabuleiro[x][i].getImagem().toString().equalsIgnoreCase("imagens/fundoTabuleiro.png")) {
                        auxiliar++;
                    }else 
                        break;
                }
                if (y + auxiliar >= 5) {
                    stateMovement = false;
                } else {
                    int cont = 0;
                    int cont2= auxiliar;
                    while (cont < cont2) {
                        tabuleiro[x][y+auxiliar] = tabuleiro[x][y+auxiliar-1];
                        cont++;
                        auxiliar--;            
                    }
                    tabuleiro[x][y] = new FundoTabuleiro();
                    stateMovement = true;
                }
            }
        } else {
            stateMovement = false;
        }
    }
    
    private void moveParaCima(int x , int y){
        int aux1 = y;
        Peca aux2 = null;
        Peca aux = tabuleiro[x][aux1];
        
        if(aux1 - 1 >= 0){
            aux2 = tabuleiro[x][aux1 - 1];    
        }
         if (aux2 != null) {
            if (aux2.getImagem().toString().equalsIgnoreCase("imagens/fundoTabuleiro.png")) {
                tabuleiro[x][y] = new FundoTabuleiro();
                tabuleiro[x][y -1] = aux;
                stateMovement = true;
            } else if (y - 1 < 0) {
                stateMovement = false;
            } else {
                
                int auxiliar = 0;
                for (int i = y; i > 0; i--) {
                    if (!tabuleiro[x][i].getImagem().toString().equalsIgnoreCase("imagens/fundoTabuleiro.png")) {
                        auxiliar++;
                    }else{
                        break;
                    }
                }
                if (y - auxiliar < 0) {
                    stateMovement = false;
                } else {
                    int cont = 0;
                    int cont2 =auxiliar;
  
                    while (cont < cont2) {
                        tabuleiro[x][y -auxiliar] = tabuleiro[x][y-auxiliar+1];
                        cont++;
                        auxiliar--;
                    }    
                    
                    tabuleiro[x][y] = new FundoTabuleiro();
                    stateMovement = true;
                }
            }
        } else {
            stateMovement = false;
        }
    }
}
