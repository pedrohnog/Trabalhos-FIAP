package br.com.fiap.managedbeans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.fiap.commands.NetgifxCommand;
import br.com.fiap.entity.Usuario;
import br.com.fiap.utils.CriptografiaUtil;

@ManagedBean
@SessionScoped
public class UsuarioMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario usuario = new Usuario();
	private NetgifxCommand netgifxCommand = new NetgifxCommand();
	
	public String realizarLogin() {
		String senha = CriptografiaUtil.gerarHash(this.usuario.getSenha());
		
		Usuario usuario = netgifxCommand.buscarUsuario(this.usuario.getApelido());
		FacesContext context = FacesContext.getCurrentInstance();
		
		if (usuario != null && usuario.getSenha().equals(senha)) {
			this.usuario = usuario;
			
			context.getExternalContext().getSessionMap().put("usuario", usuario);
			
			return "webapp/protected/main.xhtml?faces-redirect=true";
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao realizar login!", "O usuário não possui um cadastro ou a senha está incorreta. Entre em contato com um administrador para cadastrar!"));
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
