package it.spaziowiki.fatturazione.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cliente database table.
 * 
 */
@Entity
@NamedQuery(name="Cliente.findAll", query="SELECT c FROM Cliente c")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_cliente")
	private Integer idCliente;

	private String cap;

	private String denominazione;
	
	private String nickname;

	private String email;

	private String indirizzo;

	private String piva;

	private String telefono;

	//bi-directional many-to-one association to CComune
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="cod_com", referencedColumnName="cod_com"),
		@JoinColumn(name="cod_prov", referencedColumnName="cod_prov")
		})
	private CComune CComune;

	//bi-directional many-to-one association to Fattura
	@OneToMany(mappedBy="cliente")
	private List<Fattura> fatturas;

	public Cliente() {
	}

	public Integer getIdCliente() {
		return this.idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getCap() {
		return this.cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getDenominazione() {
		return this.denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIndirizzo() {
		return this.indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getPiva() {
		return this.piva;
	}

	public void setPiva(String piva) {
		this.piva = piva;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public CComune getCComune() {
		return this.CComune;
	}

	public void setCComune(CComune CComune) {
		this.CComune = CComune;
	}

	public List<Fattura> getFatturas() {
		return this.fatturas;
	}

	public void setFatturas(List<Fattura> fatturas) {
		this.fatturas = fatturas;
	}

	public Fattura addFattura(Fattura fattura) {
		getFatturas().add(fattura);
		fattura.setCliente(this);

		return fattura;
	}

	public Fattura removeFattura(Fattura fattura) {
		getFatturas().remove(fattura);
		fattura.setCliente(null);

		return fattura;
	}

}