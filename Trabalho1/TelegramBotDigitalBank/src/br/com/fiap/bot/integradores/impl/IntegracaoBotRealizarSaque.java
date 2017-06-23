package br.com.fiap.bot.integradores.impl;

import com.pengrad.telegrambot.model.Chat;

import br.com.fiap.banco.comandos.BotComando;
import br.com.fiap.banco.excecao.ContaInexistenteExcecao;
import br.com.fiap.banco.excecao.SaldoInsuficienteExcecao;
import br.com.fiap.bot.integradores.IntegracaoBotSolicitacao;

public class IntegracaoBotRealizarSaque extends IntegracaoBotSolicitacao {

	public IntegracaoBotRealizarSaque() {
		super("Quanto voc� gostaria de sacar? Informe apenas o valor", "Valor para sacar (Ex: 120,00)");
	}

	@Override
	public Boolean validarResposta(String resposta) {
		boolean respostaOk = true;
		if(resposta != null){
			resposta = resposta.trim();		
			if (resposta.contains(",")){
				resposta = resposta.replace(",", ".");
			}
			try{
				if(!(Double.valueOf(resposta) > 0D)){
					respostaOk = false;
				}				
			}catch(NumberFormatException e){
				respostaOk = false;
			}
		}	
		return respostaOk;
	}

	@Override
	public String integrarBanco(String resposta, Chat usuario) {
		String retorno = "";
		Double saldoAnterior = 0D;
		BotComando botComando = new BotComando();		
		if (resposta.contains(",")){
			resposta = resposta.replace(",", ".");
		}
		try {			
			saldoAnterior = botComando.verificarSaldo(usuario.id());
			botComando.realizarSaque(usuario.id(), Double.valueOf(resposta));			
			retorno = "Saque realizado com sucesso! Seu novo saldo � " + botComando.verificarSaldo(usuario.id());
		} catch (SaldoInsuficienteExcecao e) {
			retorno = "Saque n�o realizado! Seu saldo � insuficiente! Saldo na conta: " + saldoAnterior;			
		} catch (ContaInexistenteExcecao e) {
			retorno = "Saque n�o realizado! Voc� ainda n�o tem uma conta, para criar sua conta digite /criar_conta";
		}
		return retorno;
	}

}
