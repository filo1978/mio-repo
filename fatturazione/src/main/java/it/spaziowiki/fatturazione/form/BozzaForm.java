package it.spaziowiki.fatturazione.form;

import java.math.BigDecimal;

public class BozzaForm extends AbstractForm {

	private String denominazioneCliente;
	private String pivaCiente;
	private String descrMese;
	private String attivita;
	private BigDecimal importoAttvita;

	public String getDenominazioneCliente() {
		return denominazioneCliente;
	}

	public void setDenominazioneCliente(String denominazioneCliente) {
		this.denominazioneCliente = denominazioneCliente;
	}

	public String getPivaCiente() {
		return pivaCiente;
	}

	public void setPivaCiente(String pivaCiente) {
		this.pivaCiente = pivaCiente;
	}

	public String getDescrMese() {
		return descrMese;
	}

	public void setDescrMese(String descrMese) {
		this.descrMese = descrMese;
	}

	public String getAttivita() {
		return attivita;
	}

	public void setAttivita(String attivita) {
		this.attivita = attivita;
	}

	public BigDecimal getImportoAttvita() {
		return importoAttvita;
	}

	public void setImportoAttvita(BigDecimal importoAttvita) {
		this.importoAttvita = importoAttvita;
	}

}
