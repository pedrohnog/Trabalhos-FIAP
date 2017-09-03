package br.com.fiap.cache.constants;

import org.ehcache.config.units.MemoryUnit;

public enum TamanhoMemoria {
	
	BYTES		(MemoryUnit.B),
	KILOBYTES	(MemoryUnit.KB),
	MEGABYTES	(MemoryUnit.MB);
	
	private MemoryUnit unidadeMemoria;
	
	private TamanhoMemoria(MemoryUnit unidadeMemoria) {
		this.unidadeMemoria = unidadeMemoria;
	}
	
	public MemoryUnit getUnidadeMemoria() {
		return this.unidadeMemoria;
	}

}
