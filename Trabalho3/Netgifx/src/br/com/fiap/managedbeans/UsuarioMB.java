package br.com.fiap.managedbeans;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.fiap.commands.NetgifxCommand;
import br.com.fiap.entity.Gif;
import br.com.fiap.entity.Usuario;

@ManagedBean
@SessionScoped
public class UsuarioMB implements Serializable {

	private static final long serialVersionUID = 3660026158447830700L;

	protected transient Logger logger = LoggerFactory.getLogger(UsuarioMB.class);

	private Usuario usuario = new Usuario();
	private transient NetgifxCommand netgifxCommand = new NetgifxCommand();
	
	public String realizarLogin() {
		Usuario usuarioBD = netgifxCommand.buscarUsuario(this.usuario.getApelido());
		FacesContext context = FacesContext.getCurrentInstance();
		
		if (usuarioBD != null && usuarioBD.getSenha().equals(this.usuario.getSenha())) {
			this.usuario = usuarioBD;
			
			context.getExternalContext().getSessionMap().put("usuario", usuarioBD);
			
			return "webapp/protected/main.xhtml?faces-redirect=true";
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao realizar login!", "O usuário não possui um cadastro ou a senha está incorreta. Entre em contato com um administrador para cadastrar!"));
			return null;
		}
	}

	public String realizarLogout() {
		FacesContext context = FacesContext.getCurrentInstance();
		
		HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
		
		session.invalidate();
		
		try {
			context.getExternalContext().redirect(context.getExternalContext().getRequestContextPath() + "/index.xhtml");
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		
		return "webapp/login/login.xhtml?faces-redirect=true";
	}

	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public void favoritarGif(Gif gif){
		NetgifxCommand command = new NetgifxCommand();		
		usuario.getGifs().add(gif);									
		command.atualizarDadosUsuario(usuario);
	}

	public void desfavoritarGif(Gif gif){
		NetgifxCommand command = new NetgifxCommand();		
		usuario.getGifs().removeIf(g -> g.getIdGif() == gif.getIdGif());							
		command.atualizarDadosUsuario(usuario);
	}
	
	public boolean validarGifFavorito(Gif gif){
		return usuario.getGifs().stream().filter(us -> us.getIdGif() == gif.getIdGif()).count() > 0;
	}

}
