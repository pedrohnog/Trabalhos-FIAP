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

public class CaluladorEmprestimoUtil {
	
	private static DecimalFormat df2 = new DecimalFormat(".##", DecimalFormatSymbols.getInstance(new Locale("en", "US")));

	public static List<Emprestimo> calcularEmprestimo(Conta conta, double valor, int qtdeParcelas) {
		List<Emprestimo> parcelas = new ArrayList<>();

		double valorParcela = valor / qtdeParcelas;

		for (int i = 1; i <= qtdeParcelas; i++) {
			Emprestimo emprestimo = new Emprestimo();

			emprestimo.setConta(conta);
			emprestimo.setParcelaPaga(false);
			
			emprestimo.setNumeroParcela(i);
			emprestimo.setDataVencimento(LocalDate.now().plusMonths(i));

			valorParcela += valorParcela * ((double) Tarifas.EMPRESTIMO.getJurosMensais() / 100);

			emprestimo.setValorParcela(Double.valueOf(df2.format(valorParcela)));

			parcelas.add(emprestimo);
		}

		return parcelas;
	}

}
