package com.codeinfini.facturation.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codeinfini.facturation.model.Facture;
import com.codeinfini.facturation.model.LigneFacture;
import com.codeinfini.facturation.model.Produit;
import com.codeinfini.facturation.service.ClientService;
import com.codeinfini.facturation.service.FactureService;
import com.codeinfini.facturation.service.ProduitService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/factures")
public class FactureController {

	@Autowired
	FactureService service;
	@Autowired
	ProduitService serviceProd;
	@Autowired
	ClientService serviceClient;
	
	
	
	@GetMapping("/liste")
	public String lister( Model model) {
		
		model.addAttribute("factures", service.listFacture());
		return "vueFacture/liste_facture";
	}
	
	@GetMapping("/ajout")
	public String add(Facture facture,Produit produit,
			@Param( value = "quantite")Integer quantite,
			BindingResult result, Model model) {
		
		
		model.addAttribute("quantite", quantite);
		model.addAttribute("produit", produit);
		model.addAttribute("ligneFactures", facture.getLigneFactures());
		model.addAttribute("clients", serviceClient.clients());
		model.addAttribute("produits", serviceProd.produits());
		
		return "vueFacture/ajout_facture";
	}
	
	@PostMapping("ajout/ligneFacture")
	public String ajoutLigneFacture(Facture facture, 
			@Valid Produit produit,
			@Param( value = "quantite")int quantite,
			BindingResult result , Model model) 
	{
		
		service.ajouterLigneFacture(facture, produit, quantite);
		model.addAttribute("ligneFactures", facture.getLigneFactures());
		return "redirect:/ajout";
	}
	
	@PostMapping("/save")
	public String save(@Valid Facture facture,
			@Valid Produit produit,
			@Param( value = "quantite")int quantite,
			BindingResult result) 
	{
		if(result.hasErrors()) {
			return "vueFacture/ajout_facture";
		}
		
		
		facture.setDateFacture(LocalDate.now());
		service.ajouter(facture);
		return "redirect:liste";
		
	}
	@GetMapping("/details/{id}")
	public String detail(@PathVariable("id") Long id, Model model) {
		Facture facture = service.getById(id).get();
		
		model.addAttribute("facture", facture);
		return "/vueFacture/details_facture";
		
	}
	
//	@GetMapping("/modifier/{id}")
//	public String modifier(@PathVariable("id") long id, Model model) {
//		Facture facture = service.getById(id).get();
//		if(facture.equals(null)) {
//			throw new IllegalArgumentException("La facture n'existe pas");
//			
//		}
//		model.addAttribute("facture", facture);
//		return "vueFacture/modifier_facture";
//	}
//	@PostMapping("/update/{id}")
//	public String update(@PathVariable("id") Long id, @Valid Facture facture,
//			BindingResult result) 
//	{
//		if(result.hasErrors()) {
//			return "vueFacture/modifier_facture";
//		}
//		service.ajouter(facture);
//		return "redirect:/factures/liste";
//		
//	}
	@GetMapping("supprimer/{id}")
	public String supprimer(@PathVariable("id") Long id) {
		Facture facture = service.getById(id).get();
		service.supprimer(facture);
		
		return "redirect:/factures/liste";
	}
}
