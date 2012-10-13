package banco.utils;

import java.util.Date;
import java.util.List;

import banco.modelo.Pessoa;
import banco.modelo.StatusServico;

public interface StatusServicoUtils extends MecasoftUtils<StatusServico>{

	StatusServico findStatusFuncionario(Pessoa funcionario);
	List<StatusServico> findAllByFuncionarioAndPeriodo(Pessoa funcionario, Date dtInicial, Date dtFinal);
	
}
