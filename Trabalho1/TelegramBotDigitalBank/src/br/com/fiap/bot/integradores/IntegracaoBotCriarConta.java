package br.com.fiap.bot.integradores;

import com.pengrad.telegrambot.model.Chat;

import br.com.fiap.banco.comandos.ContaComando;

public class IntegracaoBotCriarConta extends IntegracaoBotSolicitacao{
	
	public IntegracaoBotCriarConta() {
		super("Legal, agora informe seu cpf, telefone e email no seguinte formato para que possamos criar sua conta!\n cpf - telefone - email (Ex: 35263585652 - 25547685 - jose@email.com",
				"cpf - telefone - email (Ex: 35263585652 - 25547685 - jose@email.com");
	}
	
	@Override
	public Boolean validarResposta(String resposta) {
		boolean respostaOk = true;
		if(resposta != null){
			resposta = resposta.trim();
			try{
				String [] respostas = resposta.split("-");
				
				if(respostas.length != 3){
					respostaOk = false;
				}else{
					String cpf = respostas[0].trim();
					String telefone = respostas[1].trim();
					String email = respostas[2].trim();
					if(cpf.length() != 11 || telefone.length() > 11 || email.length() > 50){
						respostaOk = false;
					}
				}
				
			}catch(NumberFormatException e){
				respostaOk = false;
			}
		}
		return respostaOk;
	}

	@Override
	public String integrarBanco(String resposta, Chat usuario) {
		ContaComando contaComando = new ContaComando();
		String [] respostas = resposta.split("-");
		String cpf = respostas[0].trim();
		String telefone = respostas[1].trim();
		String email = respostas[2].trim();
		contaComando.criarConta(usuario.id(), usuario.firstName(), usuario.lastName(), telefone, cpf, email);
		return "Parabéns! Conta criada com sucesso!";
	}
}
