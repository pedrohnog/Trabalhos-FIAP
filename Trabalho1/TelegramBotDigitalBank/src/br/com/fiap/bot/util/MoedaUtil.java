package br.com.fiap.bot.util;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Classe utilit�ria para auxiliar no tratamento de valores monet�rios
 *
 */
public class MoedaUtil {

	/**
	 * Converte valores num�ricos em moeda no padr�o brasileiro
	 * 
	 * @param valor Valor num�rico � ser tratado
	 * 
	 * @return Moeda tratada
	 */
	public static String conveterMoedaBr(double valor) {
		NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		return format.format(valor);
	}

}
