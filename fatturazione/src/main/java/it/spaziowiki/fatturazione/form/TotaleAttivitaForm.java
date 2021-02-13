package it.spaziowiki.fatturazione.form;

import java.math.BigDecimal;

public class TotaleAttivitaForm {

	private BigDecimal totaleAttivita;
	private boolean toCheck;
	
	public BigDecimal getTotaleAttivita() {
		return totaleAttivita;
	}
	public void setTotaleAttivita(BigDecimal totaleAttivita) {
		this.totaleAttivita = totaleAttivita;
	}
	public boolean isToCheck() {
		return toCheck;
	}
	public void setToCheck(boolean toCheck) {
		this.toCheck = toCheck;
	}
	
	
	
}
