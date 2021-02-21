package it.spaziowiki.fatturazione.enums;

public enum TipoFatturaEnum {

	FATTURA("FT001"),
	PREVENTIVO("FT002"),
	BLACK("FT003");
	
	private TipoFatturaEnum(String cod) {
		this.cod = cod;
	}
	
	private String cod;
	
	
	public String getCod() {
		return cod;
	}
}
