package br.com.fiap.banco.excecao;

public class PrazoEmprestimoExcedidoExcecao extends Exception {
	
	private static final long serialVersionUID = -6576489931121833674L;

	public PrazoEmprestimoExcedidoExcecao() {
		super("O prazo máximo para pagamento do empréstimo não pode exceder 36 meses (3 anos)");
	}

}
