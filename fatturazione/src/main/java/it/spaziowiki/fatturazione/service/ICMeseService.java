package it.spaziowiki.fatturazione.service;

import java.util.List;

import it.spaziowiki.fatturazione.form.PairDto;

public interface ICMeseService {

	List<PairDto> findAll();
}
