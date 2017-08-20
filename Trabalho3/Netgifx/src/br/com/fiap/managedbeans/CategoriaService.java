package br.com.fiap.managedbeans;
 
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.commons.CategoriaVO;
import br.com.fiap.commons.GifVO;
 
public class CategoriaService {
    
	public static CategoriaService INSTANCIA = null;
	
	static{
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
    	
    	gifs.add(new GifVO(1, "01", "gif 01", LocalDate.now(), 5D, "static/gif/01.gif"));
    	gifs.add(new GifVO(1, "02", "gif 02", LocalDate.now(), 5D, "static/gif/02.gif"));
    	gifs.add(new GifVO(1, "03", "gif 03", LocalDate.now(), 5D, "static/gif/03.gif"));
    	gifs.add(new GifVO(1, "04", "gif 04", LocalDate.now(), 5D, "static/gif/04.gif"));
    	gifs.add(new GifVO(1, "05", "gif 05", LocalDate.now(), 5D, "static/gif/05.gif"));
    	gifs.add(new GifVO(1, "06", "gif 06", LocalDate.now(), 5D, "static/gif/06.gif"));
    	gifs.add(new GifVO(1, "07", "gif 07", LocalDate.now(), 5D, "static/gif/07.gif"));
    	gifs.add(new GifVO(1, "08", "gif 08", LocalDate.now(), 5D, "static/gif/08.gif"));
    	gifs.add(new GifVO(1, "09", "gif 09", LocalDate.now(), 5D, "static/gif/09.gif"));
    	gifs.add(new GifVO(1, "10", "gif 10", LocalDate.now(), 5D, "static/gif/10.gif"));
    	
        return gifs;
    }
}