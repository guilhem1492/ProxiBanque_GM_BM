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

	String virementComptesCourants(double montant, Client clientEmetteur, Client clientRecepteur)
			throws SimpleException;

	String virementCourantEpargne(double montant, Client client);

	String virementEpargneCourant(double montant, Client client);
}
