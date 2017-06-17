package br.com.fiap.bot.validacao;

import br.com.fiap.bot.constantes.EnumComandosBot;

public class IntegracaoBotRealizarDeposito extends IntegracaoBotSolicitacao {

	public IntegracaoBotRealizarDeposito() {
		super("Quanto você gostaria de depositar? Digite apenas o valor.", "Valor para depositar (Ex: 500,00)");
	}

	@Override
	public Boolean validarResposta(String resposta, EnumComandosBot comandoBot) {
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
	public String integrarBanco(String resposta, EnumComandosBot comandoBot) {
		boolean retornoBanco = true; 
		
		if (retornoBanco){
			return "Deposito solicitado com sucesso!";
		}else{
			return "Ocorreu um erro com o seu deposito! \n Tente mais tarde novamente.";
		}
	}

}
