package br.com.fiap.bot.integradores;

import com.pengrad.telegrambot.model.Chat;

/**
 * Classe responsável por definir a implementação básica das integrações de consulta do Bot
 *
 */
public abstract class IntegracaoBotConsulta implements IntegracaoBot{	
	
	public abstract String integrarBanco(String resposta, Chat usuario);		
	public abstract String tratarPrimeiraInteracao(Chat usuario);
	
}
