package controle;

import javax.swing.Icon;

public interface ControleJogo {

	void inicializar() throws Exception;

	void addObservador(Observador obs);
        
        Icon getPeca(int col, int row);
        
        void jardineiroJunior();
        
        void sortearNPlayer1();
        
        void sortearNPlayer2();
   
        void setPlayerFirstNumber(int numero, String player);

        String converteNumero(int i);

        void changeFlowers(int i, String amarelo);
 
        void florClicada(String cor);

        void posicionaSapo(String cor, int x, int y);

        void colocaFlor(String cor, int x, int y);
      
        void jogoIniciou();

        void primeiraRodada();

        void floracaoAutomatica();

        void moveCells(int selectedRow, int selectedColumn, int keyCode);

        void novaRegiaEscura(int x, int y);

        void posicionaSapoRegia(String sapoClicked, int selectedColumn, int selectedRow);

        void mudaValor();
      
        int[] getp1();
        
        int[] getp2();
    
        void setPlayer1(String player1);
        
        void setPlayer2(String player2);
        
        String getPlayer1();
        
        String getPlayer2();
        
        String getJardineiroJ();
        
        String getJardineiroS();

        int getNPlayer1();
        
        int getNPlayer2();
        
        String getSapoClicked(); 
        
        int getAuxPosicaoClicadaVermelho();
    
        int getAuxPosicaoClicadaAmarelo();
}
