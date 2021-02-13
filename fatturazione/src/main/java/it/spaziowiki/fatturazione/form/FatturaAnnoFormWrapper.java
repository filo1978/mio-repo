package it.spaziowiki.fatturazione.form;

import java.math.BigDecimal;
import java.util.List;

public class FatturaAnnoFormWrapper {

	private List<FatturaAnnoForm> lista;
	private BigDecimal importoMassimo;

	public FatturaAnnoFormWrapper(List<FatturaAnnoForm> lista, BigDecimal importoMassimo) {
		super();
		this.lista = lista;
		this.importoMassimo = importoMassimo;
	}

	public List<FatturaAnnoForm> getLista() {
		return lista;
	}

	public void setLista(List<FatturaAnnoForm> lista) {
		this.lista = lista;
	}

	public BigDecimal getImportoMassimo() {
		return importoMassimo;
	}

	public void setImportoMassimo(BigDecimal importoMassimo) {
		this.importoMassimo = importoMassimo;
	}

}
