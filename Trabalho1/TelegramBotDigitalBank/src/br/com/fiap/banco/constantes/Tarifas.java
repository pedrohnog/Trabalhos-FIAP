package br.com.fiap.banco.constantes;

public enum Tarifas {
	
	SAQUE		(2.5,	0),
	EXTRATO		(1.0,	0),
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
