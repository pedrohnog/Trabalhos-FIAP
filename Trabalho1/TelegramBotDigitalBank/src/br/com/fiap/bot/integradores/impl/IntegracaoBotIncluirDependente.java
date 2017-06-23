package br.com.fiap.bot.integradores.impl;

import com.pengrad.telegrambot.model.Chat;

import br.com.fiap.banco.comandos.BotComando;
import br.com.fiap.banco.excecao.ContaInexistenteExcecao;
import br.com.fiap.banco.excecao.UsuarioDuplicadoExcecao;
import br.com.fiap.bot.integradores.IntegracaoBotSolicitacao;

/**
 * Classe respons�vel pelo comando de inclus�o de dependentes do Bot
 *
 */
public class IntegracaoBotIncluirDependente extends IntegracaoBotSolicitacao {

	public IntegracaoBotIncluirDependente() {
		super("Me fale os dados do novo dependente. Por favor, informe nesta padr�o: cpf - nome - email - telefone (Ex: 36521563511 - Joao - joao@email.com - 11223344)",
				"cpf - nome - email - telefone (Ex: 36521563511 - Joao - joao@email.com - 11223344)");
	}

	@Override
	public Boolean validarResposta(String resposta) {
		boolean respostaOk = true;
		resposta = resposta.trim();
		String[] respostas = resposta.split("-");

		if (respostas.length != 3) {
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
		String[] respostas;
		String telefone;
		String cpf;
		String email;
		BotComando botComando = new BotComando();
		String retorno = "";

		respostas = resposta.split("-");

		cpf = respostas[0];
		email = respostas[2];
		telefone = respostas[3];

		try {
			botComando.incluirDependente(usuario.id(), usuario.firstName(), usuario.lastName(), telefone.trim(),
					cpf.trim(), email.trim());
			retorno = "Parab�ns! Dependente incluido com sucesso!";
		} catch (ContaInexistenteExcecao e) {
			retorno = "Voc� ainda n�o tem uma conta, para criar sua conta digite /criar_conta";
		} catch (UsuarioDuplicadoExcecao e) {
			retorno = "O dependente que voc� est� tentando incluir j� tem conta cadastrada no banco";
		}
		return retorno;
	}

}
