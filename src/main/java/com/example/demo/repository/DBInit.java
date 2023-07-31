package com.example.demo.repository;

import org.springframework.stereotype.Component;

import com.example.demo.entity.Client;
import com.example.demo.entity.CompteCourant;
import com.example.demo.entity.CompteEpargne;
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
		Client client1 = new Client("Pierre", "Didier", "52 rue de Saintonge", 75003, "Paris", "0656434568");
		Client client2 = new Client("Pascal", "Claudine", "2 rue du Général Blaise", 75003, "Paris", "0656435413");
		Client client3 = new Client("Eliane", "Cécile", "52 Avenue de Saint-Ouen", 75017, "Paris", "0633434522");
		
		Conseiller conseiller1 = new Conseiller("Delphine", "Joelle");
		Conseiller conseiller2 = new Conseiller("René", "Olivier");

        CompteCourant compteCourant1 = new CompteCourant("123456789", 1500.0, "2001");
        CompteCourant compteCourant2 = new CompteCourant("987654321", 3500.0, "2009");
        CompteCourant compteCourant3 = new CompteCourant("324324324", 9500.0, "1997");
        
        CompteEpargne compteEpargne1 = new CompteEpargne("123456789", 1500.0,"2015");
        CompteEpargne compteEpargne2 = new CompteEpargne("123456789", 1500.0,"2004");
        CompteEpargne compteEpargne3 = new CompteEpargne("123456789", 1500.0,"1997");
        
		client1.setCompteCourant(compteCourant1);
		compteCourant1.setClient(client1);
		client2.setCompteCourant(compteCourant2);
		compteCourant2.setClient(client2);
		client3.setCompteCourant(compteCourant3);
		compteCourant3.setClient(client3);
		
		client1.setCompteEpargne(compteEpargne1);
		compteEpargne1.setClient(client1);
		client2.setCompteEpargne(compteEpargne2);
		compteEpargne2.setClient(client2);
		client3.setCompteEpargne(compteEpargne3);
		compteEpargne3.setClient(client3);
		
		conseiller1.addClient(client1);
		conseiller2.addClient(client2);
		conseiller2.addClient(client3);
		
		conseillerRepository.save(conseiller1);
		conseillerRepository.save(conseiller2);


	}

}
