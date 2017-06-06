package br.com.fiap;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
		segurado.setCpf(String.valueOf((int) (Math.random() * 11)));
		segurado.setNome("Segurado");
		segurado.setApolices(criarApolices(segurado));
		return segurado;
	}

	private static List<Apolice> criarApolices(Segurado segurado) {
		List<Apolice> apolices = new ArrayList<>();

		for (int i = 0; i < 2; i++) {
			Apolice apolice = new Apolice();
			apolice.setDataInicioVigencia(Date.from(LocalDateTime.now().plusYears(i).toInstant(ZoneOffset.UTC)));
			apolice.setDataFinalVigencia(Date.from(LocalDateTime.now().plusYears(i + 1L).toInstant(ZoneOffset.UTC)));
			apolice.setVeiculos(criarVeiculos(apolice));
			apolice.setSegurado(segurado);

			apolices.add(apolice);
		}

		return apolices;
	}

	private static List<Veiculo> criarVeiculos(Apolice apolice) {
		List<Veiculo> veiculos = new ArrayList<>();

		for (int i = 0; i < 2; i++) {
			Veiculo veiculo = new Veiculo();
			veiculo.setCodigoFipe((int) (Math.random() * 100));
			veiculo.setMarca("GM");
			veiculo.setModelo("OPALA");
			veiculo.setValor(7500D);
			veiculo.setPlaca("AUB" + (int) (Math.random() * 9999));
			veiculo.setApolice(apolice);
			
			veiculos.add(veiculo);
		}

		return veiculos;
	}
}
