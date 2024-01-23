package com.codeinfini.facturation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.codeinfini.facturation.model.Produit;

public interface ProduitService {

	public List<Produit> listProduit(String key);
	public List<Produit> produits();
	public void ajouter(Produit produit);
	public Optional<Produit> getById(long id);
	public void supprimer(Produit produit);
	public Page<Produit> findPaginated(String keyword, int pageNumber, int pageSize);
}
