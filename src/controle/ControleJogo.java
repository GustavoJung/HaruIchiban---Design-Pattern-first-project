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
        
        int[] sortearNPlayer1();
        
        int[] sortearNPlayer2();
        
        void setPlayer1(String player1);
        
        String getPlayer1();
        
        void setPlayerFirstNumber(int numero, String player);

        public String converteNumero(int i);
        
        int getNPlayer1();
        
        int getNPlayer2();

        void changeFlowers(int i, String amarelo);
 
        void florClicada(String cor);

    public void addListeners();

    public void colocaFlor(String cor, int x, int y);
}
