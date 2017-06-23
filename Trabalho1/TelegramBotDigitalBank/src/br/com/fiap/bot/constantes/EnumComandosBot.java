package br.com.fiap.bot.constantes;

import static br.com.fiap.bot.constantes.EnumTipoIntegracaoBot.CONSULTA;
import static br.com.fiap.bot.constantes.EnumTipoIntegracaoBot.SOLICITACAO;

import java.util.HashMap;
import java.util.Map;

import br.com.fiap.bot.integradores.impl.IntegracaoBotAjuda;
import br.com.fiap.bot.integradores.impl.IntegracaoBotConsultaEmprestimo;
import br.com.fiap.bot.integradores.impl.IntegracaoBotConsultaExtrato;
import br.com.fiap.bot.integradores.impl.IntegracaoBotConsultaLancamento;
import br.com.fiap.bot.integradores.impl.IntegracaoBotConsultaRetiradas;
import br.com.fiap.bot.integradores.impl.IntegracaoBotConsultaSaldoDevedorEmprestimo;
import br.com.fiap.bot.integradores.impl.IntegracaoBotConsultaTarifaPaga;
import br.com.fiap.bot.integradores.impl.IntegracaoBotConsultarSaldoConta;
import br.com.fiap.bot.integradores.impl.IntegracaoBotCriarConta;
import br.com.fiap.bot.integradores.impl.IntegracaoBotExibirInformacoesTitularDependentes;
import br.com.fiap.bot.integradores.impl.IntegracaoBotIncluirDependente;
import br.com.fiap.bot.integradores.impl.IntegracaoBotModificarConta;
import br.com.fiap.bot.integradores.impl.IntegracaoBotPagarParcelaEmprestimo;
import br.com.fiap.bot.integradores.impl.IntegracaoBotPagarTodasParcelasEmprestimo;
import br.com.fiap.bot.integradores.impl.IntegracaoBotRealizarDeposito;
import br.com.fiap.bot.integradores.impl.IntegracaoBotRealizarSaque;
import br.com.fiap.bot.integradores.impl.IntegracaoBotSolicitarEmprestimo;
import br.com.fiap.bot.integradores.impl.IntegracaoBotStart;

/**
 * Enum responsável por definir os comandos utilizados pelo bot
 *
 */
public enum EnumComandosBot {

	/**
	 * Constante que define o comando de ínicio do sistema
	 */
	START								("/start",								CONSULTA,		IntegracaoBotStart.class),
	/**
	 * Constante que define o comando de criação de conta
	 */
	CRIAR_CONTA							("/criar_conta",						SOLICITACAO,	IntegracaoBotCriarConta.class),
	/**
	 * Constante que define o comando de modificação da conta
	 */
	MODIFICAR_CONTA						("/modificar_conta",					SOLICITACAO,	IntegracaoBotModificarConta.class),
	/**
	 * Constante que define o comando de inclusão de dependente
	 */
	INCLUIR_DEPENDENTE					("/incluir_dependente",					SOLICITACAO,	IntegracaoBotIncluirDependente.class),
	/**
	 * Constante que define o comando de depósito
	 */
	REALIZAR_DEPOSITOS					("/realizar_deposito",					SOLICITACAO,	IntegracaoBotRealizarDeposito.class),
	/**
	 * Constante que define o comando de saque
	 */
	REALIZAR_SAQUE						("/realizar_saque",						SOLICITACAO,	IntegracaoBotRealizarSaque.class),
	/**
	 * Constante que define o comando de consulta de saldo
	 */
	CONSULTAR_SALDO						("/consultar_saldo_conta",				CONSULTA,		IntegracaoBotConsultarSaldoConta.class),
	/**
	 * Constante que define o comando de solicitação de empréstimo
	 */
	SOLICITAR_EMPRESTIMO				("/solicitar_emprestimo",				SOLICITACAO,	IntegracaoBotSolicitarEmprestimo.class),
	/**
	 * Constante que define o comando de pagamento de parcelas vencidas do empréstimo
	 */
	PAGAR_PARCELAS_VENCIDAS_EMPRESTIMO	("/pagar_parcelas_vencidas_emprest",	SOLICITACAO,	IntegracaoBotPagarTodasParcelasEmprestimo.class),
	/**
	 * Constante que define o comando de pagamentos de uma parcela do empréstimo
	 */
	PAGAR_PARCELA_EMPRESTIMO			("/pagar_parcela_emprestimo",			SOLICITACAO,	IntegracaoBotPagarParcelaEmprestimo.class),
	/**
	 * Constante que define o comando de exibição de dados do titular e dos dependentes
	 */
	EXIBIR_DADOS						("/exibir_info_titular_dependentes",	CONSULTA,		IntegracaoBotExibirInformacoesTitularDependentes.class),
	/**
	 * Constante que define o comando de consulta de extrato
	 */
	CONSULTAR_EXTRATO					("/consultar_extrato",					CONSULTA,		IntegracaoBotConsultaExtrato.class),
	/**
	 * Constante que define o comando de consulta de lançamentos
	 */
	CONSULTAR_LANCAMENTOS				("/consultar_lancamentos",				CONSULTA,		IntegracaoBotConsultaLancamento.class),
	/**
	 * Constante que define o comando de consulta de retiradas
	 */
	CONSULTAR_RETIRADAS					("/consultar_retiradas",				CONSULTA,		IntegracaoBotConsultaRetiradas.class),
	/**
	 * Constante que define o comando de consulta de tarifas pagas
	 */
	CONSULTAR_TARIFAS_PAGAS				("/consultar_tarifas_pagas",			CONSULTA,		IntegracaoBotConsultaTarifaPaga.class),
	/**
	 * Constante que define o comando de consulta de empréstimos
	 */
	CONSULTAR_EMPRESTIMO				("/consultar_emprestimo",				CONSULTA,		IntegracaoBotConsultaEmprestimo.class),
	/**
	 * Constante que define o comando de consulta do saldo devedor do empréstimo
	 */
	CONSULTAR_SALDO_DEVEDOR_EMPRESTIMO	("/consultar_saldo_devedor_emprest",	CONSULTA,		IntegracaoBotConsultaSaldoDevedorEmprestimo.class),
	/**
	 * Constante que define o comando de ajuda
	 */
	AJUDA								("/ajuda",								CONSULTA,		IntegracaoBotAjuda.class);

	public static final Map<String, EnumComandosBot> LISTA_COMANDO_INTERACOES = new HashMap<String, EnumComandosBot>();
	
	private String comando;
	private EnumTipoIntegracaoBot enumTipoIntegracaoBot;
	private Class<?> classeComando;
	
	EnumComandosBot(String comando, EnumTipoIntegracaoBot enumTipoIntegracaoBot, Class<?> classeComando){
		this.comando = comando;
		this.enumTipoIntegracaoBot = enumTipoIntegracaoBot;
		this.classeComando = classeComando;
	}
	
	static{
		for(EnumComandosBot enumComando : EnumComandosBot.values()){
			LISTA_COMANDO_INTERACOES.put(enumComando.comando, enumComando);
		}
	}
	
	public String getComando() {
		return this.comando;
	}

	public EnumTipoIntegracaoBot getEnumTipoIntegracaoBot() {
		return this.enumTipoIntegracaoBot;
	}
	
	public Class<?> getClasseComando() {
		return this.classeComando;
	}
}
