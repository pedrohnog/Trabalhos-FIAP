package br.com.fiap.cache.manager;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;
import org.ehcache.expiry.Expiry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.fiap.cache.constants.padroes.QuantidadeItensPadrao;
import br.com.fiap.cache.constants.padroes.QuantidadeMemoriaPadrao;
import br.com.fiap.cache.vo.CacheVO;
import br.com.fiap.cache.vo.TamanhoMaximoObjetoVO;

/**
 * 
 * Classe respons�vel pelo gerenciamento do cache e das inst�ncias criadas
 * 
 * @since 1.0.0
 * @author Pedro Nogueira
 * @version 1.0.0
 *
 */
public class GerenciadorCache<K, V> {

	protected Logger logger = LoggerFactory.getLogger(GerenciadorCache.class);

	private Class<K> tipoChave;
	private Class<V> tipoValor;
	private CacheVO cacheVO;

	private Cache<K, V> cache;

	private static CacheManager cacheManager = null;

	/**
	 * 
	 * Construtor que receber os tipos que ser�o utilizados pelo cache e as defini��es da inst�ncia
	 * 
	 * @param tipoChave Classe que ser� utilizada como chave
	 * @param tipoValor Classe que ser� utilizada como valor
	 * @param cache As defini��es da inst�ncia que ser� criada
	 */
	public GerenciadorCache(Class<K> tipoChave, Class<V> tipoValor, CacheVO cache) {
		this.tipoChave = tipoChave;
		this.tipoValor = tipoValor;
		this.cacheVO = cache;

		this.init();
	}

	/**
	 * 
	 * Inicializador das configura��es, gerenciador de cache e inst�ncias
	 * 
	 */
	private void init() {
		if (cacheManager == null) {
			CacheConfiguration<K, V> configuracaoGerenciadorCache = this.configurarGerenciador();
			cacheManager = CacheManagerBuilder.newCacheManagerBuilder().withCache("configuracaoBase", configuracaoGerenciadorCache).build(true);
		}

		if (cacheManager.getCache(this.cacheVO.getNomeCache(), tipoChave, tipoValor) != null) {
			this.cache = cacheManager.getCache(this.cacheVO.getNomeCache(), tipoChave, tipoValor);
		} else {
			CacheConfiguration<K, V> configuracaoCacheIndividual = this.configurarIndividual();
			this.cache = cacheManager.createCache(this.cacheVO.getNomeCache(), configuracaoCacheIndividual);
		}
	}

	/**
	 * 
	 * M�todo respons�vel por definir as configura��es do gerenciador
	 * 
	 * @return Configura��es do gerenciador
	 */
	private CacheConfiguration<K, V> configurarGerenciador() {
		ResourcePoolsBuilder construtorRecursos = ResourcePoolsBuilder.newResourcePoolsBuilder();

		construtorRecursos = construtorRecursos.heap(QuantidadeItensPadrao.QUANTIDADE_MAXIMA_ITENS_GERENCIADOR.getQuantidade(), EntryUnit.ENTRIES);
		construtorRecursos = construtorRecursos.offheap(QuantidadeMemoriaPadrao.QUANTIDADE_MAXIMA_MEMORIA_GERENCIADOR.getQuantidade(), QuantidadeMemoriaPadrao.QUANTIDADE_MAXIMA_MEMORIA_GERENCIADOR.getUnidade().getUnidadeMemoria());

		return CacheConfigurationBuilder.newCacheConfigurationBuilder(this.tipoChave, this.tipoValor, construtorRecursos).build();
	}

	/**
	 * 
	 * M�todo respons�vel por definir as configura��es da inst�ncia
	 * 
	 * @return Configura��es da inst�ncia
	 */
	private CacheConfiguration<K, V> configurarIndividual() {
		ResourcePoolsBuilder construtorRecursos = ResourcePoolsBuilder.newResourcePoolsBuilder();

		if (cacheVO.getQuantidadeMaximaObjeto() != null
				&& cacheVO.getQuantidadeMaximaObjeto().getQuantidadeMaximaObjetos() != null
				&& cacheVO.getQuantidadeMaximaObjeto().getQuantidadeMaximaObjetos().longValue() >= QuantidadeItensPadrao.QUANTIDADE_MINIMA_ITENS_INDIVIDUAL.getQuantidade()) {
			construtorRecursos = construtorRecursos.heap(cacheVO.getQuantidadeMaximaObjeto().getQuantidadeMaximaObjetos(), EntryUnit.ENTRIES);
		} else {
			construtorRecursos = construtorRecursos.heap(QuantidadeItensPadrao.QUANTIDADE_MAXIMA_ITENS_INDIVIDUAL.getQuantidade(), EntryUnit.ENTRIES);
		}

		if (cacheVO.getTamanhoMaximoMemoria() != null
				&& cacheVO.getTamanhoMaximoMemoria().getTamanhoMaximoMemoria() != null
				&& cacheVO.getTamanhoMaximoMemoria().getTamanhoMaximoMemoria().longValue() >= QuantidadeMemoriaPadrao.QUANTIDADE_MINIMA_MEMORIA_GERENCIADOR.getQuantidade()
				&& cacheVO.getTamanhoMaximoMemoria().getUnidadeTamanhoMemoria() != null) {
			construtorRecursos = construtorRecursos.offheap(cacheVO.getTamanhoMaximoMemoria().getTamanhoMaximoMemoria(), cacheVO.getTamanhoMaximoMemoria().getUnidadeTamanhoMemoria().getUnidadeMemoria());
		} else {
			construtorRecursos = construtorRecursos.offheap(QuantidadeMemoriaPadrao.QUANTIDADE_MAXIMA_MEMORIA_INDIVIDUAL.getQuantidade(), QuantidadeMemoriaPadrao.QUANTIDADE_MAXIMA_MEMORIA_INDIVIDUAL.getUnidade().getUnidadeMemoria());
		}

		Expiry<Object, Object> expira;
		if (cacheVO.getTempoVidaObjeto() != null && !cacheVO.getTempoVidaObjeto().isExpira()
				&& cacheVO.getTempoVidaObjeto().getTempoVidaObjeto() != null
				&& cacheVO.getTempoVidaObjeto().getTempoVidaObjeto().longValue() > 0
				&& cacheVO.getTempoVidaObjeto().getUnidadeTempoVida() != null) {
			expira = Expirations.timeToLiveExpiration(Duration.of(cacheVO.getTempoVidaObjeto().getTempoVidaObjeto(),
					cacheVO.getTempoVidaObjeto().getUnidadeTempoVida().getUnidadeTempo()));
		} else {
			expira = Expirations.timeToLiveExpiration(Duration.INFINITE);
		}

		TamanhoMaximoObjetoVO tamanhoMaximoObjeto = cacheVO.getTamanhoMaximoObjeto();
		if (tamanhoMaximoObjeto == null || tamanhoMaximoObjeto.getTamanhoMaximoObjeto() == null
				|| tamanhoMaximoObjeto.getTamanhoMaximoObjeto().longValue() <= QuantidadeMemoriaPadrao.QUANTIDADE_MINIMA_MEMORIA_OBJETO.getQuantidade()
				|| tamanhoMaximoObjeto.getUnidadeTamanhoMemoria() == null) {
			tamanhoMaximoObjeto = new TamanhoMaximoObjetoVO();
			tamanhoMaximoObjeto.setTamanhoMaximoObjeto(QuantidadeMemoriaPadrao.QUANTIDADE_MAXIMA_MEMORIA_OBJETO.getQuantidade());
			tamanhoMaximoObjeto.setUnidadeTamanhoMemoria(QuantidadeMemoriaPadrao.QUANTIDADE_MAXIMA_MEMORIA_OBJETO.getUnidade());
		}

		return CacheConfigurationBuilder
				.newCacheConfigurationBuilder(this.tipoChave, this.tipoValor, construtorRecursos).withExpiry(expira)
				.withSizeOfMaxObjectSize(tamanhoMaximoObjeto.getTamanhoMaximoObjeto(), tamanhoMaximoObjeto.getUnidadeTamanhoMemoria().getUnidadeMemoria())
				.build();
	}

	/**
	 * 
	 * M�todo respons�vel por gravar o valor definido pela chave na inst�ncia de cache
	 * 
	 * @param chave Chave de busca
	 * @param valor Valor � ser armazenado
	 */
	public void gravar(K chave, V valor) {
		logger.info("Armazendo valor em cache: %s -> %s", chave, valor);
		if (this.cache.containsKey(chave)) {
			this.cache.replace(chave, valor);
		} else {
			this.cache.put(chave, valor);
		}
	}

	/**
	 * 
	 * M�todo respons�vel por recuperar um valor armazenado na inst�ncia de cache
	 * 
	 * @param chave Chave de busca
	 * 
	 * @return Valor armazenado ou <code>null</code> se n�o existir
	 */
	public V recuperar(K chave) {
		return this.cache.get(chave);
	}

	/**
	 * 
	 * M�todo respons�vel por verificar se existe uma chave de busca registrada na inst�ncia de cache
	 * 
	 * @param chave Chave de busca
	 * 
	 * @return <code>true</code> se existir valor
	 */
	public boolean verificarExistencia(K chave) {
		return this.cache.containsKey(chave);
	}

	/**
	 * 
	 * M�todo respons�vel por remover um valor da inst�ncia de cache atrav�s da chave
	 * 
	 * @param chave Chave de busca
	 */
	public void remover(K chave) {
		this.cache.remove(chave);
	}

	/**
	 * 
	 * M�todo respons�vel por limpar todos os objetos da inst�ncia
	 * 
	 */
	public void limpar() {
		logger.info("Limpando cache");
		this.cache.clear();
	}

}
