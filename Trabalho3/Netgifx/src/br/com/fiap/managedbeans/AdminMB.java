package br.com.fiap.managedbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.fiap.entity.Usuario;

@ManagedBean
@RequestScoped
public class AdminMB {	
	
	public boolean validarAdmin(){
		HttpSession  httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		return httpSession != null && httpSession.getAttribute("usuario") != null && 
				((Usuario)httpSession.getAttribute("usuario")).getAdmin();
	}
	
	public boolean validarUsuarioLogado(){
		HttpSession  httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		return httpSession != null && httpSession.getAttribute("usuario") != null;
	}
	
	public String abrirPainel(){
		System.out.println("Redirecionando pagina");
		return "webapp/protected/admin/admin.xhtml?faces-redirect=true";
	}

}
