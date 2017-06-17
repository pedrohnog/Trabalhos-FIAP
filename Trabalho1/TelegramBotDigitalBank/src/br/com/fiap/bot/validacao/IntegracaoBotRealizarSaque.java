package br.com.fiap.bot.validacao;

import br.com.fiap.bot.constantes.EnumComandosBot;

public class IntegracaoBotRealizarSaque extends IntegracaoBotSolicitacao {

	public IntegracaoBotRealizarSaque() {
		super("Quanto você gostaria de sacar? Informe apenas o valor", "Valor para sacar (Ex: 120.00)");
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
		return "Saque realizado com sucesso!";
	}

}
