package br.com.fiap.bot.integradores;

import com.pengrad.telegrambot.model.Chat;

import br.com.fiap.banco.comandos.ContaComando;
import br.com.fiap.banco.excecao.ContaInexistenteExcecao;

public class IntegracaoBotIncluirDependente extends IntegracaoBotSolicitacao {

	public IntegracaoBotIncluirDependente() {
		super("Me fale os dados do novo dependente. Por favor, informe nesta padrão: cpf - nome - email (Ex: 36521563511 - Joao - joao@email.com)", "cpf - nome - email (Ex: 36521563511 - Joao - joao@email.com)");
	}

	@Override
	public Boolean validarResposta(String resposta) {
		boolean respostaOk = true;		
		resposta = resposta.trim();
		String [] respostas = resposta.split("-");
		
		if(respostas.length != 3){
			respostaOk = false;
		}else{
			for (int i = 0; i < respostas.length; i++) {
				if(respostas[i].trim().length() == 0){
					respostaOk = false;
					break;
				}
			}
		}			
		return respostaOk;
	}

	@Override
	public String integrarBanco(String resposta, Chat usuario) {
		ContaComando contaComando = new ContaComando();
		String retorno = "";
		try {
			//TODO aguardar ajuste no banco
			contaComando.incluirDependente(usuario.id(), "Teste 2", "Teste", "1187654321", "01010101099", "teste2@teste.com.br",
					"98765432100");
			retorno = "Parabéns! Dependente incluido com sucesso!";
		} catch (ContaInexistenteExcecao e) {
			retorno = "Você ainda não tem uma conta, para criar sua conta digite /criar_conta";
		}
		return retorno;
	}

}
