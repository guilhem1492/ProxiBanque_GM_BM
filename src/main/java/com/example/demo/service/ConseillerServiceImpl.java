package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Client;
import com.example.demo.entity.Conseiller;
import com.example.demo.repository.ConseillerRepository;

@Service
public class ConseillerServiceImpl implements ConseillerService {

	private final ConseillerRepository conseillerRepository;

	public ConseillerServiceImpl(ConseillerRepository conseillerRepository) {
		this.conseillerRepository = conseillerRepository;
	}

	@Override
	public List<Conseiller> getAllConseillers() {
		return conseillerRepository.findAll();
	}

	@Override
	public Conseiller saveConseiller(Conseiller conseiller) {
		return conseillerRepository.save(conseiller);
	}

	@Override
	public Optional<Conseiller> getConseillerById(Long id) {
		return conseillerRepository.findById(id);
	}

	@Override
	public void deleteConseiller(Long id) {
		conseillerRepository.deleteById(id);
		;
	}

	@Override
	public boolean conseillerExistById(Long id) {
		return conseillerRepository.existsById(id);
	}

	@Override
	public List<Conseiller> saveAllConseillers(List<Conseiller> conseillers) {
		return conseillerRepository.saveAll(conseillers);
	}

	@Override
	public Conseiller updateConseiller(Conseiller conseiller) {
		return null;
	}

	@Override
	public void deleteClient(Client client) {
		// TODO Auto-generated method stub

	}

	@Override
	public Client saveClientForConseiller(Client client) {
		// TODO Auto-generated method stub
		return null;
	}

}
