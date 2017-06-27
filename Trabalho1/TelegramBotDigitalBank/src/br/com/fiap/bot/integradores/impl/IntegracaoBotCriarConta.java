package br.com.fiap.bot.integradores.impl;

import com.pengrad.telegrambot.model.Chat;

import br.com.fiap.banco.comandos.BotComando;
import br.com.fiap.banco.excecao.ContaExistenteExcecao;
import br.com.fiap.bot.integradores.IntegracaoBotSolicitacao;
import br.com.fiap.bot.util.PropriedadesUtil;

/**
 * Classe responsável pelo comando de criação de conta do Bot
 *
 */
public class IntegracaoBotCriarConta extends IntegracaoBotSolicitacao {

	public IntegracaoBotCriarConta() {
		super(PropriedadesUtil.carregarMensagensIntegracao().getProperty("COMANDO_CRIAR_CONTA"), PropriedadesUtil.carregarMensagensIntegracao().getProperty("COMANDO_CRIAR_CONTA_FORMATO"));
	}

	@Override
	public boolean validarResposta(String resposta) {
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
			String nomeUsuario = usuario.firstName().replaceAll("[^A-Za-z0-9]", "");
			String sobrenomeUsuario = "";
			if(usuario.lastName() != null) {
				sobrenomeUsuario = usuario.lastName().replaceAll("[^A-Za-z0-9]", "");
			}
			
			botComando.criarConta(usuario.id(), nomeUsuario, sobrenomeUsuario, telefone, cpf, email);
			return PropriedadesUtil.carregarMensagensIntegracao().getProperty("RETORNO_CONTA_CRIADA");
		} catch (ContaExistenteExcecao e) {
			return PropriedadesUtil.carregarMensagensIntegracao().getProperty("RETORNO_CONTA_EXISTENTE");
		}

	}
}
