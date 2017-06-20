package br.com.fiap.bot.integradores.impl;

import com.pengrad.telegrambot.model.Chat;

import br.com.fiap.bot.integradores.IntegracaoBotConsulta;

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
