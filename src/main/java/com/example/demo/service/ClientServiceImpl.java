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
	public String virementComptesCourants(double montant, Client clientEmetteur, Client clientRecepteur)
			throws SimpleException {
		String messageReponse;

		try {
			if (montant > 0) {
				CompteCourant compteCourantEmetteur = clientEmetteur.getCompteCourant();
				CompteCourant compteCourantRecepteur = clientRecepteur.getCompteCourant();
				double soldeEmetteur = compteCourantEmetteur.getSolde();
				if (montant <= soldeEmetteur
						|| soldeEmetteur + compteCourantEmetteur.getAutorisationDecouvert() >= montant) {
					double nouveauSoldeEmetteur = soldeEmetteur - montant;
					compteCourantEmetteur.setSolde(nouveauSoldeEmetteur);
					double nouveauSoldeRecepteur = compteCourantRecepteur.getSolde() + montant;
					compteCourantRecepteur.setSolde(nouveauSoldeRecepteur);
					messageReponse = "Virement effectué avec succès !";
					clientRepository.save(clientEmetteur);
					clientRepository.save(clientRecepteur);
					return messageReponse;
				} else {
					messageReponse = "solde insuffisant";
					throw new SimpleException(messageReponse);
				}
			} else {
				messageReponse = "Le montant du virement doit être positif";
				throw new SimpleException(messageReponse);
			}

		} catch (Exception e) {
			return e.getMessage();
		}

	}

	@Override
	public String virementCourantEpargne(double montant, Client client) throws SimpleException {
		String messageReponse;

		try {
			if (montant > 0) {
				double soldeEmetteur = client.getCompteCourant().getSolde();
				double nouveauSoldeEmetteur;
				double nouveauSoldeRecepteur;
				if (soldeEmetteur >= montant
						|| soldeEmetteur + client.getCompteCourant().getAutorisationDecouvert() >= montant) {
					nouveauSoldeEmetteur = soldeEmetteur - montant;
					client.getCompteCourant().setSolde(nouveauSoldeEmetteur);
					nouveauSoldeRecepteur = client.getCompteEpargne().getSolde() + montant;
					client.getCompteEpargne().setSolde(nouveauSoldeRecepteur);
					clientRepository.save(client);
					messageReponse = "Virement effectué avec succès !";
					return messageReponse;
				} else {
					messageReponse = "Solde insuffisant";
					throw new SimpleException(messageReponse);
				}

			} else {
				messageReponse = "Le montant du virement doit être positif";
				throw new SimpleException(messageReponse);
			}

		} catch (Exception e) {
			return e.getMessage();
		}

	}

	@Override
	public String virementEpargneCourant(double montant, Client client) throws SimpleException {
		String messageReponse;

		try {
			if (montant > 0) {
				double soldeEpargne = client.getCompteEpargne().getSolde();
				if (montant <= soldeEpargne) {
					double nouveauSoldeEpargne = soldeEpargne - montant;
					client.getCompteEpargne().setSolde(nouveauSoldeEpargne);
					double nouveauSoldeCourant = client.getCompteCourant().getSolde() + montant;
					client.getCompteCourant().setSolde(nouveauSoldeCourant);
					clientRepository.save(client);
					messageReponse = "Virement effectué avec succès !";
					return messageReponse;
				} else {
					messageReponse = "Solde épargne insuffisant";
					throw new SimpleException(messageReponse);
				}
			} else {
				messageReponse = "Le montant du virement doit être positif";
				throw new SimpleException(messageReponse);
			}

		} catch (Exception e) {
			return e.getMessage();
		}

	}
}
