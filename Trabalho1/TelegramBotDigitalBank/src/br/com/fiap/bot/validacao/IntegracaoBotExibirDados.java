package br.com.fiap.bot.validacao;

import br.com.fiap.bot.constantes.EnumComandosBot;

public class IntegracaoBotExibirDados extends IntegracaoBotConsulta {

	@Override
	public String integrarBanco(String resposta, EnumComandosBot comandoBot) {
		return "CONTA - CPF - NOME - SALDO!";
	}

	@Override
	public String exibeMensagemPrimeiraIntegracao() {
		return "CONTA - CPF - NOME - SALDO!";
	}

}
