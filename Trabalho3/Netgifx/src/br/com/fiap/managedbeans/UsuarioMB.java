package br.com.fiap.managedbeans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.fiap.commands.NetgifxCommand;
import br.com.fiap.entity.Gif;
import br.com.fiap.entity.Usuario;

@ManagedBean
@SessionScoped
public class UsuarioMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario usuario = new Usuario();
	private NetgifxCommand netgifxCommand = new NetgifxCommand();
	
	public String realizarLogin() {
		Usuario usuario = netgifxCommand.buscarUsuario(this.usuario.getApelido());
		FacesContext context = FacesContext.getCurrentInstance();
		
		if (usuario != null && usuario.getSenha().equals(this.usuario.getSenha())) {
			this.usuario = usuario;
			
			context.getExternalContext().getSessionMap().put("usuario", usuario);
			
			return "webapp/protected/main.xhtml?faces-redirect=true";
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao realizar login!", "O usuário não possui um cadastro ou a senha está incorreta. Entre em contato com um administrador para cadastrar!"));
			return null;
		}
	}

	public String realizarLogout() {

		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().remove("#{usuarioMB}");
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.invalidate();

		return "../../login.xhtml?faces-redirect=true";
	}

	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public void favoritarGif(Gif gif){
		if(!(usuario.getGifs().stream().filter(us -> us.getIdGif() == gif.getIdGif()).count() > 0)){
			System.out.println("Favoritar porque ainda nao é favorito!");
			
			NetgifxCommand command = new NetgifxCommand();
			
			usuario.getGifs().add(gif);									
			command.atualizarDadosUsuario(usuario);			
			
		}else{
			System.out.println("Já é favorito!");
		}
	}

}
