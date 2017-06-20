package br.com.fiap.bot.integradores;

import com.pengrad.telegrambot.model.Chat;

import br.com.fiap.banco.comandos.OperacoesComando;
import br.com.fiap.banco.excecao.ContaInexistenteExcecao;

public class IntegracaoBotRealizarDeposito extends IntegracaoBotSolicitacao {

	public IntegracaoBotRealizarDeposito() {
		super("Quanto você gostaria de depositar? Digite apenas o valor.", "Valor para depositar (Ex: 500,00)");
	}

	@Override
	public Boolean validarResposta(String resposta) {
		boolean respostaOk = true;
		if(resposta != null){
			resposta = resposta.trim();
			if (resposta.contains(",")){
				resposta.replace(",", ".");
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
		OperacoesComando operacoesComando = new OperacoesComando();
		try {
			operacoesComando.realizarDeposito(usuario.id(), Double.valueOf(resposta));
			retorno = "Deposito realizado com sucesso!";
		} catch (ContaInexistenteExcecao e) {
			retorno = "Deposito não realizado! Você ainda não tem uma conta, para criar sua conta digite /criar_conta";
		}
		
		return retorno;
	}

}
