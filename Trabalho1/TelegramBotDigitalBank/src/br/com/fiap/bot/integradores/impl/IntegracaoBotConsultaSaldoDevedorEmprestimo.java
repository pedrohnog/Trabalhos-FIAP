package br.com.fiap.bot.integradores.impl;

import com.pengrad.telegrambot.model.Chat;

import br.com.fiap.banco.comandos.BotComando;
import br.com.fiap.banco.dados.EmprestimoDetalhe;
import br.com.fiap.bot.constantes.ConstantesBot;
import br.com.fiap.bot.integradores.IntegracaoBotConsulta;

public class IntegracaoBotConsultaSaldoDevedorEmprestimo extends IntegracaoBotConsulta {

	@Override
	public String integrarBanco(String resposta, Chat usuario) {
		StringBuffer retorno = new StringBuffer();
		BotComando botComando = new BotComando();
		EmprestimoDetalhe emprestimoDetalhe = botComando.verificarSaldoDevedorPrazoEmprestimo(usuario.id());

		retorno.append("O saldo devedor do seu empréstimo é: ")
		.append(emprestimoDetalhe.getSaldoDevedor())
		.append(ConstantesBot.PULAR_UMA_LINHA)
		.append("A quantidade de parelas em aberto é: ")
		.append(emprestimoDetalhe.getPrazoPagamento());
		
		return retorno.toString();
	}

	@Override
	public String tratarPrimeiraInteracao(Chat usuario) {
		return this.integrarBanco("",usuario);
	}

}
