package it.spaziowiki.fatturazione.exception;

public class ClienteInsertException extends Exception {
	
	public ClienteInsertException(String message) {
		super(message);
	}

	public ClienteInsertException(Throwable t) {
		super(t);

	}
}
