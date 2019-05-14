package controle;

import builder.TabuleiroBuilder;
import builder.TabuleiroConcreto;
import command.ColocaFlor;
import command.ColocaSapo;
import command.CommandInvoker;
import command.MoveNenufar;
import command.NovaRegiaEscura;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.Icon;

import model.Peca;
import tabuleiro.Tabuleiro;
import builder.Director;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControleJogoImpl implements ControleJogo {
        private String player1;
        private String player2;
        private String jardineiroJunior;
        private String jardineiroSenior;
        private String acaoAtual;
        private String corSapoClicked = "";
        private int nPlayer1 = -1;
        private int nPlayer2 = -1;
	private Peca[][] tabuleiro;
	private int ultTecla;
	private String tipoHeroi;
	private MovimentoHeroi movimentoHeroi;
	private List<Observador> observadores = new ArrayList<>();
	private CommandInvoker commandInvoker;
        private int[] p1;
        private int[] p2;
        private Util util;
        private int auxPosicaoClicadaVermelho;
        private int auxPosicaoClicadaAmarelo;
        
	@Override
	public void addObservador(Observador obs) {
		observadores.add(obs);
	}
	
	@Override
	public void inicializar() {
            util = new Util();
            TabuleiroBuilder tabuleiroB = new TabuleiroConcreto();
            Director director = new Director(tabuleiroB);
            director.construir();
            commandInvoker = new CommandInvoker();
            tabuleiro = tabuleiroB.getTabuleiro().getTabuleiro();
	}

	@Override
	public Icon getPeca(int col, int row) {	
		return (tabuleiro[col][row] == null?null:tabuleiro[col][row].getImagem());
	}

	@Override
	public void pressTecla(int keyCode) {
		this.ultTecla = keyCode;
	}

	@Override
	public void run() throws Exception {
		
		this.movimentoHeroi = MovimentoHeroi.criarMovimentoHeroi(tipoHeroi);
		tabuleiro[0][1] = this.movimentoHeroi.getPeca();
		notificarMudancaTabuleiro();
		
		Thread t = new Thread() {
			
			@Override
			public void run() {
				try {
					// posicoes do heroi
					int x = 0;
					int y = 1;
					
					Peca pecaAnterior = null;
					
					while (true) {
						// lerInputs
						movimentoHeroi.zerarDeslocamento();

						// como nao interessa nesse exercicio, nao estou consistindo se chegou no limite do mundo 
						switch (ultTecla) {
						    case 37: movimentoHeroi.vaiParaEsquerda(tabuleiro[x-1][y]); break;
					    	case 38: movimentoHeroi.vaiParaCima(tabuleiro[x][y-1]); break;
						    case 39: movimentoHeroi.vaiParaDireita(tabuleiro[x+1][y]); break;
						    case 40: movimentoHeroi.vaiParaBaixo(tabuleiro[x][y+1]); break;
						}
						ultTecla = 0;

						// mudar a posicao do heroi
						if (movimentoHeroi.getX() != 0) {
							Peca p = tabuleiro[x + movimentoHeroi.getX()][y];
							tabuleiro[x + movimentoHeroi.getX()][y] = movimentoHeroi.getPeca();
							tabuleiro[x][y] = pecaAnterior;
							pecaAnterior = p;
							x = x + movimentoHeroi.getX();
						} else {
							if (movimentoHeroi.getY() != 0) {
								Peca p = tabuleiro[x][y + movimentoHeroi.getY()];
								tabuleiro[x][y + movimentoHeroi.getY()] = movimentoHeroi.getPeca();
								tabuleiro[x][y] = pecaAnterior;
								pecaAnterior = p;
								y = y + movimentoHeroi.getY();
							}
						}
						
						notificarMudancaTabuleiro();						
						
						Thread.sleep(100); // soh para dar um tempinho
					}
				} catch (Exception e) {

					notificarFimJogo(e.toString());
				}
			}
		};
		t.start();
	
		notificarIniciouJogo();
		
	}

	@Override
	public void setTipoHeroi(String tipoHeroi) {
		this.tipoHeroi = tipoHeroi;
	}

    @Override
    public void jardineiroJunior() {
        if((this.nPlayer1 != -1 && this.nPlayer2 != -1) && (this.nPlayer1 < this.nPlayer2)){ 
            jardineiroJunior = player1;
            jardineiroSenior = player2;
            for(Observador obs: observadores){
                obs.jardineiroJunior(1);
            }
        }else if((this.nPlayer1 != -1 && this.nPlayer2 != -1) && (this.nPlayer1 == this.nPlayer2)){
            //floracao
            for(Observador obs: observadores){
                obs.jardineiroJunior(0);
            }
    }else{
            if(this.nPlayer1 != -1 && this.nPlayer2 != -1){  
                jardineiroJunior = player2;
                jardineiroSenior = player1;
                for(Observador obs: observadores){
                    obs.jardineiroJunior(2);
                }
            }
         }
    }
    
    @Override
    public void sortearNPlayer1() {
        Random r = new Random();
        int [] player1 = new int[8];
        int random = -1;
        for(int i=0; i<8; i++){
            random = r.nextInt(8) + 1;
            if(!util.numExiste(player1, random)){
              player1[i] =random;
            }else{
                i--;
            }
        }
        p1= player1;
    }

    @Override
    public void sortearNPlayer2() {
        Random r = new Random();
        int [] player2 = new int[8];
        int random = -1;
        
        for(int i=0; i<8; i++){
          random = r.nextInt(8)+1;
          if(!util.numExiste(player2, random)){ 
            player2[i] = random;
          }else{
              i--;
          }
        }
        p2 = player2;    
    }
    
    @Override
    public void setPlayer1(String player1){
        this.player1 = player1;   
        }
    
    @Override
    public void setPlayer2(String player2){
            this.player2 = player2;
     }

    @Override
    public void setPlayerFirstNumber(int numero,String player) {
        if(player.equalsIgnoreCase(this.player1)){
           if(this.nPlayer1 == -1)
               this.nPlayer1 = numero;
                jardineiroJunior();
        }else{
            if(this.nPlayer2 == -1)
              this.nPlayer2 = numero;
                jardineiroJunior();
        }
    }

    @Override
    public String converteNumero(int i) {  
        return util.numeroExtenso(i);
    }

    @Override
    public int getNPlayer1() {
        return this.nPlayer1;
    }

    @Override
    public int getNPlayer2() {
        return this.nPlayer2;
    }

    @Override
    public void changeFlowers(int num, String cor) {
        if(cor.equalsIgnoreCase("Vermelho"))
            auxPosicaoClicadaVermelho = num ;
        else
            auxPosicaoClicadaAmarelo = num ;
            
        for(Observador obs: observadores){
            obs.notificarMudancaFlor(num, cor);
        }
    }
	
    @Override
    public void florClicada(String cor){
        for(Observador obs: observadores){
           if(cor.equalsIgnoreCase("Amarelo")){
               obs.florAmarelaClicked();
           }else{
               obs.florVermelhaClicked();
           }
        }
    }

    @Override
    public void colocaFlor(String cor, int x, int y) {
        if(util.naoFundo(x, y) && util.naoFlor(x,y)){
            if(!util.temSapo(x, y).equalsIgnoreCase("")){
                corSapoClicked = util.temSapo(x,y).substring(4);
                acaoAtual = "Jardineiro S- Posicione o sapo " + this.corSapoClicked + "!";
                try{
                commandInvoker.execute(new ColocaFlor(Tabuleiro.getInstance(), x, y, cor));  
                colocaSapo(this.corSapoClicked); 
                }catch(Exception ex){
                    Logger.getLogger(ControleJogoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                try {
                    commandInvoker.execute(new ColocaFlor(Tabuleiro.getInstance(), x, y, cor));
                    acaoAtual = "Jardineiro J -Selecione a régia que deseja movimentar!!";
                    notificaRemoveListenerFlor(acaoAtual); 
                } catch (Exception ex) {
                    Logger.getLogger(ControleJogoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
               }                      
               notificarMudancaTabuleiro();
            }else{
            acaoAtual = "Jardineiro S - Impossível colocar flor aqui! Coloque numa regia vazia!";
                notificarFlorLocalInvalido(acaoAtual);          
            }
        }
    
    @Override
    public void posicionaSapo(String cor, int x, int y){
        if(util.naoFundo(x, y) && util.naoFlor(x,y) && util.naoSapo(x, y)){
            try {
                commandInvoker.execute(new ColocaSapo(Tabuleiro.getInstance(), x, y, cor));
                acaoAtual = "Jardineiro J - Selecione a régia que deseja movimentar!Use as setas!";
                notificarSapoColocado();
            } catch (Exception ex) {
                Logger.getLogger(ControleJogoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            }else{
             acaoAtual = "Jardineiro J - Impossível colocar o sapo! Coloque numa regia clara vazia!";
             notificarSapoLocalInvalido(acaoAtual);          
        }      
    }
       
    @Override
    public void novaRegiaEscura(int x, int y) {
        if(util.naoFundo(x, y) && util.naoFlor(x, y)){
            if(!util.temSapo(x, y).equalsIgnoreCase("")){
                corSapoClicked = util.temSapo(x, y).substring(4);
                acaoAtual = "Jardineiro S - Posicione o sapo " + this.corSapoClicked + "!";  
                    try {
                        commandInvoker.execute(new NovaRegiaEscura(Tabuleiro.getInstance(), x, y, player1));
                        colocaSapo("regiaEscura");      
                    } catch (Exception ex) {
                       Logger.getLogger(ControleJogoImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }else{
                try {
                    commandInvoker.execute(new NovaRegiaEscura(Tabuleiro.getInstance(), x, y, player1));
                       acaoAtual = "Fim da rodada! Iniciando uma nova";
                    notificarRemoveListenerNovaRegiaEscura(); 
                    notificarNovaRodada();
                } catch (Exception ex) {
                    Logger.getLogger(ControleJogoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
             } 
            notificarJogadaAconteceu(acaoAtual);
            notificarMudancaTabuleiro();
        }else{
            acaoAtual = "Jardineiro S - Impossível tornar em régia escura! Selecione uma clara!";
            notificarRegiaEscuraInvalida();
        }
    }

    @Override
    public void posicionaSapoRegia(String sapoClicked, int selectedColumn, int selectedRow) {
        if(util.naoFundo(selectedColumn, selectedRow) && util.naoFlor(selectedColumn,selectedRow) && util.naoSapo(selectedColumn, selectedRow)){
            try {
                commandInvoker.execute(new ColocaSapo(Tabuleiro.getInstance(), selectedColumn,selectedRow,sapoClicked));
                 acaoAtual = "Fim da rodada! Iniciando uma nova";
                notificarMudancaTabuleiro();
                notificarRemoveListeners();
                notificarJogadaAconteceu(acaoAtual);
            } catch (Exception ex) {
                Logger.getLogger(ControleJogoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
           }else{
             acaoAtual = "Jardineiro S - Impossível colocar o sapo! Coloque numa regia clara vazia!";
             notificarSapoLocalInvalido(acaoAtual);          
        }      
    }

    @Override
    public String getPlayer1() {
        return this.player1;
    }

    @Override
    public int[] getp1() {
        return this.p1;
    }

    @Override
    public int[] getp2() {
        return this.p2;
    }

    @Override
   public void jogoIniciou(){
       if(this.jardineiroJunior != null && this.jardineiroSenior != null){
           this.acaoAtual = "Jardineiro S - Posicione sua flor!"; 
           notificarJogoIniciou();
       }
   }

    @Override
    public void primeiraRodada() {
        int [] regiaEscura = util.getRegiaEscura();
            try {
                commandInvoker.execute(new ColocaFlor(Tabuleiro.getInstance(), regiaEscura[0],regiaEscura[1], jardineiroJunior));
                notificarJogadaAconteceu(acaoAtual);
            } catch (Exception ex) {
                Logger.getLogger(ControleJogoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    @Override
    public String getPlayer2() {
        return this.player2;
    }

    @Override
    public String getJardineiroJ() {
        return this.jardineiroJunior;
    }

    @Override
    public String getJardineiroS() {
        return this.jardineiroSenior;
    }

    @Override
    public void floracaoAutomatica() {
       int sapoAmarelo[] = util.getSapoAmarelo();
       int sapoVermelho[] = util.getSapoVermelho();
            try {
                commandInvoker.execute(new ColocaFlor(Tabuleiro.getInstance(), sapoAmarelo[0], sapoAmarelo[1], "Amarelo"));
            } catch (Exception ex) {
                Logger.getLogger(ControleJogoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                commandInvoker.execute(new ColocaFlor(Tabuleiro.getInstance(), sapoVermelho[0], sapoVermelho[1], "Vermelho"));
            } catch (Exception ex) {
                Logger.getLogger(ControleJogoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
       this.nPlayer1 = -1;
       this.nPlayer2 = -1;
       notificarFloracaoAutomatica();
    }

    public int getRandom(int num,String player){
    int retorno =-1;
        if(player.equalsIgnoreCase(player1))
        retorno = util.randomValue(p1, nPlayer1);
    else
       retorno =  util.randomValue(p1, nPlayer2);
        
        return retorno;
    }
    
    private void notificarFloracaoAutomatica(){
        for(Observador obs: observadores){
           obs.notificarFloracaoAutomatica();
       }
    }
    
    private void colocaSapo(String sapo) {
        for(Observador obs: observadores){
            obs.notificarColocarSapo(sapo);
            obs.notificarJogadaAconteceu(acaoAtual);
        }
        
    }
    
    private void notificarIniciouJogo() {
		for (Observador obs:observadores)
			obs.iniciouJogo();
	}

    private void notificarMudancaTabuleiro() {
		for (Observador obs:observadores)
			obs.mudouTabuleiro();
		
	}
	
    private void notificarFimJogo(String msgErro) {
		for (Observador obs:observadores)
			obs.fimDeJogo(msgErro);
    }
        
    private void notificarJogoIniciou(){
            for(Observador obs: observadores){
                obs.notifcarJogoIniciou();
            }
        }
        
    private void notificarJogadaAconteceu(String acaoAtual1){
         for(Observador obs: observadores){
            obs.notificarJogadaAconteceu(acaoAtual);
            }
        }

    @Override
    public String getSapoClicked() {
        return this.corSapoClicked;
    }

    @Override
    public void moveCells(int selectedRow, int selectedColumn, int keyCode) {
        try {
                commandInvoker.execute(new MoveNenufar(Tabuleiro.getInstance(), selectedColumn, selectedRow, player1, keyCode));
                if(Tabuleiro.getInstance().getStateMovement()){
                   acaoAtual = " Jardineiro Sênior: Escolha uma nova régia escura!";
                    notificarMoveuCelula(acaoAtual);
                }else
                  acaoAtual = "Jardineiro J - Movimento célula inválido!";     
                  notificarMovimentoCelulaInvalido();
            } catch (Exception ex) {
                notificarMovimentoCelulaInvalido();
            }
       }


    private void notificaRemoveListenerFlor(String acao) {
        for(Observador obs: observadores){
            obs.notificarColocouFlor(acao);
        }
    }

    private void notificarMoveuCelula(String acaoAtual) {
            for(Observador obs: observadores){
            obs.removeListener();
            obs.notificarMoveuCelula(this.acaoAtual);
            
        }     
    }

    private void notificarFlorLocalInvalido(String acaoAtual) {
        for(Observador obs: observadores){
            obs.notificarFlorLocalErrado(acaoAtual);
        }
    }

    private void notificarNovaRegiaEscura() {
        for(Observador obs: observadores){
            obs.mudouTabuleiro();
            obs.notificarNovaRegiaEscura(acaoAtual);
        }
    }

    private void notificarSapoLocalInvalido(String acaoAtual) {
        for(Observador obs: observadores){
            obs.notificarSapoLocalErrado(acaoAtual);
        }
    }

    private void notificarSapoColocado() {
        for(Observador obs: observadores){
            obs.notificarSapoColocado(acaoAtual);
        }
    }

    private void notificarRegiaEscuraInvalida() {
        for(Observador obs: observadores){
            obs.notificarRegiaEscuraInvalida(acaoAtual);
        }
    }

    private void notificarRemoveListenerNovaRegiaEscura() {
        for(Observador obs: observadores){
            obs.notificarRemoveListenerRegiaEscura(acaoAtual);
        }
    }

    private void notificarRemoveListeners() {
        for(Observador obs: observadores){
            obs.notificarRemoveListeners();
        }
    }

    public int getAuxPosicaoClicadaVermelho() {
        return auxPosicaoClicadaVermelho;
    }

    public int getAuxPosicaoClicadaAmarelo() {
        return auxPosicaoClicadaAmarelo;
    }

    @Override
    public void mudaValor() {            
        int a =  new Random().nextInt(8)+1;
        int v = new Random().nextInt(8)+1;
        
        if(this.player1.equalsIgnoreCase("Vermelho")){
            p1[this.auxPosicaoClicadaAmarelo-1] =v;
            p2[this.auxPosicaoClicadaVermelho-1] = a;
        }else{
           p1[this.auxPosicaoClicadaAmarelo-1] = a;
           p2[this.auxPosicaoClicadaVermelho-1] = v;
       }    
    }

    private void notificarMovimentoCelulaInvalido() {
        for(Observador obs: observadores){
            obs.notificarMovimentoCelulaInvalido(this.acaoAtual);
        }
    }

    private void notificarNovaRodada() {
        this.nPlayer1 =-1;
        this.nPlayer2 =-1;
        notificarMudancaTabuleiro();
        for(Observador obs: observadores){
            obs.notificarNovaRodada();
        }
    }

  
    
}
