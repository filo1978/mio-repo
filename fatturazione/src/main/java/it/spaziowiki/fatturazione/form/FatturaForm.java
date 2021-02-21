package it.spaziowiki.fatturazione.form;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import it.spaziowiki.fatturazione.enums.TipoFatturaEnum;

public class FatturaForm extends AbstractForm {

	private Integer idFattura;
	@NotNull(message="{campo.obbligatorio}")
	private Integer idCliente;
	private String codTipo;
	private String descrTipo;
	private String numeroFattura;
	private String dtFattura;
	private String idBollo;
	@NotNull(message="{campo.obbligatorio}")
	private BigDecimal importoNetto;
	private BigDecimal importoLordo;
	@NotNull(message="{campo.obbligatorio}")
	private BigDecimal iva;
	@NotBlank(message="{campo.obbligatorio}")
	private String oggetto;
	private String descrStato;
	private String codStato;
	private String denominazioneCliente;
	private Integer anno;
	private String pivaCiente;

	public Integer getIdFattura() {
		return idFattura;
	}

	public void setIdFattura(Integer idFattura) {
		this.idFattura = idFattura;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getCodTipo() {
		return codTipo;
	}

	public void setCodTipo(String codTipo) {
		this.codTipo = codTipo;
	}

	public String getDescrTipo() {
		return descrTipo;
	}

	public void setDescrTipo(String descrTipo) {
		this.descrTipo = descrTipo;
	}

	public String getNumeroFattura() {
		return numeroFattura;
	}

	public void setNumeroFattura(String numeroFattura) {
		this.numeroFattura = numeroFattura;
	}

	public String getDtFattura() {
		return dtFattura;
	}

	public void setDtFattura(String dtFattura) {
		this.dtFattura = dtFattura;
	}

	public String getIdBollo() {
		return idBollo;
	}

	public void setIdBollo(String idBollo) {
		this.idBollo = idBollo;
	}

	public BigDecimal getImportoNetto() {
		return importoNetto;
	}

	public void setImportoNetto(BigDecimal importoNetto) {
		this.importoNetto = importoNetto;
	}

	public BigDecimal getIva() {
		return iva;
	}

	public void setIva(BigDecimal iva) {
		this.iva = iva;
	}

	public String getOggetto() {
		return oggetto;
	}

	public void setOggetto(String oggetto) {
		this.oggetto = oggetto;
	}

	public String getDescrStato() {
		return descrStato;
	}

	public void setDescrStato(String descrStato) {
		this.descrStato = descrStato;
	}

	public String getCodStato() {
		return codStato;
	}

	public void setCodStato(String codStato) {
		this.codStato = codStato;
	}

	public BigDecimal getImportoLordo() {
		return importoLordo;
	}

	public void setImportoLordo(BigDecimal importoLordo) {
		this.importoLordo = importoLordo;
	}

	public String getDenominazioneCliente() {
		return denominazioneCliente;
	}

	public void setDenominazioneCliente(String denominazioneCliente) {
		this.denominazioneCliente = denominazioneCliente;
	}
	
	public boolean isTipoFattura(){
		return codTipo!=null&&codTipo.equals(TipoFatturaEnum.FATTURA.getCod());
	}

	public Integer getAnno() {
		return anno;
	}

	public void setAnno(Integer anno) {
		this.anno = anno;
	}

	public String getPivaCiente() {
		return pivaCiente;
	}

	public void setPivaCiente(String pivaCiente) {
		this.pivaCiente = pivaCiente;
	}

}
