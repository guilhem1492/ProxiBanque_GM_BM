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
//@RequestMapping("/conseiller")
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

	@GetMapping("/conseiller/{id}")
	Optional<Conseiller> getConseillerById(@PathVariable Long id) {
		return conseillerService.getConseillerById(id);
	}

	@PutMapping("/conseiller/{id}")
	ResponseEntity<Conseiller> putConseiller(@PathVariable Long id, @RequestBody Conseiller conseiller) {
		return (conseillerService.conseillerExistById(id))
				? new ResponseEntity<>(conseillerService.saveConseiller(conseiller), HttpStatus.OK)
				: new ResponseEntity<>(conseillerService.saveConseiller(conseiller), HttpStatus.CREATED);
	}

	@DeleteMapping("/conseiller/{id}")
	void deleteConseiller(@PathVariable Long id) {
		conseillerService.deleteConseiller(id);
	}

	@GetMapping("/clients")
	Iterable<Client> getAllClients() {
		return clientService.getAllClients();

	}

//	@PutMapping("/client/{id}")
//	ResponseEntity<Client> putClient(@PathVariable Long id, @RequestBody Client client) {
//		return (clientService.clientExistById(id))
//				? new ResponseEntity<>(clientService.saveClient(client), HttpStatus.OK)
//				: new ResponseEntity<>(clientService.saveClient(client), HttpStatus.CREATED);
//	}

	@PutMapping("/client/{id}")
	void putClient(@PathVariable Long id, @RequestBody Client client) {
		clientService.virementCourantEpargne(500, client);
	}

}
