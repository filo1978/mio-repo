package it.spaziowiki.fatturazione.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;


/**
 * The persistent class for the attivita database table.
 * 
 */
@Entity
@NamedQuery(name="Attivita.findAll", query="SELECT a FROM Attivita a")
public class Attivita implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_attivita")
	private Integer idAttivita;

	private String descrizione;
	
	@Column(name="importo_netto")
	private BigDecimal importoNetto;

	//bi-directional many-to-one association to Fattura
	@ManyToOne
	@JoinColumn(name="id_fattura")
	private Fattura fattura;

	public Attivita() {
	}

	public Integer getIdAttivita() {
		return this.idAttivita;
	}

	public void setIdAttivita(Integer idAttivita) {
		this.idAttivita = idAttivita;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Fattura getFattura() {
		return this.fattura;
	}

	public void setFattura(Fattura fattura) {
		this.fattura = fattura;
	}

	public BigDecimal getImportoNetto() {
		return importoNetto;
	}

	public void setImportoNetto(BigDecimal importoNetto) {
		this.importoNetto = importoNetto;
	}
	

}