package br.com.fiap.bot.integradores.impl;

import com.pengrad.telegrambot.model.Chat;

import br.com.fiap.banco.comandos.BotComando;
import br.com.fiap.banco.excecao.ContaInexistenteExcecao;
import br.com.fiap.banco.excecao.UsuarioDuplicadoExcecao;
import br.com.fiap.bot.integradores.IntegracaoBotSolicitacao;
import br.com.fiap.bot.util.PropriedadesUtil;

/**
 * Classe responsável pelo comando de inclusão de dependentes do Bot
 *
 */
public class IntegracaoBotIncluirDependente extends IntegracaoBotSolicitacao {

	public IntegracaoBotIncluirDependente() {
		super(PropriedadesUtil.carregarMensagensIntegracao().getProperty("INCLUIR_DEPENDENTE"), PropriedadesUtil.carregarMensagensIntegracao().getProperty("INCLUIR_DEPENDENTE_FORMATO"));
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
			String nomeUsuario = usuario.firstName().replaceAll("[^A-Za-z0-9]", "");
			String sobrenomeUsuario = usuario.lastName().replaceAll("[^A-Za-z0-9]", "");
			
			botComando.incluirDependente(usuario.id(), nomeUsuario, sobrenomeUsuario, telefone.trim(), cpf.trim(), email.trim());
			retorno = PropriedadesUtil.carregarMensagensIntegracao().getProperty("RETORNO_DEPENDENTE_INCLUIDO");
		} catch (ContaInexistenteExcecao e) {
			retorno = PropriedadesUtil.carregarMensagensIntegracao().getProperty("RETORNO_CONTA_INEXISTENTE");
		} catch (UsuarioDuplicadoExcecao e) {
			retorno = PropriedadesUtil.carregarMensagensIntegracao().getProperty("RETORNO_DEPENDENTE_DUPLICADO");
		}
		return retorno;
	}

}
