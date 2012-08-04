package tela.filter;

import org.eclipse.jface.viewers.Viewer;

import banco.modelo.TipoVeiculo;

public class TipoVeiculoFilter extends MecasoftFilter{

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if(searchNull())
			return true;
		
		TipoVeiculo tipo = (TipoVeiculo)element;
		
		if(tipo.getNome().toLowerCase().matches(search.toLowerCase()))
			return true;
		
		if(tipo.getHodometro() && "hod�metro".matches(search.toLowerCase()))
			return true;
		
		if(tipo.getHorimetro() && "hor�metro".matches(search.toLowerCase()))
			return true;
		
		return false;
	}

}
