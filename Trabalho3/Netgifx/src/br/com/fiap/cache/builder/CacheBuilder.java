package br.com.fiap.cache.builder;

import br.com.fiap.cache.constants.TamanhoMemoria;
import br.com.fiap.cache.constants.TempoVida;
import br.com.fiap.cache.manager.GerenciadorCache;

/**
 * 
 * Interface respons�vel pela defini��o do construtor do cache
 * 
 * @since 1.0.0
 * @author Pedro Nogueira
 * @version 1.0.0
 *
 */
public interface CacheBuilder<K, V> {

	/**
	 * 
	 * M�todo respons�vel por definir o tamanho m�ximo de mem�ria que ser� utilizado por uma inst�ncia do cache
	 * 
	 * @param valor Valor que ser� utilizado
	 * @param tamanhoMemoria Unidade de bytes que ser� utilizada
	 * 
	 * @return A pr�pria classe
	 */
	public abstract CacheBuilder<K, V> definirTamanhoMaximoMemoria(Long valor, TamanhoMemoria tamanhoMemoria);

	/**
	 * 
	 * M�todo respons�vel por definir o tamanho m�ximo de mem�ria que cada objeto utilizar� dentro do cache
	 * 
	 * @param valor Valor que ser� utilizado
	 * @param tamanhoMemoria Unidade de bytes que ser� utilizada
	 * 
	 * @return A pr�pria classe
	 */
	public abstract CacheBuilder<K, V> definirTamanhoMaximoObjeto(Long valor, TamanhoMemoria tamanhoMemoria);

	/**
	 * 
	 * Define o tempo de um objeto no cache antes de expirar
	 * 
	 * Se definido junto com o <code>definirTempoVidaNaoExpira</code> ent�o o �ltimo � ser definido prevalecer�
	 * 
	 * @param valor Valor que ser� utilizado
	 * @param tempoVida Unidade de tempo que ser� utilizada
	 * 
	 * @return A pr�pria classe
	 */
	public abstract CacheBuilder<K, V> definirTempoVida(Long valor, TempoVida tempoVida);

	/**
	 * 
	 * Define que o objeto no cache n�o expira
	 * 
	 * Se definido junto com o <code>definirTempoVida</code> ent�o o �ltimo � ser definido prevalecer�
	 * 
	 * @return A pr�pria classe
	 */
	public abstract CacheBuilder<K, V> definirTempoVidaNaoExpira();

	/**
	 * 
	 * Define a quantidade m�xima de objetos que ser� armazenada em uma inst�ncia do cache
	 * 
	 * @param valor Valor que ser� utilizado
	 * 
	 * @return A pr�pria classe
	 */
	public abstract CacheBuilder<K, V> definirQuantidadeMaximaObjetos(Long valor);

	/**
	 * 
	 * Inicializa o gerenciador de cache
	 * 
	 * @param nomeCache Nome da inst�ncia que est� sendo criada
	 * 
	 * @return O gerenciador de cache
	 */
	public abstract GerenciadorCache<K, V> build(String nomeCache);

}
