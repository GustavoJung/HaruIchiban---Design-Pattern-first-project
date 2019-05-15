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

        void notificarColocarSapo(String acao);

        void removeListener();

        void notificarColocouFlor(String acao);

        void notificarMoveuCelula(String acaoAtual);

        void notificarFlorLocalErrado(String acaoAtual);
    
        void notificarNovaRegiaEscura(String acaoAtual);

        void notificarSapoLocalErrado(String acaoAtual);

        void notificarSapoColocado(String acaoAtual);

        void notificarRegiaEscuraInvalida(String acaoAtual);

        void notificarRemoveListenerRegiaEscura(String acaoAtual);

        void notificarRemoveListeners();

        void notificarMovimentoCelulaInvalido(String acaoAtual);

        void notificarNovaRodada();
        
}
