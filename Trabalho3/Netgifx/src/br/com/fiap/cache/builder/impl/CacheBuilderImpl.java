package br.com.fiap.cache.builder.impl;

import br.com.fiap.cache.builder.CacheBuilder;
import br.com.fiap.cache.constants.TamanhoMemoria;
import br.com.fiap.cache.constants.TempoVida;
import br.com.fiap.cache.manager.GerenciadorCache;
import br.com.fiap.cache.vo.CacheVO;
import br.com.fiap.cache.vo.QuantidadeMaximaObjetoVO;
import br.com.fiap.cache.vo.TamanhoMaximoMemoriaVO;
import br.com.fiap.cache.vo.TamanhoMaximoObjetoVO;
import br.com.fiap.cache.vo.TempoVidaObjetoVO;

/**
 * 
 * Classe responsável pela definição do construtor do cache
 * 
 * @since 1.0.0
 * @author Pedro Nogueira
 * @version 1.0.0
 *
 */
public class CacheBuilderImpl<K, V> implements CacheBuilder<K, V> {

	private Class<K> tipoChave;
	private Class<V> tipoValor;
	private CacheVO cache;

	/**
	 * 
	 * Inicializador estático para o construtor
	 * 
	 * @param <K> Tipo de objeto que será utilizado como chave
	 * @param <V> Tipo de objeto que será utilizado como valor
	 * 
	 * @param tipoChave Classe que será utilizada como chave
	 * @param tipoValor Classe que será utilizada como valor
	 * 
	 * @return A própria classe
	 */
	public static <K, V> CacheBuilderImpl<K, V> init(Class<K> tipoChave, Class<V> tipoValor) {
		return new CacheBuilderImpl<>(tipoChave, tipoValor);
	}

	/**
	 * 
	 * Construtor que receber os tipos que serão utilizados pelo cache
	 * 
	 * @param tipoChave Classe que será utilizada como chave
	 * @param tipoValor Classe que será utilizada como valor
	 */
	private CacheBuilderImpl(Class<K> tipoChave, Class<V> tipoValor) {
		this.tipoChave = tipoChave;
		this.tipoValor = tipoValor;
		this.cache = new CacheVO();
	}

	/**
	 * 
	 * Método responsável por definir o tamanho máximo de memória que será utilizado por uma instância do cache
	 * 
	 * @param valor Valor que será utilizado
	 * @param tamanhoMemoria Unidade de bytes que será utilizada
	 * 
	 * @return A própria classe
	 */
	@Override
	public CacheBuilder<K, V> definirTamanhoMaximoMemoria(Long valor, TamanhoMemoria tamanhoMemoria) {
		TamanhoMaximoMemoriaVO tamanhoMaximoMemoria = new TamanhoMaximoMemoriaVO();

		tamanhoMaximoMemoria.setTamanhoMaximoMemoria(valor);
		tamanhoMaximoMemoria.setUnidadeTamanhoMemoria(tamanhoMemoria);

		this.cache.setTamanhoMaximoMemoria(tamanhoMaximoMemoria);

		return this;
	}

	/**
	 * 
	 * Método responsável por definir o tamanho máximo de memória que cada objeto utilizará dentro do cache
	 * 
	 * @param valor Valor que será utilizado
	 * @param tamanhoMemoria Unidade de bytes que será utilizada
	 * 
	 * @return A própria classe
	 */
	@Override
	public CacheBuilder<K, V> definirTamanhoMaximoObjeto(Long valor, TamanhoMemoria tamanhoMemoria) {
		TamanhoMaximoObjetoVO tamanhoMaximoObjeto = new TamanhoMaximoObjetoVO();

		tamanhoMaximoObjeto.setTamanhoMaximoObjeto(valor);
		tamanhoMaximoObjeto.setUnidadeTamanhoMemoria(tamanhoMemoria);

		this.cache.setTamanhoMaximoObjeto(tamanhoMaximoObjeto);

		return this;
	}

	/**
	 * 
	 * Define o tempo de um objeto no cache antes de expirar
	 * 
	 * Se definido junto com o <code>definirTempoVidaNaoExpira</code> então o último à ser definido prevalecerá
	 * 
	 * @param valor Valor que será utilizado
	 * @param tempoVida Unidade de tempo que será utilizada
	 * 
	 * @return A própria classe
	 */
	@Override
	public CacheBuilder<K, V> definirTempoVida(Long valor, TempoVida tempoVida) {
		TempoVidaObjetoVO tempoVidaObjeto = new TempoVidaObjetoVO();

		tempoVidaObjeto.setExpira(true);
		tempoVidaObjeto.setTempoVidaObjeto(valor);
		tempoVidaObjeto.setUnidadeTempoVida(tempoVida);

		this.cache.setTempoVidaObjeto(tempoVidaObjeto);

		return this;
	}

	/**
	 * 
	 * Define que o objeto no cache não expira
	 * 
	 * Se definido junto com o <code>definirTempoVida</code> então o último à ser definido prevalecerá
	 * 
	 * @return A própria classe
	 */
	@Override
	public CacheBuilder<K, V> definirTempoVidaNaoExpira() {
		TempoVidaObjetoVO tempoVidaObjeto = new TempoVidaObjetoVO();

		tempoVidaObjeto.setExpira(false);

		this.cache.setTempoVidaObjeto(tempoVidaObjeto);

		return this;
	}

	/**
	 * 
	 * Define a quantidade máxima de objetos que será armazenada em uma instância do cache
	 * 
	 * @param valor Valor que será utilizado
	 * 
	 * @return A própria classe
	 */
	@Override
	public CacheBuilder<K, V> definirQuantidadeMaximaObjetos(Long valor) {
		QuantidadeMaximaObjetoVO quantidadeMaximaObjeto = new QuantidadeMaximaObjetoVO();

		quantidadeMaximaObjeto.setQuantidadeMaximaObjetos(valor);

		this.cache.setQuantidadeMaximaObjeto(quantidadeMaximaObjeto);

		return this;
	}

	/**
	 * 
	 * Inicializa o gerenciador de cache
	 * 
	 * @param nomeCache Nome da instância que está sendo criada
	 * 
	 * @return O gerenciador de cache
	 */
	@Override
	public GerenciadorCache<K, V> build(String nomeCache) {
		this.cache.setNomeCache(nomeCache);

		return new GerenciadorCache<>(tipoChave, tipoValor, cache);
	}
}
