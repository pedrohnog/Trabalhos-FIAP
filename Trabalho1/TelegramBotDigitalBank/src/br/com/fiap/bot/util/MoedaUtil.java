package br.com.fiap.bot.util;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Classe utilitária para auxiliar no tratamento de valores monetários
 *
 */
public class MoedaUtil {

	/**
	 * Converte valores numéricos em moeda no padrão brasileiro
	 * 
	 * @param valor Valor numérico à ser tratado
	 * 
	 * @return Moeda tratada
	 */
	public static String conveterMoedaBr(double valor) {
		NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		return format.format(valor);
	}

}
