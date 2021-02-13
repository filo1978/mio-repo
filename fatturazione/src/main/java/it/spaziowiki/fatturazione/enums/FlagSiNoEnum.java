package it.spaziowiki.fatturazione.enums;

public enum FlagSiNoEnum {

	SI("S","Sì"),
	NO("N","No");
	
	private FlagSiNoEnum(String cod, String descrizione) {
		this.cod = cod;
		this.descrizione = descrizione;
	}
	
	private String cod;
	private String descrizione;
	
	
	public String getCod() {
		return cod;
	}
	public String getDescrizione() {
		return descrizione;
	}
}
