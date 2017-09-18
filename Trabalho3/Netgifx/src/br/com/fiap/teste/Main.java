package br.com.fiap.teste;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.fiap.commands.NetgifxCommand;
import br.com.fiap.entity.Categoria;
import br.com.fiap.entity.Gif;
import br.com.fiap.entity.Usuario;

public class Main {

	static NetgifxCommand netgifxCommand = new NetgifxCommand();

	public static void main(String[] args) {
		cargaInicial();
		
		//As buscas são executadas duas vezes para testar o cache
		
//		buscarUsuario();
//		buscarUsuario();
//		
//		buscarGif();
//		buscarGif();
//		
//		listarCategorias();
//		listarCategorias();
	}

	private static void cargaInicial() {
		Set<Gif> gifs = criarGifs();
		List<Categoria> categorias = criarCategorias();
		
		List<Gif> listaGifs = new ArrayList<>();
		listaGifs.addAll(gifs);
		
		atualizarGifCategoria(categorias, listaGifs);
		
		Usuario usuarioAdministrador = new Usuario();
		criarUsuarioAdministrador(usuarioAdministrador);
		atualizarFavoritos(usuarioAdministrador, gifs);
		
		Usuario usuarioComum = new Usuario();
		criarUsuarioComum(usuarioComum);
		atualizarFavoritos(usuarioComum, gifs);
	}

	private static void criarUsuarioAdministrador(Usuario usuario) {
		usuario.setNome("Administrador");
		usuario.setCpf("01010101099");
		usuario.setEmail("admin@netgifx.com");
		usuario.setSenha("admin");
		usuario.setTelefone("11987654321");
		usuario.setApelido("admin");
		usuario.setAdmin(true);

		netgifxCommand.cadastrarUsuario(usuario);
	}

	private static void criarUsuarioComum(Usuario usuario) {
		usuario.setNome("Usuário");
		usuario.setCpf("05210105209");
		usuario.setEmail("usuario@gmail.com");
		usuario.setSenha("user");
		usuario.setTelefone("11912345678");
		usuario.setApelido("user");
		usuario.setAdmin(false);

		netgifxCommand.cadastrarUsuario(usuario);
	}

	private static Set<Gif> criarGifs() {
		Set<Gif> gifs;
		
		gifs = CadastroDeGifInicial.criarGifs();

		for (Gif gif : gifs) {
			netgifxCommand.cadastrarGif(gif);
		}
		
		return gifs;
	}

	private static List<Categoria> criarCategorias() {
		List<Categoria> categorias;
		
		categorias = CadastroDeCategoriaInicial.criarCategorias();
		
		for (Categoria categoria : categorias) {
			netgifxCommand.cadastrarCategoria(categoria);
		}

		return categorias;
	}

	private static void atualizarFavoritos(Usuario usuario, Set<Gif> gifs) {
		ArrayList<Gif> listaDeGifs = new ArrayList<>();
		listaDeGifs.addAll(gifs);
		
		Set<Gif> gifsFavoritos = new HashSet<>();
		
		for (int i = 0; i < listaDeGifs.size(); i++) {
			if (usuario.getAdmin()) {
				if (i % 3 == 0 || i % 5 == 0) {
					gifsFavoritos.add(listaDeGifs.get(i));
				}
			} else {
				if (i % 2 == 0 && i % 3 != 1) {
					gifsFavoritos.add(listaDeGifs.get(i));
				}
			}
		}
		
		netgifxCommand.atualizarFavoritos(usuario, gifsFavoritos);
	}

	private static void atualizarGifCategoria(List<Categoria> categorias, List<Gif> gifs) {
		for (int i = 0; i < gifs.size(); i++) {
			Gif gif = gifs.get(i);
			
			int numeroGif = Integer.parseInt(gif.getCaminho().substring(gif.getCaminho().length() - 2));
			
			netgifxCommand.atualizarGifCategoria(gifs.get(i), AssociarCategoriaGifInicial.definirCategoria(numeroGif, categorias));
		}
	}
}
