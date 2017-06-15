package br.com.fiap.bot.validacao;

import br.com.fiap.bot.contantes.EnumComandosBot;

public abstract class IntegracaoBotConsulta implements IntegracaoBot{
		
	IntegracaoBotConsulta(){
	}
	public abstract String integrarBanco(String resposta, EnumComandosBot comandoBot);
		
	public abstract String exibeMensagemPrimeiraIntegracao();
}
