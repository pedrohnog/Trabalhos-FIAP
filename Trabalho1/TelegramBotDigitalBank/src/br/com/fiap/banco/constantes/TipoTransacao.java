package br.com.fiap.banco.constantes;

public enum TipoTransacao {

	SAQUE(1), DEPOSITO(2), TARIFA(3), EMPRESTIMO(4), JUROS(5);

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
