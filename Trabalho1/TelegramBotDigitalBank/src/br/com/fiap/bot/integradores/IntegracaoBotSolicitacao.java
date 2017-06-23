package br.com.fiap.bot.integradores;

import com.pengrad.telegrambot.model.Chat;

import br.com.fiap.bot.util.PropriedadesUtil;

/**
 * Classe responsável por definir a implementação básica das integrações de solicitação do Bot
 *
 */
public abstract class IntegracaoBotSolicitacao implements IntegracaoBot {

	public static String ERRO_RESPOSTA = PropriedadesUtil.carregarMensagensIntegracao().getProperty("ERRO_RESPOSTA");

	private String dominioResposta;
	private String mensagemPrimeiraIntegracao;

	protected IntegracaoBotSolicitacao(String mensagemPrimeiraIntegracao, String dominioResposta) {
		this.mensagemPrimeiraIntegracao = String.format(mensagemPrimeiraIntegracao, dominioResposta);
		this.dominioResposta = dominioResposta;
	}

	public abstract Boolean validarResposta(String resposta);

	public abstract String integrarBanco(String resposta, Chat usuario);

	public String informarErroNaResposta() {
		return String.format(ERRO_RESPOSTA, this.dominioResposta);
	}

	public String tratarPrimeiraInteracao(Chat usuario) {
		return mensagemPrimeiraIntegracao;
	}

}
