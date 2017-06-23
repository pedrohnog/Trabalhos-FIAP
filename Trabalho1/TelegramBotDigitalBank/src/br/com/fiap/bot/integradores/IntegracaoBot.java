package br.com.fiap.bot.integradores;

import com.pengrad.telegrambot.model.Chat;

/**
 * Interface para defini��o do tratamento do Bot
 *
 */
public interface IntegracaoBot {

	/**
	 * Realiza a integra��o com o banco
	 */
	public abstract String integrarBanco(String resposta, Chat usuario);

	/**
	 * Realiza o tratamento na primeira intera��o
	 */
	public abstract String tratarPrimeiraInteracao(Chat usuario);

}
