package br.com.fiap.cache.vo;

import java.io.Serializable;

public class CacheVO implements Serializable {

	private static final long serialVersionUID = 6528821926107863748L;

	private String nomeCache;
	private TamanhoMaximoMemoriaVO tamanhoMaximoMemoria;
	private TamanhoMaximoObjetoVO tamanhoMaximoObjeto;
	private TempoVidaObjetoVO tempoVidaObjeto;
	private QuantidadeMaximaObjetoVO quantidadeMaximaObjeto;

	public String getNomeCache() {
		return nomeCache;
	}

	public void setNomeCache(String nomeCache) {
		this.nomeCache = nomeCache;
	}

	public TamanhoMaximoMemoriaVO getTamanhoMaximoMemoria() {
		return tamanhoMaximoMemoria;
	}

	public void setTamanhoMaximoMemoria(TamanhoMaximoMemoriaVO tamanhoMaximoMemoria) {
		this.tamanhoMaximoMemoria = tamanhoMaximoMemoria;
	}

	public TamanhoMaximoObjetoVO getTamanhoMaximoObjeto() {
		return tamanhoMaximoObjeto;
	}

	public void setTamanhoMaximoObjeto(TamanhoMaximoObjetoVO tamanhoMaximoObjeto) {
		this.tamanhoMaximoObjeto = tamanhoMaximoObjeto;
	}

	public TempoVidaObjetoVO getTempoVidaObjeto() {
		return tempoVidaObjeto;
	}

	public void setTempoVidaObjeto(TempoVidaObjetoVO tempoVidaObjeto) {
		this.tempoVidaObjeto = tempoVidaObjeto;
	}

	public QuantidadeMaximaObjetoVO getQuantidadeMaximaObjeto() {
		return quantidadeMaximaObjeto;
	}

	public void setQuantidadeMaximaObjeto(QuantidadeMaximaObjetoVO quantidadeMaximaObjeto) {
		this.quantidadeMaximaObjeto = quantidadeMaximaObjeto;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CacheVO [nomeCache=").append(nomeCache).append(", tamanhoMaximoMemoria=")
				.append(tamanhoMaximoMemoria).append(", tamanhoMaximoObjeto=").append(tamanhoMaximoObjeto)
				.append(", tempoVidaObjeto=").append(tempoVidaObjeto).append(", quantidadeMaximaObjeto=")
				.append(quantidadeMaximaObjeto).append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nomeCache == null) ? 0 : nomeCache.hashCode());
		result = prime * result + ((quantidadeMaximaObjeto == null) ? 0 : quantidadeMaximaObjeto.hashCode());
		result = prime * result + ((tamanhoMaximoMemoria == null) ? 0 : tamanhoMaximoMemoria.hashCode());
		result = prime * result + ((tamanhoMaximoObjeto == null) ? 0 : tamanhoMaximoObjeto.hashCode());
		result = prime * result + ((tempoVidaObjeto == null) ? 0 : tempoVidaObjeto.hashCode());
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
		if (!(obj instanceof CacheVO)) {
			return false;
		}
		CacheVO other = (CacheVO) obj;
		if (nomeCache == null) {
			if (other.nomeCache != null) {
				return false;
			}
		} else if (!nomeCache.equals(other.nomeCache)) {
			return false;
		}
		if (quantidadeMaximaObjeto == null) {
			if (other.quantidadeMaximaObjeto != null) {
				return false;
			}
		} else if (!quantidadeMaximaObjeto.equals(other.quantidadeMaximaObjeto)) {
			return false;
		}
		if (tamanhoMaximoMemoria == null) {
			if (other.tamanhoMaximoMemoria != null) {
				return false;
			}
		} else if (!tamanhoMaximoMemoria.equals(other.tamanhoMaximoMemoria)) {
			return false;
		}
		if (tamanhoMaximoObjeto == null) {
			if (other.tamanhoMaximoObjeto != null) {
				return false;
			}
		} else if (!tamanhoMaximoObjeto.equals(other.tamanhoMaximoObjeto)) {
			return false;
		}
		if (tempoVidaObjeto == null) {
			if (other.tempoVidaObjeto != null) {
				return false;
			}
		} else if (!tempoVidaObjeto.equals(other.tempoVidaObjeto)) {
			return false;
		}
		return true;
	}

}
