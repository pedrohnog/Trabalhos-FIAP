package br.com.fiap.bot.integradores;

import com.pengrad.telegrambot.model.Chat;

import br.com.fiap.bot.util.PropriedadesUtil;

/**
 * Classe respons�vel por definir a implementa��o b�sica das integra��es de solicita��o do Bot
 *
 */
public abstract class IntegracaoBotSolicitacao implements IntegracaoBot {

	private String dominioResposta;
	private String mensagemPrimeiraIntegracao;

	protected IntegracaoBotSolicitacao(String mensagemPrimeiraIntegracao, String dominioResposta) {
		this.mensagemPrimeiraIntegracao = String.format(mensagemPrimeiraIntegracao, dominioResposta);
		this.dominioResposta = dominioResposta;
	}

	public abstract boolean validarResposta(String resposta);

	public abstract String integrarBanco(String resposta, Chat usuario);

	public String informarErroNaResposta() {
		return String.format(PropriedadesUtil.carregarMensagensIntegracao().getProperty("ERRO_RESPOSTA"),
				this.dominioResposta);
	}

	public String tratarPrimeiraInteracao(Chat usuario) {
		return mensagemPrimeiraIntegracao;
	}

}
