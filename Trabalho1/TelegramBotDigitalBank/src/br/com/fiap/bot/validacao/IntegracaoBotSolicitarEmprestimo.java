package br.com.fiap.bot.validacao;

import br.com.fiap.bot.contantes.EnumComandosBot;

public class IntegracaoBotSolicitarEmprestimo extends IntegracaoBotSolicitacao {

	IntegracaoBotSolicitarEmprestimo(String mensagemPrimeiraIntegracao, String dominioResposta) {
		super(mensagemPrimeiraIntegracao, dominioResposta);
	}

	@Override
	public Boolean validarResposta(String resposta, EnumComandosBot comandoBot) {
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
	public String integrarBanco(String resposta, EnumComandosBot comandoBot) {
		return "Emprestimo solicitado com sucesso!";
	}

}
