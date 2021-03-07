package it.spaziowiki.fatturazione.service;

import java.util.List;

import it.spaziowiki.fatturazione.exception.AttivitaSaveException;
import it.spaziowiki.fatturazione.form.AttivitaForm;

public interface IAttivitaService {

	List<AttivitaForm> findAttivitaByFattura(Integer idFattura);

	AttivitaForm getEmptyAttivita(Integer idFattura);
	
	AttivitaForm getDettaglioAttivita(Integer idAttivita);

	AttivitaForm insert(AttivitaForm attivitaForm) throws AttivitaSaveException;

	AttivitaForm update(AttivitaForm attivitaForm) throws AttivitaSaveException;
	
	AttivitaForm delete(AttivitaForm attivitaForm) ;
}
