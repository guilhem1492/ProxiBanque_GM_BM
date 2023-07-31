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

		Conseiller c1 = new Conseiller("Marley", "Bob");
		Conseiller c2 = new Conseiller("Zuckerberg", "Mark");
		Conseiller c3 = new Conseiller("Wayne", "John");

		c1.addClient(new Client("Morgane", "Jessy", "rue de la Paix", 75008, "Paris", "0611111111"));
		c2.addClient(new Client("Lucky", "Luke", "avenue de l'Opéra", 75009, "Paris", "0622222222"));
		c3.addClient(new Client("Pogacar", "Tadej", "rue des Plantes", 75014, "Paris", "0633333333"));

		conseillerRepository.saveAll(List.of(c1, c2, c3));
		System.out.println(c3.getClients());
	}

}
