package br.com.fiap.bot.integradores.impl;

import com.pengrad.telegrambot.model.Chat;

import br.com.fiap.bot.constantes.ConstantesBot;
import br.com.fiap.bot.integradores.IntegracaoBotConsulta;

/**
 * Classe respons�vel pelo comando de start do Bot
 *
 */
public class IntegracaoBotStart extends IntegracaoBotConsulta {

	@Override
	public String integrarBanco(String resposta, Chat usuario) {
		return "";
	}

	@Override
	public String tratarPrimeiraInteracao(Chat usuario) {
		return "Ol� " + usuario.firstName() + "!" + ConstantesBot.PULAR_UMA_LINHA
				+ "Meu nome � Fiap Bank, sou um atendente virtual e irei ajudar voc�." + ConstantesBot.PULAR_UMA_LINHA
				+ "Para saber o que voc� pode fazer por aqui, digite /ajuda";
	}

}
