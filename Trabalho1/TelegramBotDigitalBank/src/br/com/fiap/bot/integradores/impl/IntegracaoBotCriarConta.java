package br.com.fiap.bot.integradores.impl;

import com.pengrad.telegrambot.model.Chat;

import br.com.fiap.banco.comandos.BotComando;
import br.com.fiap.banco.excecao.ContaExistenteExcecao;
import br.com.fiap.bot.constantes.ConstantesBot;
import br.com.fiap.bot.integradores.IntegracaoBotSolicitacao;

/**
 * Classe responsável pelo comando de criação de conta do Bot
 *
 */
public class IntegracaoBotCriarConta extends IntegracaoBotSolicitacao {

	public IntegracaoBotCriarConta() {
		super("Legal, agora informe seu cpf, telefone e email no seguinte formato para que possamos criar sua conta!"
				+ ConstantesBot.PULAR_UMA_LINHA
				+ " cpf - telefone - email (Ex: 35263585652 - 25547685 - jose@email.com",
				"cpf - telefone - email (Ex: 35263585652 - 25547685 - jose@email.com");
	}

	@Override
	public Boolean validarResposta(String resposta) {
		boolean respostaOk = true;
		if (resposta != null) {
			resposta = resposta.trim();
			try {
				String[] respostas = resposta.split("-");

				if (respostas.length != 3) {
					respostaOk = false;
				} else {
					String cpf = respostas[0].trim();
					String telefone = respostas[1].trim();
					String email = respostas[2].trim();
					if (cpf.length() != 11 || telefone.length() > 11 || email.length() > 50) {
						respostaOk = false;
					}
				}

			} catch (NumberFormatException e) {
				respostaOk = false;
			}
		}
		return respostaOk;
	}

	@Override
	public String integrarBanco(String resposta, Chat usuario) {
		BotComando botComando = new BotComando();

		String[] respostas = resposta.split("-");

		String cpf = respostas[0].trim();
		String telefone = respostas[1].trim();
		String email = respostas[2].trim();

		try {
			botComando.criarConta(usuario.id(), usuario.firstName(), usuario.lastName(), telefone, cpf, email);
			return "Parabéns! Conta criada com sucesso!";
		} catch (ContaExistenteExcecao e) {
			return "Você já possui uma conta. Não será possível criar uma nova!";
		}

	}
}
