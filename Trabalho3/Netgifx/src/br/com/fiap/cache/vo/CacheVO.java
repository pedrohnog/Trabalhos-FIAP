package br.com.fiap.cache.vo;

import java.io.Serializable;

public class CacheVO implements Serializable {

	private static final long serialVersionUID = 7566018600587352178L;
	
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
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((nomeCache == null) ? 0 : nomeCache.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CacheVO other = (CacheVO) obj;
		if (nomeCache == null) {
			if (other.nomeCache != null)
				return false;
		} else if (!nomeCache.equals(other.nomeCache))
			return false;
		return true;
	}
	
	
}
