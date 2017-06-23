package br.com.fiap.bot.integradores;

import com.pengrad.telegrambot.model.Chat;

/**
 * Interface para definição do tratamento do Bot
 *
 */
public interface IntegracaoBot {

	/**
	 * Realiza a integração com o banco
	 */
	public abstract String integrarBanco(String resposta, Chat usuario);

	/**
	 * Realiza o tratamento na primeira interação
	 */
	public abstract String tratarPrimeiraInteracao(Chat usuario);

}
