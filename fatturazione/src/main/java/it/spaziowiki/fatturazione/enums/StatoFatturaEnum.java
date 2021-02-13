package it.spaziowiki.fatturazione.enums;

public enum StatoFatturaEnum {

	PAGATA("ST01"),
	NON_PAGATA("ST02"),
	ANNULLATA("ST03");
	
	private StatoFatturaEnum(String cod) {
		this.cod = cod;
	}
	
	private String cod;
	
	
	public String getCod() {
		return cod;
	}
}
