package com.codeinfini.facturation.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.codeinfini.facturation.model.Produit;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {
	@Query(value ="select * from Produit p where p.nom like %:keyword% or p.description like %:keyword%",
			nativeQuery = true)
	List<Produit> findByKeyword(String keyword);
	Page<Produit> findAll(Pageable pageable);
	static Page<Produit> findByTitleContainingIgnoreCase(String keyword, Pageable paging) {
		// TODO Auto-generated method stub
		return null;
	}
}
