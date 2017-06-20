package br.com.fiap.banco.excecao;

/**
 * Exceção para tratamento quando solicitar um novo empréstimo sem ter quitado um aberto
 *
 */
public class EmprestimoAbertoExcecao extends Exception {
	
	private static final long serialVersionUID = -8556625379904529166L;

	public EmprestimoAbertoExcecao() {
		super("O usuário já possui um empréstimo em aberto");
	}

}
