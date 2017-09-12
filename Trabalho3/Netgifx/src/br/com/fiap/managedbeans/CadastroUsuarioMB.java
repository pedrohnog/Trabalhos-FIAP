package br.com.fiap.managedbeans;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.com.fiap.commands.NetgifxCommand;
import br.com.fiap.entity.Usuario;


@ManagedBean
@RequestScoped
public class CadastroUsuarioMB {

	private Usuario usuario;	
	
	@PostConstruct
	public void init(){
		usuario = new Usuario();
	}
	
	public void cadastrarUsuario(){
		 
		 NetgifxCommand command = new NetgifxCommand();
		 
		 if(command.buscarUsuario(usuario.getApelido()) == null){
			 
			 command.cadastrarUsuario(usuario);
			 FacesContext.getCurrentInstance().addMessage(
	                 null, new FacesMessage(FacesMessage.SEVERITY_INFO,
	                 "Usuário cadastrado com sucesso!", "Usuário cadastrado com sucesso!"));
			 this.usuario = new Usuario();
		 }else{
			 FacesContext.getCurrentInstance().addMessage(
	                 null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                 "Já existe cadastro com este apelido!", "Já existe cadastro com este apelido!"));			 
		 }

		 
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
