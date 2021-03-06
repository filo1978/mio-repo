package it.spaziowiki.fatturazione.service;

import java.util.List;

import it.spaziowiki.fatturazione.form.PairDto;

public interface ITipoFatturaService {

	List<PairDto> findAllByOrderByDescrizioneAsc();

	List<PairDto> findBozzaFatturaByOrderByDescrizioneAsc();
}
