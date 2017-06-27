package br.com.fiap.banco.dados;

import java.io.Serializable;
import java.util.List;

import br.com.fiap.banco.entidades.Transacao;

/**
 * Classe responsável por cuidar dos detalhes das transações
 *
 */
public class TransacaoDetalhe implements Serializable {

	private static final long serialVersionUID = -8946530411695752192L;
	
	private List<Transacao> transacoes;
	private double somatorio;

	public List<Transacao> getTransacoes() {
		return transacoes;
	}

	public void setTransacoes(List<Transacao> transacoes) {
		this.transacoes = transacoes;
	}

	public double getSomatorio() {
		return somatorio;
	}

	public void setSomatorio(double somatorio) {
		this.somatorio = somatorio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(somatorio);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((transacoes == null) ? 0 : transacoes.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof TransacaoDetalhe)) {
			return false;
		}
		TransacaoDetalhe other = (TransacaoDetalhe) obj;
		if (Double.doubleToLongBits(somatorio) != Double.doubleToLongBits(other.somatorio)) {
			return false;
		}
		if (transacoes == null) {
			if (other.transacoes != null) {
				return false;
			}
		} else if (!transacoes.equals(other.transacoes)) {
			return false;
		}
		return true;
	}

}
