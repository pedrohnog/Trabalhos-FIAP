package br.com.fiap.bot.integradores.impl;

import java.util.List;

import com.pengrad.telegrambot.model.Chat;

import br.com.fiap.banco.comandos.BotComando;
import br.com.fiap.banco.entidades.Emprestimo;
import br.com.fiap.bot.constantes.ConstantesBot;
import br.com.fiap.bot.integradores.IntegracaoBotConsulta;
import br.com.fiap.bot.util.DataUtil;
import br.com.fiap.bot.util.MoedaUtil;
import br.com.fiap.bot.util.PropriedadesUtil;

/**
 * Classe responsável pelo comando de consulta de empréstimo do Bot
 *
 */
public class IntegracaoBotConsultaEmprestimo extends IntegracaoBotConsulta {

	@Override
	public String integrarBanco(String resposta, Chat usuario) {
		BotComando botComando = new BotComando();
		StringBuffer retorno = new StringBuffer();
		
		List<Emprestimo> emprestimosNaoPagos = botComando.listarEmprestimosNaoPagos(usuario.id());
		List<Emprestimo> emprestimosVencidos = botComando.listarEmprestimosVencidos(usuario.id());

		if (!emprestimosNaoPagos.isEmpty() || !emprestimosVencidos.isEmpty()) {

			if (!emprestimosVencidos.isEmpty()) {
				retorno.append(PropriedadesUtil.carregarMensagensIntegracao().getProperty("CONSULTAR_EMPRESTIMOS_VENCIDOS"));

				emprestimosVencidos.forEach(p -> retorno.append(p.getNumeroParcela()).append(" - ")
						.append(DataUtil.conveterDataPadraoBr(p.getDataVencimento())).append(" - ")
						.append(MoedaUtil.conveterMoedaBr(p.getValorParcela())).append(ConstantesBot.PULAR_UMA_LINHA));

				retorno.append(ConstantesBot.PULAR_DUAS_LINHA);
			}

			if (!emprestimosNaoPagos.isEmpty()) {
				retorno.append(PropriedadesUtil.carregarMensagensIntegracao().getProperty("CONSULTAR_EMPRESTIMOS_NAO_PAGOS"));
				retorno.append(ConstantesBot.PULAR_DUAS_LINHA);

				emprestimosNaoPagos.forEach(p -> retorno.append(p.getNumeroParcela()).append(" - ")
						.append(DataUtil.conveterDataPadraoBr(p.getDataVencimento())).append(" - ")
						.append(MoedaUtil.conveterMoedaBr(p.getValorParcela())).append(ConstantesBot.PULAR_UMA_LINHA));

				retorno.append(ConstantesBot.PULAR_DUAS_LINHA);
			}

		} else {
			retorno.append(PropriedadesUtil.carregarMensagensIntegracao().getProperty("RETORNO_SEM_PARCELAS"));
		}

		return retorno.toString();
	}

	@Override
	public String tratarPrimeiraInteracao(Chat usuario) {
		return this.integrarBanco("", usuario);
	}

}
