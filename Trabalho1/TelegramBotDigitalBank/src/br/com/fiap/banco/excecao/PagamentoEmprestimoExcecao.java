package br.com.fiap.banco.excecao;

/**
 * Exceção para tratamento quando tentar pagar uma parcela e não existir ou já estiver paga
 *
 */
public class PagamentoEmprestimoExcecao extends Exception {

	private static final long serialVersionUID = 4497697016628439950L;

	public PagamentoEmprestimoExcecao() {
		super("Não é possível realizar o pagamento da parcela selecionada. A parcela não existe ou já está paga.");
	}
}
