package br.com.fiap.banco.constantes;

/**
 * Enum responsável pelos custos de serviço e juros
 *
 */
public enum Tarifas {
	
	/**
	 * Constante referente ao custo de serviço do saque
	 */
	SAQUE		(2.5,	0),
	/**
	 * Constante referente ao custo de serviço do extrato
	 */
	EXTRATO		(1.0,	0),
	/**
	 * Constante referente ao custo de serviço e juros mensais do empréstimo
	 */
	EMPRESTIMO	(15.0,	5);
	
	private double custoServico;
	private int jurosMensais;

	private Tarifas(double custoServico, int jurosMensais) {
		this.custoServico = custoServico;
		this.jurosMensais = jurosMensais;
	}

	public double getCustoServico() {
		return custoServico;
	}

	public int getJurosMensais() {
		return jurosMensais;
	}
}
