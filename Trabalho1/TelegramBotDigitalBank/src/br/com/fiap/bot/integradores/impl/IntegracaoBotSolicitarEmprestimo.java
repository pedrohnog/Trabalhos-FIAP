package br.com.fiap.bot.integradores.impl;

import java.text.NumberFormat;
import java.util.Locale;

import com.pengrad.telegrambot.model.Chat;

import br.com.fiap.banco.comandos.BotComando;
import br.com.fiap.banco.excecao.ContaInexistenteExcecao;
import br.com.fiap.banco.excecao.EmprestimoAbertoExcecao;
import br.com.fiap.banco.excecao.PrazoEmprestimoExcedidoExcecao;
import br.com.fiap.banco.excecao.SaldoInsuficienteExcecao;
import br.com.fiap.banco.excecao.ValorEmprestimoExcedidoExcecao;
import br.com.fiap.bot.constantes.ConstantesBot;
import br.com.fiap.bot.integradores.IntegracaoBotSolicitacao;
import br.com.fiap.bot.util.MoedaUtil;
import br.com.fiap.bot.util.PropriedadesUtil;

/**
 * Classe responsável pelo comando de solicitação de empréstimo do Bot
 *
 */
public class IntegracaoBotSolicitarEmprestimo extends IntegracaoBotSolicitacao {

	public IntegracaoBotSolicitarEmprestimo() {
		super(PropriedadesUtil.carregarMensagensIntegracao().getProperty("SOLICITAR_EMPRESTIMO"), PropriedadesUtil.carregarMensagensIntegracao().getProperty("SOLICITAR_EMPRESTIMO_FORMATO"));
	}

	@Override
	public boolean validarResposta(String resposta) {
		boolean respostaOk = true;
		if (resposta != null) {
			String[] respostas = resposta.split("-");
			try {
				if (respostas.length == 2) {
					String respostaValor = respostas[0].trim().replace(",", ".");
					if (!(Double.valueOf(respostaValor) > 0D)) {
						respostaOk = false;
					}
					if (!(Integer.valueOf(respostas[1].trim()) > 0D)) {
						respostaOk = false;
					}
				} else {
					respostaOk = false;
				}
			} catch (Exception e) {
				respostaOk = false;
			}
		} else {
			respostaOk = false;
		}
		return respostaOk;
	}

	@Override
	public String integrarBanco(String resposta, Chat usuario) {
		BotComando botComando = new BotComando();
		String[] respostas = resposta.split("-");
		String retorno = "";
		Double valorEmprestimo = Double.valueOf(respostas[0].trim().replace(",", "."));
		Integer quantidadeParcelas = Integer.valueOf(respostas[1].trim());

		try {
			botComando.solicitarEmprestimo(usuario.id(), valorEmprestimo, quantidadeParcelas);
			retorno = String.format(PropriedadesUtil.carregarMensagensIntegracao().getProperty("RETORNO_EMPRESTIMO_SOLICITADO"), MoedaUtil.conveterMoedaBr(botComando.verificarSaldo(usuario.id())));
		} catch (ContaInexistenteExcecao e) {
			retorno = PropriedadesUtil.carregarMensagensIntegracao().getProperty("RETORNO_CONTA_INEXISTENTE");
		} catch (ValorEmprestimoExcedidoExcecao e) {
			try {
				retorno = String.format(PropriedadesUtil.carregarMensagensIntegracao().getProperty("RETORNO_EXCEDIDO_VALOR_EMPRESTIMO"), MoedaUtil.conveterMoedaBr(botComando.verificarValorMaximoEmprestimo(usuario.id())));
			} catch (ContaInexistenteExcecao e1) {
				retorno = PropriedadesUtil.carregarMensagensIntegracao().getProperty("RETORNO_CONTA_INEXISTENTE");
			} catch (SaldoInsuficienteExcecao e1) {
				try {
					retorno = String.format(PropriedadesUtil.carregarMensagensIntegracao().getProperty("RETORNO_SALDO_INSUFICIENTE"), MoedaUtil.conveterMoedaBr(botComando.verificarSaldo(usuario.id())));
				} catch (ContaInexistenteExcecao e2) {
					retorno = PropriedadesUtil.carregarMensagensIntegracao().getProperty("RETORNO_CONTA_INEXISTENTE");
				}
			}
		} catch (PrazoEmprestimoExcedidoExcecao e) {
			retorno = String.format(PropriedadesUtil.carregarMensagensIntegracao().getProperty("RETORNO_EXCEDIDO_PRAZO_EMPRESTIMO"), "36");
		} catch (SaldoInsuficienteExcecao e) {
			try {
				retorno = String.format(PropriedadesUtil.carregarMensagensIntegracao().getProperty("RETORNO_SALDO_INSUFICIENTE"), MoedaUtil.conveterMoedaBr(botComando.verificarSaldo(usuario.id())));
			} catch (ContaInexistenteExcecao e2) {
				retorno = PropriedadesUtil.carregarMensagensIntegracao().getProperty("RETORNO_CONTA_INEXISTENTE");
			}
		} catch (EmprestimoAbertoExcecao e) {
			retorno = PropriedadesUtil.carregarMensagensIntegracao().getProperty("RETORNO_EMPRESTIMO_ABERTO");
		}
		return retorno;
	}

	@Override
	public String tratarPrimeiraInteracao(Chat usuario) {

		BotComando botComando = new BotComando();
		Double valorMaximoEmprestimo = null;
		NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		StringBuffer retorno = new StringBuffer();

		try {
			valorMaximoEmprestimo = botComando.verificarValorMaximoEmprestimo(usuario.id());
			retorno.append(super.tratarPrimeiraInteracao(usuario)).append(ConstantesBot.PULAR_UMA_LINHA)
					.append("O limite máximo que você pode pedir emprestado é: ")
					.append(format.format(valorMaximoEmprestimo));
		} catch (ContaInexistenteExcecao e) {
			retorno.append(PropriedadesUtil.carregarMensagensIntegracao().getProperty("RETORNO_CONTA_INEXISTENTE"));
		} catch (SaldoInsuficienteExcecao e) {
			try {
				retorno.append(String.format(PropriedadesUtil.carregarMensagensIntegracao().getProperty("RETORNO_SALDO_INSUFICIENTE"), MoedaUtil.conveterMoedaBr(botComando.verificarSaldo(usuario.id()))));
			} catch (ContaInexistenteExcecao e1) {
				retorno.append(PropriedadesUtil.carregarMensagensIntegracao().getProperty("RETORNO_CONTA_INEXISTENTE"));
			}
		}
		return retorno.toString();
	}

}
