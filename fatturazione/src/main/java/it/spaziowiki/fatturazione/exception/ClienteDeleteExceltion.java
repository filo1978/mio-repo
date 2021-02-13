package it.spaziowiki.fatturazione.exception;

public class ClienteDeleteExceltion extends Exception {
	
	public ClienteDeleteExceltion(String message) {
		super(message);
	}

	public ClienteDeleteExceltion(Throwable t) {
		super(t);
		
	}
}