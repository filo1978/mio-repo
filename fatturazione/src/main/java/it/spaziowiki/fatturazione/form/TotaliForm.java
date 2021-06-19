package it.spaziowiki.fatturazione.form;

import java.math.BigDecimal;

public class TotaliForm extends AbstractForm {

	private String tipo;
	private String cliente;
	private String data;
	private BigDecimal importo;
	private Integer idFattura;

	public TotaliForm(String tipo, String cliente, String data, BigDecimal importo, Integer idFattura) {
		super();
		this.tipo = tipo;
		this.cliente = cliente;
		this.data = data;
		this.importo = importo;
		this.idFattura = idFattura;
	}

	public String getTipo() {
		return tipo;
	}

	public String getCliente() {
		return cliente;
	}

	public String getData() {
		return data;
	}

	public BigDecimal getImporto() {
		return importo;
	}

	public Integer getIdFattura() {
		return idFattura;
	}

}
