package br.com.fiap.teste;

import java.time.LocalDate;
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

	private static void buscarGif() {
		Gif gif1 = netgifxCommand.buscarGif(2);
		System.out.println(gif1.getNome());
		Gif gif2 = netgifxCommand.buscarGif(3);
		System.out.println(gif2.getNome());
	}

	private static void buscarUsuario() {
		Usuario usuario = netgifxCommand.buscarUsuario("admin");
		System.out.println(usuario.getApelido());
	}

	private static void listarCategorias() {
		List<Categoria> categorias = netgifxCommand.listarCategorias();

		for (Categoria categoria : categorias) {
			System.out.println(categoria.getNome());

			for (Gif gif : categoria.getGifs()) {
				System.out.println(gif.getNome());
			}

		}
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
		Set<Gif> gifs = new HashSet<Gif>();
		
		Gif gif1 = new Gif();
		gif1.setNome("Um");
		gif1.setCaminho("static/img/01");
		gif1.setDescricao("Primeiro");
		gif1.setDataPublicacao(LocalDate.now());

		Gif gif2 = new Gif();
		gif2.setNome("Dois");
		gif2.setCaminho("static/img/02");
		gif2.setDescricao("Segundo");
		gif2.setDataPublicacao(LocalDate.now());

		Gif gif3 = new Gif();
		gif3.setNome("Três");
		gif3.setCaminho("static/img/03");
		gif3.setDescricao("Terceiro");
		gif3.setDataPublicacao(LocalDate.now());

		Gif gif4 = new Gif();
		gif4.setNome("Quatro");
		gif4.setCaminho("static/img/04");
		gif4.setDescricao("Quarto");
		gif4.setDataPublicacao(LocalDate.now());

		Gif gif5 = new Gif();
		gif5.setNome("Cinco");
		gif5.setCaminho("static/img/05");
		gif5.setDescricao("Quinto");
		gif5.setDataPublicacao(LocalDate.now());

		Gif gif6 = new Gif();
		gif6.setNome("Seis");
		gif6.setCaminho("static/img/06");
		gif6.setDescricao("Sexto");
		gif6.setDataPublicacao(LocalDate.now());

		Gif gif7 = new Gif();
		gif7.setNome("Sete");
		gif7.setCaminho("static/img/07");
		gif7.setDescricao("Sétimo");
		gif7.setDataPublicacao(LocalDate.now());

		Gif gif8 = new Gif();
		gif8.setNome("Oito");
		gif8.setCaminho("static/img/08");
		gif8.setDescricao("Oitavo");
		gif8.setDataPublicacao(LocalDate.now());
		
		gifs.add(gif1);
		gifs.add(gif2);
		gifs.add(gif3);
		gifs.add(gif4);
		gifs.add(gif5);
		gifs.add(gif6);
		gifs.add(gif7);
		gifs.add(gif8);

		netgifxCommand.cadastrarGif(gif1);
		netgifxCommand.cadastrarGif(gif2);
		netgifxCommand.cadastrarGif(gif3);
		netgifxCommand.cadastrarGif(gif4);
		netgifxCommand.cadastrarGif(gif5);
		netgifxCommand.cadastrarGif(gif6);
		netgifxCommand.cadastrarGif(gif7);
		netgifxCommand.cadastrarGif(gif8);
		
		return gifs;
	}

	private static List<Categoria> criarCategorias() {
		List<Categoria> categorias = new ArrayList<Categoria>();
		
		Categoria categoria1 = new Categoria();
		categoria1.setNome("Esporte");

		Categoria categoria2 = new Categoria();
		categoria2.setNome("Comédia");

		categorias.add(categoria1);
		categorias.add(categoria2);

		netgifxCommand.cadastrarCategoria(categoria1);
		netgifxCommand.cadastrarCategoria(categoria2);
		
		return categorias;
	}

	private static void atualizarFavoritos(Usuario usuario, Set<Gif> gifs) {
		netgifxCommand.atualizarFavoritos(usuario, gifs);
	}

	private static void atualizarGifCategoria(List<Categoria> categorias, List<Gif> gifs) {
		for (int i = 0; i < gifs.size(); i++) {
			List<Categoria> listaCategoria = new ArrayList<>();
			
			if (i % 3 == 0) {
				listaCategoria.addAll(categorias);
			} else {
				listaCategoria.add(categorias.get(i % 2));
			}
			
			netgifxCommand.atualizarGifCategoria(gifs.get(i), listaCategoria);
		}
	}
}
