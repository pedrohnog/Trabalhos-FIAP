package br.com.fiap.banco.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.banco.constantes.Tarifas;
import br.com.fiap.banco.entidades.Conta;
import br.com.fiap.banco.entidades.Emprestimo;

public class CaluladorEmprestimoUtil {

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

			emprestimo.setValorParcela(valorParcela);

			parcelas.add(emprestimo);
		}

		return parcelas;
	}

}
