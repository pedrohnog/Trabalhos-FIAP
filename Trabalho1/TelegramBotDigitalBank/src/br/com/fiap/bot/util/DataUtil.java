package br.com.fiap.bot.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataUtil {
	
	public static String conveterDataPadraoBr(LocalDateTime localDateTime){
		return localDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

}
