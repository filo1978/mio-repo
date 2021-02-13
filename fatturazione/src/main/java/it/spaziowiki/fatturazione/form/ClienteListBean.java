package it.spaziowiki.fatturazione.form;

import java.util.List;

public class ClienteListBean {

	private long numeroTotaleElementi;
	private long numeroTotalePagina;
	private List<ClienteForm> listaClieti;

	public ClienteListBean(long numeroTotaleElementi, long numeroTotalePagina, List<ClienteForm> listaClieti) {
		super();
		this.numeroTotaleElementi = numeroTotaleElementi;
		this.numeroTotalePagina = numeroTotalePagina;
		this.listaClieti = listaClieti;
	}

	public long getNumeroTotaleElementi() {
		return numeroTotaleElementi;
	}

	public void setNumeroTotaleElementi(long numeroTotaleElementi) {
		this.numeroTotaleElementi = numeroTotaleElementi;
	}

	public long getNumeroTotalePagina() {
		return numeroTotalePagina;
	}

	public void setNumeroTotalePagina(long numeroTotalePagina) {
		this.numeroTotalePagina = numeroTotalePagina;
	}

	public List<ClienteForm> getListaClieti() {
		return listaClieti;
	}

	public void setListaClieti(List<ClienteForm> listaClieti) {
		this.listaClieti = listaClieti;
	}

}
