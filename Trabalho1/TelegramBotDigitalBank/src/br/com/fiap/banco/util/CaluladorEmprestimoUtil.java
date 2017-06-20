package br.com.fiap.banco.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.banco.constantes.Tarifas;
import br.com.fiap.banco.entidades.Emprestimo;

public class CaluladorEmprestimoUtil {

	public List<Emprestimo> calcularEmprestimo(double valor, int qtdeParcelas) {
		List<Emprestimo> parcelas = new ArrayList<>();

		double valorParcela = valor / qtdeParcelas;

		for (int i = 1; i <= qtdeParcelas; i++) {
			Emprestimo emprestimo = new Emprestimo();

			emprestimo.setNumeroParcela(i);
			emprestimo.setDataVencimento(LocalDate.now().plusMonths(i));
			emprestimo.setParcelaPaga(false);

			valorParcela += valorParcela * ((double) Tarifas.EMPRESTIMO.getJurosMensais() / 100);

			emprestimo.setValorParcela(valorParcela);

			parcelas.add(emprestimo);
		}

		return parcelas;
	}

	public static void main(String[] args) {
		CaluladorEmprestimoUtil caluladorEmprestimoUtil = new CaluladorEmprestimoUtil();
		List<Emprestimo> lista = caluladorEmprestimoUtil.calcularEmprestimo(5000, 20);

		for (Emprestimo emprestimoDetalhe : lista) {
			System.out.println(emprestimoDetalhe.getValorParcela());
		}
	}

}
