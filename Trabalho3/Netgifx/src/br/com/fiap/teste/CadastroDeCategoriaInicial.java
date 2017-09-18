package br.com.fiap.teste;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.entity.Categoria;

public class CadastroDeCategoriaInicial {
	
	private CadastroDeCategoriaInicial() {
		//Construtor
	}

	public static List<Categoria> criarCategorias() {
		List<Categoria> categorias = new ArrayList<>();
		
		categorias.add(criarCategoria("Com�dia"));
		categorias.add(criarCategoria("Esporte"));
		categorias.add(criarCategoria("Fail"));
		categorias.add(criarCategoria("Animal"));
		categorias.add(criarCategoria("Susto"));
		categorias.add(criarCategoria("Pegadinha"));
		categorias.add(criarCategoria("Nojento"));
		categorias.add(criarCategoria("Pol�tica"));
		categorias.add(criarCategoria("Famoso"));
		categorias.add(criarCategoria("Desenho"));
		categorias.add(criarCategoria("Crian�a"));
		categorias.add(criarCategoria("Anivers�rio"));

		return categorias;
	}
	
	private static synchronized Categoria criarCategoria(String nome) {
		Categoria categoria = new Categoria();
		
		categoria.setNome(nome);
		
		return categoria;
	}

}
