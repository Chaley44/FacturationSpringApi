package com.codeinfini.facturation.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeinfini.facturation.model.Facture;
import com.codeinfini.facturation.model.LigneFacture;
import com.codeinfini.facturation.model.Produit;
import com.codeinfini.facturation.repository.FactureRepository;
import com.codeinfini.facturation.repository.LigneFactureRepository;
@Service
public class FactureServiceImp implements FactureService {

	@Autowired
	FactureRepository repo;
	@Autowired
	LigneFactureRepository repoLigne;
	
	@Override
	public List<Facture> listFacture() {
		return repo.findAll();
	}

	@Override
	public void ajouter(Facture facture) {

		
		for(LigneFacture lf: facture.getLigneFactures()) {
			lf.setFacture(facture);
			repoLigne.save(lf);
		}
		facture.setDateFacture(LocalDate.now());
//		facture.getMontantTotalHt();
//		facture.getTotalTva();
//		facture.getMontantTTC();
		repo.save(facture);

	}

	@Override
	public Optional<Facture> getById(long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public void supprimer(Facture facture) {
		repo.delete(facture);

	}

	@Override
	public void ajouterLigneFacture(Facture facture, Produit produit, int quantite) {
//		float totalHt;
		for(LigneFacture l : facture.getLigneFactures()) {
			if(l.getProduit() == produit) {
				
				l.setQuantite(l.getQuantite() + quantite) ;
			}else {
				LigneFacture ligneFacture = new LigneFacture();
				ligneFacture.setProduit(produit);
				ligneFacture.setQuantite(quantite);
				
//				totalHt = produit.getPrixUnitaire() * quantite;
//				ligneFacture.setTotalHt(totalHt);
				ligneFacture.setFacture(facture);
				repoLigne.save(ligneFacture);
			}
		}
		
	}

	@Override
	public List<LigneFacture> getByFacture(Facture facture) {
		// TODO Auto-generated method stub
		return repoLigne.getByFacture(facture);
	}

}
