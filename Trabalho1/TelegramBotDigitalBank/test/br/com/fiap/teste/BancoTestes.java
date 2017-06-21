package br.com.fiap.teste;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.fiap.teste.banco.CriacaoContaTeste;
import br.com.fiap.teste.banco.DepositoTeste;
import br.com.fiap.teste.banco.ExibicaoDadosUsuariosTeste;
import br.com.fiap.teste.banco.ExibicaoLancamentosTeste;
import br.com.fiap.teste.banco.ExibicaoRetiradasTeste;
import br.com.fiap.teste.banco.ExibicaoSaldoDevedorEmprestimoTeste;
import br.com.fiap.teste.banco.ExibicaoTarifasTeste;
import br.com.fiap.teste.banco.InclusaoDependenteTeste;
import br.com.fiap.teste.banco.ModificacaoContaTeste;
import br.com.fiap.teste.banco.SaqueTeste;
import br.com.fiap.teste.banco.SolicitacaoEmprestimoTeste;
import br.com.fiap.teste.banco.SolicitacaoExtratoTeste;
import br.com.fiap.teste.banco.SolicitacaoSaldoTeste;
import br.com.fiap.teste.banco.VerificacaoSaldoFinalTeste;

@RunWith(Suite.class)
@SuiteClasses({ 
	CriacaoContaTeste.class,
	ModificacaoContaTeste.class,
	InclusaoDependenteTeste.class,
	ExibicaoDadosUsuariosTeste.class,
	DepositoTeste.class,
	SaqueTeste.class,
	SolicitacaoSaldoTeste.class,
	SolicitacaoExtratoTeste.class,
	SolicitacaoEmprestimoTeste.class,
	ExibicaoSaldoDevedorEmprestimoTeste.class,
	ExibicaoLancamentosTeste.class,
	ExibicaoRetiradasTeste.class,
	ExibicaoTarifasTeste.class,
	VerificacaoSaldoFinalTeste.class
})
public class BancoTestes {

}
