package br.com.fiap.bot.validacao;

import br.com.fiap.bot.constantes.EnumComandosBot;

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
