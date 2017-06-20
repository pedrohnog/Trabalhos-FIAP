package br.com.fiap.banco.constantes;

/**
 * Enum responsável por mapear os tipos de usuários existentes
 *
 */
public enum TipoUsuario {

	/**
	 * Constante que define o usuário principal da conta
	 */
	PRINCIPAL(1),
	/**
	 * Constante que define um usuário dependente da conta
	 */
	DEPENDENTE(2);

	private int codigo;

	private TipoUsuario(int codigo) {
		this.codigo = codigo;
	}

	public int getCodigo() {
		return codigo;
	}

	public static TipoUsuario getTipoUsuario(int codigo) {
		if (codigo > 0) {
			for (TipoUsuario tipoUsuario : values()) {
				if (tipoUsuario.codigo == codigo) {
					return tipoUsuario;
				}
			}
		}
		return null;
	}

}
