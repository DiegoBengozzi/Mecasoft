package aplicacao.guice;

import banco.DAO.FuncionarioDAO;
import banco.DAO.PapelDAO;
import banco.DAO.PessoaDAO;
import banco.DAO.TipoVeiculoDAO;
import banco.DAO.UsuarioDAO;
import banco.DAO.VeiculoDAO;
import banco.utils.FuncionarioUtils;
import banco.utils.PapelUtils;
import banco.utils.PessoaUtils;
import banco.utils.TipoVeiculoUtils;
import banco.utils.UsuarioUtils;
import banco.utils.VeiculoUtils;

import com.google.inject.AbstractModule;

public class MecasoftModule extends AbstractModule{

	@Override
	protected void configure() {
		bind(PapelUtils.class).to(PapelDAO.class);
		bind(UsuarioUtils.class).to(UsuarioDAO.class);
		bind(FuncionarioUtils.class).to(FuncionarioDAO.class);
		bind(PessoaUtils.class).to(PessoaDAO.class);
		bind(VeiculoUtils.class).to(VeiculoDAO.class);
		bind(TipoVeiculoUtils.class).to(TipoVeiculoDAO.class);
	}

}
