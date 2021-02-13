package it.spaziowiki.fatturazione.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.spaziowiki.fatturazione.entities.Attivita;

public interface AttivitaRepository extends JpaRepository<Attivita, Integer>{

	List<Attivita> findByFatturaIdFattura(Integer idFattura);
	
	@Query("SELECT max(f.idAttivita) FROM Attivita f")
	Integer getMaxIdAttivita();
}
