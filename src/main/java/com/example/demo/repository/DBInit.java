package com.example.demo.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.entity.Client;
import com.example.demo.entity.Conseiller;

import jakarta.annotation.PostConstruct;

@Component
public class DBInit {
	private ConseillerRepository conseillerRepository;
	private ClientRepository clientRepository;

	public DBInit(ConseillerRepository conseillerRepository, ClientRepository clientRepository) {
		this.conseillerRepository = conseillerRepository;
		this.clientRepository = clientRepository;
	}

	@PostConstruct
	public void start() {

		conseillerRepository.saveAll(List.of(new Conseiller("Bob", "Marley"), new Conseiller("Mark", "Zuckerberg"),
				new Conseiller("John", "Wayne")));

		clientRepository.save(new Client("Morgane", "Jessy", "Rue de La Paix", 75001, "Paris", "0654332482"));
		clientRepository.save(new Client("JOJO", "Luke", "Rue d'Opera", 75003, "Paris", "0623323485"));
	}

}
