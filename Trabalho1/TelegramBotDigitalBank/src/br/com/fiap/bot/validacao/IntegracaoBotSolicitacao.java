package br.com.fiap.bot.validacao;

import br.com.fiap.bot.contantes.EnumComandosBot;

public abstract class IntegracaoBotSolicitacao implements IntegracaoBot{
	
	public static String ERRO_RESPOSTA = "Ops, alguma problema com a sua resposta, responda novamente seguindo o formato a seguir: \n";
	private String dominioResposta;	
	private String mensagemPrimeiraIntegracao;
	
	IntegracaoBotSolicitacao(){
	}
	protected IntegracaoBotSolicitacao( String mensagemPrimeiraIntegracao,String dominioResposta){
		this.mensagemPrimeiraIntegracao = mensagemPrimeiraIntegracao;
		this.dominioResposta = dominioResposta;
	}
	
	public abstract Boolean validarResposta(String resposta, EnumComandosBot comandoBot);
	public abstract String integrarBanco(String resposta, EnumComandosBot comandoBot);
	
	public String informarErroNaResposta() {
		return ERRO_RESPOSTA + this.dominioResposta;
	}
	
	public String exibeMensagemPrimeiraIntegracao() {
		return mensagemPrimeiraIntegracao;
	}
	
}
