package br.com.fiap.bot.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Classe utilit�ria para auxiliar no tratamento de arquivos .properties
 *
 */
public class PropriedadesUtil {

	private static Properties mensagensIntegracao = null;

	/**
	 * L� o arquivo de mensagens de integra��o
	 *
	 * @return Objeto Properties com as mensagens de integra��o
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
