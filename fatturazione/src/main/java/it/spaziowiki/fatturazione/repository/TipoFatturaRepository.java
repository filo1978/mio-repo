package it.spaziowiki.fatturazione.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.spaziowiki.fatturazione.entities.TipoFattura;

public interface TipoFatturaRepository extends JpaRepository<TipoFattura, String> {

	List<TipoFattura> findAllByOrderByDescrizioneAsc();
	
	
	@Query("SELECT f FROM TipoFattura f where cod in ('FT001','FT002') ")
	List<TipoFattura> findBozzaFatturaByOrderByDescrizioneAsc();
}
