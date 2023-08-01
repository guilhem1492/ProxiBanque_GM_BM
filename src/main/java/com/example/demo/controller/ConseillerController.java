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

import com.example.demo.entity.Conseiller;
import com.example.demo.service.ConseillerService;

@RestController
public class ConseillerController {

	private final ConseillerService conseillerService;

	public ConseillerController(ConseillerService conseillerService) {
		this.conseillerService = conseillerService;
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

}