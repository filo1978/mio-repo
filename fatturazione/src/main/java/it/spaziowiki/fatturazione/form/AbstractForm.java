package it.spaziowiki.fatturazione.form;

public abstract class AbstractForm {
	
	public String getFormName() {
		return this.getClass().getName();
	}

}
