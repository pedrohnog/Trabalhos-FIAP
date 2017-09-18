package br.com.fiap.cache.vo;

import java.io.Serializable;

import br.com.fiap.cache.constants.TempoVida;

public class TempoVidaObjetoVO implements Serializable {

	private static final long serialVersionUID = -9055423054600893749L;
	
	private Long tempoVidaObjeto;
	private TempoVida unidadeTempoVida;
	private boolean expira = false;

	public Long getTempoVidaObjeto() {
		return tempoVidaObjeto;
	}

	public void setTempoVidaObjeto(Long tempoVidaObjeto) {
		this.tempoVidaObjeto = tempoVidaObjeto;
	}

	public TempoVida getUnidadeTempoVida() {
		return unidadeTempoVida;
	}

	public void setUnidadeTempoVida(TempoVida unidadeTempoVida) {
		this.unidadeTempoVida = unidadeTempoVida;
	}

	public boolean isExpira() {
		return expira;
	}

	public void setExpira(boolean expira) {
		this.expira = expira;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TempoVidaObjetoVO [tempoVidaObjeto=").append(tempoVidaObjeto).append(", unidadeTempoVida=").append(unidadeTempoVida).append(", expira=").append(expira).append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (expira ? 1231 : 1237);
		result = PRIME * result + ((tempoVidaObjeto == null) ? 0 : tempoVidaObjeto.hashCode());
		result = PRIME * result + ((unidadeTempoVida == null) ? 0 : unidadeTempoVida.hashCode());
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
		if (!(obj instanceof TempoVidaObjetoVO)) {
			return false;
		}
		TempoVidaObjetoVO other = (TempoVidaObjetoVO) obj;
		if (expira != other.expira) {
			return false;
		}
		if (tempoVidaObjeto == null) {
			if (other.tempoVidaObjeto != null) {
				return false;
			}
		} else if (!tempoVidaObjeto.equals(other.tempoVidaObjeto)) {
			return false;
		}
		return unidadeTempoVida == other.unidadeTempoVida;
	}
}
