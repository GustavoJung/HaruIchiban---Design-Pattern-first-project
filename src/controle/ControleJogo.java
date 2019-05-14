package controle;

import javax.swing.Icon;

public interface ControleJogo {

	void inicializar() throws Exception;

	Icon getPeca(int col, int row) throws Exception;

	void pressTecla(int keyCode) throws Exception;

	void run() throws Exception;

	void setTipoHeroi(String tipoHeroi) throws Exception;

	void addObservador(Observador obs);
        
        void jardineiroJunior();
        
        void sortearNPlayer1();
        
        void sortearNPlayer2();
        
        void setPlayer1(String player1);
        
        void setPlayer2(String player2);
        
        String getPlayer1();
        
        String getPlayer2();
        
        String getJardineiroJ();
        
        String getJardineiroS();
        
        void setPlayerFirstNumber(int numero, String player);

        String converteNumero(int i);
        
        int getNPlayer1();
        
        int getNPlayer2();

        void changeFlowers(int i, String amarelo);
 
        void florClicada(String cor);

        void posicionaSapo(String cor, int x, int y);

        void colocaFlor(String cor, int x, int y);
        
        int[] getp1();
        
        int[] getp2();

        void jogoIniciou();

        void primeiraRodada();

        void floracaoAutomatica();
        
        String getSapoClicked();

    public void moveCells(int selectedRow, int selectedColumn, int keyCode);

    public void selecionaCelulaMovimentar(int selectedColumn, int selectedRow);

    public void novaRegiaEscura(int x, int y);

    public void posicionaSapoRegia(String sapoClicked, int selectedColumn, int selectedRow);

    int getAuxPosicaoClicadaVermelho();
    
    int getAuxPosicaoClicadaAmarelo();

    public void mudaValor();

    
}
