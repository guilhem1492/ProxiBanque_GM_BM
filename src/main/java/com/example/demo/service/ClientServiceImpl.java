package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Client;
import com.example.demo.entity.CompteCourant;
import com.example.demo.repository.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService {

	private final ClientRepository clientRepository;

	public ClientServiceImpl(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	@Override
	public Client saveClient(Client client) {
		return clientRepository.save(client);
	}

	@Override
	public Optional<Client> getClientById(Long id) {
		return clientRepository.findById(id);
	}

	@Override
	public void deleteClient(Long id) {
		clientRepository.deleteById(id);
	}

	@Override
	public boolean clientExistById(Long id) {
		return clientRepository.existsById(id);
	}

	@Override
	public List<Client> getAllClients() {
		return clientRepository.findAll();
	}

	@Override
	public void virementComptesCourants(int montant, Client clientEmetteur, Client clientRecepteur) {
		CompteCourant compteRecepteur = clientRecepteur.getCompteCourant();
		double soldeEmetteur = clientEmetteur.getCompteCourant().getSolde();
		if (montant < soldeEmetteur) {
			double nouveauSoldeEmetteur = soldeEmetteur - montant;
			clientEmetteur.getCompteCourant().setSolde(nouveauSoldeEmetteur);
			double nouveauSoldeRecepteur = compteRecepteur.getSolde() + montant;
			compteRecepteur.setSolde(nouveauSoldeRecepteur);
		} else {
			System.out.println("solde insuffisant");
		}
	}

	@Override
	public void virementCourantEpargne(int montant, Client client) {

		double soldeEmetteur = client.getCompteCourant().getSolde();
		if (montant < soldeEmetteur) {
			double nouveauSoldeEmetteur = soldeEmetteur - montant;
			client.getCompteCourant().setSolde(nouveauSoldeEmetteur);
			double nouveauSoldeRecepteur = client.getCompteEpargne().getSolde() + montant;
			client.getCompteEpargne().setSolde(nouveauSoldeRecepteur);
		} else {
			System.out.println("solde insuffisant"); // exception Runtime à ajouter
		}
	}

	@Override
	public void virementEpargneCourant(int montant, Client client) {
		double soldeEmetteur = client.getCompteEpargne().getSolde();
		if (montant < soldeEmetteur) {
			double nouveauSoldeEmetteur = soldeEmetteur - montant;
			client.getCompteEpargne().setSolde(nouveauSoldeEmetteur);
			double nouveauSoldeRecepteur = client.getCompteCourant().getSolde() + montant;
			client.getCompteCourant().setSolde(nouveauSoldeRecepteur);
		} else {
			System.out.println("solde insuffisant");
		}
	}

}
