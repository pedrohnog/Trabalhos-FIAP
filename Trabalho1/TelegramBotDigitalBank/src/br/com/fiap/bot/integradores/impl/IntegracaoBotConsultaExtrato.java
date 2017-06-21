package br.com.fiap.bot.integradores.impl;

import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import com.pengrad.telegrambot.model.Chat;

import br.com.fiap.banco.comandos.BotComando;
import br.com.fiap.banco.constantes.TipoTransacao;
import br.com.fiap.banco.entidades.Transacao;
import br.com.fiap.banco.excecao.ContaInexistenteExcecao;
import br.com.fiap.banco.excecao.SaldoInsuficienteExcecao;
import br.com.fiap.bot.integradores.IntegracaoBotConsulta;
import br.com.fiap.bot.util.ConverteMoedaUtil;

public class IntegracaoBotConsultaExtrato extends IntegracaoBotConsulta {

	@Override
	public String integrarBanco(String resposta, Chat usuario) {
		StringBuffer retorno = new StringBuffer();
		BotComando botComando = new BotComando();
		NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		try {
			List<Transacao> transacoes = botComando.verificarExtrato(usuario.id());
			retorno.append("EXTRATO DA CONTA")
			.append("\n");
			for (Transacao transacao : transacoes) {
				retorno.append(transacao.getDataHora().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " ")
				.append(TipoTransacao.getTipoTransacao(transacao.getTipoTransacao()).toString() + " ")
				.append(ConverteMoedaUtil.conveterMoedaBr(transacao.getValor()))
				.append("\n");
			}
		} catch (SaldoInsuficienteExcecao e) {
			retorno.append("Saldo insuficiente para consultar o extrato!");
		} catch (ContaInexistenteExcecao e) {
			retorno.append("Você ainda não tem uma conta, para criar sua conta digite /criar_conta");
		}
		
		return retorno.toString();
	}

	@Override
	public String tratarPrimeiraInteracao(Chat usuario) {
		return this.integrarBanco("",usuario);
	}

}
