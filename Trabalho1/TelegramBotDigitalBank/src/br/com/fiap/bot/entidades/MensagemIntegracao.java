package br.com.fiap.bot.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MENSAGEM_INTEGRACAO", catalog = "DBBotBank")
public class MensagemIntegracao implements Serializable{

	private static final long serialVersionUID = 2205725959674343189L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "id_telegram", unique = false, nullable = false)
	private Long idTelegram;

	@Column(name = "nome", unique = false, nullable = false, length = 50)
	private String nomeTelegram;
	
	@Column(name = "mensagem_rec", unique = false, nullable = false, length = 5000)
	private String mensagemRecebida;
	
	@Column(name = "mensagem_env", unique = false, nullable = false, length = 5000)
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
