package br.com.fiap.bot.integradores;

import com.pengrad.telegrambot.model.Chat;

import br.com.fiap.bot.constantes.ConstantesBot;

public abstract class IntegracaoBotSolicitacao implements IntegracaoBot{
	
	public static String ERRO_RESPOSTA = "Ops, alguma problema com a sua resposta, responda novamente seguindo o formato a seguir: " + ConstantesBot.PULAR_UMA_LINHA;
	private String dominioResposta;	
	private String mensagemPrimeiraIntegracao;
	
	protected IntegracaoBotSolicitacao(String mensagemPrimeiraIntegracao,String dominioResposta){
		this.mensagemPrimeiraIntegracao = mensagemPrimeiraIntegracao;
		this.dominioResposta = dominioResposta;
	}
	
	public abstract Boolean validarResposta(String resposta);
	public abstract String integrarBanco(String resposta, Chat usuario);
	
	public String informarErroNaResposta() {
		return ERRO_RESPOSTA + this.dominioResposta;
	}
	
	public String tratarPrimeiraInteracao(Chat usuario) {
		return mensagemPrimeiraIntegracao;
	}
	
}
