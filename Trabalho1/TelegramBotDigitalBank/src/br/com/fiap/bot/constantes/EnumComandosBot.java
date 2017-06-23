package br.com.fiap.bot.constantes;

import java.util.HashMap;
import java.util.Map;

/**
 * Enum respons�vel por definir os comandos utilizados pelo bot
 *
 */
public enum EnumComandosBot {

	/**
	 * Constante que define o comando de �nicio do sistema
	 */
	START								("/start",								EnumTipoIntegracaoBot.CONSULTA),
	/**
	 * Constante que define o comando de cria��o de conta
	 */
	CRIAR_CONTA							("/criar_conta",						EnumTipoIntegracaoBot.SOLICITACAO),
	/**
	 * Constante que define o comando de modifica��o da conta
	 */
	MODIFICAR_CONTA						("/modificar_conta",					EnumTipoIntegracaoBot.SOLICITACAO),
	/**
	 * Constante que define o comando de inclus�o de dependente
	 */
	INCLUIR_DEPENDENTE					("/incluir_dependente",					EnumTipoIntegracaoBot.SOLICITACAO),
	/**
	 * Constante que define o comando de dep�sito
	 */
	REALIZAR_DEPOSITOS					("/realizar_deposito",					EnumTipoIntegracaoBot.SOLICITACAO),
	/**
	 * Constante que define o comando de saque
	 */
	REALIZAR_SAQUE						("/realizar_saque",						EnumTipoIntegracaoBot.SOLICITACAO),
	/**
	 * Constante que define o comando de consulta de saldo
	 */
	CONSULTAR_SALDO						("/consultar_saldo_conta",				EnumTipoIntegracaoBot.CONSULTA),
	/**
	 * Constante que define o comando de solicita��o de empr�stimo
	 */
	SOLICITAR_EMPRESTIMO				("/solicitar_emprestimo",				EnumTipoIntegracaoBot.SOLICITACAO),
	/**
	 * Constante que define o comando de pagamento de parcelas vencidas do empr�stimo
	 */
	PAGAR_PARCELAS_VENCIDAS_EMPRESTIMO	("/pagar_parcelas_vencidas_emprest",	EnumTipoIntegracaoBot.SOLICITACAO),
	/**
	 * Constante que define o comando de pagamentos de uma parcela do empr�stimo
	 */
	PAGAR_PARCELA_EMPRESTIMO			("/pagar_parcela_emprestimo",			EnumTipoIntegracaoBot.SOLICITACAO),
	/**
	 * Constante que define o comando de exibi��o de dados do titular e dos dependentes
	 */
	EXIBIR_DADOS						("/exibir_info_titular_dependentes",	EnumTipoIntegracaoBot.CONSULTA),
	/**
	 * Constante que define o comando de consulta de extrato
	 */
	CONSULTAR_EXTRATO					("/consultar_extrato",					EnumTipoIntegracaoBot.CONSULTA),
	/**
	 * Constante que define o comando de consulta de lan�amentos
	 */
	CONSULTAR_LANCAMENTOS				("/consultar_lancamentos",				EnumTipoIntegracaoBot.CONSULTA),
	/**
	 * Constante que define o comando de consulta de retiradas
	 */
	CONSULTAR_RETIRADAS					("/consultar_retiradas",				EnumTipoIntegracaoBot.CONSULTA),
	/**
	 * Constante que define o comando de consulta de tarifas pagas
	 */
	CONSULTAR_TARIFAS_PAGAS				("/consultar_tarifas_pagas",			EnumTipoIntegracaoBot.CONSULTA),
	/**
	 * Constante que define o comando de consulta de empr�stimos
	 */
	CONSULTAR_EMPRESTIMO				("/consultar_emprestimo",				EnumTipoIntegracaoBot.CONSULTA),
	/**
	 * Constante que define o comando de consulta do saldo devedor do empr�stimo
	 */
	CONSULTAR_SALDO_DEVEDOR_EMPRESTIMO	("/consultar_saldo_devedor_emprest",	EnumTipoIntegracaoBot.CONSULTA),
	/**
	 * Constante que define o comando de ajuda
	 */
	AJUDA								("/ajuda",								EnumTipoIntegracaoBot.CONSULTA );

	public static final Map<String, EnumComandosBot> LISTA_COMANDO_INTERACOES = new HashMap<String, EnumComandosBot>();
	
	private String comando;
	private EnumTipoIntegracaoBot enumTipoIntegracaoBot;
	
	EnumComandosBot(String comando, EnumTipoIntegracaoBot enumTipoIntegracaoBot){
		this.comando = comando;
		this.enumTipoIntegracaoBot = enumTipoIntegracaoBot;
	}
	
	static{
		for(EnumComandosBot enumComando : EnumComandosBot.values()){
			LISTA_COMANDO_INTERACOES.put(enumComando.comando, enumComando);
		}
	}
	
	public String getComando() {
		return comando;
	}

	public EnumTipoIntegracaoBot getEnumTipoIntegracaoBot() {
		return enumTipoIntegracaoBot;
	}
}
