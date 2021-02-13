package it.spaziowiki.fatturazione.form;

import java.util.List;

public class ImportoMeseAnnoForm {

	private Integer anno;
	private List<ImportoMeseForm> lista;

	public Integer getAnno() {
		return anno;
	}

	public void setAnno(Integer anno) {
		this.anno = anno;
	}

	public List<ImportoMeseForm> getLista() {
		return lista;
	}

	public void setLista(List<ImportoMeseForm> lista) {
		this.lista = lista;
	}

}
