package com.codeinfini.facturation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.codeinfini.facturation.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

	@Query(value ="select * from Client c where c.nom like %:keyword%",
			nativeQuery = true)
	List<Client> findByKeyword(String key);
}
