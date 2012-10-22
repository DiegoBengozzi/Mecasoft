package aplicacao.helper;

public class ReportHelper {

	public static String getReport(String nome){
		return nome.concat(".jasper");
	}
	
	public static String SERVICO_SINTETICO = getReport("servicoSintetico");
	public static String SERVICO_ANALITICO = getReport("servicoAnalitico");
	public static String DUPLICATA = getReport("duplicatas");
	public static String FLUXO_TRABALHO = getReport("fluxoTrabalho");
	
}
