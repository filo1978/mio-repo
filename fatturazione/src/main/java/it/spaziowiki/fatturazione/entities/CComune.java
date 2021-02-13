package it.spaziowiki.fatturazione.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the c_comune database table.
 * 
 */
@Entity
@Table(name="c_comune")
@NamedQuery(name="CComune.findAll", query="SELECT c FROM CComune c")
public class CComune implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CComunePK id;

	private String descrizione;

	//bi-directional many-to-one association to CProvincia
	@ManyToOne
	@JoinColumn(name="cod_prov", insertable=false, updatable=false)
	private CProvincia CProvincia;

	//bi-directional many-to-one association to Cliente
	@OneToMany(mappedBy="CComune")
	private List<Cliente> clientes;

	public CComune() {
	}

	public CComunePK getId() {
		return this.id;
	}

	public void setId(CComunePK id) {
		this.id = id;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public CProvincia getCProvincia() {
		return this.CProvincia;
	}

	public void setCProvincia(CProvincia CProvincia) {
		this.CProvincia = CProvincia;
	}

	public List<Cliente> getClientes() {
		return this.clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Cliente addCliente(Cliente cliente) {
		getClientes().add(cliente);
		cliente.setCComune(this);

		return cliente;
	}

	public Cliente removeCliente(Cliente cliente) {
		getClientes().remove(cliente);
		cliente.setCComune(null);

		return cliente;
	}

}