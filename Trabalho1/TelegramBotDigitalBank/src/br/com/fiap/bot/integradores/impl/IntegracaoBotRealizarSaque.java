package br.com.fiap.bot.integradores.impl;

import com.pengrad.telegrambot.model.Chat;

import br.com.fiap.banco.comandos.BotComando;
import br.com.fiap.banco.excecao.ContaInexistenteExcecao;
import br.com.fiap.banco.excecao.SaldoInsuficienteExcecao;
import br.com.fiap.bot.integradores.IntegracaoBotSolicitacao;
import br.com.fiap.bot.util.MoedaUtil;
import br.com.fiap.bot.util.PropriedadesUtil;

/**
 * Classe responsável pelo comando de saque do Bot
 *
 */
public class IntegracaoBotRealizarSaque extends IntegracaoBotSolicitacao {

	public IntegracaoBotRealizarSaque() {
		super(PropriedadesUtil.carregarMensagensIntegracao().getProperty("REALIZAR_SAQUE"), PropriedadesUtil.carregarMensagensIntegracao().getProperty("REALIZAR_SAQUE_FORMATO"));
	}

	@Override
	public Boolean validarResposta(String resposta) {
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
		Double saldoAnterior = 0D;
		BotComando botComando = new BotComando();
		if (resposta.contains(",")) {
			resposta = resposta.replace(",", ".");
		}
		try {
			saldoAnterior = botComando.verificarSaldo(usuario.id());
			botComando.realizarSaque(usuario.id(), Double.valueOf(resposta));
			retorno = String.format(PropriedadesUtil.carregarMensagensIntegracao().getProperty("RETORNO_SAQUE_REALIZADO"), MoedaUtil.conveterMoedaBr(botComando.verificarSaldo(usuario.id())));
		} catch (SaldoInsuficienteExcecao e) {
			retorno = String.format(PropriedadesUtil.carregarMensagensIntegracao().getProperty("RETORNO_SALDO_INSUFICIENTE"), saldoAnterior);
		} catch (ContaInexistenteExcecao e) {
			retorno = PropriedadesUtil.carregarMensagensIntegracao().getProperty("RETORNO_CONTA_INEXISTENTE");
		}
		return retorno;
	}

}
