package it.spaziowiki.fatturazione.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.spaziowiki.fatturazione.entities.Fattura;
import it.spaziowiki.fatturazione.entities.TipoFattura;

@Repository
public interface FatturaRepository extends JpaRepository<Fattura, Integer> {

	List<Fattura> findByClienteIdClienteAndTipoFattura(Integer idCliente, TipoFattura tipoFattura);
	
	List<Fattura> findByTipoFattura(TipoFattura tipoFattura);
	
	@Query("SELECT max(f.idFattura) FROM Fattura f")
	Integer getMaxIdFattura();
	
	@Query("SELECT max(f.numeroFattura) FROM Fattura f where year(f.dtFattura)= :anno")
	String getMaxNumFatturaAnno(@Param("anno") Integer anno);
	
	Fattura findByIdBollo(String idBollo);
	
	@Query("SELECT max(f.dtFattura) FROM Fattura f where f.dtFattura!=null")
	Date getMaxDataFattura();
	
	@Query(value = "select min(dt_fattura) from fattura where tipo='FT001' and id_fattura!=:idFattura and dt_fattura<=( select dt_fattura from fattura where id_fattura=:idFattura )", nativeQuery = true)
	Date getMinDataFatturaUpdate(@Param("idFattura") Integer idFattura);
	
	@Query(value = "select max(dt_fattura) from fattura where tipo='FT001' and id_fattura!=:idFattura and dt_fattura>=( select dt_fattura from fattura where id_fattura=:idFattura )", nativeQuery = true)
	Date getMaxDataFatturaUpdate(@Param("idFattura") Integer idFattura);

	@Query(value = "SELECT year(dt_fattura) as anno_fattura,  "+
					"sum(importo_netto) as importo_netto_fatturato, "+
					"sum((importo_netto*(1+iva/100))) as importo_lordo_fatturato, "+
			        "COALESCE( (select sum(f1.importo_netto) as importo_netto  "+
					"	from  fattura f1  "+
			        "    where year(f1.dt_fattura)=year(f.dt_fattura) and f.tipo=f1.tipo and (f1.stato is null or f1.stato='ST02')),0) as importo_netto_non_pagato, "+
			        "COALESCE((select sum((f1.importo_netto*(1+f1.iva/100)))  "+
					"	from  fattura f1  "+
			        "    where year(f1.dt_fattura)=year(f.dt_fattura) and f.tipo=f1.tipo and (f1.stato is null or f1.stato='ST02')),0) as importo_lordo_non_pagato, "+
			        "COALESCE( (select sum(f1.importo_netto) as importo_netto  "+
					"	from  fattura f1  "+
			        "    where year(f1.dt_fattura)=year(f.dt_fattura) and f.tipo=f1.tipo and f1.stato='ST03'),0) as importo_netto_annullato, "+
			        "COALESCE((select sum((f1.importo_netto*(1+f1.iva/100)))  "+
					"	from  fattura f1  "+
			        "    where year(f1.dt_fattura)=year(f.dt_fattura) and f.tipo=f1.tipo and f1.stato='ST03'),0) as importo_lordo_annullato "+
			"FROM fattura f "+
			"where tipo='FT001' and stato!='ST03'"+
			"group by anno_fattura "+
			"order by anno_fattura desc", nativeQuery = true)
	List<Object[]> getTotaleFattoreProjection();
	
	@Query(value = "SELECT sum(importo_netto) as importo, month(dt_fattura) as mese "
			+ "FROM fattura "
			+ "where stato='ST01' and year(dt_fattura)= :anno and tipo= :tipo "
			+ "group by month(dt_fattura)"
			+ "order by  mese asc ", nativeQuery = true)
	List<Object[]> getFattureAnnoMeseProjection(@Param("anno") Integer anno,@Param("tipo") String tipo);
	
	@Query(value = "select sum(f.importo_netto) as importo, c.denominazione "
			+ "from fattura f, cliente c "
			+ "where c.id_cliente=f.id_cliente and year(f.dt_fattura)= :anno and f.stato='ST01' and tipo= :tipo "
			+ "group by c.denominazione "
			+ "order by importo asc ", nativeQuery = true)
	List<Object[]> getFattureAnnoClienteProjection(@Param("anno") Integer anno,@Param("tipo") String tipo);
	
	@Query(value = "select sum(importo_netto) as importo, year(dt_fattura) as anno "
			+ "from fattura "
			+ "where stato='ST01' and tipo= :tipo "
			+ "group by anno "
			+ "order by anno asc ", nativeQuery = true)
	List<Object[]> getTotaleFattureAnnoProjection(@Param("tipo") String tipo);
	
	
	@Query(value = "select year(dt_fattura) as anno "
			+ "from fattura "
			+ "where stato='ST01' and tipo= :tipo "
			+ "group by anno "
			+ "order by anno desc ", nativeQuery = true)
	List<Integer> getAllAnnoFatture(@Param("tipo") String tipo);
}
