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
import br.com.fiap.bot.util.DataUtil;
import br.com.fiap.bot.util.MoedaUtil;
import br.com.fiap.bot.util.PropriedadesUtil;

/**
 * Classe responsável pelo comando de consulta de extrato do Bot
 *
 */
public class IntegracaoBotConsultaExtrato extends IntegracaoBotConsulta {

	@Override
	public String integrarBanco(String resposta, Chat usuario) {
		StringBuffer retorno = new StringBuffer();
		BotComando botComando = new BotComando();
		try {
			List<Transacao> transacoes = botComando.verificarExtrato(usuario.id());
			retorno.append(PropriedadesUtil.carregarMensagensIntegracao().getProperty("EXTRATO"));

			transacoes.forEach(t -> retorno.append(DataUtil.conveterDataPadraoBr(t.getDataHora()) + " - ")
					.append(TipoTransacao.getTipoTransacao(t.getTipoTransacao()).toString() + ": ")
					.append(MoedaUtil.conveterMoedaBr(t.getValor())).append(ConstantesBot.PULAR_UMA_LINHA));

			retorno.append(String.format(PropriedadesUtil.carregarMensagensIntegracao().getProperty("CONSULTAR_SALDO"), MoedaUtil.conveterMoedaBr(botComando.verificarSaldo(usuario.id()))));
		} catch (SaldoInsuficienteExcecao e) {
			try {
				retorno.append(String.format(PropriedadesUtil.carregarMensagensIntegracao().getProperty("CONSULTAR_SALDO"), MoedaUtil.conveterMoedaBr(botComando.verificarSaldo(usuario.id()))));
			} catch (ContaInexistenteExcecao e1) {
				retorno.append(PropriedadesUtil.carregarMensagensIntegracao().getProperty("RETORNO_CONTA_INEXISTENTE"));
			}
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
