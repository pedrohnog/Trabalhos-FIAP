package br.com.fiap.bot.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Classe utilit�ria para auxiliar no tratamento de datas
 *
 */
public class DataUtil {

	/**
	 * Tratamento de datas com hor�rio para o padr�o brasileiro
	 * 
	 * @param localDateTime Data/hora � ser tratada
	 * 
	 * @return Data/hora tratada
	 */
	public static String conveterDataPadraoBr(LocalDateTime localDateTime) {
		return localDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

	/**
	 * Tratamento de datas para o padr�o brasileiro
	 * 
	 * @param localDate Data � ser tratada
	 * 
	 * @return Data tratada
	 */
	public static String conveterDataPadraoBr(LocalDate localDate) {
		return localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

}
