package br.com.fiap.bot.validacao;

import br.com.fiap.bot.contantes.EnumComandosBot;

public class IntegracaoBotConsultaDeposito extends IntegracaoBotConsulta {

	@Override
	public String integrarBanco(String resposta, EnumComandosBot comandoBot) {
		return "DEPOSITO";
	}

	@Override
	public String exibeMensagemPrimeiraIntegracao() {
		return "DEPOSITO";
	}

}
