package com.codeinfini.facturation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeinfini.facturation.model.Client;
import com.codeinfini.facturation.repository.ClientRepository;

@Service
public class ClientServiceImp implements ClientService {

	@Autowired
	ClientRepository repo;
	
	@Override
	public List<Client> listClient(String key) {
		if(key != null) {
			return repo.findByKeyword(key);
		}
		return repo.findAll();
	}

	@Override
	public void ajouter(Client client) {
		repo.save(client);

	}

	@Override
	public Optional<Client> getById(long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public void supprimer(Client client) {
		repo.delete(client);

	}

	@Override
	public List<Client> clients() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

}
