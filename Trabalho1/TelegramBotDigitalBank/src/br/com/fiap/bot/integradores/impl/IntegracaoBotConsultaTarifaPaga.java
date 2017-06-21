package br.com.fiap.bot.integradores.impl;

import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.pengrad.telegrambot.model.Chat;

import br.com.fiap.banco.comandos.BotComando;
import br.com.fiap.banco.dados.TransacaoDetalhe;
import br.com.fiap.banco.entidades.Transacao;
import br.com.fiap.banco.excecao.ContaInexistenteExcecao;
import br.com.fiap.bot.integradores.IntegracaoBotConsulta;

public class IntegracaoBotConsultaTarifaPaga extends IntegracaoBotConsulta {

	@Override
	public String integrarBanco(String resposta, Chat usuario) {
		BotComando botComando = new BotComando();
		TransacaoDetalhe transacaoDetalhe;
		StringBuilder retorno = new StringBuilder();
		NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		
		try {
			transacaoDetalhe = botComando.listarTarifas(usuario.id());
			retorno.append("EXTRATO DE TARIFAS \n");
			for (Transacao transacao : transacaoDetalhe.getTransacoes()) {
				retorno.append(transacao.getDataHora().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
						+ ":" + format.format(transacao.getValor()) + "\n");
			}
			retorno.append("Total: " + transacaoDetalhe.getSomatorio());
		} catch (ContaInexistenteExcecao e) {
			retorno.append("Você ainda não tem uma conta, para criar sua conta digite /criar_conta"); 
		}		
		return retorno.toString();
	}

	@Override
	public String tratarPrimeiraInteracao(Chat usuario) {
		return "TARIFA";
	}

}
