package br.com.fiap.bot.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Classe utilitária para auxiliar no tratamento de arquivos .properties
 *
 */
public class PropriedadesUtil {

	private static Properties mensagensIntegracao = null;

	/**
	 * Lê o arquivo de mensagens de integração
	 *
	 * @return Objeto Properties com as mensagens de integração
	 */
	public static Properties carregarMensagensIntegracao() {
		if (mensagensIntegracao == null) {
			mensagensIntegracao = new Properties();

			InputStream inputStream = PropriedadesUtil.class.getClassLoader().getResourceAsStream("mensagensIntegracao.properties");

			try {
				mensagensIntegracao.load(inputStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return mensagensIntegracao;
	}

}
