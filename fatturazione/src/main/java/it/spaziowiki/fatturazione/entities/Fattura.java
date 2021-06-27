package it.spaziowiki.fatturazione.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the fattura database table.
 * 
 */
@Entity
@NamedQuery(name="Fattura.findAll", query="SELECT f FROM Fattura f")
public class Fattura implements Serializable , Cloneable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_fattura")
	private Integer idFattura;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dt_fattura")
	private Date dtFattura;

	@Column(name="id_bollo")
	private String idBollo;

	@Column(name="importo_netto")
	private BigDecimal importoNetto;

	private BigDecimal iva;

	@Column(name="numero_fattura")
	private String numeroFattura;

	private String oggetto;

	//bi-directional many-to-one association to Attivita
	@OneToMany(mappedBy="fattura")
	private List<Attivita> attivitas;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="id_cliente")
	private Cliente cliente;

	//bi-directional many-to-one association to TipoFattura
	@ManyToOne
	@JoinColumn(name="tipo")
	private TipoFattura tipoFattura;
	
	//bi-directional many-to-one association to StatoFattura
	@ManyToOne
	@JoinColumn(name="stato")
	private StatoFattura statoFattura;
	
	//bi-directional many-to-one association to CMese
	@ManyToOne
	@JoinColumn(name="cod_mese")
	private CMese mese;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_pagamento")
	private Date dataPagamento;
	
	@Column(name="dati_pagamento")
	private String datiPagamento;

	public Fattura() {
	}

	public Integer getIdFattura() {
		return this.idFattura;
	}

	public void setIdFattura(Integer idFattura) {
		this.idFattura = idFattura;
	}

	public Date getDtFattura() {
		return this.dtFattura;
	}

	public void setDtFattura(Date dtFattura) {
		this.dtFattura = dtFattura;
	}

	public String getIdBollo() {
		return this.idBollo;
	}

	public void setIdBollo(String idBollo) {
		this.idBollo = idBollo;
	}

	public BigDecimal getImportoNetto() {
		return this.importoNetto;
	}

	public void setImportoNetto(BigDecimal importoNetto) {
		this.importoNetto = importoNetto;
	}

	public BigDecimal getIva() {
		return this.iva;
	}

	public void setIva(BigDecimal iva) {
		this.iva = iva;
	}

	public String getNumeroFattura() {
		return this.numeroFattura;
	}

	public void setNumeroFattura(String numeroFattura) {
		this.numeroFattura = numeroFattura;
	}

	public String getOggetto() {
		return this.oggetto;
	}

	public void setOggetto(String oggetto) {
		this.oggetto = oggetto;
	}

	public List<Attivita> getAttivitas() {
		return this.attivitas;
	}

	public void setAttivitas(List<Attivita> attivitas) {
		this.attivitas = attivitas;
	}

	public Attivita addAttivita(Attivita attivita) {
		getAttivitas().add(attivita);
		attivita.setFattura(this);

		return attivita;
	}

	public Attivita removeAttivita(Attivita attivita) {
		getAttivitas().remove(attivita);
		attivita.setFattura(null);

		return attivita;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public TipoFattura getTipoFattura() {
		return this.tipoFattura;
	}

	public void setTipoFattura(TipoFattura tipoFattura) {
		this.tipoFattura = tipoFattura;
	}
	
	
	public StatoFattura getStatoFattura() {
		return statoFattura;
	}

	public void setStatoFattura(StatoFattura statoFattura) {
		this.statoFattura = statoFattura;
	}
	
	public CMese getMese() {
		return mese;
	}

	public void setMese(CMese mese) {
		this.mese = mese;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public String getDatiPagamento() {
		return datiPagamento;
	}

	public void setDatiPagamento(String datiPagamento) {
		this.datiPagamento = datiPagamento;
	}

	public Fattura clone() {
		try {
			Fattura f = (Fattura) super.clone();
			f.setDtFattura((Date) dtFattura.clone());
			return f;
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}


}