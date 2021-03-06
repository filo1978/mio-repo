package it.spaziowiki.fatturazione.form;

import javax.validation.constraints.NotBlank;

public class ClienteForm extends AbstractForm{

	private Integer idCliente;
	@NotBlank(message="{campo.obbligatorio}")
	private String cap;
	@NotBlank(message="{campo.obbligatorio}")
	private String denominazione;
	@NotBlank(message="{campo.obbligatorio}")
	private String nickname;
	private String email;
	@NotBlank(message="{campo.obbligatorio}")
	private String indirizzo;
	@NotBlank(message="{campo.obbligatorio}")
	private String piva;
	private String telefono;
	@NotBlank(message="{campo.obbligatorio}")
	private String codCom;
	@NotBlank(message="{campo.obbligatorio}")
	private String codProv;
	private String decrComune;
	private String siglaProv;
	private int numeroFatturePagare=0;
	private int numeroFattureDaPagare=0;
	
	
	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getDenominazione() {
		return denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getPiva() {
		return piva;
	}

	public void setPiva(String piva) {
		this.piva = piva;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCodCom() {
		return codCom;
	}

	public void setCodCom(String codCom) {
		this.codCom = codCom;
	}

	public String getCodProv() {
		return codProv;
	}

	public void setCodProv(String codProv) {
		this.codProv = codProv;
	}

	public String getDecrComune() {
		return decrComune;
	}

	public void setDecrComune(String decrComune) {
		this.decrComune = decrComune;
	}

	public String getSiglaProv() {
		return siglaProv;
	}

	public void setSiglaProv(String siglaProv) {
		this.siglaProv = siglaProv;
	}

	public int getNumeroFatturePagare() {
		return numeroFatturePagare;
	}

	public void setNumeroFatturePagare(int numeroFatturePagare) {
		this.numeroFatturePagare = numeroFatturePagare;
	}

	public int getNumeroFattureDaPagare() {
		return numeroFattureDaPagare;
	}

	public void setNumeroFattureDaPagare(int numeroFattureDaPagare) {
		this.numeroFattureDaPagare = numeroFattureDaPagare;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

}
