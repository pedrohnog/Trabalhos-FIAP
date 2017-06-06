package br.com.fiap;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.fiap.entity.Apolice;
import br.com.fiap.entity.Segurado;
import br.com.fiap.entity.Veiculo;
import br.com.fiap.helper.SeguroHelper;

public class Main {

	public static void main(String[] args) {

		Segurado segurado = criarSegurado();

		try (SeguroHelper seguroHelper = new SeguroHelper()) {
			seguroHelper.criarNovoSeguro(segurado);

			System.out.println(seguroHelper.buscarSeguro(segurado.getId()));

			seguroHelper.listarSegurados().forEach(System.out::println);
			seguroHelper.listarApolices().forEach(System.out::println);
			seguroHelper.listarVeiculos().forEach(System.out::println);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static Segurado criarSegurado() {
		Segurado segurado = new Segurado();
		segurado.setNome(JOptionPane.showInputDialog("Informe o nome do segurado"));
		segurado.setCpf(JOptionPane.showInputDialog("Informe o CPF do segurado (sem pontos ou traços)"));
		segurado.setApolices(criarApolices(segurado));
		return segurado;
	}

	private static List<Apolice> criarApolices(Segurado segurado) {
		List<Apolice> apolices = new ArrayList<>();

		int quantidade;
		try {
			quantidade = Math.abs(Integer.valueOf(JOptionPane.showInputDialog("Informe a quantidade de apólices")));
		} catch(NumberFormatException e) {
			System.err.println("Quantidade de apólices inválida");
			throw e;
		}
		
		for (int i = 0; i < quantidade; i++) {
			Apolice apolice = new Apolice();
			
			//Não será colocado JOptionPane.showInputDialog nesse ponto pois assumiremos que a apólice começa a valer hoje
			apolice.setDataInicioVigencia(Date.from(LocalDateTime.now().plusYears(i).toInstant(ZoneOffset.UTC)));
			apolice.setDataFinalVigencia(Date.from(LocalDateTime.now().plusYears(i + 1L).toInstant(ZoneOffset.UTC)));
			apolice.setVeiculos(criarVeiculos(apolice, i));
			apolice.setSegurado(segurado);

			apolices.add(apolice);
		}

		return apolices;
	}

	private static List<Veiculo> criarVeiculos(Apolice apolice, int posicao) {
		List<Veiculo> veiculos = new ArrayList<>();
		
		int quantidade;
		try {
			quantidade = Math.abs(Integer.valueOf(JOptionPane.showInputDialog("Informe a quantidade de veículos da " + (posicao+1) + "ª apólice!")));
		} catch(NumberFormatException e) {
			System.err.println("Quantidade de veículos inválida");
			throw e;
		}

		for (int i = 0; i < quantidade; i++) {
			Veiculo veiculo = new Veiculo();
			try {
				veiculo.setCodigoFipe(Math.abs(Integer.valueOf(JOptionPane.showInputDialog("Informe o código FIPE do " + (i+1) + "º veículo"))));
			} catch(NumberFormatException e) {
				System.err.println("Código FIPE inválido");
				throw e;
			}
			veiculo.setMarca(JOptionPane.showInputDialog("Informe a marca do " + (i+1) + "º veículo"));
			veiculo.setModelo(JOptionPane.showInputDialog("Informe o modelo do " + (i+1) + "º veículo"));
			try {
				veiculo.setValor(Math.abs(Double.valueOf(JOptionPane.showInputDialog("Informe o valor do " + (i+1) + "º veículo"))));
			} catch(NumberFormatException e) {
				System.err.println("Valor inválido");
				throw e;
			}
			veiculo.setPlaca(JOptionPane.showInputDialog("Informe a placa do veículo"));
			veiculo.setApolice(apolice);

			veiculos.add(veiculo);
		}

		return veiculos;
	}
}
