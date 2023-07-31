package com.example.demo.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Client;
import com.example.demo.entity.Conseiller;
import com.example.demo.service.ClientService;
import com.example.demo.service.ConseillerService;

@RestController
public class RestApiController {

	private final ConseillerService conseillerService;
	private final ClientService clientService;

	public RestApiController(ConseillerService conseillerService, ClientService clientService) {
		this.conseillerService = conseillerService;
		this.clientService = clientService;
	}

	@GetMapping("/conseillers")
	Iterable<Conseiller> getAllConseillers() {
		return conseillerService.getAllConseillers();

	}

	@PostMapping("/conseiller")
	Conseiller postConseiller(@RequestBody Conseiller Conseiller) {
		return conseillerService.saveConseiller(Conseiller);
	}

	@GetMapping("/conseillers/{id}")
	Optional<Conseiller> getConseillerById(@PathVariable Long id) {
		return conseillerService.getConseillerById(id);
	}

	@PutMapping("/conseillers/{id}")
	ResponseEntity<Conseiller> putConseiller(@PathVariable Long id, @RequestBody Conseiller conseiller) {
		return (conseillerService.conseillerExistById(id))
				? new ResponseEntity<>(conseillerService.saveConseiller(conseiller), HttpStatus.OK)
				: new ResponseEntity<>(conseillerService.saveConseiller(conseiller), HttpStatus.CREATED);
	}

	@DeleteMapping("/conseillers/{id}")
	void deleteConseiller(@PathVariable Long id) {
		conseillerService.deleteConseiller(id);
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
		clientService.virementEpargneCourant(montant, clientService.getClientById(id).get());
		String responseMessage = "Virement effectué avec succès !";
		return ResponseEntity.ok(responseMessage);
	}

	@PutMapping("/clients/virementcourantepargne/{id}/{montant}")
	ResponseEntity<String> virementCourantEpargne(@PathVariable Long id, @PathVariable int montant) {
		clientService.virementCourantEpargne(montant, clientService.getClientById(id).get());
		String responseMessage = "Virement effectué avec succès !";
		return ResponseEntity.ok(responseMessage);
	}

	@PutMapping("/clients/virement/{idEmetteur}/{idRecepteur}/{montant}")
	ResponseEntity<String> virementCompteCourantToCompteCourant(@PathVariable Long idEmetteur,
			@PathVariable Long idRecepteur, @PathVariable int montant) {
		Optional<Client> optionalClientEmetteur = clientService.getClientById(idEmetteur);
		Optional<Client> optionalClientRecepteur = clientService.getClientById(idRecepteur);

		Client clientEmetteur = optionalClientEmetteur.get();
		Client clientRecepteur = optionalClientRecepteur.get();

		clientService.virementComptesCourants(montant, clientEmetteur, clientRecepteur);

		String responseMessage = "Virement effectué avec succès !";

		return ResponseEntity.ok(responseMessage);
	}
}