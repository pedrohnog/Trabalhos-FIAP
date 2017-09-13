package br.com.fiap.managedbeans;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
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
		System.out.println("teste");
		 try {
		        UploadedFile arq = arquivo;
		        InputStream in = new BufferedInputStream(arq.getInputstream());            
		        File file = new File("../standalone/deployments/Netgifx-0.0.1-SNAPSHOT.war/resources/static/img/"+arq.getFileName());
		        
		        gif.setCaminho(file.getAbsolutePath());
		        FileOutputStream fout = new FileOutputStream(file);
		        while (in.available() != 0) {
		            fout.write(in.read());
		        }
	
		        fout.close();
		        FacesMessage msg = new FacesMessage("O Arquivo ", file.getName() + " salvo.");
		        FacesContext.getCurrentInstance().addMessage("msgUpdate", msg);
		    } catch (Exception ex) {
		        System.out.println("erro no upload "+ex.getMessage());
		    }
	}	
	
//	public void fileUploadAction(FileUploadEvent event) throws IOException {
//	    try {
//	        UploadedFile arq = event.getFile();
//	        InputStream in = new BufferedInputStream(arq.getInputstream());            
//	        File file = new File("resources/static/img/"+arq.getFileName());
//	        gif.setCaminho(file.getAbsolutePath());
//	        FileOutputStream fout = new FileOutputStream(file);
//	        while (in.available() != 0) {
//	            fout.write(in.read());
//	        }
//
//	        fout.close();
//	        FacesMessage msg = new FacesMessage("O Arquivo ", file.getName() + " salvo.");
//	        FacesContext.getCurrentInstance().addMessage("msgUpdate", msg);
//	    } catch (Exception ex) {
//	        System.out.println("erro no upload "+ex.getMessage());
//	    }
//	}
	
}
