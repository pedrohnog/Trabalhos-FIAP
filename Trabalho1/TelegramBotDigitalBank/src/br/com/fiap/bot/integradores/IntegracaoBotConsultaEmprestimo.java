package br.com.fiap.bot.integradores;

import com.pengrad.telegrambot.model.Chat;

public class IntegracaoBotConsultaEmprestimo extends IntegracaoBotConsulta {

	@Override
	public String integrarBanco(String resposta, Chat usuario) {
		return "EMPRESTIMO";
	}

	@Override
	public String tratarPrimeiraInteracao(Chat usuario) {
		return "EMPRESTIMO";
	}

}
