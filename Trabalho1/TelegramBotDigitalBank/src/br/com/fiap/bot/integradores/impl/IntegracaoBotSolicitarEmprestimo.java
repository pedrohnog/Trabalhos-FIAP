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
import br.com.fiap.bot.integradores.IntegracaoBotSolicitacao;
import br.com.fiap.bot.util.ConverteMoedaUtil;

public class IntegracaoBotSolicitarEmprestimo extends IntegracaoBotSolicitacao {

	IntegracaoBotSolicitarEmprestimo() {
		super("Para efetivar seu empréstimo, informe o valor que você precisa e a quantidade de parcelas.", "valor - quantidade parcelas (Ex: 500,00 - 36)");
	}

	@Override
	public Boolean validarResposta(String resposta) {
		boolean respostaOk = true;
		if(resposta != null){
			String [] respostas = resposta.split("-");
			try{
				if(!(Double.valueOf(respostas[0].trim()) > 0D)){
					respostaOk = false;
				}	
				if(!(Integer.valueOf(respostas[1].trim()) > 0D)){
					respostaOk = false;
				}	
			}catch(NumberFormatException e){
				respostaOk = false;
			}
		}else{
			respostaOk = false;			
		}
		return respostaOk;
	}

	@Override
	public String integrarBanco(String resposta, Chat usuario) {
		BotComando botComando = new BotComando();
		String [] respostas = resposta.split("-");
		String retorno = "";
		Double valorEmprestimo = Double.valueOf(respostas[0].trim());
		Integer quantidadeParcelas = Integer.valueOf(respostas[1].trim());
		
		try {
			botComando.solicitarEmprestimo(usuario.id(), valorEmprestimo, quantidadeParcelas);
			retorno = "Emprestimo solicitado com sucesso! O dinheiro está na sua conta!";
		} catch (ContaInexistenteExcecao e) {
			retorno = "Você ainda não tem uma conta, para criar sua conta digite /criar_conta";
		} catch (ValorEmprestimoExcedidoExcecao e) {
			try {				
				retorno = "Empréstimo não confirmado! O valor máximo que você pode solicitar é: "
						+ ConverteMoedaUtil.conveterMoedaBr(botComando.verificarValorMaximoEmprestimo(usuario.id()));
			} catch (ContaInexistenteExcecao | SaldoInsuficienteExcecao e1) {
				retorno = "Empréstimo não confirmado! Valor excedido!";
			}
			
		} catch (PrazoEmprestimoExcedidoExcecao e) {
			retorno = "Empréstimo não confirmado! O valor máximo de parcela que você pode solicitar é 36";
		} catch (SaldoInsuficienteExcecao e) {
			retorno = "Empréstimo não confirmado! Saldo na conta insuficiente!";
		} catch (EmprestimoAbertoExcecao e) {
			retorno = "Empréstimo não confirmado! Você já tem um empréstimo aberto.";
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
			retorno.append(super.tratarPrimeiraInteracao(usuario))
			.append("\nO limite máximo que você pode pedir empresta é: ")
			.append(format.format(valorMaximoEmprestimo));
		} catch (ContaInexistenteExcecao e) {
			retorno.append("Você ainda não tem uma conta, para criar sua conta digite /criar_conta");
		} catch (SaldoInsuficienteExcecao e) {
			retorno.append("Desculpe, mas você não tem saldo para solicitar empréstimo.");
		}
		return retorno.toString();
	}

}
