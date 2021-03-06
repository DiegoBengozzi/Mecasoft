package tela.editingSupport;

import static aplicacao.helper.MessageHelper.openWarning;

import java.math.BigDecimal;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;

import banco.modelo.ItemServico;

public class QuantidadeItemServicoEditingSupport extends EditingSupport{

	private TableViewer viewer;
	
	public QuantidadeItemServicoEditingSupport(TableViewer viewer) {
		super(viewer);
		this.viewer = viewer;
	}

	@Override
	protected CellEditor getCellEditor(Object element) {
		TextCellEditor tce = new TextCellEditor(viewer.getTable());
		return tce;
	}

	@Override
	protected boolean canEdit(Object element) {
		return true;
	}

	@Override
	protected Object getValue(Object element) {
		ItemServico is = (ItemServico)element;
		
		if(is != null)
			return is.getQuantidade().toString();
		
		return "";
	}

	@Override
	protected void setValue(Object element, Object value) {
		try{
			ItemServico is = (ItemServico)element;
			String quantidade = (String) value;
			
			if(!quantidade.isEmpty()){
				Integer valorQuantidade = Integer.parseInt(quantidade);
				
				if(is.getItem().isEstocavel() && is.getItem().getQuantidade().compareTo(valorQuantidade) < 0){
					openWarning("Quantidade insuficiente em estoque.");
					return;
				}
				
				is.setQuantidade(valorQuantidade);
				
				BigDecimal total = is.getValorUnitario().multiply(new BigDecimal(is.getQuantidade()))
					.subtract(is.getDesconto()).add(is.getAcrescimo());
				
				is.setTotal(total);
				viewer.refresh();
			}
		}catch(Exception e){}
		
	}

}
