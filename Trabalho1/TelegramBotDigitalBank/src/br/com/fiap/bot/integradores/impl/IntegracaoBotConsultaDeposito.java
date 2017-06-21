package br.com.fiap.bot.integradores.impl;

import com.pengrad.telegrambot.model.Chat;

import br.com.fiap.bot.integradores.IntegracaoBotConsulta;

public class IntegracaoBotConsultaDeposito extends IntegracaoBotConsulta {

	@Override
	public String integrarBanco(String resposta, Chat usuario) {
		return "DEPOSITO";
	}

	@Override
	public String tratarPrimeiraInteracao(Chat usuario) {
		return "DEPOSITO";
	}

}
