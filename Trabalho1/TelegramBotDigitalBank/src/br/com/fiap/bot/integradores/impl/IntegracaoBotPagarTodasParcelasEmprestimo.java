package br.com.fiap.bot.integradores.impl;

import java.util.List;

import com.pengrad.telegrambot.model.Chat;

import br.com.fiap.banco.comandos.BotComando;
import br.com.fiap.banco.entidades.Emprestimo;
import br.com.fiap.banco.excecao.ContaInexistenteExcecao;
import br.com.fiap.bot.integradores.IntegracaoBotSolicitacao;
import br.com.fiap.bot.util.MoedaUtil;
import br.com.fiap.bot.util.PropriedadesUtil;

/**
 * Classe responsável pelo comando de pagamento de todas as parcelas do empréstimo do Bot
 *
 */
public class IntegracaoBotPagarTodasParcelasEmprestimo extends IntegracaoBotSolicitacao {

	public IntegracaoBotPagarTodasParcelasEmprestimo() {
		super(PropriedadesUtil.carregarMensagensIntegracao().getProperty("PAGAR_PARCELAS_VENCIDAS"), PropriedadesUtil.carregarMensagensIntegracao().getProperty("PAGAR_PARCELAS_VENCIDAS_FORMATO"));
	}

	@Override
	public boolean validarResposta(String resposta) {
		boolean respostaOk = true;

		try {
			if (resposta == null ||
					!(
							resposta.trim().toUpperCase().equals("SIM") || resposta.trim().toUpperCase().equals("S") || 
							resposta.trim().toUpperCase().equals("NAO") || resposta.trim().toUpperCase().equals("NÃO") || resposta.trim().toUpperCase().equals("N"))
					) {
				respostaOk = false;
			}
		} catch (Exception e) {
			respostaOk = false;
		}
		return respostaOk;
	}

	@Override
	public String integrarBanco(String resposta, Chat usuario) {
		BotComando botComando = new BotComando();
		StringBuffer retorno = new StringBuffer();
		List<Emprestimo> emprestimosNaoPagos = botComando.listarEmprestimosNaoPagos(usuario.id());
		List<Emprestimo> emprestimosVencidos = botComando.listarEmprestimosVencidos(usuario.id());

		if (!emprestimosNaoPagos.isEmpty() || !emprestimosVencidos.isEmpty()) {
			if (resposta.trim().toUpperCase().equals("NAO") || resposta.trim().toUpperCase().equals("NÃO") || resposta.trim().toUpperCase().equals("N")) {
				try {
					retorno.append(String.format(PropriedadesUtil.carregarMensagensIntegracao().getProperty("RETORNO_PAGAR_PARCELAS_NAO"), MoedaUtil.conveterMoedaBr(botComando.verificarSaldo(usuario.id()))));
				} catch (ContaInexistenteExcecao e) {
					retorno.append(PropriedadesUtil.carregarMensagensIntegracao().getProperty("RETORNO_CONTA_INEXISTENTE"));
				}
			} else {
				try {
					botComando.pagarParcelasVencidasEmprestimo(usuario.id());
				} catch (ContaInexistenteExcecao e) {
					retorno.append(PropriedadesUtil.carregarMensagensIntegracao().getProperty("RETORNO_CONTA_INEXISTENTE"));
				}

				try {
					retorno.append(String.format(PropriedadesUtil.carregarMensagensIntegracao().getProperty("RETORNO_PAGAR_PARCELAS_SIM"), MoedaUtil.conveterMoedaBr(botComando.verificarSaldo(usuario.id()))));
				} catch (ContaInexistenteExcecao e) {
					retorno.append(PropriedadesUtil.carregarMensagensIntegracao().getProperty("RETORNO_CONTA_INEXISTENTE"));
				}
			}
		} else {
			retorno.append(PropriedadesUtil.carregarMensagensIntegracao().getProperty("RETORNO_SEM_PARCELAS"));
		}
		return retorno.toString();
	}

}
