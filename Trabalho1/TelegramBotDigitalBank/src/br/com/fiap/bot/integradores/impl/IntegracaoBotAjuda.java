package br.com.fiap.bot.integradores.impl;

import com.pengrad.telegrambot.model.Chat;

import br.com.fiap.bot.constantes.ConstantesBot;
import br.com.fiap.bot.constantes.EnumComandosBot;
import br.com.fiap.bot.integradores.IntegracaoBot;

/**
 * Classe responsável pelo comando de ajuda do Bot
 *
 */
public class IntegracaoBotAjuda implements IntegracaoBot {

	@Override
	public String integrarBanco(String resposta, Chat usuario) {
		return "";
	}

	@Override
	public String tratarPrimeiraInteracao(Chat usuario) {
		StringBuffer retorno = new StringBuffer();
		retorno.append("Segue a lista de comandos que podem ser efetuados, espero poder ajudar... ");

		for (EnumComandosBot comandoBot : EnumComandosBot.values()) {
			retorno.append(ConstantesBot.PULAR_UMA_LINHA).append(comandoBot.getComando());
		}

		return retorno.toString();
	}

}
