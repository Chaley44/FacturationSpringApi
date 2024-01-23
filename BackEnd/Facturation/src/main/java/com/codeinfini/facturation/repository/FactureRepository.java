package com.codeinfini.facturation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.codeinfini.facturation.model.Facture;

@Repository
public interface FactureRepository extends JpaRepository<Facture, Long> {

//	@Query(value ="select * from Facture f where f.nom like %:keyword%",
//			nativeQuery = true)
//	List<Facture> findByKeyword(String key);
}
