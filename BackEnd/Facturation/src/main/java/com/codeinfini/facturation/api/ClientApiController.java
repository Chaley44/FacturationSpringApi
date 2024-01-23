package com.codeinfini.facturation.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeinfini.facturation.model.Client;
import com.codeinfini.facturation.service.ClientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/clients")
@CrossOrigin("http://localhost:4200/")
public class ClientApiController {
	
	@Autowired
	ClientService service;
	
	@GetMapping("liste")
	public List<Client> lister(){
		return service.clients();
	}
	
	@PostMapping("save")
	public void save(@RequestBody Client client) {
		service.ajouter(client);
	}
	
	@GetMapping("/{id}")
	public Optional<Client> recherhcer(@PathVariable("id") Long id){
		return service.getById(id);
	}
	
	@PutMapping("/update/{id}")
	public void update(@PathVariable("id") Long id, @RequestBody Client client) 
	{
		Client clientUpdate = service.getById(id).get();
		clientUpdate = client;
		service.ajouter(clientUpdate);		
	}
	
	@GetMapping("supprimer/{id}")
	public void supprimer(@PathVariable("id") Long id) {
		Client client = service.getById(id).get();
		service.supprimer(client);
		
	}

}
