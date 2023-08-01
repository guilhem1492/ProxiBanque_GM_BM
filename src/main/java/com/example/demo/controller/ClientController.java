package com.example.demo.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Client;
import com.example.demo.service.ClientService;

@RestController
public class ClientController {

	private final ClientService clientService;

	public ClientController(ClientService clientService) {
		super();
		this.clientService = clientService;
	}

	@GetMapping("/clients")
	Iterable<Client> getAllClients() {
		return clientService.getAllClients();

	}

	@GetMapping("/clients/{id}")
	Optional<Client> getClientById(@PathVariable Long id) {
		return clientService.getClientById(id);
	}

	@PutMapping("/clients/virementepargnecourant/{id}/{montant}")
	ResponseEntity<String> virementEpargneCourant(@PathVariable Long id, @PathVariable int montant) {
		String messageReponse = clientService.virementEpargneCourant(montant, clientService.getClientById(id).get());
		return ResponseEntity.ok(messageReponse);
	}

	@PutMapping("/clients/virementcourantepargne/{id}/{montant}")
	ResponseEntity<String> virementCourantEpargne(@PathVariable Long id, @PathVariable int montant) {
		String responseMessage = clientService.virementCourantEpargne(montant, clientService.getClientById(id).get());
		return ResponseEntity.ok(responseMessage);
	}

	@PutMapping("/clients/virement/{idEmetteur}/{idRecepteur}/{montant}")
	ResponseEntity<String> virementCompteCourantToCompteCourant(@PathVariable Long idEmetteur,
			@PathVariable Long idRecepteur, @PathVariable int montant) {
		Optional<Client> optionalClientEmetteur = clientService.getClientById(idEmetteur);
		Optional<Client> optionalClientRecepteur = clientService.getClientById(idRecepteur);
		Client clientEmetteur = optionalClientEmetteur.get();
		Client clientRecepteur = optionalClientRecepteur.get();
		String responseMessage = clientService.virementComptesCourants(montant, clientEmetteur, clientRecepteur);
		return ResponseEntity.ok(responseMessage);
	}

}
