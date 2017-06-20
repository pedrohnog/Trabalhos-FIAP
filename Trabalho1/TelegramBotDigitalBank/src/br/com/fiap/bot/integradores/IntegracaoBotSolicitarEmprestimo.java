package br.com.fiap.bot.integradores;

import com.pengrad.telegrambot.model.Chat;

public class IntegracaoBotSolicitarEmprestimo extends IntegracaoBotSolicitacao {

	IntegracaoBotSolicitarEmprestimo() {
		super("mensagemPrimeiraIntegracao", "dominioResposta");
	}

	@Override
	public Boolean validarResposta(String resposta) {
		boolean respostaOk = true;
		if(resposta != null){
			resposta = resposta.trim();
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
		return "Emprestimo solicitado com sucesso!";
	}
	
	@Override
	public String tratarPrimeiraInteracao(Chat usuario) {
		return "Verificar....";
	}

}
