package br.com.fiap.bot.integradores;

import com.pengrad.telegrambot.model.Chat;

/**
 * Classe respons�vel por definir a implementa��o b�sica das integra��es de consulta do Bot
 *
 */
public abstract class IntegracaoBotConsulta implements IntegracaoBot{	
	
	public abstract String integrarBanco(String resposta, Chat usuario);		
	public abstract String tratarPrimeiraInteracao(Chat usuario);
	
}
