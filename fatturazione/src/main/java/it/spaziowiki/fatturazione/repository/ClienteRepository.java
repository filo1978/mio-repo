package it.spaziowiki.fatturazione.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.spaziowiki.fatturazione.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

	@Query("SELECT max(c.idCliente) FROM Cliente c")
	Integer getMaxIdCliente();
	
	@Query("SELECT count(c.idCliente) FROM Cliente c")
	Integer getNumClienti();
	
	Page<Cliente> findAll(Pageable pageable);
	
	List<Cliente> findByPiva(String piva);
	
	List<Cliente>  findAllByOrderByDenominazioneAsc();

	
}
