package br.com.fiap.commands;

import br.com.fiap.dao.impl.GifDao;
import br.com.fiap.entity.Gif;

public class GifCommand {

	public synchronized Gif buscarGif(Integer id) {
		Gif gif = null;
		
		try (GifDao gifDao = new GifDao();) {
			gif = gifDao.buscarGif(id);
		}
		
		return gif;
	}
	
	public synchronized void atualizarGif(Gif gif) {
		
		try (GifDao gifDao = new GifDao();) {
			gifDao.atualizar(gif);
		}
	}
	
	public synchronized void cadastrarGif(Gif gif) {
		try (GifDao gifDao = new GifDao();) {
			gifDao.cadastrarGif(gif);
		}
	}
}
