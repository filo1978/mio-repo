package it.spaziowiki.fatturazione.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the c_provincia database table.
 * 
 */
@Entity
@Table(name="c_provincia")
@NamedQuery(name="CProvincia.findAll", query="SELECT c FROM CProvincia c")
public class CProvincia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cod_prov")
	private String codProv;

	private String descrizione;

	private String sigla;

	//bi-directional many-to-one association to CComune
	@OneToMany(mappedBy="CProvincia")
	private List<CComune> CComunes;

	public CProvincia() {
	}

	public String getCodProv() {
		return this.codProv;
	}

	public void setCodProv(String codProv) {
		this.codProv = codProv;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getSigla() {
		return this.sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public List<CComune> getCComunes() {
		return this.CComunes;
	}

	public void setCComunes(List<CComune> CComunes) {
		this.CComunes = CComunes;
	}

	public CComune addCComune(CComune CComune) {
		getCComunes().add(CComune);
		CComune.setCProvincia(this);

		return CComune;
	}

	public CComune removeCComune(CComune CComune) {
		getCComunes().remove(CComune);
		CComune.setCProvincia(null);

		return CComune;
	}

}