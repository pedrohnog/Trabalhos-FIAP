package br.com.fiap.bot.validacao;

import br.com.fiap.bot.constantes.EnumComandosBot;

public class IntegracaoBotConsultaEmprestimo extends IntegracaoBotConsulta {

	@Override
	public String integrarBanco(String resposta, EnumComandosBot comandoBot) {
		return "EMPRESTIMO";
	}

	@Override
	public String exibeMensagemPrimeiraIntegracao() {
		return "EMPRESTIMO";
	}

}
