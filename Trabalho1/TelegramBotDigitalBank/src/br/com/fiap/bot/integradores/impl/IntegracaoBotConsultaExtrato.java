package br.com.fiap.bot.integradores.impl;

import java.util.List;

import com.pengrad.telegrambot.model.Chat;

import br.com.fiap.banco.comandos.BotComando;
import br.com.fiap.banco.constantes.TipoTransacao;
import br.com.fiap.banco.entidades.Transacao;
import br.com.fiap.banco.excecao.ContaInexistenteExcecao;
import br.com.fiap.banco.excecao.SaldoInsuficienteExcecao;
import br.com.fiap.bot.constantes.ConstantesBot;
import br.com.fiap.bot.integradores.IntegracaoBotConsulta;
import br.com.fiap.bot.util.MoedaUtil;
import br.com.fiap.bot.util.DataUtil;

public class IntegracaoBotConsultaExtrato extends IntegracaoBotConsulta {

	@Override
	public String integrarBanco(String resposta, Chat usuario) {
		StringBuffer retorno = new StringBuffer();
		BotComando botComando = new BotComando();
		try {
			List<Transacao> transacoes = botComando.verificarExtrato(usuario.id());
			retorno.append("EXTRATO DA CONTA")
			.append(ConstantesBot.PULAR_DUAS_LINHA);
			
			transacoes.forEach(t -> retorno
				.append(DataUtil.conveterDataPadraoBr(t.getDataHora()) + " - ")
				.append(TipoTransacao.getTipoTransacao(t.getTipoTransacao()).toString() + ": ")
				.append(MoedaUtil.conveterMoedaBr(t.getValor()))
				.append(ConstantesBot.PULAR_UMA_LINHA));
			
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
