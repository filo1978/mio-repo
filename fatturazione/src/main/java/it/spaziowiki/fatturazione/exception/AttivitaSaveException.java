package it.spaziowiki.fatturazione.exception;

public class AttivitaSaveException extends Exception {

	public AttivitaSaveException(String message) {
		super(message);
	}

	public AttivitaSaveException(Throwable t) {
		super(t);
		
	}
}
