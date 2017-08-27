package br.com.fiap.managedbeans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

@ManagedBean
@SessionScoped
public class UsuarioMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean usuarioLogado = false;
	private boolean acaoLoggout = false;
	private String usuario = "";
	private String senha = "";

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
