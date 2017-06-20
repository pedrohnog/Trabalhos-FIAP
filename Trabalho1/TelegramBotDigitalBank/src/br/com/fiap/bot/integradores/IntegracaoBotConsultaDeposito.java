package br.com.fiap.bot.integradores;

import com.pengrad.telegrambot.model.Chat;

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
