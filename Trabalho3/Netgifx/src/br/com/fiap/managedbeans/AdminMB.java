package br.com.fiap.managedbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.fiap.entity.Usuario;

@ManagedBean
@RequestScoped
public class AdminMB {

	private static final String ATRIBUTOUSUARIO = "usuario";
	private static final String PAINELADMIN = "webapp/protected/admin/admin.xhtml?faces-redirect=true";

	public boolean validarAdmin() {
		HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		
		return httpSession != null && httpSession.getAttribute(ATRIBUTOUSUARIO) != null && ((Usuario) httpSession.getAttribute(ATRIBUTOUSUARIO)).getAdmin();
	}

	public boolean validarUsuarioLogado() {
		HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		
		return httpSession != null && httpSession.getAttribute(ATRIBUTOUSUARIO) != null;
	}

	public String abrirPainel() {
		return PAINELADMIN;
	}

}
