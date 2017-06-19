package br.com.fiap.banco.constantes;

public enum TipoUsuario {

	PRINCIPAL(1), DEPENDENTE(2);

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
