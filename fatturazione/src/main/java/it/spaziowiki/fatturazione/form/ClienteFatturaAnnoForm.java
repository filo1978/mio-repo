package it.spaziowiki.fatturazione.form;

import java.math.BigDecimal;

public class ClienteFatturaAnnoForm {

	private String denominazioneCliente;
	private BigDecimal fatturatoCliente;

	public String getDenominazioneCliente() {
		return denominazioneCliente;
	}

	public void setDenominazioneCliente(String denominazioneCliente) {
		this.denominazioneCliente = denominazioneCliente;
	}

	public BigDecimal getFatturatoCliente() {
		return fatturatoCliente;
	}

	public void setFatturatoCliente(BigDecimal fatturatoCliente) {
		this.fatturatoCliente = fatturatoCliente;
	}

}
