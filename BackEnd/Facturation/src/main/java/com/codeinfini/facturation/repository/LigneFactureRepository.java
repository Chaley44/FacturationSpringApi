package com.codeinfini.facturation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codeinfini.facturation.model.Facture;
import com.codeinfini.facturation.model.LigneFacture;
@Repository
public interface LigneFactureRepository extends JpaRepository<LigneFacture, Long> {

	List<LigneFacture> getByFacture(Facture facture);
}
