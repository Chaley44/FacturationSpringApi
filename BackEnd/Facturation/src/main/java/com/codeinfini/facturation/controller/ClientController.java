package com.codeinfini.facturation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codeinfini.facturation.model.Client;
import com.codeinfini.facturation.service.ClientService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/clients")
public class ClientController {

	@Autowired
	ClientService service;
	
	@GetMapping("/liste")
	public String lister(@Param(value = "keyword") String keyword, Model model) {
		
		model.addAttribute("clients", service.listClient(keyword));
		return "vueClient/liste_client";
	}
	@GetMapping("/clear")
	public String clear() {
		
		
		return "redirect:/clients/liste";
	}
	
	@GetMapping("/ajout")
	public String add(Client client) {
		return "vueClient/ajout_client";
	}
	
	@PostMapping("/save")
	public String save(@Valid Client client, BindingResult result) 
	{
		if(result.hasErrors()) {
			return "vueClient/ajout_client";
		}
		service.ajouter(client);
		return "redirect:liste";
		
	}
	
	@GetMapping("/modifier/{id}")
	public String modifier(@PathVariable("id") long id, Model model) {
		Client client = service.getById(id).get();
		if(client.equals(null)) {
			throw new IllegalArgumentException("Le client n'existe pas");
			
		}
		model.addAttribute("client", client);
		return "vueClient/modifier_client";
	}
	@PostMapping("/update/{id}")
	public String update(@PathVariable("id") Long id, @Valid Client client,
			BindingResult result) 
	{
		if(result.hasErrors()) {
			return "vueClient/modifier_client";
		}
		service.ajouter(client);
		return "redirect:/clients/liste";
		
	}
	@GetMapping("supprimer/{id}")
	public String supprimer(@PathVariable("id") Long id) {
		Client client = service.getById(id).get();
		service.supprimer(client);
		
		return "redirect:/clients/liste";
	}
	
}
