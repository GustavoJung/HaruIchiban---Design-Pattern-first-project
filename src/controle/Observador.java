package controle;

public interface Observador {

	void mudouTabuleiro();

	void iniciouJogo();

	void fimDeJogo(String msgErro);
        
        void jardineiroJunior(int player);

        void notificarMudancaFlor(int numero, String player);

        void florAmarelaClicked();
        
        void florVermelhaClicked();
        
        
}
