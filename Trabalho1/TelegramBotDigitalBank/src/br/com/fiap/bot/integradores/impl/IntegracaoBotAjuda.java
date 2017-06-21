package br.com.fiap.bot.integradores.impl;

import com.pengrad.telegrambot.model.Chat;

import br.com.fiap.bot.integradores.IntegracaoBot;

public class IntegracaoBotAjuda implements IntegracaoBot {

	public String integrarBanco(String resposta, Chat usuario) {
		return "";
	}

	public String tratarPrimeiraInteracao(Chat usuario) {
		return  "Segue a lista de comandos que podem ser efetuados, espero poder ajudar... "
				+"\n/criar_conta"
				+"\n/modificar_conta"
				+"\n/incluir_dependente"
				+"\n/exibir_dados"
				+"\n/realizar_deposito"
				+"\n/realizar_saque"
				+"\n/consultar_extrato"
				+"\n/consultar_depositos"
				+"\n/consultar_saques"
				+"\n/consultar_tarifas_pagas"
				+"\n/solicitar_emprestimo"
				+"\n/consultar_emprestimo"
				+"\n/ajuda";
	}

}
