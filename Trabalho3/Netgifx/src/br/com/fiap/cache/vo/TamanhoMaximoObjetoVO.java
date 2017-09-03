package br.com.fiap.cache.vo;

import java.io.Serializable;

import br.com.fiap.cache.constants.TamanhoMemoria;

public class TamanhoMaximoObjetoVO implements Serializable {

	private static final long serialVersionUID = -8165314539536566513L;
	
	private Long tamanhoMaximoObjeto;
	private TamanhoMemoria unidadeTamanhoMemoria;

	public Long getTamanhoMaximoObjeto() {
		return tamanhoMaximoObjeto;
	}

	public void setTamanhoMaximoObjeto(Long tamanhoMaximoObjeto) {
		this.tamanhoMaximoObjeto = tamanhoMaximoObjeto;
	}

	public TamanhoMemoria getUnidadeTamanhoMemoria() {
		return unidadeTamanhoMemoria;
	}

	public void setUnidadeTamanhoMemoria(TamanhoMemoria unidadeTamanhoMemoria) {
		this.unidadeTamanhoMemoria = unidadeTamanhoMemoria;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TamanhoMaximoObjetoVO [tamanhoMaximoObjeto=").append(tamanhoMaximoObjeto).append(", unidadeTamanhoMemoria=").append(unidadeTamanhoMemoria).append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tamanhoMaximoObjeto == null) ? 0 : tamanhoMaximoObjeto.hashCode());
		result = prime * result + ((unidadeTamanhoMemoria == null) ? 0 : unidadeTamanhoMemoria.hashCode());
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
		if (!(obj instanceof TamanhoMaximoObjetoVO)) {
			return false;
		}
		TamanhoMaximoObjetoVO other = (TamanhoMaximoObjetoVO) obj;
		if (tamanhoMaximoObjeto == null) {
			if (other.tamanhoMaximoObjeto != null) {
				return false;
			}
		} else if (!tamanhoMaximoObjeto.equals(other.tamanhoMaximoObjeto)) {
			return false;
		}
		if (unidadeTamanhoMemoria != other.unidadeTamanhoMemoria) {
			return false;
		}
		return true;
	}

}
