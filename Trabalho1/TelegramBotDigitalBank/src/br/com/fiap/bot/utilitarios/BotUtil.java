package br.com.fiap.bot.utilitarios;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendMessage;

import br.com.fiap.bot.constantes.EnumComandosBot;
import br.com.fiap.bot.constantes.EnumTipoIntegracaoBot;
import br.com.fiap.bot.dao.impl.MensagemIntegracaoDao;
import br.com.fiap.bot.entidades.MensagemIntegracao;
import br.com.fiap.bot.integradores.IntegracaoBot;
import br.com.fiap.bot.integradores.IntegracaoBotSolicitacao;
import br.com.fiap.bot.integradores.impl.IntegracaoBotAjuda;
import br.com.fiap.bot.integradores.impl.IntegracaoBotConsultaDeposito;
import br.com.fiap.bot.integradores.impl.IntegracaoBotConsultaExtrato;
import br.com.fiap.bot.integradores.impl.IntegracaoBotConsultaSaque;
import br.com.fiap.bot.integradores.impl.IntegracaoBotCriarConta;
import br.com.fiap.bot.integradores.impl.IntegracaoBotExibirDados;
import br.com.fiap.bot.integradores.impl.IntegracaoBotIncluirDependente;
import br.com.fiap.bot.integradores.impl.IntegracaoBotModificarConta;
import br.com.fiap.bot.integradores.impl.IntegracaoBotRealizarDeposito;
import br.com.fiap.bot.integradores.impl.IntegracaoBotRealizarSaque;
import br.com.fiap.bot.integradores.impl.IntegracaoBotStart;

public class BotUtil {

	private static final String TOKEN_ACESSO = "TOKEN";
	private static Map<Long,EnumComandosBot> ULTIMO_COMANDO_DO_USUARIO = new HashMap<>();
	
	/**
	 * Método responsável por realizar o start do bot e ficar buscando as mensagens enviadas
	 */
	public void executarBot(){
		TelegramBot bot = TelegramBotAdapter.build(TOKEN_ACESSO);		
		int m = 0;

		while (true) {
			List<Update> updates = bot.execute(new GetUpdates().limit(100).offset(m)).updates();
			
			for (Update update : updates) {
				m = update.updateId() + 1;
				
				if(update.message() != null){
					bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
					
					String mensagemRetorno = tratarMensagemBot(update);
					
					bot.execute(new SendMessage(update.message().chat().id(), mensagemRetorno.toString()));
				}
				
			}
		}
	}

	/**
	 * Método resposável por entender a ação solicitada pelo usuário e realizar as tratativas
	 * @param update Requisicao recebida pelo telegram
	 * @return Mensagem de resposta para o usuário
	 */
	private String tratarMensagemBot(Update update) {
		String mensagemRetorno = "";

		Chat usuario = update.message().chat();
		String mensagemRecebida = update.message().text();

		if (EnumComandosBot.LISTA_COMANDO_INTERACOES.containsKey(mensagemRecebida)) {
			
			IntegracaoBot resposta = BotUtil.definirClasseIntegracao(EnumComandosBot.LISTA_COMANDO_INTERACOES.get(mensagemRecebida));
			mensagemRetorno = resposta.tratarPrimeiraInteracao(usuario);
			
			if(EnumComandosBot.LISTA_COMANDO_INTERACOES.get(mensagemRecebida).getEnumTipoIntegracaoBot().equals(EnumTipoIntegracaoBot.SOLICITACAO)){
				//Adiciona no cache da aplicação a última interação realiza para tratamento posterior
				adicionarHistoricoIntegracaoUsuario(usuario.id(), EnumComandosBot.LISTA_COMANDO_INTERACOES.get(mensagemRecebida));
			}else{
				//Remove última interacao realizada porque foi realizada uma interação do tipo Consulta 
				removerHistoricoIntegracaoUsuario(usuario.id());
			}
			
		} else if (ULTIMO_COMANDO_DO_USUARIO.containsKey(usuario.id())) {
			
			EnumComandosBot ultimoComandoExecutado = ULTIMO_COMANDO_DO_USUARIO.get(usuario.id());			
			IntegracaoBotSolicitacao resposta = (IntegracaoBotSolicitacao) BotUtil.definirClasseIntegracao(ultimoComandoExecutado);
			
			//Valida se a resposta está correta, se estiver retorna e retira do historico
			if (resposta.validarResposta(mensagemRecebida)) {
				mensagemRetorno = resposta.integrarBanco(mensagemRecebida,	usuario);
				removerHistoricoIntegracaoUsuario(usuario.id());
			} else {
				mensagemRetorno = resposta.informarErroNaResposta();
			}
			
		} else {
			mensagemRetorno = "Desculpe, não entendi... digite /ajuda para obter a lista de comandos conhecidos.";

		}
		
		salvarMensagens(usuario, mensagemRecebida, mensagemRetorno);
		
		return mensagemRetorno;
	}
	
	/**
	 * Método responsável por definir qual será a classe de validação e integração conforme os comandos
	 * @param ultimoComandoExecutado
	 * @return
	 */
	private static IntegracaoBot definirClasseIntegracao(EnumComandosBot ultimoComandoExecutado) {
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
		case "/exibir_dados":
			retorno = new IntegracaoBotExibirDados();
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
		case "/consultar_depositos":
			retorno = new IntegracaoBotConsultaDeposito();
			break;
		case "/consultar_saques":
			retorno = new IntegracaoBotConsultaSaque();
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
	private void removerHistoricoIntegracaoUsuario(Long id){
		ULTIMO_COMANDO_DO_USUARIO.remove(id);
	}
	
	/**
	 * Método responsável por adicionar ou atualizar o último comando executado pelo usuário
	 * @param id de identificação do usuário
	 * @param comando que será adicionado ou atualizado no histórico do usuário
	 */
	private void adicionarHistoricoIntegracaoUsuario(Long id, EnumComandosBot comando){
		ULTIMO_COMANDO_DO_USUARIO.put(id, comando);
	}
	/**
	 * Metodo resposanvel por gravar na base todas as interaçoes do usuario
	 * @param usuario informações usuario telegram
	 * @param mensagemRecebida do usuario
	 * @param mensagemEnviada para o usuario
	 */
	
	private void salvarMensagens(Chat usuario, String mensagemRecebida, String mensagemEnviada ){
		MensagemIntegracao mensagemIntegracao = new MensagemIntegracao();
		MensagemIntegracaoDao mensagemIntegracaoDao = new MensagemIntegracaoDao();
		
		System.out.println(mensagemEnviada);
		System.out.println("mensagemEnviada" + mensagemEnviada.length());
		System.out.println(mensagemRecebida);
		System.out.println("mensagemRecebida - " + mensagemRecebida.length());
		
		mensagemIntegracao.setIdTelegram(usuario.id());
		mensagemIntegracao.setNomeTelegram(usuario.firstName());
		mensagemIntegracao.setMensagemRecebida(mensagemRecebida);
		mensagemIntegracao.setMensagemEnviada(mensagemEnviada);	
		
		mensagemIntegracaoDao.adicionar(mensagemIntegracao);
		mensagemIntegracaoDao.close();
		
		
	}
}
