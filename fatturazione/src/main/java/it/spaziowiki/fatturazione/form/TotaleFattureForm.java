package it.spaziowiki.fatturazione.form;

import java.math.BigDecimal;

public class TotaleFattureForm extends AbstractForm {

	private Integer annoFattura;

	private BigDecimal importoNettoFatturato;

	private BigDecimal importoLordoFatturato;

	private BigDecimal importoNettoNonPagato;

	private BigDecimal importoLordoNonPagato;
	
	private BigDecimal importoNettoPagato;

	private BigDecimal importoLordoPagato;

	private BigDecimal importoNettoAnnullato;

	private BigDecimal importoLordoAnnullato;
	
	public Integer getAnnoFattura() {
		return annoFattura;
	}

	public void setAnnoFattura(Integer annoFattura) {
		this.annoFattura = annoFattura;
	}

	public BigDecimal getImportoNettoFatturato() {
		return importoNettoFatturato;
	}

	public void setImportoNettoFatturato(BigDecimal importoNettoFatturato) {
		this.importoNettoFatturato = importoNettoFatturato;
	}

	public BigDecimal getImportoLordoFatturato() {
		return importoLordoFatturato;
	}

	public void setImportoLordoFatturato(BigDecimal importoLordoFatturato) {
		this.importoLordoFatturato = importoLordoFatturato;
	}

	public BigDecimal getImportoNettoNonPagato() {
		return importoNettoNonPagato;
	}

	public void setImportoNettoNonPagato(BigDecimal importoNettoNonPagato) {
		this.importoNettoNonPagato = importoNettoNonPagato;
	}

	public BigDecimal getImportoLordoNonPagato() {
		return importoLordoNonPagato;
	}

	public void setImportoLordoNonPagato(BigDecimal importoLordoNonPagato) {
		this.importoLordoNonPagato = importoLordoNonPagato;
	}

	public BigDecimal getImportoNettoPagato() {
		return importoNettoPagato;
	}

	public void setImportoNettoPagato(BigDecimal importoNettoPagato) {
		this.importoNettoPagato = importoNettoPagato;
	}

	public BigDecimal getImportoLordoPagato() {
		return importoLordoPagato;
	}

	public void setImportoLordoPagato(BigDecimal importoLordoPagato) {
		this.importoLordoPagato = importoLordoPagato;
	}

	public BigDecimal getImportoNettoAnnullato() {
		return importoNettoAnnullato;
	}

	public void setImportoNettoAnnullato(BigDecimal importoNettoAnnullato) {
		this.importoNettoAnnullato = importoNettoAnnullato;
	}

	public BigDecimal getImportoLordoAnnullato() {
		return importoLordoAnnullato;
	}

	public void setImportoLordoAnnullato(BigDecimal importoLordoAnnullato) {
		this.importoLordoAnnullato = importoLordoAnnullato;
	}

}
