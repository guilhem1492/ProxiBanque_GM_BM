package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Client;
import com.example.demo.service.ClientService;
import com.example.demo.service.SimpleException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clients")
public class ClientController {

	private final ClientService clientService;

	public ClientController(ClientService clientService) {
		super();
		this.clientService = clientService;
	}

	@GetMapping
	Iterable<Client> getAllClients() {
		return clientService.getAllClients();

	}

	@GetMapping("/{id}")
	Optional<Client> getClientById(@PathVariable Long id) {
		return clientService.getClientById(id);
	}

	@PostMapping
	public ResponseEntity<Client> testUser(@Valid @RequestBody Client client) {
		return ResponseEntity.ok(client);
	}

	@PutMapping("/virementepargnecourant/{id}/{montant}")
	ResponseEntity<String> virementEpargneCourant(@PathVariable Long id, @PathVariable double montant) {
		String messageReponse = clientService.virementEpargneCourant(montant, clientService.getClientById(id).get());
		return ResponseEntity.ok(messageReponse);
	}

	@PutMapping("/virementcourantepargne/{id}/{montant}")
	ResponseEntity<String> virementCourantEpargne(@PathVariable Long id, @PathVariable double montant) {
		String responseMessage = clientService.virementCourantEpargne(montant, clientService.getClientById(id).get());
		return ResponseEntity.ok(responseMessage);
	}

	@PutMapping("/virement/{idEmetteur}/{idRecepteur}/{montant}")
	ResponseEntity<String> virementCompteCourantToCompteCourant(@PathVariable Long idEmetteur,
			@PathVariable Long idRecepteur, @PathVariable double montant) throws SimpleException {
		Optional<Client> optionalClientEmetteur = clientService.getClientById(idEmetteur);
		Optional<Client> optionalClientRecepteur = clientService.getClientById(idRecepteur);
		Client clientEmetteur = optionalClientEmetteur.get();
		Client clientRecepteur = optionalClientRecepteur.get();
		String responseMessage = clientService.virementComptesCourants(montant, clientEmetteur, clientRecepteur);
		return ResponseEntity.ok(responseMessage);
	}

	@ResponseStatus(code = HttpStatus.I_AM_A_TEAPOT)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationException(MethodArgumentNotValidException e) {
		HashMap<String, String> errors = new HashMap<>();
		e.getBindingResult().getAllErrors().forEach(error -> {
			String fieldName = ((FieldError) error).getField();
			String errorMsg = error.getDefaultMessage();

			errors.put(fieldName, errorMsg);

		});

		return errors;
	}

}
