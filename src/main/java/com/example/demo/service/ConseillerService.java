package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Client;
import com.example.demo.entity.Conseiller;

public interface ConseillerService {
	
	List<Conseiller> getAllConseillers();

	Conseiller saveConseiller(Conseiller conseiller);
	
	List<Conseiller> saveAllConseillers(List<Conseiller> conseillers);

	Optional<Conseiller> getConseillerById(Long id);

	void deleteConseiller(Long id);

	boolean conseillerExistById(Long id);
	
	Conseiller updateConseiller(Conseiller conseiller);
	
	Client saveClientForConseiller(Client client);
	
	void deleteClient(Client client);
}
