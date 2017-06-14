package br.com.fiap.bot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.MessageEntity;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetUpdatesResponse;

import br.com.fiap.bot.contantes.EnumComandosBot;

public class Main {
	
	private static final String TOKEN_ACESSO = "TOKEN";
	private static Map<Long,String> ULTIMO_COMANDO_USUARIOS = new HashMap<>();
	
	
	public static void main(String[] args) {
		
		TelegramBot bot = TelegramBotAdapter.build(TOKEN_ACESSO);

		GetUpdatesResponse updatesResponse;
		
		int m = 0;

		while (true) {
			updatesResponse = bot.execute(new GetUpdates().limit(100).offset(m));
			List<Update> updates = updatesResponse.updates();

			for (Update update : updates) {

				m = update.updateId() + 1;
				
				Chat usuario = update.message().chat();
				MessageEntity mensagemChat = null;
				if(update.message().entities() != null && update.message().entities().length > 0){
					mensagemChat = update.message().entities()[0];
				}
				if(mensagemChat != null && mensagemChat.type().toString().equals("bot_command")){
					ULTIMO_COMANDO_USUARIOS.put(usuario.id(), update.message().text());
					System.out.println("Mensagem é um comando");
				}else{
					System.out.println("Mensagem nao é um comando");
				}
				
				StringBuffer mensagemRetorno = new StringBuffer();
				if(update.message().text().equals("/start")){
					mensagemRetorno.append("Olá ")
							.append(usuario.firstName())
							.append("!")
							.append("\n")
							.append("Sou um atendente virtual e irei ajudar você.")
							.append("\n")
							.append("Para saber o que você pode fazer por aqui, digite /ajuda");
				}
				else if(EnumComandosBot.LISTA_COMANDOS_PARAMETRIZADOS.containsKey(update.message().text())){
					mensagemRetorno.append(EnumComandosBot.LISTA_COMANDOS_PARAMETRIZADOS.get(update.message().text()).getMensagemRetorno());
				}else{
					mensagemRetorno.append("Não entendi... ");
				}
				
				bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
				bot.execute(new SendMessage(update.message().chat().id(), mensagemRetorno.toString()));
			}
		}
	}

}

