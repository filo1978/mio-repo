package it.spaziowiki.fatturazione.exception;

public class FatturaDeleteException extends Exception {
	
	public FatturaDeleteException(String message) {
		super(message);
	}

	public FatturaDeleteException(Throwable t) {
		super(t);

	}
}
