package com.codeinfini.facturation.service;

import java.util.List;
import java.util.Optional;

import com.codeinfini.facturation.model.Client;

public interface ClientService {

	public List<Client> listClient(String key);
	public List<Client> clients();
	public void ajouter(Client client);
	public Optional<Client> getById(long id);
	public void supprimer(Client client);
}
