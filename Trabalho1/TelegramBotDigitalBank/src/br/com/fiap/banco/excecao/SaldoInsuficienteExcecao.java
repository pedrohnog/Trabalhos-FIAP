package br.com.fiap.banco.excecao;

/**
 * Exceção para tratamento quando tentar realizar uma operação na qual não tenha saldo suficiente
 *
 */
public class SaldoInsuficienteExcecao extends Exception {

	private static final long serialVersionUID = -8600292077740904195L;

	public SaldoInsuficienteExcecao() {
		super("Saldo insuficiente!");
	}
}
