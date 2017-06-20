package br.com.fiap.bot.integradores;

import com.pengrad.telegrambot.model.Chat;

public class IntegracaoBotStart extends IntegracaoBotConsulta {

	@Override
	public String integrarBanco(String resposta, Chat usuario) {
		return "";
	}

	@Override
	public String tratarPrimeiraInteracao(Chat usuario) {
		return "Olá "+usuario.firstName()+"!\nMeu nome é Pedrite, sou um atendente virtual e irei ajudar você.\nPara saber o que você pode fazer por aqui, digite /ajuda";
	}

}
