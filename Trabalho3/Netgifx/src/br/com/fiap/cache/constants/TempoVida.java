package br.com.fiap.cache.constants;

import java.util.concurrent.TimeUnit;

public enum TempoVida {
	
	SEGUNDOS	(TimeUnit.SECONDS),
	MINUTOS		(TimeUnit.MINUTES),
	HORAS		(TimeUnit.HOURS),
	DIAS		(TimeUnit.DAYS);
	
	private TimeUnit unidadeTempo;
	
	private TempoVida(TimeUnit unidadeTempo) {
		this.unidadeTempo = unidadeTempo;
	}
	
	public TimeUnit getUnidadeTempo() {
		return this.unidadeTempo;
	}

}
