package br.com.fiap.bot.validacao;

import br.com.fiap.bot.contantes.EnumComandosBot;

public class IntegracaoBotAjuda extends IntegracaoBotConsulta {

	@Override
	public String integrarBanco(String resposta, EnumComandosBot comandoBot) {
		return "";
	}

	@Override
	public String exibeMensagemPrimeiraIntegracao() {
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
