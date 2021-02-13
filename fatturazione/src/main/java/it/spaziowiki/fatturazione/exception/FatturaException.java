package it.spaziowiki.fatturazione.exception;

public class FatturaException extends Exception {

	public FatturaException(String message) {
		super(message);
	}

	public FatturaException(Throwable t) {
		super(t);
		
	}
}
