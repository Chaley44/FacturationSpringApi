package com.codeinfini.facturation.controller;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codeinfini.facturation.model.Produit;
import com.codeinfini.facturation.repository.ProduitRepository;
import com.codeinfini.facturation.service.ProduitService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/produits")
public class ProduitController {
	
	@Autowired
	ProduitService service;
	
	@GetMapping("/liste")
	public String lister(@Param(value = "keyword") String keyword,
			Model model) 
	{
		
        
		model.addAttribute("produits", service.listProduit(keyword));
		model.addAttribute("keyword",keyword);
		return showPaginatedPage(keyword, 1, model);
	}
	@GetMapping("/liste/page/{pageNumber}")
	public String showPaginatedPage(@Param(value = "keyword") String keyword, @PathVariable("pageNumber") int pageNumber, Model model)
	{
		int pageSize=5;
		Page<Produit> page = service.findPaginated(keyword, pageNumber,pageSize);
		List<Produit>  produitList = page.getContent();

		model.addAttribute("pageCourente", pageNumber);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("produits", produitList);
		model.addAttribute("page", page);

	    return "vueProduit/liste_produit";
	  }
	
	@GetMapping("/clear")
	public String clear() {
		
		
		return "redirect:/produits/liste";
	}
	
	@GetMapping("/ajout")
	public String add(Produit produit) {
		return "vueProduit/ajout_produit";
	}
	
	@PostMapping("/save")
	public String save(@Valid Produit produit, BindingResult result) 
	{
		if(result.hasErrors()) {
			return "vueProduit/ajout_produit";
		}
		service.ajouter(produit);
		return "redirect:liste";
		
	}
	
	@GetMapping("/modifier/{id}")
	public String modifier(@PathVariable("id") long id, Model model) {
		Produit produit = service.getById(id).get();
		if(produit.equals(null)) {
			throw new IllegalArgumentException("Le produit n'existe pas");
			
		}
		model.addAttribute("produit", produit);
		return "vueProduit/modifier_produit";
	}
	@PostMapping("/update/{id}")
	public String update(@PathVariable("id") Long id, @Valid Produit produit,
			BindingResult result) 
	{
		if(result.hasErrors()) {
			return "vueProduit/modifier_produit";
		}
		service.ajouter(produit);
		return "redirect:/produits/liste";
		
	}
	@GetMapping("supprimer/{id}")
	public String supprimer(@PathVariable("id") Long id) {
		Produit produit = service.getById(id).get();
		service.supprimer(produit);
		
		return "redirect:/produits/liste";
	}
}
