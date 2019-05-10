package controle;

public interface Observador {

	void mudouTabuleiro();

	void iniciouJogo();

	void fimDeJogo(String msgErro);
        
        void jardineiroJunior(int player);

        void notificarMudancaFlor(int numero, String player);

        void florAmarelaClicked();
        
        void florVermelhaClicked();

        void notifcarJogoIniciou();

        void notificarFloracaoAutomatica();
        
        void notificarJogadaAconteceu(String acaoAtual);

    public void notificarColocarSapo();

    public void removeListener();
        
}
