package tela.view;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.ResourceManager;

import tela.componentes.MecasoftText;
import tela.editor.AbrirOrdemServicoEditor;
import tela.editor.editorInput.AbrirOrdemServicoEditorInput;
import tela.filter.ServicoPrestadoFilter;
import aplicacao.service.ServicoPrestadoService;
import banco.modelo.ServicoPrestado;

public class ServicoPrestadoView extends ViewPart {

	public static final String ID = "tela.view.ServicoPrestadoView"; //$NON-NLS-1$
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private Text txtFiltro;
	private Table table;
	private TableViewer tvServicoPrestado;
	private Action actionAtualizar;
	private Action actionNovo;
	
	private ServicoPrestadoService service = new ServicoPrestadoService();
	private ServicoPrestadoFilter filtro = new ServicoPrestadoFilter();
	private MecasoftText txtDataInicial;
	private MecasoftText txtDataFinal;

	public ServicoPrestadoView() {
		createActions();
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Form frmServiosPrestados = formToolkit.createForm(container);
		formToolkit.paintBordersFor(frmServiosPrestados);
		frmServiosPrestados.setText("Servi\u00E7os Prestados");
		frmServiosPrestados.getBody().setLayout(new GridLayout(5, false));
		
		Label lblBuscar = new Label(frmServiosPrestados.getBody(), SWT.NONE);
		lblBuscar.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		formToolkit.adapt(lblBuscar, true, true);
		lblBuscar.setText("Buscar:");
		
		txtFiltro = new Text(frmServiosPrestados.getBody(), SWT.BORDER);
		txtFiltro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				filtro.setSearch(txtFiltro.getText());
				tvServicoPrestado.refresh();
			}
		});
		txtFiltro.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 4, 1));
		txtFiltro.setMessage("Filtro...");
		formToolkit.adapt(txtFiltro, true, true);
		
		Label lblPeriodoDe = formToolkit.createLabel(frmServiosPrestados.getBody(), "Per\u00EDodo de", SWT.NONE);
		lblPeriodoDe.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		
		txtDataInicial = new MecasoftText(frmServiosPrestados.getBody(), SWT.NONE);
		txtDataInicial.setOptions(MecasoftText.NUMEROS, 10);
		txtDataInicial.addChars("//", new Integer[]{2, 4}, null, null);
		formToolkit.adapt(txtDataInicial);
		formToolkit.paintBordersFor(txtDataInicial);
		
		Label lblAte = new Label(frmServiosPrestados.getBody(), SWT.NONE);
		formToolkit.adapt(lblAte, true, true);
		lblAte.setText("at\u00E9");
		
		txtDataFinal = new MecasoftText(frmServiosPrestados.getBody(), SWT.NONE);
		txtDataFinal.setOptions(MecasoftText.NUMEROS, 10);
		txtDataFinal.addChars("//", new Integer[]{2, 4}, null, null);
		formToolkit.adapt(txtDataFinal);
		formToolkit.paintBordersFor(txtDataFinal);
		
		tvServicoPrestado = new TableViewer(frmServiosPrestados.getBody(), SWT.BORDER | SWT.FULL_SELECTION);
		table = tvServicoPrestado.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 5, 1));
		tvServicoPrestado.setContentProvider(ArrayContentProvider.getInstance());
		tvServicoPrestado.addFilter(filtro);
		formToolkit.paintBordersFor(table);
		
		TableViewerColumn tvcNumero = new TableViewerColumn(tvServicoPrestado, SWT.NONE);
		tvcNumero.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				return ((ServicoPrestado)element).getId().toString();
			}
		});
		TableColumn tblclmnNumero = tvcNumero.getColumn();
		tblclmnNumero.setWidth(100);
		tblclmnNumero.setText("N\u00FAmero");
		
		TableViewerColumn tvcCliente = new TableViewerColumn(tvServicoPrestado, SWT.NONE);
		tvcCliente.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				return ((ServicoPrestado)element).getCliente().getNomeFantasia();
			}
		});
		TableColumn tblclmnCliente = tvcCliente.getColumn();
		tblclmnCliente.setWidth(203);
		tblclmnCliente.setText("Cliente");
		
		TableViewerColumn tvcVeiculo = new TableViewerColumn(tvServicoPrestado, SWT.NONE);
		tvcVeiculo.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				return ((ServicoPrestado)element).getVeiculo().getModelo();
			}
		});
		TableColumn tblclmnVeculo = tvcVeiculo.getColumn();
		tblclmnVeculo.setWidth(148);
		tblclmnVeculo.setText("Ve\u00EDculo");
		
		TableViewerColumn tvcMecanico = new TableViewerColumn(tvServicoPrestado, SWT.NONE);
		tvcMecanico.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				ServicoPrestado sp = (ServicoPrestado)element;
				return sp.getListaStatus().get(sp.getListaStatus().size()-1).getFuncionario().getNomeFantasia();
			}
		});
		TableColumn tblclmnMecnico = tvcMecanico.getColumn();
		tblclmnMecnico.setWidth(152);
		tblclmnMecnico.setText("Mec\u00E2nico");
		
		TableViewerColumn tvcStatus = new TableViewerColumn(tvServicoPrestado, SWT.NONE);
		tvcStatus.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				ServicoPrestado sp = (ServicoPrestado)element;
				return sp.getListaStatus().get(sp.getListaStatus().size()-1).getStatus().getDescricao();
			}
		});
		TableColumn tblclmnStatusAtual = tvcStatus.getColumn();
		tblclmnStatusAtual.setWidth(95);
		tblclmnStatusAtual.setText("Status Atual");
		frmServiosPrestados.getToolBarManager().add(actionAtualizar);
		frmServiosPrestados.getToolBarManager().add(actionNovo);
		frmServiosPrestados.updateToolBar();
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
		{
			actionAtualizar = new Action("Atualizar") {				@Override
				public void run() {
					// TODO Auto-generated method stub
					super.run();
				}
			};
			actionAtualizar.setImageDescriptor(ResourceManager.getPluginImageDescriptor("mecasoft", "assents/funcoes/refresh16.png"));
		}
		{
			actionNovo = new Action("Abrir Nova Ordem") {				@Override
				public void run() {
					try {
						getSite().getPage().openEditor(new AbrirOrdemServicoEditorInput(), AbrirOrdemServicoEditor.ID);
					} catch (PartInitException e) {
						e.printStackTrace();
					}
				}
			};
			actionNovo.setImageDescriptor(ResourceManager.getPluginImageDescriptor("mecasoft", "assents/funcoes/add16.png"));
		}
	}

	@Override
	public void setFocus() {
		// Set the focus
	}

}
