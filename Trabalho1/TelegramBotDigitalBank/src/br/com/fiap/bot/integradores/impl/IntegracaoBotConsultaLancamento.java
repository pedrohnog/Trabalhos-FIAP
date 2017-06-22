package br.com.fiap.bot.integradores.impl;

import com.pengrad.telegrambot.model.Chat;

import br.com.fiap.banco.comandos.BotComando;
import br.com.fiap.banco.constantes.TipoTransacao;
import br.com.fiap.banco.dados.TransacaoDetalhe;
import br.com.fiap.banco.excecao.ContaInexistenteExcecao;
import br.com.fiap.bot.integradores.IntegracaoBotConsulta;
import br.com.fiap.bot.util.DataUtil;
import br.com.fiap.bot.util.MoedaUtil;

public class IntegracaoBotConsultaLancamento extends IntegracaoBotConsulta {

	@Override
	public String integrarBanco(String resposta, Chat usuario) {
		BotComando botComando = new BotComando();
		TransacaoDetalhe transacaoDetalhe;
		StringBuilder retorno = new StringBuilder();
		
		try {
			transacaoDetalhe = botComando.listarLancamentos(usuario.id());
			retorno.append("EXTRATO DE LANÇAMENTOS \n\n");
			
			transacaoDetalhe.getTransacoes().forEach(t -> retorno
					.append(DataUtil.conveterDataPadraoBr(t.getDataHora())).append(" - ")
					.append(TipoTransacao.getTipoTransacao(t.getTipoTransacao()).toString()).append(": ")
					.append(MoedaUtil.conveterMoedaBr(t.getValor()))
					.append("\n\n"));
			
			retorno.append("TOTAL: " + MoedaUtil.conveterMoedaBr(transacaoDetalhe.getSomatorio())); 
			
		} catch (ContaInexistenteExcecao e) {
			retorno.append("Você ainda não tem uma conta, para criar sua conta digite /criar_conta");			
		}
			
		
		return "DEPOSITO";
	}

	@Override
	public String tratarPrimeiraInteracao(Chat usuario) {
		return "DEPOSITO";
	}

}
