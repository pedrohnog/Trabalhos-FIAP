package br.com.fiap.managedbeans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.UploadedFile;

import br.com.fiap.entity.Gif;


@ManagedBean
@RequestScoped
public class CadastroGifMB {

	private Gif gif;
	private UploadedFile arquivo;
	
	@PostConstruct
	public void init(){
		gif = new Gif();
	}
	
	public Gif getGif() {
		return gif;
	}

	public void setGif(Gif gif) {
		this.gif = gif;
	}

	public UploadedFile getArquivo() {
		return arquivo;
	}

	public void setArquivo(UploadedFile arquivo) {
		this.arquivo = arquivo;
	}

	public void cadastrarGif(){
		
	}	
	
}
