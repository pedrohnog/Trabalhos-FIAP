package br.com.fiap.teste;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.entity.Categoria;

public class AssociarCategoriaGifInicial {
	
	public static synchronized List<Categoria> definirCategoria(int numeroGif, List<Categoria> categorias) {
		List<Categoria> categoriasGif = new ArrayList<>();
		
		switch(numeroGif) {
			case 1:
				categoriasGif.add(categorias.get(0));
				categoriasGif.add(categorias.get(1));
				categoriasGif.add(categorias.get(2));
				break;
			case 2:
				categoriasGif.add(categorias.get(1));
				categoriasGif.add(categorias.get(2));
				break;
			case 3:
				categoriasGif.add(categorias.get(0));
				categoriasGif.add(categorias.get(3));
				break;
			case 4:
				categoriasGif.add(categorias.get(3));
				categoriasGif.add(categorias.get(4));
				break;
			case 5:
				categoriasGif.add(categorias.get(1));
				categoriasGif.add(categorias.get(4));
				categoriasGif.add(categorias.get(5));
				break;
			case 6:
				categoriasGif.add(categorias.get(3));
				break;
			case 7:
				categoriasGif.add(categorias.get(2));
				categoriasGif.add(categorias.get(3));
				break;
			case 8:
				categoriasGif.add(categorias.get(3));
				categoriasGif.add(categorias.get(6));
				break;
			case 9:
				categoriasGif.add(categorias.get(0));
				categoriasGif.add(categorias.get(3));
				break;
			case 10:
				categoriasGif.add(categorias.get(7));
				break;
			case 11:
				categoriasGif.add(categorias.get(3));
				break;
			case 12:
				categoriasGif.add(categorias.get(2));
				categoriasGif.add(categorias.get(3));
				break;
			case 13:
				categoriasGif.add(categorias.get(0));
				categoriasGif.add(categorias.get(3));
				break;
			case 14:
				categoriasGif.add(categorias.get(0));
				break;
			case 15:
				categoriasGif.add(categorias.get(2));
				break;
			case 16:
				categoriasGif.add(categorias.get(0));
				categoriasGif.add(categorias.get(8));
				break;
			case 17:
				categoriasGif.add(categorias.get(1));
				categoriasGif.add(categorias.get(2));
				break;
			case 18:
				categoriasGif.add(categorias.get(0));
				categoriasGif.add(categorias.get(2));
				break;
			case 19:
				categoriasGif.add(categorias.get(0));
				categoriasGif.add(categorias.get(3));
				break;
			case 20:
				categoriasGif.add(categorias.get(3));
				categoriasGif.add(categorias.get(4));
				categoriasGif.add(categorias.get(10));
				break;
			case 21:
				categoriasGif.add(categorias.get(0));
				categoriasGif.add(categorias.get(10));
				break;
			case 22:
				categoriasGif.add(categorias.get(3));
				break;
			case 23:
				categoriasGif.add(categorias.get(0));
				categoriasGif.add(categorias.get(9));
				break;
			case 24:
				categoriasGif.add(categorias.get(0));
				categoriasGif.add(categorias.get(3));
				break;
			case 25:
				categoriasGif.add(categorias.get(0));
				categoriasGif.add(categorias.get(3));
				categoriasGif.add(categorias.get(4));
				categoriasGif.add(categorias.get(5));
				break;
			case 26:
				categoriasGif.add(categorias.get(3));
				break;
			case 27:
				categoriasGif.add(categorias.get(8));
				break;
			case 28:
				categoriasGif.add(categorias.get(2));
				categoriasGif.add(categorias.get(3));
				categoriasGif.add(categorias.get(11));
				break;
			case 29:
				categoriasGif.add(categorias.get(0));
				categoriasGif.add(categorias.get(3));
				break;
			case 30:
				categoriasGif.add(categorias.get(8));
				break;
			case 31:
				categoriasGif.add(categorias.get(0));
				categoriasGif.add(categorias.get(3));
				break;
			case 32:
				categoriasGif.add(categorias.get(0));
				categoriasGif.add(categorias.get(6));
				break;
			case 33:
				categoriasGif.add(categorias.get(10));
				categoriasGif.add(categorias.get(11));
				break;
			case 34:
				categoriasGif.add(categorias.get(1));
				categoriasGif.add(categorias.get(2));
				break;
			case 35:
				categoriasGif.add(categorias.get(5));
				break;
			case 36:
				categoriasGif.add(categorias.get(0));
				break;
			case 37:
				categoriasGif.add(categorias.get(1));
				categoriasGif.add(categorias.get(2));
				break;
			case 38:
				categoriasGif.add(categorias.get(2));
				break;
			case 39:
				categoriasGif.add(categorias.get(0));
				break;
			case 40:
				categoriasGif.add(categorias.get(0));
				break;
			case 41:
				categoriasGif.add(categorias.get(8));
				break;
			case 42:
				categoriasGif.add(categorias.get(0));
				break;
			case 43:
				categoriasGif.add(categorias.get(0));
				categoriasGif.add(categorias.get(7));
				break;
			case 44:
				categoriasGif.add(categorias.get(7));
				break;
		}
		
		return categoriasGif;
	}

}
