package com.example.demo.service;

import java.util.Optional;

import com.example.demo.entity.Client;

public interface ClientService {
	Client saveClient(Client client);

	Optional<Client> getClientById(Long id);

	void deleteClient(Long id);

	boolean clientExistById(Long id);
}
