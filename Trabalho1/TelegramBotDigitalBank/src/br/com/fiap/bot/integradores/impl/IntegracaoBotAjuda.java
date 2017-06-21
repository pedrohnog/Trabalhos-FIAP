package br.com.fiap.bot.integradores.impl;

import com.pengrad.telegrambot.model.Chat;

import br.com.fiap.bot.constantes.EnumComandosBot;
import br.com.fiap.bot.integradores.IntegracaoBot;

public class IntegracaoBotAjuda implements IntegracaoBot {

	public String integrarBanco(String resposta, Chat usuario) {
		return "";
	}

	public String tratarPrimeiraInteracao(Chat usuario) {
		StringBuffer retorno = new StringBuffer();
		retorno.append("Segue a lista de comandos que podem ser efetuados, espero poder ajudar... ");
		
		for (EnumComandosBot comandoBot : EnumComandosBot.values()) {
			retorno.append("\n").append(comandoBot.getComando());
		}
		
		return retorno.toString();
	}

}
