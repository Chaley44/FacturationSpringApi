package com.codeinfini.facturation.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeinfini.facturation.model.Produit;
import com.codeinfini.facturation.service.ProduitService;

@RestController
@RequestMapping("/api/produits")
@CrossOrigin("http://localhost:4200/")
public class ProduitApiController {

	@Autowired
	ProduitService service;
	
	@GetMapping("/liste")
	public List<Produit> liste() {
		
		return service.produits();
	}
	
	
	@PostMapping("/save")
	public void save(@RequestBody Produit produit) 
	{
		service.ajouter(produit);
	}
	
	@GetMapping("/{id}")
	public Optional<Produit> rechercher(@PathVariable("id") long id){
		
		return service.getById(id);
	}
	
	@PutMapping("/update/{id}")
	public void update(@PathVariable("id") Long id, @RequestBody Produit produit) 
	{
		Produit produitUpdate = service.getById(id).get();
		produitUpdate = produit;
		service.ajouter(produitUpdate);
	}
	
	@DeleteMapping("supprimer/{id}")
	public void supprimer(@PathVariable("id") Long id) {
		Produit produit = service.getById(id).get();
		service.supprimer(produit);
		
	}
}
