package br.com.fiap.banco.excecao;

/**
 * Exceção para tratamento quando solicitar um empréstimo com um valor maior que o máximo permitido para a conta
 *
 */
public class ValorEmprestimoExcedidoExcecao extends Exception {
	
	private static final long serialVersionUID = -3674565706679030569L;

	public ValorEmprestimoExcedidoExcecao() {
		super("O valor de empréstimo solicitado excede o limite máximo permitido");
	}

}
