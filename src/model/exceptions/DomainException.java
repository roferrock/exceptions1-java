package model.exceptions;

public class DomainException extends RuntimeException {

	// versão da classe serializable
	private static final long serialVersionUID = 1L;
	
	// recebe uma String personalizada como argumento
	public DomainException(String msg) {
		super(msg);
	}

}
