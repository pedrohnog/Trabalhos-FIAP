package br.com.fiap.managedbeans;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.time.LocalDate;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.UploadedFile;

import br.com.fiap.commands.NetgifxCommand;
import br.com.fiap.entity.Categoria;
import br.com.fiap.entity.Gif;
import br.com.fiap.utils.ArquivoUtil;
import br.com.fiap.utils.ConversorImagensUtil;


@ManagedBean
@RequestScoped
public class CadastroGifMB {

	private Gif gif;
	private UploadedFile arquivo;
	private Categoria[] categoriasSelecionadas;
	
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

	public Categoria[] getCategoriasSelecionadas() {
		return categoriasSelecionadas;
	}

	public void setCategoriasSelecionadas(Categoria[] categoriasSelecionadas) {
		this.categoriasSelecionadas = categoriasSelecionadas;
	}

	public void cadastrarGif() {
		
		if(validarDados()){
			NetgifxCommand command = new NetgifxCommand();
			UploadedFile arq = arquivo;
			try {
				
				FacesContext.getCurrentInstance().getResourceLibraryContracts();
				
				InputStream in = new BufferedInputStream(arq.getInputstream());
				
				String realPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/static/img");
				
				File file = new File(realPath, arq.getFileName());
				
				gif.setCaminho("static/img/" + file.getName().replace(".gif", ""));
				gif.setDataPublicacao(LocalDate.now());
				
				
				ArquivoUtil.gravarArquivo(in, file);
				ConversorImagensUtil.converterGifParaPng(file);
				command.cadastrarGif(gif);
				
				
				gif = new Gif();
				arquivo = null;
				categoriasSelecionadas = null;
				
				FacesContext.getCurrentInstance().addMessage(
		                 null, new FacesMessage(FacesMessage.SEVERITY_INFO,
		                 "GIF cadastrado com sucesso!", "GIF cadastrado com sucesso!"));
				
			} catch (Exception ex) {
				FacesContext.getCurrentInstance().addMessage(
		                 null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
		                 "Ocorreu um erro ao tentar cadastrar o gif!", "Ocorreu um erro ao tentar cadastrar o gif!"));
			}
		}
	}

	private boolean validarDados() {
		
		boolean dadosValido;
		
		if(gif.getDescricao().length() < 40){
			dadosValido = false;
			FacesContext.getCurrentInstance().addMessage(
	                 null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                 "A história deve conter pelo menos 40 caracteres!", "A história deve conter pelo menos 40 caracteres"));
			
		}else if(arquivo == null || !arquivo.getFileName().contains(".gif")){
			dadosValido = false;
			FacesContext.getCurrentInstance().addMessage(
	                 null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                 "Adicione um arquivo com a extensão .gif!", "Adicione um arquivo com a extensão .gif!"));
		}else if(categoriasSelecionadas.length > 0){
			
			for (int i = 0; i < categoriasSelecionadas.length; i++) {
				Categoria categoria = new Categoria(categoriasSelecionadas[i].getIdCategoria(),categoriasSelecionadas[i].getNome());
				gif.getCategorias().add(categoria);
			}
			dadosValido = true;
			
		}else{
			dadosValido = false;
			FacesContext.getCurrentInstance().addMessage(
	                 null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                 "Necessário adicionar pelo menos uma categoria!", "Necessário adicionar pelo menos uma categoria!"));
		}
		
		return dadosValido;
	}
	
}
