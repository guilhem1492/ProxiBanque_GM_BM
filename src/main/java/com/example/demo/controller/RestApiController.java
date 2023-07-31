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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Conseiller;
import com.example.demo.service.ConseillerService;

@RestController
@RequestMapping("/conseiller")
public class RestApiController {

	private final ConseillerService conseillerService;

	public RestApiController(ConseillerService conseillerService) {
		this.conseillerService = conseillerService;
	}

	@GetMapping
	Iterable<Conseiller> getConseiller() {
		return conseillerService.getAllConseillers();

	}

	@PostMapping
	Conseiller postConseiller(@RequestBody Conseiller Conseiller) {
		return conseillerService.saveConseiller(Conseiller);
	}

	@GetMapping("/{id}")
	Optional<Conseiller> getConseillerById(@PathVariable Long id) {
		return conseillerService.getConseillerById(id);
	}

	@PutMapping("/{id}")
	ResponseEntity<Conseiller> putConseiller(@PathVariable Long id, @RequestBody Conseiller conseiller) {
		return (conseillerService.conseillerExistById(id))
				? new ResponseEntity<>(conseillerService.saveConseiller(conseiller), HttpStatus.OK)
				: new ResponseEntity<>(conseillerService.saveConseiller(conseiller), HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	void deleteConseiller(@PathVariable Long id) {
		conseillerService.deleteConseiller(id);
	}
}
