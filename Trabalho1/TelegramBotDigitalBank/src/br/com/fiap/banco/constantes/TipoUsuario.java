package br.com.fiap.banco.constantes;

public enum TipoUsuario {

	PRINCIPAL	(1), 
	DEPENDENTE	(2);

	private int codigo;

	private TipoUsuario(int codigo) {
		this.codigo = codigo;
	}

	public int getCodigo() {
		return codigo;
	}

}
