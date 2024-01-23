package com.codeinfini.facturation.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeinfini.facturation.model.Facture;
import com.codeinfini.facturation.model.LigneFacture;
import com.codeinfini.facturation.model.Produit;
import com.codeinfini.facturation.service.FactureService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/factures")
@CrossOrigin("http://localhost:4200/")
public class FactureApiController {

	@Autowired
	FactureService service;
	
	@GetMapping("/liste")
	public List<Facture> lister(){
		return service.listFacture();
	}
	
	@PostMapping("ajout/ligneFacture")
	public void ajoutLigneFacture(@RequestBody Facture facture, 
			@Valid Produit produit,
			@Param( value = "quantite")int quantite) 
	{
		
		service.ajouterLigneFacture(facture, produit, quantite);
	}
	
	@GetMapping("/{id}")
	public Optional<Facture> search(@PathVariable("id") long id) {
		
		return service.getById(id);
	}
	
	@PostMapping("/save")
	public void save(@RequestBody Facture facture) {
		
		System.out.print(facture.getLigneFactures());
		service.ajouter(facture);
	}
	
	@PutMapping("/update/{id}")
	public void update(@PathVariable("id") Long id, @RequestBody Facture facture) 
	{
		Facture factureUpdate = service.getById(id).get();
		factureUpdate = facture;
		service.ajouter(factureUpdate);
	}
	
	@DeleteMapping("supprimer/{id}")
	public void supprimer(@PathVariable("id") Long id) {
		Facture facture = service.getById(id).get();
		service.supprimer(facture);
		
	}
	
	@GetMapping("/ligneFacture/{id}")
	public List<LigneFacture> listerLigneFacture(@PathVariable("id") long id){
		Facture facture = service.getById(id).get();
		return service.getByFacture(facture);
	}
}
