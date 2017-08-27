package br.com.fiap.managedbeans;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import br.com.fiap.commons.GifVO;

@ManagedBean
@SessionScoped
public class UsuarioMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean usuarioLogado = false;
	private boolean acaoLoggout = false;
	private String usuario = "";
	private String senha = "";
	
	private List<GifVO> gifsFavoritos = null;

	public boolean isUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(boolean usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public boolean isAcaoLoggout() {
		return acaoLoggout;
	}

	public void setAcaoLoggout(boolean acaoLoggout) {
		this.acaoLoggout = acaoLoggout;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void realizarLoggout() {
		this.usuarioLogado = false;
	}


	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<GifVO> getGifsFavoritos() {
		gifsFavoritos =  new ArrayList<>();
		gifsFavoritos.add(new GifVO(1, "01", "gif 01", LocalDate.now(), 5D, "static/img/gif/01.gif", "static/img/png/01.png"));
		gifsFavoritos.add(new GifVO(2, "02", "gif 02", LocalDate.now(), 5D, "static/img/gif/02.gif", "static/img/png/02.png"));
		
		return gifsFavoritos;
	}

	public void setGifsFavoritos(List<GifVO> gifsFavoritos) {
		this.gifsFavoritos = gifsFavoritos;
	}
	
	public void adicionarGifFavorito(GifVO gif) {
		//TODO Implementar lógica para adicionar gif, obs, validar se já nao eh favorito
		System.out.println("Adicionar");
		System.out.println(gif.getNome());
		System.out.println(gif.getCaminhoAnimado());
	}

	public void realizarLogin(ActionEvent actionEvent) {
		
		System.out.println(this.usuario);
		if("erro".equals(this.usuario)){
			addMessageError("Erro ao realizar Login!", "O usuário informado ou a senha estão incorretas");
			this.usuarioLogado = false;
		}else{
			this.usuarioLogado = true;
		}
		
	}

	public void addMessageError(String resumo, String detalhe) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, resumo, detalhe);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}
