package br.com.fiap.bot.integradores.impl;

import com.pengrad.telegrambot.model.Chat;

import br.com.fiap.banco.comandos.BotComando;
import br.com.fiap.banco.excecao.ContaInexistenteExcecao;
import br.com.fiap.bot.integradores.IntegracaoBotSolicitacao;
import br.com.fiap.bot.util.PropriedadesUtil;

/**
 * Classe responsável pelo comando de modificação de conta do Bot
 *
 */
public class IntegracaoBotModificarConta extends IntegracaoBotSolicitacao {

	public IntegracaoBotModificarConta() {
		super(PropriedadesUtil.carregarMensagensIntegracao().getProperty("MODIFICAR_CONTA"), PropriedadesUtil.carregarMensagensIntegracao().getProperty("MODIFICAR_CONTA_FORMATO"));
	}

	@Override
	public Boolean validarResposta(String resposta) {
		boolean respostaOk = true;
		resposta = resposta.trim();
		String[] respostas = resposta.split("-");

		if (respostas.length != 2) {
			respostaOk = false;
		} else {
			for (int i = 0; i < respostas.length; i++) {
				if (respostas[i].trim().length() == 0) {
					respostaOk = false;
					break;
				}
			}
		}

		return respostaOk;
	}

	@Override
	public String integrarBanco(String resposta, Chat usuario) {
		String retorno = "";
		String[] respostas;
		BotComando botComando = new BotComando();

		respostas = resposta.split("-");

		try {
			botComando.modificarConta(usuario.id(), respostas[0].trim(), respostas[1].trim());
			retorno = PropriedadesUtil.carregarMensagensIntegracao().getProperty("RETORNO_CONTA_MODIFICADA");
		} catch (ContaInexistenteExcecao e) {
			retorno = PropriedadesUtil.carregarMensagensIntegracao().getProperty("RETORNO_CONTA_INEXISTENTE");
		}
		return retorno;
	}

}
