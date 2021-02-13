package it.spaziowiki.fatturazione.form;

import java.math.BigDecimal;

public class FatturaAnnoForm {

	private BigDecimal importo;
	private Integer anno;

	public FatturaAnnoForm(BigDecimal importo, Integer anno) {
		super();
		this.importo = importo;
		this.anno = anno;
	}

	public BigDecimal getImporto() {
		return importo;
	}

	public void setImporto(BigDecimal importo) {
		this.importo = importo;
	}

	public Integer getAnno() {
		return anno;
	}

	public void setAnno(Integer anno) {
		this.anno = anno;
	}

}
