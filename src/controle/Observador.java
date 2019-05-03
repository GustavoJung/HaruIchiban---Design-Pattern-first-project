package controle;

public interface Observador {

	void mudouTabuleiro();

	void iniciouJogo();

	void fimDeJogo(String msgErro);
        
        void jardineiroJunior(int player);

    public void notificarMudancaFlor(int numero, String player);

        
        
        
}
