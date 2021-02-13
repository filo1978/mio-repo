package it.spaziowiki.fatturazione.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import it.spaziowiki.fatturazione.entities.Attivita;
import it.spaziowiki.fatturazione.exception.AttivitaSaveException;
import it.spaziowiki.fatturazione.form.AttivitaForm;
import it.spaziowiki.fatturazione.form.factory.AttivitaFormFactory;
import it.spaziowiki.fatturazione.repository.AttivitaRepository;
import it.spaziowiki.fatturazione.repository.FatturaRepository;
import it.spaziowiki.fatturazione.service.IAttivitaService;

@Service
@Transactional
public class AttivitaService implements IAttivitaService {
	
	@Autowired
	private AttivitaRepository attivitaRepository;
	
	@Autowired
	private AttivitaFormFactory attivitaFormFactory;
	
	@Autowired
	private FatturaRepository fatturaRepository;

	@Override
	public List<AttivitaForm> findAttivitaByFattura(Integer idFattura) {
		List<Attivita>l=attivitaRepository.findByFatturaIdFattura(idFattura);
		return attivitaFormFactory.getList(l);
	}

	@Override
	public AttivitaForm getEmptyAttivita(Integer idFattura) {
		AttivitaForm attivitaForm = new AttivitaForm();
		attivitaForm.setIdFattura(idFattura);
		attivitaForm.setIdAttivita(new Integer(-1));
		return attivitaForm;
	}

	@Override
	public AttivitaForm insert(AttivitaForm attivitaForm) throws AttivitaSaveException{
		checkAttivita(attivitaForm);
		Attivita attivita = new Attivita();
		attivita.setDescrizione(attivitaForm.getDescrizione());
		attivita.setFattura(fatturaRepository.findById(attivitaForm.getIdFattura()).get());
		attivita.setIdAttivita(getIdAttivita());
		attivita.setImportoNetto(attivitaForm.getImportoNettoAttivita());
		attivitaRepository.save(attivita);
		attivitaForm= attivitaFormFactory.getForm(attivita);
		return attivitaForm;
	}
	
	@Override
	public AttivitaForm update(AttivitaForm attivitaForm) throws AttivitaSaveException{
		checkAttivita(attivitaForm);
		Attivita attivitaDb=attivitaRepository.findById(attivitaForm.getIdAttivita()).get();
		attivitaDb.setDescrizione(attivitaForm.getDescrizione());
		attivitaDb.setImportoNetto(attivitaForm.getImportoNettoAttivita());
		attivitaRepository.save(attivitaDb);
		attivitaForm= attivitaFormFactory.getForm(attivitaDb);
		return attivitaForm;
	}
	
	
	private void checkAttivita(AttivitaForm attivitaForm) throws AttivitaSaveException{
		if(!StringUtils.hasText(attivitaForm.getDescrizione()))
			throw new AttivitaSaveException("Il campo descrizione è obbligatorio");
		
	}

	private Integer getIdAttivita() {
		Integer idAttivita=attivitaRepository.getMaxIdAttivita();
		if(idAttivita==null){
			idAttivita=1;
		}else{
			idAttivita++;
		}
		return idAttivita;
	}

	@Override
	public AttivitaForm getDettaglioAttivita(Integer idAttivita) {
		return attivitaFormFactory.getForm(attivitaRepository.findById(idAttivita).get());
	}

	@Override
	public void delete(AttivitaForm attivitaForm) {
		Attivita attivitaDb=attivitaRepository.findById(attivitaForm.getIdAttivita()).get();
		attivitaRepository.delete(attivitaDb);
	}

}
