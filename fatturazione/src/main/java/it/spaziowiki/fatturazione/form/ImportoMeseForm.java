package it.spaziowiki.fatturazione.form;

import java.math.BigDecimal;

public class ImportoMeseForm {

	private String mese;
	private BigDecimal importo;

	public String getMese() {
		return mese;
	}

	public void setMese(String mese) {
		this.mese = mese;
	}

	public BigDecimal getImporto() {
		return importo;
	}

	public void setImporto(BigDecimal importo) {
		this.importo = importo;
	}

}
