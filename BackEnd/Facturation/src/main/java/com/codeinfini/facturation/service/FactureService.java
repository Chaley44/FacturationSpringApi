package com.codeinfini.facturation.service;

import java.util.List;
import java.util.Optional;

import com.codeinfini.facturation.model.Facture;
import com.codeinfini.facturation.model.LigneFacture;
import com.codeinfini.facturation.model.Produit;

public interface FactureService {

	public List<Facture> listFacture();
	public void ajouter(Facture facture);
	public Optional<Facture> getById(long id);
	public void supprimer(Facture facture);
	public List<LigneFacture> getByFacture(Facture facture);
	public void ajouterLigneFacture(Facture facture, Produit produit, int quantite);
}
