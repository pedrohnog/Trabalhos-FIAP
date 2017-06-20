package br.com.fiap.bot.integradores;

import com.pengrad.telegrambot.model.Chat;

public class IntegracaoBotExibirDados extends IntegracaoBotConsulta {

	@Override
	public String integrarBanco(String resposta, Chat usuario) {
		return "CONTA - CPF - NOME - SALDO!";
	}

	@Override
	public String tratarPrimeiraInteracao(Chat usuario) {
		return "CONTA - CPF - NOME - SALDO!";
	}

}
