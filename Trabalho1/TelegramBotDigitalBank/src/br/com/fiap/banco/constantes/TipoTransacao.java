package br.com.fiap.banco.constantes;

/**
 * Enum responsável por mapear os tipos de transações existentes
 *
 */
public enum TipoTransacao {

	/**
	 * Constante que define a transação de saque
	 */
	SAQUE(1),
	/**
	 * Constante que define a transação de depósito
	 */
	DEPOSITO(2),
	/**
	 * Constante que define a transação de tarifas pagas nas operações
	 */
	TARIFA(3),
	/**
	 * Constante que define a transação de solicitação de empréstimo
	 */
	EMPRESTIMO(4),
	/**
	 * Constante que define a transação de juros pagos nas parcelas do empréstimo
	 */
	JUROS(5),
	/**
	 * Constante que define a transação de parcelas pagas do empréstimo
	 */
	PAGAMENTO_EMPRESTIMO(6);

	private int codigo;

	private TipoTransacao(int codigo) {
		this.codigo = codigo;
	}

	public int getCodigo() {
		return codigo;
	}

	public static TipoTransacao getTipoTransacao(int codigo) {
		if (codigo > 0) {
			for (TipoTransacao tipoTransacao : values()) {
				if (tipoTransacao.codigo == codigo) {
					return tipoTransacao;
				}
			}
		}
		return null;
	}

}
