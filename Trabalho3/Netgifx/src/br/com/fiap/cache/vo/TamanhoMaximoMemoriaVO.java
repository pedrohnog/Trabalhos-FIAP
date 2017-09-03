package br.com.fiap.cache.vo;

import java.io.Serializable;

import br.com.fiap.cache.constants.TamanhoMemoria;

public class TamanhoMaximoMemoriaVO implements Serializable {

	private static final long serialVersionUID = 1505001757381061127L;

	private Long tamanhoMaximoMemoria;
	private TamanhoMemoria unidadeTamanhoMemoria;

	public Long getTamanhoMaximoMemoria() {
		return tamanhoMaximoMemoria;
	}

	public void setTamanhoMaximoMemoria(Long tamanhoMaximoMemoria) {
		this.tamanhoMaximoMemoria = tamanhoMaximoMemoria;
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
		builder.append("TamanhoMemoriaVO [tamanhoMaximoMemoria=").append(tamanhoMaximoMemoria).append(", unidadeTamanhoMemoria=").append(unidadeTamanhoMemoria).append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tamanhoMaximoMemoria == null) ? 0 : tamanhoMaximoMemoria.hashCode());
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
		if (!(obj instanceof TamanhoMaximoMemoriaVO)) {
			return false;
		}
		TamanhoMaximoMemoriaVO other = (TamanhoMaximoMemoriaVO) obj;
		if (tamanhoMaximoMemoria == null) {
			if (other.tamanhoMaximoMemoria != null) {
				return false;
			}
		} else if (!tamanhoMaximoMemoria.equals(other.tamanhoMaximoMemoria)) {
			return false;
		}
		if (unidadeTamanhoMemoria != other.unidadeTamanhoMemoria) {
			return false;
		}
		return true;
	}

}
