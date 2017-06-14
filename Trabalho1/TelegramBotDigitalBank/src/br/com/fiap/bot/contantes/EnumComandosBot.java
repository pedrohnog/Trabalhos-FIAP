package br.com.fiap.bot.contantes;

import java.util.HashMap;
import java.util.Map;

public enum EnumComandosBot {

	CRIAR_CONTA("/criar_conta", "Favor informar o seu CPF para criar uma nova conta"),
	MODIFICAR_CONTA("/modificar_conta", "Favor informar os dados para atualizar no seguinte padrão 'cpf - email'"),
	INCLUIR_DEPENDENTE("/incluir_dependente", "Favor informar os dados para inserir novo dependente no seguinte padrão 'cpf - nome - email'"),
	EXIBIR_DADOS("/exibir_dados", "Dados da conta:"),
	REALIZAR_DEPOSITOS("/realizar_deposito", "Informe o valor do depósito a ser realizado"),
	REALIZAR_SAQUE("/realizar_saque", "Quanto você gostaria de sacar?"),
	CONSULTAR_EXTRATO("/consultar_extrato", "Extrato da sua conta:"),
	CONSULTA_DEPOSITOS("/consultar_depositos", "Depositos realizados:"),
	CONSULTAR_SAQUES("/consultar_saques", "Saques realizados:"),
	CONSULTAR_TARIFAS_PAGAS("/consultar_tarifas_pagas", "Tarifas pagas:"),
	SOLICITAR_EMPRESTIMO("/solicitar_emprestimo", "Qual o valor do empréstimo?"),
	CONSULTAR_EMPRESTIMO("/consultar_emprestimo", "Segue dados do(s) seu(s) emprestimo(s)"),
	AJUDA("/ajuda", "Segue a lista de comandos que podem ser efetuados "
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
			+"\n/ajuda");
	
	public static final Map<String, EnumComandosBot> LISTA_COMANDOS_PARAMETRIZADOS = new HashMap<String, EnumComandosBot>();
	
	private String comando;
	private String mensagemRetorno;
	
	EnumComandosBot(String comando, String mensagemRetorno){
		this.comando = comando;
		this.mensagemRetorno = mensagemRetorno;
	}
	
	static{
		for(EnumComandosBot enumComando : EnumComandosBot.values()){
			LISTA_COMANDOS_PARAMETRIZADOS.put(enumComando.comando, enumComando);
		}
	}
	
	public String getComando() {
		return comando;
	}

	public String getMensagemRetorno() {
		return mensagemRetorno;
	}	
	
}
