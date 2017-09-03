package br.com.fiap.cache.constants.padroes;

import br.com.fiap.cache.constants.TamanhoMemoria;

public enum QuantidadeMemoriaPadrao {
	
	QUANTIDADE_MINIMA_MEMORIA_GERENCIADOR	(1L,	TamanhoMemoria.MEGABYTES),
	QUANTIDADE_MAXIMA_MEMORIA_GERENCIADOR	(64L,	TamanhoMemoria.MEGABYTES),
	QUANTIDADE_MINIMA_MEMORIA_INDIVIDUAL	(1L,	TamanhoMemoria.MEGABYTES),
	QUANTIDADE_MAXIMA_MEMORIA_INDIVIDUAL	(4L,	TamanhoMemoria.MEGABYTES),
	QUANTIDADE_MINIMA_MEMORIA_OBJETO		(1L,	TamanhoMemoria.BYTES),
	QUANTIDADE_MAXIMA_MEMORIA_OBJETO		(32L,	TamanhoMemoria.KILOBYTES);
	
	private Long quantidade;
	private TamanhoMemoria unidade;
	
	private QuantidadeMemoriaPadrao(Long quantidade, TamanhoMemoria unidade) {
		this.quantidade = quantidade;
		this.unidade = unidade;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public TamanhoMemoria getUnidade() {
		return unidade;
	}

}
