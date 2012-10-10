package aplicacao.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationException extends Exception {

	private static final long serialVersionUID = -6792343688787102258L;
	private String mensagem;
	private List<String> mensagens;

	public ValidationException(String mensagem, List<String> mensagens) {
		this.mensagem = mensagem;
		this.mensagens = mensagens;
	}
	
	public ValidationException(String mensagem) {
		this.mensagem = mensagem;
		this.mensagens = new ArrayList<String>();
	}

	@Override
	public String getMessage() {
		return mensagem;
	}

	public List<String> getMensagens() {
		return mensagens;
	}
}