package it.spaziowiki.fatturazione.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.spaziowiki.fatturazione.entities.CProvincia;

@Repository
public interface CProvinciaRepository extends JpaRepository<CProvincia, String>{

	List<CProvincia> findAllByOrderByDescrizioneAsc();
}
