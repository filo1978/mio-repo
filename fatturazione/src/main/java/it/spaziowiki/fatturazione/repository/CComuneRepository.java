package it.spaziowiki.fatturazione.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.spaziowiki.fatturazione.entities.CComune;
import it.spaziowiki.fatturazione.entities.CComunePK;

@Repository
public interface CComuneRepository extends JpaRepository<CComune, CComunePK> {

	List<CComune> findByIdCodProvOrderByDescrizioneAsc(String codProv);
}
