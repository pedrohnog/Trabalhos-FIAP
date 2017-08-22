package br.com.fiap.managedbeans;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.commons.CategoriaVO;
import br.com.fiap.commons.GifVO;

public class CategoriaService {

	public static CategoriaService INSTANCIA = null;

	static {
		INSTANCIA = new CategoriaService();
	}

	public List<CategoriaVO> criarCategorias(String nome) {
		List<CategoriaVO> list = new ArrayList<CategoriaVO>();

		list.add(new CategoriaVO(getIdCategoria(), nome, getGifs()));

		return list;
	}

	private Integer getIdCategoria() {
		return 1;
	}

	public List<GifVO> getGifs() {

		List<GifVO> gifs = new ArrayList<>();

		gifs.add(new GifVO(1, "01", "gif 01", LocalDate.now(), 5D, "static/img/gif/01.gif", "static/img/png/01.png"));
		gifs.add(new GifVO(2, "02", "gif 02", LocalDate.now(), 5D, "static/img/gif/02.gif", "static/img/png/02.png"));
		gifs.add(new GifVO(3, "03", "gif 03", LocalDate.now(), 5D, "static/img/gif/03.gif", "static/img/png/03.png"));
		gifs.add(new GifVO(4, "04", "gif 04", LocalDate.now(), 5D, "static/img/gif/04.gif", "static/img/png/04.png"));
		gifs.add(new GifVO(5, "05", "gif 05", LocalDate.now(), 5D, "static/img/gif/05.gif", "static/img/png/05.png"));
		gifs.add(new GifVO(6, "06", "gif 06", LocalDate.now(), 5D, "static/img/gif/06.gif", "static/img/png/06.png"));
		gifs.add(new GifVO(7, "07", "gif 07", LocalDate.now(), 5D, "static/img/gif/07.gif", "static/img/png/07.png"));
		gifs.add(new GifVO(8, "08", "gif 08", LocalDate.now(), 5D, "static/img/gif/08.gif", "static/img/png/08.png"));
		gifs.add(new GifVO(9, "09", "gif 09", LocalDate.now(), 5D, "static/img/gif/09.gif", "static/img/png/09.png"));
		gifs.add(new GifVO(10, "10", "gif 10", LocalDate.now(), 5D, "static/img/gif/10.gif", "static/img/png/10.png"));

		return gifs;
	}
}