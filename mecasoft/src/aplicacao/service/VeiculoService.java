package aplicacao.service;

import java.util.List;

import banco.modelo.TipoVeiculo;
import banco.modelo.Veiculo;
import banco.utils.VeiculoUtils;

public class VeiculoService extends MecasoftService<Veiculo>{

	private Veiculo veiculo;
	
	@Override
	public VeiculoUtils getDAO() {
		return getInjector().getInstance(VeiculoUtils.class);
	}
	
	public void saveOrUpdate(){
		getDAO().saveOrUpdate(veiculo);
	}
	
	public Veiculo find(Long id){
		return getDAO().find(id);
	}
	
	public List<Veiculo> findAll(){
		return getDAO().findAll();
	}
	
	public List<Veiculo> findAllByTipo(TipoVeiculo tipo){
		return getDAO().findAllByTipo(tipo);
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

}
