package br.com.fiap.bot.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Classe utilitária para auxiliar no tratamento de datas
 *
 */
public class DataUtil {

	/**
	 * Tratamento de datas com horário para o padrão brasileiro
	 * 
	 * @param localDateTime Data/hora à ser tratada
	 * 
	 * @return Data/hora tratada
	 */
	public static String conveterDataPadraoBr(LocalDateTime localDateTime) {
		return localDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

	/**
	 * Tratamento de datas para o padrão brasileiro
	 * 
	 * @param localDate Data à ser tratada
	 * 
	 * @return Data tratada
	 */
	public static String conveterDataPadraoBr(LocalDate localDate) {
		return localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

}
