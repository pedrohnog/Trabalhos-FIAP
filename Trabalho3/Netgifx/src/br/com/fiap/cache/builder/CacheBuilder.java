package br.com.fiap.cache.builder;

import br.com.fiap.cache.constants.TamanhoMemoria;
import br.com.fiap.cache.constants.TempoVida;
import br.com.fiap.cache.manager.GerenciadorCache;

/**
 * 
 * Interface responsável pela definição do construtor do cache
 * 
 * @since 1.0.0
 * @author Pedro Nogueira
 * @version 1.0.0
 *
 */
public interface CacheBuilder<K, V> {

	/**
	 * 
	 * Método responsável por definir o tamanho máximo de memória que será utilizado por uma instância do cache
	 * 
	 * @param valor Valor que será utilizado
	 * @param tamanhoMemoria Unidade de bytes que será utilizada
	 * 
	 * @return A própria classe
	 */
	public abstract CacheBuilder<K, V> definirTamanhoMaximoMemoria(Long valor, TamanhoMemoria tamanhoMemoria);

	/**
	 * 
	 * Método responsável por definir o tamanho máximo de memória que cada objeto utilizará dentro do cache
	 * 
	 * @param valor Valor que será utilizado
	 * @param tamanhoMemoria Unidade de bytes que será utilizada
	 * 
	 * @return A própria classe
	 */
	public abstract CacheBuilder<K, V> definirTamanhoMaximoObjeto(Long valor, TamanhoMemoria tamanhoMemoria);

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
	public abstract CacheBuilder<K, V> definirTempoVida(Long valor, TempoVida tempoVida);

	/**
	 * 
	 * Define que o objeto no cache não expira
	 * 
	 * Se definido junto com o <code>definirTempoVida</code> então o último à ser definido prevalecerá
	 * 
	 * @return A própria classe
	 */
	public abstract CacheBuilder<K, V> definirTempoVidaNaoExpira();

	/**
	 * 
	 * Define a quantidade máxima de objetos que será armazenada em uma instância do cache
	 * 
	 * @param valor Valor que será utilizado
	 * 
	 * @return A própria classe
	 */
	public abstract CacheBuilder<K, V> definirQuantidadeMaximaObjetos(Long valor);

	/**
	 * 
	 * Inicializa o gerenciador de cache
	 * 
	 * @param nomeCache Nome da instância que está sendo criada
	 * 
	 * @return O gerenciador de cache
	 */
	public abstract GerenciadorCache<K, V> build(String nomeCache);

}
