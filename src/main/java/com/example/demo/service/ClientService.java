package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Client;

public interface ClientService {
	Client saveClient(Client client);

	List<Client> getAllClients();

	Optional<Client> getClientById(Long id);

	void deleteClient(Long id);

	boolean clientExistById(Long id);

	void virementComptesCourants(int montant, Client clientEmetteur, Client clientRecepteur);

	void virementCourantEpargne(int montant, Client client);

	void virementEpargneCourant(int montant, Client client);
}
