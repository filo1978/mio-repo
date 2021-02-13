package it.spaziowiki.fatturazione.form;

public class PairDto extends AbstractForm {

	private String cod;
	private String descrizione;

	public PairDto(String cod, String descrizione) {
		super();
		this.cod = cod;
		this.descrizione = descrizione;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}
