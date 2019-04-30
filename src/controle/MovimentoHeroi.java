package controle;

import model.RegiaEscura;
import model.Sapo;
import model.Peca;

public abstract class MovimentoHeroi {

	private Sapo peca;
	private int x;
	private int y;
	
	public MovimentoHeroi() {

		this.peca = criarPeca(); // outra aplicacao do template method
		
	}
	
	protected abstract Sapo criarPeca();

	public Sapo getPeca() {
		return peca;
	}
	
	/*
	 * Template Method: os quatro metodos seguintes possuem a logica basica. 
	 *    Porem, esses metodos invocam um metodo abstrato que deve ser implementado nos descendentes. 
	 *
	 */

	public void vaiParaCima(Peca peca) {
		// essa comparacao entre tipos de classes nao eh o mais recomendado...
		// vamos resolver isso no futuro com outros padroes
		if (peca == null || peca.getClass() == RegiaEscura.class || validarOutrosTiposCasas(peca)) {
			y--;
		}
	}

	public void vaiParaEsquerda(Peca peca) {
		// essa comparacao entre tipos de classes nao eh o mais recomendado...
		// vamos resolver isso no futuro com outros padroes
		if (peca == null || peca.getClass() == RegiaEscura.class || validarOutrosTiposCasas(peca)) {
			x--;
		}
	}

	public void vaiParaBaixo(Peca peca) {
		// essa comparacao entre tipos de classes nao eh o mais recomendado...
		// vamos resolver isso no futuro com outros padroes
		if (peca == null || peca.getClass() == RegiaEscura.class || validarOutrosTiposCasas(peca)) {
			y++;
		}
	}

	public void vaiParaDireita(Peca peca) {
		// essa comparacao entre tipos de classes nao eh o mais recomendado...
		// vamos resolver isso no futuro com outros padroes
		if (peca == null || peca.getClass() == RegiaEscura.class || validarOutrosTiposCasas(peca)) {
			x++;
		}
	}

	protected abstract boolean validarOutrosTiposCasas(Peca peca);

	public void zerarDeslocamento() {
		this.x = 0;
		this.y = 0;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	/*
	 * Factory Method: um metodo estatico que retorne a instancia de um objeto 
	 *     de acordo com o parametro especificado.
	 */
	
	public static MovimentoHeroi criarMovimentoHeroi(String tipoHeroi) {
		return null;
	}
	
}
