package it.spaziowiki.fatturazione.exception;

public class ClienteUpdateException extends Exception {

	public ClienteUpdateException(String message) {
		super(message);
	}

	public ClienteUpdateException(Throwable t) {
		super(t);

	}
}
