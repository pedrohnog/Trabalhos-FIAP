package br.com.fiap.cache.constants.padroes;

public enum QuantidadeItensPadrao {
	
	QUANTIDADE_MINIMA_ITENS_GERENCIADOR	(1L),
	QUANTIDADE_MAXIMA_ITENS_GERENCIADOR (2000L),
	QUANTIDADE_MINIMA_ITENS_INDIVIDUAL	(1L),
	QUANTIDADE_MAXIMA_ITENS_INDIVIDUAL	(100L);
	
	private Long quantidade;
	
	private QuantidadeItensPadrao(Long quantidade) {
		this.quantidade = quantidade;
	}
	
	public Long getQuantidade() {
		return this.quantidade;
	}

}
