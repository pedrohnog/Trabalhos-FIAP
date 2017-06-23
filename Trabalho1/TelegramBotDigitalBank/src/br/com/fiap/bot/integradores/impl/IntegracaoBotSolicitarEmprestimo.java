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

/**
 * Classe respons�vel pelo comando de solicita��o de empr�stimo do Bot
 *
 */
public class IntegracaoBotSolicitarEmprestimo extends IntegracaoBotSolicitacao {

	public IntegracaoBotSolicitarEmprestimo() {
		super("Para efetivar seu empr�stimo, informe o valor que voc� precisa e a quantidade de parcelas. "
				+ ConstantesBot.PULAR_UMA_LINHA
				+ "Informe no seguinte padr�o: valor - quantidade parcelas (Ex: 500,00 - 36)",
				"valor - quantidade parcelas (Ex: 500,00 - 36)");
	}

	@Override
	public Boolean validarResposta(String resposta) {
		boolean respostaOk = true;
		if (resposta != null) {
			String[] respostas = resposta.split("-");
			try {
				if (respostas.length == 2) {
					if (!(Double.valueOf(respostas[0].trim()) > 0D)) {
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
		Double valorEmprestimo = Double.valueOf(respostas[0].trim());
		Integer quantidadeParcelas = Integer.valueOf(respostas[1].trim());

		try {
			botComando.solicitarEmprestimo(usuario.id(), valorEmprestimo, quantidadeParcelas);
			retorno = "Emprestimo solicitado com sucesso! O dinheiro est� na sua conta!";
		} catch (ContaInexistenteExcecao e) {
			retorno = "Voc� ainda n�o tem uma conta, para criar sua conta digite /criar_conta";
		} catch (ValorEmprestimoExcedidoExcecao e) {
			try {
				retorno = "Empr�stimo n�o confirmado! O valor m�ximo que voc� pode solicitar �: "
						+ MoedaUtil.conveterMoedaBr(botComando.verificarValorMaximoEmprestimo(usuario.id()));
			} catch (ContaInexistenteExcecao | SaldoInsuficienteExcecao e1) {
				retorno = "Empr�stimo n�o confirmado! Valor excedido!";
			}

		} catch (PrazoEmprestimoExcedidoExcecao e) {
			retorno = "Empr�stimo n�o confirmado! O valor m�ximo de parcela que voc� pode solicitar � 36";
		} catch (SaldoInsuficienteExcecao e) {
			retorno = "Empr�stimo n�o confirmado! Saldo na conta insuficiente!";
		} catch (EmprestimoAbertoExcecao e) {
			retorno = "Empr�stimo n�o confirmado! Voc� j� tem um empr�stimo aberto.";
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
					.append("O limite m�ximo que voc� pode pedir emprestado �: ")
					.append(format.format(valorMaximoEmprestimo));
		} catch (ContaInexistenteExcecao e) {
			retorno.append("Voc� ainda n�o tem uma conta, para criar sua conta digite /criar_conta");
		} catch (SaldoInsuficienteExcecao e) {
			retorno.append("Desculpe, mas voc� n�o tem saldo para solicitar empr�stimo.");
		}
		return retorno.toString();
	}

}
