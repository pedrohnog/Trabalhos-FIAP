package br.com.fiap.bot.integradores.impl;

import com.pengrad.telegrambot.model.Chat;

import br.com.fiap.banco.comandos.BotComando;
import br.com.fiap.banco.excecao.ContaInexistenteExcecao;
import br.com.fiap.bot.integradores.IntegracaoBotSolicitacao;
import br.com.fiap.bot.util.MoedaUtil;
import br.com.fiap.bot.util.PropriedadesUtil;

/**
 * Classe responsável pelo comando de depósito do Bot
 *
 */
public class IntegracaoBotRealizarDeposito extends IntegracaoBotSolicitacao {

	public IntegracaoBotRealizarDeposito() {
		super(PropriedadesUtil.carregarMensagensIntegracao().getProperty("REALIZAR_DEPOSITO"), PropriedadesUtil.carregarMensagensIntegracao().getProperty("REALIZAR_DEPOSITO_FORMATO"));
	}

	@Override
	public boolean validarResposta(String resposta) {
		boolean respostaOk = true;
		if (resposta != null) {
			resposta = resposta.trim();
			if (resposta.contains(",")) {
				resposta = resposta.replace(",", ".");
			}
			try {
				if (!(Double.valueOf(resposta) > 0D)) {
					respostaOk = false;
				}
			} catch (NumberFormatException e) {
				respostaOk = false;
			}
		}
		return respostaOk;
	}

	@Override
	public String integrarBanco(String resposta, Chat usuario) {
		String retorno = "";
		BotComando botComando = new BotComando();
		if (resposta.contains(",")) {
			resposta = resposta.replace(",", ".");
		}
		try {
			botComando.realizarDeposito(usuario.id(), Double.valueOf(resposta));
			retorno = String.format(PropriedadesUtil.carregarMensagensIntegracao().getProperty("RETORNO_DEPOSITO_REALIZADO"), MoedaUtil.conveterMoedaBr(botComando.verificarSaldo(usuario.id())));
		} catch (ContaInexistenteExcecao e) {
			retorno = PropriedadesUtil.carregarMensagensIntegracao().getProperty("RETORNO_CONTA_INEXISTENTE");
		}

		return retorno;
	}

}
