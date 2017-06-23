package br.com.fiap.bot.util;

import java.util.ArrayList;
import java.util.List;

import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;

import br.com.fiap.bot.Bot;
import br.com.fiap.bot.constantes.ConstantesBot;
import br.com.fiap.bot.constantes.EnumComandosBot;
import br.com.fiap.bot.dao.impl.MensagemIntegracaoDao;
import br.com.fiap.bot.entidades.MensagemIntegracao;
import br.com.fiap.bot.integradores.IntegracaoBot;
import br.com.fiap.bot.integradores.impl.IntegracaoBotAjuda;
import br.com.fiap.bot.integradores.impl.IntegracaoBotConsultaEmprestimo;
import br.com.fiap.bot.integradores.impl.IntegracaoBotConsultaExtrato;
import br.com.fiap.bot.integradores.impl.IntegracaoBotConsultaLancamento;
import br.com.fiap.bot.integradores.impl.IntegracaoBotConsultaSaldoDevedorEmprestimo;
import br.com.fiap.bot.integradores.impl.IntegracaoBotConsultaRetiradas;
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

public class BotUtil {

	public static String retirarComandoDaMensagem(String mensagemRecebida, String comando) {
		return mensagemRecebida.replaceAll(comando, "");
	}

	/**
	 * Encontra todos os comandos conhecidos pelo bot enviados pelo usuário
	 * @param mensagemRecebida Mensagem enviada pelo usuário
	 * @return List<String> com os comandos encontrados
	 */
	public static List<String> encontrarComandosNaMensagem(String mensagemRecebida) {
		String [] palavras = mensagemRecebida.split(" ");
		List<String> comandosEncontratos = new ArrayList<>(); 
		for (int i = 0; i < palavras.length; i++) {
			if(palavras[i].startsWith("/") && EnumComandosBot.LISTA_COMANDO_INTERACOES.containsKey(palavras[i].trim())){
				comandosEncontratos.add(palavras[i].trim());
			}
		}
		return comandosEncontratos;
	}

	/**
	 * Valida ser a mensagemRecebida é apenas o comando solicita (ex: /criar_conta) 
	 * @param mensagemRecebida Mensagem enviada pelo usuário no telegram
	 * @return true - Mensagem com um comando simples | false - Mensagem provavelmente contém o comando e o complemento referente o comando
	 */
	public static boolean isComandoSimples(String mensagemRecebida) {
		return EnumComandosBot.LISTA_COMANDO_INTERACOES.containsKey(mensagemRecebida);
	}

	/**
	 * Valida se o bot esta aguardando uma resposta do usuário 
	 * @param usuario Usuario do Telegram
	 * @return true - Bot aguardando resposta do Usuario | false - Bot não esta aguardando mensagem de resposta
	 */
	public static boolean botAguardandoRespostaDoUsuario(Chat usuario) {
		return Bot.ULTIMO_COMANDO_DO_USUARIO.containsKey(usuario.id());
	}

	/**
	 * Válida se no comando enviado contém uma entidade do tipo bot_command 
	 * @param message Objeto Message do Telegram
	 * @return <code>true</code> se na mensagem contém pelo menos um comando, se não <code>false</code>
	 */
	public static boolean isMensagemComComando(Message message) {
		boolean mensagemComComando = false;
		
		if(message.entities() != null){
			for (int i = 0; i < message.entities().length; i++) {
				if(message.entities()[i].type().name().equals(ConstantesBot.BOT_COMMAND)){
					mensagemComComando = true;
					break;
				}
			}
		}
		
		return mensagemComComando;
	}

	/**
	 * Método responsável por definir qual será a classe de validação e integração conforme os comandos
	 * @param EnumComandosBot referente o comando executado
	 * @return Instância da classe de acordo com o comando executado
	 */
	public static IntegracaoBot definirClasseIntegracao(EnumComandosBot ultimoComandoExecutado) {
		IntegracaoBot retorno = null;
		
		switch (ultimoComandoExecutado.getComando()) {
		case "/start":
			retorno = new IntegracaoBotStart();
			break;
		case "/ajuda":
			retorno = new IntegracaoBotAjuda();
			break;			
		case "/criar_conta":
			retorno = new IntegracaoBotCriarConta();
			break;
		case "/modificar_conta":
			retorno = new IntegracaoBotModificarConta();
			break;
		case "/incluir_dependente":
			retorno = new IntegracaoBotIncluirDependente();
			break;
		case "/exibir_info_titular_dependentes":
			retorno = new IntegracaoBotExibirInformacoesTitularDependentes();
			break;
		case "/realizar_deposito":
			retorno = new IntegracaoBotRealizarDeposito();
			break;
		case "/realizar_saque":
			retorno = new IntegracaoBotRealizarSaque();
			break;
		case "/consultar_extrato":
			retorno = new IntegracaoBotConsultaExtrato();
			break;
		case "/consultar_lancamentos":
			retorno = new IntegracaoBotConsultaLancamento();
			break;
		case "/consultar_retiradas":
			retorno = new IntegracaoBotConsultaRetiradas();
			break;
		case "/solicitar_emprestimo":
			retorno = new IntegracaoBotSolicitarEmprestimo();
			break;
		case "/consultar_tarifas_pagas":
			retorno = new IntegracaoBotConsultaTarifaPaga();
			break;
		case "/consultar_emprestimo":
			retorno = new IntegracaoBotConsultaEmprestimo();
			break;
		case "/pagar_parcelas_vencidas_emprest":
			retorno = new IntegracaoBotPagarTodasParcelasEmprestimo();
			break;
		case "/pagar_parcela_emprestimo":
			retorno = new IntegracaoBotPagarParcelaEmprestimo();
			break;
		case "/consultar_saldo_devedor_emprest":
			retorno = new IntegracaoBotConsultaSaldoDevedorEmprestimo();
			break;
		case "/consultar_saldo_conta":
			retorno = new IntegracaoBotConsultarSaldoConta();
			break;
				 
			
		default:
			break;
		}
		return retorno;
	}

	/**
	 * Método responsável por remover os comandos do histórico do usuário
	 * @param id de identificação do usuário
	 */
	public static void removerHistoricoIntegracaoUsuario(Long id){
		Bot.ULTIMO_COMANDO_DO_USUARIO.remove(id);
	}
	
	/**
	 * Método responsável por adicionar ou atualizar o último comando executado pelo usuário
	 * @param id de identificação do usuário
	 * @param comando que será adicionado ou atualizado no histórico do usuário
	 */
	public static void adicionarHistoricoIntegracaoUsuario(Long id, EnumComandosBot comando){
		Bot.ULTIMO_COMANDO_DO_USUARIO.put(id, comando);
	}
	
	/**
	 * Metodo responsável por gravar na base todas as interaçoes do usuário
	 * @param usuario informações usuario telegram
	 * @param mensagemRecebida do usuario
	 * @param mensagemEnviada para o usuario
	 */	
	public static void salvarMensagens(Chat usuario, String mensagemRecebida, String mensagemEnviada ){
		MensagemIntegracao mensagemIntegracao = new MensagemIntegracao();
		MensagemIntegracaoDao mensagemIntegracaoDao = new MensagemIntegracaoDao();
		
		mensagemIntegracao.setIdTelegram(usuario.id());
		mensagemIntegracao.setNomeTelegram(usuario.firstName());
		mensagemIntegracao.setMensagemRecebida(mensagemRecebida);
		mensagemIntegracao.setMensagemEnviada(mensagemEnviada);	
		
		mensagemIntegracaoDao.adicionar(mensagemIntegracao);
		mensagemIntegracaoDao.close();		
		
	}
}
