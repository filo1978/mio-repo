package it.spaziowiki.fatturazione.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the c_comune database table.
 * 
 */
@Embeddable
public class CComunePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="cod_com")
	private String codCom;

	@Column(name="cod_prov", insertable=false, updatable=false)
	private String codProv;

	public CComunePK() {
	}
	public String getCodCom() {
		return this.codCom;
	}
	public void setCodCom(String codCom) {
		this.codCom = codCom;
	}
	public String getCodProv() {
		return this.codProv;
	}
	public void setCodProv(String codProv) {
		this.codProv = codProv;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CComunePK)) {
			return false;
		}
		CComunePK castOther = (CComunePK)other;
		return 
			this.codCom.equals(castOther.codCom)
			&& this.codProv.equals(castOther.codProv);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codCom.hashCode();
		hash = hash * prime + this.codProv.hashCode();
		
		return hash;
	}
}