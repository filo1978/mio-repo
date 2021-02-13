package it.spaziowiki.fatturazione.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AttivitaForm extends AbstractForm {

	private Integer idFattura;
	private Integer idAttivita;
	private String descrizione;
	private BigDecimal importoNettoAttivita;
	private List<String>errors;
	private String msgOk;

	public Integer getIdFattura() {
		return idFattura;
	}

	public void setIdFattura(Integer idFattura) {
		this.idFattura = idFattura;
	}

	public Integer getIdAttivita() {
		return idAttivita;
	}

	public void setIdAttivita(Integer idAttivita) {
		this.idAttivita = idAttivita;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public BigDecimal getImportoNettoAttivita() {
		return importoNettoAttivita;
	}

	public void setImportoNettoAttivita(BigDecimal importoNettoAttivita) {
		this.importoNettoAttivita = importoNettoAttivita;
	}

	public void addError(String error){
		if(errors==null)
			errors=new ArrayList<String>();
		errors.add(error);
	}
	
	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public String getMsgOk() {
		return msgOk;
	}

	public void setMsgOk(String msgOk) {
		this.msgOk = msgOk;
	}

}
