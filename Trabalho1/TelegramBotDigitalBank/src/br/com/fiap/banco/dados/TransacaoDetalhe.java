package br.com.fiap.banco.dados;

import java.io.Serializable;
import java.util.List;

import br.com.fiap.banco.entidades.Transacao;

public class TransacaoDetalhe implements Serializable {

	private static final long serialVersionUID = -3191534842798150141L;
	
	private List<Transacao> transacoes;
	private Double somatorio;

	public List<Transacao> getTransacoes() {
		return transacoes;
	}

	public void setTransacoes(List<Transacao> transacoes) {
		this.transacoes = transacoes;
	}

	public Double getSomatorio() {
		return somatorio;
	}

	public void setSomatorio(Double somatorio) {
		this.somatorio = somatorio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((somatorio == null) ? 0 : somatorio.hashCode());
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
		if (somatorio == null) {
			if (other.somatorio != null) {
				return false;
			}
		} else if (!somatorio.equals(other.somatorio)) {
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
