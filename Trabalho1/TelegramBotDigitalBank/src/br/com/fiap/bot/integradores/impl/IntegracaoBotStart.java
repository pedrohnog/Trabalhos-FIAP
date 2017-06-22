package br.com.fiap.bot.integradores.impl;

import com.pengrad.telegrambot.model.Chat;

import br.com.fiap.bot.constantes.ConstantesBot;
import br.com.fiap.bot.integradores.IntegracaoBotConsulta;

public class IntegracaoBotStart extends IntegracaoBotConsulta {

	@Override
	public String integrarBanco(String resposta, Chat usuario) {
		return "";
	}

	@Override
	public String tratarPrimeiraInteracao(Chat usuario) {
		return "Olá "+usuario.firstName()+"!" + ConstantesBot.PULAR_UMA_LINHA
				+"Meu nome é Fiap Bank, sou um atendente virtual e irei ajudar você." + ConstantesBot.PULAR_UMA_LINHA
				+ "Para saber o que você pode fazer por aqui, digite /ajuda";
	}

}
