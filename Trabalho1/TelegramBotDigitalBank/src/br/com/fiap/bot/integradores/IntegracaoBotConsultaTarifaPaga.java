package br.com.fiap.bot.integradores;

import com.pengrad.telegrambot.model.Chat;

public class IntegracaoBotConsultaTarifaPaga extends IntegracaoBotConsulta {

	@Override
	public String integrarBanco(String resposta, Chat usuario) {
		return "TARIFA";
	}

	@Override
	public String tratarPrimeiraInteracao(Chat usuario) {
		return "TARIFA";
	}

}
