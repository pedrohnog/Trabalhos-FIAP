package br.com.fiap.bot.validacao;

import br.com.fiap.bot.contantes.EnumComandosBot;

public class IntegracaoBotCriarConta extends IntegracaoBotSolicitacao{
	
	public IntegracaoBotCriarConta() {
		super("Legal, agora informe seu cpf para que possamos criar sua conta!", "XXXXXXXXXXX - Ex:35263585652");
	}
	
	@Override
	public Boolean validarResposta(String resposta, EnumComandosBot comandoBot) {
		boolean respostaOk = true;
		if(resposta != null){
			resposta = resposta.trim();
			try{
				if(!(Long.valueOf(resposta) > 0L) || resposta.length() != 11 ){
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
		return "Parabéns! Conta criada com sucesso!";
	}
}
