package br.com.fiap.bot.validacao;

import br.com.fiap.bot.contantes.EnumComandosBot;

public class IntegracaoBotRealizarDeposito extends IntegracaoBotSolicitacao {

	public IntegracaoBotRealizarDeposito() {
		super("Quanto você gostaria de depositar? Digite apenas o valor.", "Valor para depositar (Ex: 500.00)");
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
		return "Deposito solicitado com sucesso!";
	}

}
