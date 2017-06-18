package br.com.fiap.banco.excecao;

public class ContaInexistenteExcecao extends Exception {

	private static final long serialVersionUID = 8985501046491187447L;

	public ContaInexistenteExcecao() {
		super("A conta informada não existe!");
	}

}
