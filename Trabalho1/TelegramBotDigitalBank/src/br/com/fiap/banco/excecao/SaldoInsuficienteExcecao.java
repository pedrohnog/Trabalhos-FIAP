package br.com.fiap.banco.excecao;

public class SaldoInsuficienteExcecao extends Exception {

	private static final long serialVersionUID = -8600292077740904195L;

	public SaldoInsuficienteExcecao() {
		super("Saldo insuficiente!");
	}
}
