package mecasoft;

import static aplicacao.helper.MessageHelper.openQuestion;

import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.eclipse.ui.handlers.IHandlerService;

import aplicacao.helper.UsuarioHelper;
import aplicacao.service.CaixaService;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

	private CaixaService caixaService = new CaixaService();
	
	public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
		super(configurer);
	}

	public ActionBarAdvisor createActionBarAdvisor(
			IActionBarConfigurer configurer) {
		return new ApplicationActionBarAdvisor(configurer);
	}

	public void preWindowOpen() {
		IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
		configurer.setInitialSize(new Point(1000, 800));
		configurer.setShowCoolBar(false);
		configurer.setShowProgressIndicator(true);
		configurer.setShowStatusLine(true);
		configurer.setTitle("MECASOFT");
	}
	
	private void verificacaoMenus() {
		// Chamada ao command que vai habilitar os menus
		IHandlerService handlerService = (IHandlerService) Activator.getDefault().getWorkbench().getService(IHandlerService.class);
		try {
			handlerService.executeCommand("mecasoft.permissaoUsuarioCommand", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean preWindowShellClose() {
		return openQuestion("Tem certeza que deseja finalizar o sistema?");
	}
	
	public void verificacaoCaixa(){
		UsuarioHelper.setCaixa(caixaService.findCaixaAtual());
	}
	
	@Override
	public void postWindowOpen() {
		verificacaoMenus();
		verificacaoCaixa();
	}
	
}
