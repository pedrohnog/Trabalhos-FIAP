package br.com.fiap.managedbeans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;


@ManagedBean
@RequestScoped
public class CadastroUsuarioMB {

	private String login;
	private String senha;	
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void cadastrarUsuario(){
		 FacesContext.getCurrentInstance().addMessage(
                 null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                 "Usuario cadastrado com sucesso!", "Usuario cadastrado com sucesso!"));
		 this.login = null;	
		 this.senha = null;
	}
	
}
