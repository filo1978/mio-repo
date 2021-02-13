package it.spaziowiki.fatturazione.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipo_fattura database table.
 * 
 */
@Entity
@Table(name="stato_fattura")
@NamedQuery(name="StatoFattura.findAll", query="SELECT s FROM StatoFattura s")
public class StatoFattura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String cod;

	private String descrizione;

	//bi-directional many-to-one association to Fattura
	@OneToMany(mappedBy="statoFattura")
	private List<Fattura> fatturas;

	public StatoFattura() {
	}

	public String getCod() {
		return this.cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public List<Fattura> getFatturas() {
		return this.fatturas;
	}

	public void setFatturas(List<Fattura> fatturas) {
		this.fatturas = fatturas;
	}

	
}