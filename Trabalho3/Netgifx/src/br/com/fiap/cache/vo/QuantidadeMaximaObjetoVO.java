package br.com.fiap.cache.vo;

import java.io.Serializable;

public class QuantidadeMaximaObjetoVO implements Serializable {

	private static final long serialVersionUID = -5681282461613520902L;

	public Long quantidadeMaximaObjetos;

	public Long getQuantidadeMaximaObjetos() {
		return quantidadeMaximaObjetos;
	}

	public void setQuantidadeMaximaObjetos(Long quantidadeMaximaObjetos) {
		this.quantidadeMaximaObjetos = quantidadeMaximaObjetos;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("QuantidadeMaximaObjetoVO [quantidadeMaximaObjetos=").append(quantidadeMaximaObjetos).append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((quantidadeMaximaObjetos == null) ? 0 : quantidadeMaximaObjetos.hashCode());
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
		if (!(obj instanceof QuantidadeMaximaObjetoVO)) {
			return false;
		}
		QuantidadeMaximaObjetoVO other = (QuantidadeMaximaObjetoVO) obj;
		if (quantidadeMaximaObjetos == null) {
			if (other.quantidadeMaximaObjetos != null) {
				return false;
			}
		} else if (!quantidadeMaximaObjetos.equals(other.quantidadeMaximaObjetos)) {
			return false;
		}
		return true;
	}

}
