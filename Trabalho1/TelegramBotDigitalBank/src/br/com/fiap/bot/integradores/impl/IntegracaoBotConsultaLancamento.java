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

/**
 * Classe respons�vel pelo comando de consulta de lan�amentos do Bot
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
			retorno.append("EXTRATO DE LAN�AMENTOS").append(ConstantesBot.PULAR_DUAS_LINHA);

			transacaoDetalhe.getTransacoes()
					.forEach(t -> retorno.append(DataUtil.conveterDataPadraoBr(t.getDataHora())).append(" - ")
							.append(TipoTransacao.getTipoTransacao(t.getTipoTransacao()).toString()).append(": ")
							.append(MoedaUtil.conveterMoedaBr(t.getValor())).append(ConstantesBot.PULAR_DUAS_LINHA));

			retorno.append("TOTAL: " + MoedaUtil.conveterMoedaBr(transacaoDetalhe.getSomatorio()));

		} catch (ContaInexistenteExcecao e) {
			retorno.append("Voc� ainda n�o tem uma conta, para criar sua conta digite /criar_conta");
		}

		return retorno.toString();
	}

	@Override
	public String tratarPrimeiraInteracao(Chat usuario) {
		return this.integrarBanco("", usuario);
	}

}
