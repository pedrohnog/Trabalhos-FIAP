package br.com.fiap.bot.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "MensagensIntegracao", catalog = "DBBot")
public class MensagemIntegracao implements Serializable{

	private static final long serialVersionUID = -1798591717859526500L;

	@Column(name = "id", unique = false, nullable = false)
	private Long idTelegram;

	@Column(name = "nome", unique = false, nullable = false, length = 50)
	private String nomeTelegram;
	
	@Column(name = "mensagemRecebida", unique = false, nullable = false, length = 300)
	private String mensagemRecebida;
	
	@Column(name = "mensagemEnviada", unique = false, nullable = false, length = 300)
	private String mensagemEnviada;
	
	public Long getIdTelegram() {
		return idTelegram;
	}

	public void setIdTelegram(Long idTelegram) {
		this.idTelegram = idTelegram;
	}

	public String getNomeTelegram() {
		return nomeTelegram;
	}

	public void setNomeTelegram(String nomeTelegram) {
		this.nomeTelegram = nomeTelegram;
	}

	public String getMensagemRecebida() {
		return mensagemRecebida;
	}

	public void setMensagemRecebida(String mensagemRecebida) {
		this.mensagemRecebida = mensagemRecebida;
	}

	public String getMensagemEnviada() {
		return mensagemEnviada;
	}

	public void setMensagemEnviada(String mensagemEnviada) {
		this.mensagemEnviada = mensagemEnviada;
	}
}
