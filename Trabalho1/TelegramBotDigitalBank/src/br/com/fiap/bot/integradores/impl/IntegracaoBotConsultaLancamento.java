package br.com.fiap.bot.integradores.impl;

import com.pengrad.telegrambot.model.Chat;

import br.com.fiap.banco.comandos.BotComando;
import br.com.fiap.banco.constantes.TipoTransacao;
import br.com.fiap.banco.dados.TransacaoDetalhe;
import br.com.fiap.banco.excecao.ContaInexistenteExcecao;
import br.com.fiap.bot.constantes.ConstantesBot;
import br.com.fiap.bot.integradores.IntegracaoBotConsulta;
import br.com.fiap.bot.util.DataUtil;
import br.com.fiap.bot.util.MoedaUtil;
import br.com.fiap.bot.util.PropriedadesUtil;

/**
 * Classe responsável pelo comando de consulta de lançamentos do Bot
 *
 */
public class IntegracaoBotConsultaLancamento extends IntegracaoBotConsulta {

	@Override
	public String integrarBanco(String resposta, Chat usuario) {
		BotComando botComando = new BotComando();
		TransacaoDetalhe transacaoDetalhe;
		StringBuilder retorno = new StringBuilder();

		try {
			transacaoDetalhe = botComando.listarLancamentos(usuario.id());
			retorno.append(PropriedadesUtil.carregarMensagensIntegracao().getProperty("CONSULTAR_LANCAMENTOS"));

			transacaoDetalhe.getTransacoes()
					.forEach(t -> retorno.append(DataUtil.conveterDataPadraoBr(t.getDataHora())).append(" - ")
							.append(TipoTransacao.getTipoTransacao(t.getTipoTransacao()).toString()).append(": ")
							.append(MoedaUtil.conveterMoedaBr(t.getValor())).append(ConstantesBot.PULAR_DUAS_LINHA));

			retorno.append("TOTAL: ").append(MoedaUtil.conveterMoedaBr(transacaoDetalhe.getSomatorio()));

		} catch (ContaInexistenteExcecao e) {
			retorno.append(PropriedadesUtil.carregarMensagensIntegracao().getProperty("RETORNO_CONTA_INEXISTENTE"));
		}

		return retorno.toString();
	}

	@Override
	public String tratarPrimeiraInteracao(Chat usuario) {
		return this.integrarBanco("", usuario);
	}

}
