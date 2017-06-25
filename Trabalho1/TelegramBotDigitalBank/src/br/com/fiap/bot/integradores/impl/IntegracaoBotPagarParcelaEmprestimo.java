package br.com.fiap.bot.integradores.impl;

import java.util.List;

import com.pengrad.telegrambot.model.Chat;

import br.com.fiap.banco.comandos.BotComando;
import br.com.fiap.banco.entidades.Emprestimo;
import br.com.fiap.banco.excecao.ContaInexistenteExcecao;
import br.com.fiap.banco.excecao.PagamentoEmprestimoExcecao;
import br.com.fiap.bot.constantes.ConstantesBot;
import br.com.fiap.bot.integradores.IntegracaoBotSolicitacao;
import br.com.fiap.bot.util.DataUtil;
import br.com.fiap.bot.util.MoedaUtil;
import br.com.fiap.bot.util.PropriedadesUtil;

/**
 * Classe responsável pelo comando de pagamento de uma parcela do empréstimo do Bot
 *
 */
public class IntegracaoBotPagarParcelaEmprestimo extends IntegracaoBotSolicitacao {

	public IntegracaoBotPagarParcelaEmprestimo() {
		super("", "xxx (ex: 12)");
	}

	@Override
	public Boolean validarResposta(String resposta) {
		boolean respostaOk = true;
		if (resposta != null) {
			resposta = resposta.trim();
			try {
				if (!(Integer.valueOf(resposta) > 0D)) {
					respostaOk = false;
				}
			} catch (NumberFormatException e) {
				respostaOk = false;
			}
		}
		return respostaOk;
	}

	@Override
	public String integrarBanco(String resposta, Chat usuario) {
		BotComando botComando = new BotComando();
		StringBuffer retorno = new StringBuffer();

		try {
			botComando.pagarParcelaEmprestimo(usuario.id(), Integer.valueOf(resposta));
			retorno.append(String.format(PropriedadesUtil.carregarMensagensIntegracao().getProperty("RETORNO_PARCELA_PAGA"), MoedaUtil.conveterMoedaBr(botComando.verificarSaldo(usuario.id()))));
		} catch (ContaInexistenteExcecao e) {
			retorno.append(PropriedadesUtil.carregarMensagensIntegracao().getProperty("RETORNO_CONTA_INEXISTENTE"));
		} catch (PagamentoEmprestimoExcecao e) {
			retorno.append(PropriedadesUtil.carregarMensagensIntegracao().getProperty("RETORNO_PARCELA_INEXISTENTE_PAGA"));
		}

		return retorno.toString();
	}

	@Override
	public String tratarPrimeiraInteracao(Chat usuario) {
		StringBuffer retorno = new StringBuffer();

		BotComando botComando = new BotComando();
		List<Emprestimo> parcelas = botComando.listarEmprestimosNaoPagos(usuario.id());

		if (parcelas.isEmpty()) {
			retorno.append(PropriedadesUtil.carregarMensagensIntegracao().getProperty("PAGAR_PARCELA_NAO_ENCONTRADA"));
		} else {
			retorno.append(PropriedadesUtil.carregarMensagensIntegracao().getProperty("PAGAR_PARCELA"));
			parcelas.forEach(p -> retorno.append(p.getNumeroParcela()).append(" | ")
					.append(DataUtil.conveterDataPadraoBr(p.getDataVencimento())).append(" | ")
					.append(MoedaUtil.conveterMoedaBr(p.getValorParcela() + p.getJuros())).append(ConstantesBot.PULAR_UMA_LINHA));
		}

		return retorno.toString();
	}

}
