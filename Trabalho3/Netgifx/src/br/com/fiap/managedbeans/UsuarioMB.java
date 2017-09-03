package br.com.fiap.managedbeans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.fiap.commands.NetgifxCommand;
import br.com.fiap.entity.Usuario;

@ManagedBean
@SessionScoped
public class UsuarioMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario usuario = new Usuario();
	private NetgifxCommand netgifxCommand = new NetgifxCommand();
	
	public String realizarLogin() {
		Usuario usuario = netgifxCommand.buscarUsuario(this.usuario.getApelido());
		
		if (usuario != null) {
			this.usuario = usuario;
			//TODO Criar uma sessao para o usuario
			return "webapp/protected/main.xhtml?faces-redirect=true";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário não cadastrado!", "O usuário não possui um cadastro. Entre em contato com um administrador para cadastrar!"));
			return null;
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
