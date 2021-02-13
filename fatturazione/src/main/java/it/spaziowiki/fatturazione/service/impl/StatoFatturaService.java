package it.spaziowiki.fatturazione.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.spaziowiki.fatturazione.form.PairDto;
import it.spaziowiki.fatturazione.form.factory.StatoFatturaFormFactory;
import it.spaziowiki.fatturazione.repository.StatoFatturaRepository;
import it.spaziowiki.fatturazione.service.IStatoFatturaService;

@Service
@Transactional
public class StatoFatturaService implements IStatoFatturaService {

	@Autowired
	private StatoFatturaRepository statoFatturaRepository;
	
	@Autowired
	private StatoFatturaFormFactory statoFatturaFormFactory;
	
	@Override
	public List<PairDto> findAllByOrderByDescrizioneAsc() {
		return statoFatturaFormFactory.getList(statoFatturaRepository.findAllByOrderByDescrizioneAsc());
	}

}
