package br.com.fiap.bot.integradores;

import com.pengrad.telegrambot.model.Chat;

public interface IntegracaoBot {
	public abstract String integrarBanco(String resposta, Chat usuario);
	public abstract String tratarPrimeiraInteracao(Chat usuario);
}
