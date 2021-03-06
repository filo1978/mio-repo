package it.spaziowiki.fatturazione.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.spaziowiki.fatturazione.entities.CMese;

public interface CMeseRepository extends JpaRepository<CMese, String>{

	List<CMese> findAllByOrderByCodAsc();
	
}
