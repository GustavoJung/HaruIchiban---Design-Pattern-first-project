package controle;

import builder.TabuleiroBuilder;
import builder.TabuleiroConcreto;
import command.ColocaFlor;
import command.CommandInvoker;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.Icon;

import model.Peca;
import tabuleiro.Tabuleiro;
import view.Director;

public class ControleJogoImpl implements ControleJogo {
        private String player1;
        private int nPlayer1 = -1;
        private int nPlayer2 = -1;
	private Peca[][] tabuleiro;
	private int ultTecla;
	private String tipoHeroi;
	private MovimentoHeroi movimentoHeroi;
	private List<Observador> observadores = new ArrayList<>();
	private CommandInvoker commandInvoker;
        
	@Override
	public void addObservador(Observador obs) {
		observadores.add(obs);
	}
	
	@Override
	public void inicializar() {
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

    @Override
    public void jardineiroJunior() {
        if((this.nPlayer1 != -1 && this.nPlayer2 != -1) && (this.nPlayer1 < this.nPlayer2)){ 
            for(Observador obs: observadores){
                obs.jardineiroJunior(1);
            }
        }else{
            if(this.nPlayer1 != -1 && this.nPlayer2 != -1){  
                for(Observador obs: observadores){
                    obs.jardineiroJunior(2);
                }
            }
         }
    }
    
    @Override
    public int[] sortearNPlayer1() {
        Random r = new Random();
        int [] player1 = new int[8];
        for(int i=0; i<8; i++){
            player1[i] = r.nextInt(8) + 1;
        }
        return player1;
    }

    @Override
    public int[] sortearNPlayer2() {
        Random r = new Random();
        int [] player2 = new int[8];
        for(int i=0; i<3; i++){
            player2[i] = r.nextInt(8) + 1;
        }
        return player2;    
    }
    
    public void setPlayer1(String player1){
        this.player1 = player1;
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
        return new Util().numeroExtenso(i);
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
        for(Observador obs: observadores){
        obs.notificarMudancaFlor(num, cor);
            break;
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
    public void addListeners() {
      
    }

    @Override
    public void colocaFlor(String cor, int x, int y) {
        commandInvoker.execute(new ColocaFlor(Tabuleiro.getInstance(), x, y, cor));
        for(Observador obs: observadores){
            obs.mudouTabuleiro();
        }
                
    }

    @Override
    public String getPlayer1() {
        return this.player1;
    }
}
