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

	public void realizarLogin(ActionEvent actionEvent) {
		this.usuarioLogado = true;
		addMessage("Login realizado!!");
	}

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}
