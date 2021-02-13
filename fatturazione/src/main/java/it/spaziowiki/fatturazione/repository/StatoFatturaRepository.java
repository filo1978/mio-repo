package it.spaziowiki.fatturazione.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.spaziowiki.fatturazione.entities.StatoFattura;

@Repository
public interface StatoFatturaRepository extends JpaRepository<StatoFattura, String> {

	List<StatoFattura> findAllByOrderByDescrizioneAsc();
}
