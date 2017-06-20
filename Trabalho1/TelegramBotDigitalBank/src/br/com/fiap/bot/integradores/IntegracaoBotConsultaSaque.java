package br.com.fiap.bot.integradores;

import com.pengrad.telegrambot.model.Chat;

public class IntegracaoBotConsultaSaque extends IntegracaoBotConsulta {

	@Override
	public String integrarBanco(String resposta, Chat usuario) {
		return "SAQUE";
	}

	@Override
	public String tratarPrimeiraInteracao(Chat usuario) {
		return "SAQUE";
	}

}
