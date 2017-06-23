package br.com.fiap.bot.integradores.impl;

import com.pengrad.telegrambot.model.Chat;

import br.com.fiap.bot.constantes.ConstantesBot;
import br.com.fiap.bot.constantes.EnumComandosBot;
import br.com.fiap.bot.integradores.IntegracaoBot;
import br.com.fiap.bot.util.PropriedadesUtil;

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
		retorno.append(PropriedadesUtil.carregarMensagensIntegracao().getProperty("AJUDA"));

		for (EnumComandosBot comandoBot : EnumComandosBot.values()) {
			retorno.append(ConstantesBot.PULAR_UMA_LINHA).append(comandoBot.getComando());
		}

		return retorno.toString();
	}

}
