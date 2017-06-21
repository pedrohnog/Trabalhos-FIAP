package br.com.fiap.teste;

public class ConstantesTeste {
	
	/* ID DO TELEGRAM */
	public static final long idTelegram = 1234;
	public static final long idInvalido = 9999;
	
	/* DADOS DO USUÁRIO PRINCIPAL */
	public static final String nomePrincipal = "Nome Principal";
	public static final String sobrenomePrincipal = "Sobrenome Principal";
	public static final String telefonePrincipal = "11987654321";
	public static final String cpfPrincipal = "12345678900";
	public static final String emailPrincipal = "principal@teste.com.br";
	
	/* DADOS DO DEPENDENTE */
	public static final String nomeDependente = "Nome Dependente";
	public static final String sobrenomeDependente = "Sobrenome Dependente";
	public static final String telefoneDependente = "99123456789";
	public static final String cpfDependente = "01010101099";
	public static final String emailDependente = "dependente@teste.com.br";
	
	/* DADOS PARA ALTERAÇÃO DO USUÁRIO */
	public static final String cpfNovo = "98765432100";
	public static final String emailNovo = "novo@teste.com.br";
	
	/* VALORES */
	public static final double deposito = 100_000d;
	public static final double saque = 50_000d;
	public static final double saqueInvalido = 1_000_000d;
	public static final double saldoEsperado = 49_997.5d;
	
	/* EMPRESTIMO */
	public static final double valorEmprestimo = 100_000d;
	public static final double valorEmprestimoInvalido = 10_000_000d;
	public static final int prazoEmprestimo = 12;
	public static final int prazoEmprestimoInvalido = 90;
	public static final double saldoDevedor = 139_274.83d;
	
	/* VALOR FINAL */
	public static final double valorFinal = 149_981.5d;
}
