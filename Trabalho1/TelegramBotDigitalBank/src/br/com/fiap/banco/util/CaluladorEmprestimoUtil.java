package br.com.fiap.banco.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.com.fiap.banco.constantes.Tarifas;
import br.com.fiap.banco.entidades.Conta;
import br.com.fiap.banco.entidades.Emprestimo;

/**
 * Classe utilitária para auxiliar no cálculo das parcelas de empréstimo e seus devidos juros
 *
 */
public class CaluladorEmprestimoUtil {
	
	private static DecimalFormat df2 = new DecimalFormat(".##", DecimalFormatSymbols.getInstance(new Locale("en", "US")));

	/**
	 * Calcula o empréstimo, juros e as datas de vencimento
	 * 
	 * @param conta Objeto conta que está solicitando o empréstimo
	 * @param valor Valor total do empréstimo
	 * @param qtdeParcelas Prazo para pagamento (em meses)
	 * 
	 * @return Lista com todas as parcelas e juros calculados e as datas de vencimento
	 */
	public static List<Emprestimo> calcularEmprestimo(Conta conta, double valor, int qtdeParcelas) {
		List<Emprestimo> parcelas = new ArrayList<>();

		double valorParcela = valor / qtdeParcelas;
		double valorJuros = valorParcela;

		for (int i = 1; i <= qtdeParcelas; i++) {
			Emprestimo emprestimo = new Emprestimo();

			emprestimo.setConta(conta);
			emprestimo.setParcelaPaga(false);
			
			emprestimo.setNumeroParcela(i);
			emprestimo.setDataVencimento(LocalDate.now().plusMonths(i));

			valorJuros += valorJuros * ((double) Tarifas.EMPRESTIMO.getJurosMensais() / 100);

			emprestimo.setValorParcela(Double.valueOf(df2.format(valorParcela)));
			emprestimo.setJuros(Double.valueOf(df2.format(valorJuros - valorParcela)));

			parcelas.add(emprestimo);
		}

		return parcelas;
	}

}
