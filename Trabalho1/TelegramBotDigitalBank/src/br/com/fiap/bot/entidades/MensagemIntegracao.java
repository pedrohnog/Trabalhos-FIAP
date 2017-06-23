package br.com.fiap.bot.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entidade que representa a tabela Mensagem_Integracao no BD
 *
 */
@Entity
@Table(name = "MENSAGEM_INTEGRACAO", catalog = "DBBotBank")
public class MensagemIntegracao implements Serializable {

	private static final long serialVersionUID = 1685332432291411751L;

	/**
	 * Id auto-gerado
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	/**
	 * Número do Telegram do cliente
	 */
	@Column(name = "id_telegram", unique = false, nullable = false)
	private Long idTelegram;

	/**
	 * Nome do cliente
	 */
	@Column(name = "nome", unique = false, nullable = false, length = 50)
	private String nomeTelegram;

	/**
	 * Mensagem que foi recebida pelo Bot
	 */
	@Column(name = "mensagem_rec", unique = false, nullable = false, length = 5000)
	private String mensagemRecebida;

	/**
	 * Resposta enviada pelo Bot
	 */
	@Column(name = "mensagem_env", unique = false, nullable = false, length = 5000)
	private String mensagemEnviada;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof MensagemIntegracao)) {
			return false;
		}
		MensagemIntegracao other = (MensagemIntegracao) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
}
