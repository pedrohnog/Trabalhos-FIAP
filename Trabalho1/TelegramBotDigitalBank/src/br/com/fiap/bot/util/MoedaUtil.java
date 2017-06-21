package br.com.fiap.bot.util;

import java.text.NumberFormat;
import java.util.Locale;

public class MoedaUtil {
	
	public static String conveterMoedaBr(double valor){
		NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		return format.format(valor);
	}

}
