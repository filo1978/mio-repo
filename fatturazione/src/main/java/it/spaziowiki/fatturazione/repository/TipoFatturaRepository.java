package it.spaziowiki.fatturazione.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.spaziowiki.fatturazione.entities.TipoFattura;

public interface TipoFatturaRepository extends JpaRepository<TipoFattura, String> {

	List<TipoFattura> findAllByOrderByDescrizioneAsc();
}
