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

    public void notificarColocarSapo(String acao);

    public void removeListener();

    public void notificarSelecionouCelula(int selectedColumn, int selectedRow);

    public void notificarColocouFlor(String acao);

    public void notificarMoveuCelula(String acaoAtual);

    public void notificarFlorLocalErrado(String acaoAtual);
    
    void notificarNovaRegiaEscura(String acaoAtual);

    public void notificarSapoLocalErrado(String acaoAtual);

    public void notificarSapoColocado(String acaoAtual);

    public void notificarRegiaEscuraInvalida(String acaoAtual);

    public void notificarRemoveListenerRegiaEscura(String acaoAtual);

    public void notificarRemoveListeners();
        
}
