package it.spaziowiki.fatturazione.form;


public class CComuneForm extends AbstractForm{

	private String codCom;
	private String codProv;
	private String descrizione;

	public String getCodCom() {
		return codCom;
	}

	public void setCodCom(String codCom) {
		this.codCom = codCom;
	}

	public String getCodProv() {
		return codProv;
	}

	public void setCodProv(String codProv) {
		this.codProv = codProv;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}
