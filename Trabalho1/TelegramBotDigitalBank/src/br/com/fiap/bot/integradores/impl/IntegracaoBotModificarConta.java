package br.com.fiap.bot.integradores.impl;

import com.pengrad.telegrambot.model.Chat;

import br.com.fiap.banco.comandos.BotComando;
import br.com.fiap.banco.excecao.ContaInexistenteExcecao;
import br.com.fiap.bot.integradores.IntegracaoBotSolicitacao;

/**
 * Classe responsável pelo comando de modificação de conta do Bot
 *
 */
public class IntegracaoBotModificarConta extends IntegracaoBotSolicitacao {

	public IntegracaoBotModificarConta() {
		super("Me fale os seus novos dados para que possamos atualizar. Por favor, informe nesta padrão: cpf - email (Ex: 36521563511 - joao@email.com)",
				"cpf - email (Ex: 36521563511 - joao@email.com)");
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
			retorno = "Parabéns! Conta modificada com sucesso!";
		} catch (ContaInexistenteExcecao e) {
			retorno = "Você ainda não tem uma conta, para criar sua conta digite /criar_conta";
		}
		return retorno;
	}

}
